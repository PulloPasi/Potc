package net.mcreator.cannon.event;

import net.mcreator.cannon.CannonMod;
import net.mcreator.cannon.util.RiggingStructureHelper;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;

import net.minecraftforge.event.level.ChunkEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.ServerLifecycleHooks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.util.Iterator;

@Mod.EventBusSubscriber(modid = CannonMod.MODID)
public final class StructureRiggingHandler {

    private static final ResourceLocation DUTCHMAN_ID = new ResourceLocation(CannonMod.MODID, "dutchman");
    private static final int MAX_RETRY_ATTEMPTS = 40;
    private static final Set<StructureKey> PROCESSED_STARTS = ConcurrentHashMap.newKeySet();
    private static final Map<StructureKey, PendingRigging> PENDING_RIGGING = new ConcurrentHashMap<>();
    private static final Logger LOGGER = LogManager.getLogger();

    private StructureRiggingHandler() {
    }

    @SubscribeEvent
    public static void onChunkLoaded(ChunkEvent.Load event) {
        if (!(event.getLevel() instanceof ServerLevel level)) {
            return;
        }

        ChunkAccess chunk = event.getChunk();
        chunk.getAllStarts().forEach((structure, start) -> {
            if (start == null || !start.isValid()) {
                return;
            }

            ResourceLocation startId = level.registryAccess()
                .registryOrThrow(Registries.STRUCTURE)
                .getKey(structure);
            if (!Objects.equals(startId, DUTCHMAN_ID)) {
                return;
            }

            LOGGER.debug("Processing Dutchman structure start at {} in chunk {}", start.getBoundingBox(), chunk.getPos());

            Optional<PoolElementStructurePiece> poolPieceOpt = start.getPieces().stream()
                .filter(piece -> piece instanceof PoolElementStructurePiece)
                .map(piece -> (PoolElementStructurePiece) piece)
                .findFirst();

            poolPieceOpt.ifPresent(poolPiece -> {
                BoundingBox box = poolPiece.getBoundingBox();
                BlockPos origin = new BlockPos(box.minX(), box.minY(), box.minZ());
                StructureKey key = new StructureKey(level.dimension(), origin.immutable());
                LOGGER.debug("Found pool piece {} with origin {} rotation {}", poolPiece.getElement().getClass().getSimpleName(), origin,
                    poolPiece.getRotation());
                if (PROCESSED_STARTS.contains(key)) {
                    return;
                }
                if (PENDING_RIGGING.containsKey(key)) {
                    return;
                }

                StructurePlaceSettings settings = new StructurePlaceSettings()
                    .setRotation(poolPiece.getRotation())
                    .setMirror(Mirror.NONE);

                if (!level.hasChunksAt(box.minX(), box.minY(), box.minZ(), box.maxX(), box.maxY(), box.maxZ())) {
                    PendingRigging pending = PendingRigging.from(key, DUTCHMAN_ID, settings.getRotation(), settings.getMirror(), box);
                    PENDING_RIGGING.put(key, pending);
                    return;
                }

                boolean applied = RiggingStructureHelper.applyRigging(level, DUTCHMAN_ID, origin, settings);
                if (!applied) {
                    LOGGER.debug("Rigging apply returned false for Dutchman at {}, scheduling retry", origin);
                    PendingRigging pending = PendingRigging.from(key, DUTCHMAN_ID, settings.getRotation(), settings.getMirror(), box);
                    pending.incrementAttempts();
                    PENDING_RIGGING.put(key, pending);
                } else {
                    PROCESSED_STARTS.add(key);
                }
            });
        });
    }

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase != TickEvent.Phase.END || PENDING_RIGGING.isEmpty()) {
            return;
        }

        var server = ServerLifecycleHooks.getCurrentServer();
        if (server == null) {
            return;
        }

        Iterator<Map.Entry<StructureKey, PendingRigging>> iterator = PENDING_RIGGING.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<StructureKey, PendingRigging> entry = iterator.next();
            PendingRigging pending = entry.getValue();
            ServerLevel level = server.getLevel(pending.key().dimension());
            if (level == null) {
                iterator.remove();
                continue;
            }

            if (!pending.areChunksLoaded(level)) {
                continue;
            }

            StructurePlaceSettings settings = new StructurePlaceSettings()
                .setRotation(pending.rotation())
                .setMirror(pending.mirror());

            if (RiggingStructureHelper.applyRigging(level, pending.riggingId(), pending.key().origin(), settings)) {
                LOGGER.debug("Successfully applied deferred rigging for {} at {}", pending.riggingId(), pending.key().origin());
                PROCESSED_STARTS.add(pending.key());
                iterator.remove();
            } else if (pending.incrementAttempts() > MAX_RETRY_ATTEMPTS) {
                LOGGER.warn("Giving up applying rigging {} at {} after {} attempts", pending.riggingId(), pending.key().origin(), pending.attempts());
                iterator.remove();
            }
        }
    }

    private record StructureKey(ResourceKey<Level> dimension, BlockPos origin) {
    }

    private static final class PendingRigging {
        private final StructureKey key;
        private final ResourceLocation riggingId;
        private final Rotation rotation;
        private final Mirror mirror;
        private final int minX;
        private final int minY;
        private final int minZ;
        private final int maxX;
        private final int maxY;
        private final int maxZ;
        private int attempts;

        private PendingRigging(StructureKey key, ResourceLocation riggingId, Rotation rotation, Mirror mirror,
            int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
            this.key = key;
            this.riggingId = riggingId;
            this.rotation = rotation;
            this.mirror = mirror;
            this.minX = minX;
            this.minY = minY;
            this.minZ = minZ;
            this.maxX = maxX;
            this.maxY = maxY;
            this.maxZ = maxZ;
            this.attempts = 0;
        }

        private static PendingRigging from(StructureKey key, ResourceLocation riggingId, Rotation rotation, Mirror mirror, BoundingBox box) {
            return new PendingRigging(key, riggingId, rotation, mirror,
                box.minX(), box.minY(), box.minZ(), box.maxX(), box.maxY(), box.maxZ());
        }

        private StructureKey key() {
            return key;
        }

        private ResourceLocation riggingId() {
            return riggingId;
        }

        private Rotation rotation() {
            return rotation;
        }

        private Mirror mirror() {
            return mirror;
        }

        private int attempts() {
            return attempts;
        }

        private int incrementAttempts() {
            return ++attempts;
        }

        private boolean areChunksLoaded(ServerLevel level) {
            if (level == null) {
                return false;
            }
            if (!level.hasChunksAt(minX, minY, minZ, maxX, maxY, maxZ)) {
                return false;
            }
            return true;
        }
    }
}
