package net.mcreator.cannon.util;

import net.mcreator.cannon.CannonMod;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtIo;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

/**
 * Shared helpers for saving and restoring rigging data alongside structures.
 */
public final class RiggingStructureHelper {
    public static final String LIGHT_KEY = "light";
    public static final String HEAVY_KEY = "heavy";
    public static final String ORIGIN_KEY = "origin";

    private static final Logger LOGGER = LogManager.getLogger();

    private RiggingStructureHelper() {
    }

    /**
     * Applies the rigging stored under the given identifier to the world at the provided
     * structure origin. The {@link StructurePlaceSettings} should match the settings used when
     * the structure itself was placed so that rotation and mirroring are respected. If the
     * structure was not rotated or mirrored, you can pass {@code new StructurePlaceSettings()}.
     */
    public static boolean applyRigging(ServerLevel level, ResourceLocation riggingId, BlockPos origin, StructurePlaceSettings settings) {
        CompoundTag data = loadRiggingData(level.getServer(), riggingId);
        if (data == null) {
            return false;
        }

        if (settings == null) {
            settings = new StructurePlaceSettings();
        }

        ListTag lightList = data.getList(LIGHT_KEY, CompoundTag.TAG_COMPOUND);
        ListTag heavyList = data.getList(HEAVY_KEY, CompoundTag.TAG_COMPOUND);

        List<RiggingConnection> lightConnections = LeadConnectionManager.deserializeConnections(lightList, BlockPos.ZERO);
        List<RiggingConnection> heavyConnections = HeavyLeadConnectionManager.deserializeConnections(heavyList, BlockPos.ZERO);

        LOGGER.debug("Loaded rigging {} -> {} light, {} heavy connections", riggingId, lightConnections.size(), heavyConnections.size());

        Rotation rotation = settings.getRotation();
        Mirror mirror = settings.getMirror();
        RiggingBounds bounds = calculateBounds(lightConnections, heavyConnections);

        LOGGER.debug("Applying rigging {} with rotation={} mirror={} bounds={}", riggingId, rotation, mirror, bounds);

        int created = 0;
        created += placeConnections(level, lightConnections, origin, rotation, mirror, bounds, false);
        created += placeConnections(level, heavyConnections, origin, rotation, mirror, bounds, true);

        if (created == 0) {
            LOGGER.debug("No rigging connections created for {} at {}", riggingId, origin);
        }
        return created > 0;
    }

    /**
     * Convenience overload for structures placed without rotation or mirroring.
     */
    public static boolean applyRigging(ServerLevel level, ResourceLocation riggingId, BlockPos origin) {
        return applyRigging(level, riggingId, origin, new StructurePlaceSettings());
    }

    /**
     * Loads rigging data either from the current world's {@code rigging/} folder (used while
     * authoring structures) or from the active data packs under
     * {@code data/<namespace>/rigging/<path>.nbt}. Returns {@code null} if nothing is found.
     */
    public static CompoundTag loadRiggingData(MinecraftServer server, ResourceLocation id) {
        // Check the world save folder first so creators can iterate quickly.
        Path worldPath = server.getWorldPath(new net.minecraft.world.level.storage.LevelResource("rigging"))
            .resolve(id.getNamespace())
            .resolve(id.getPath() + ".nbt");
        if (Files.isRegularFile(worldPath)) {
            try {
                return NbtIo.readCompressed(worldPath.toFile());
            } catch (IOException ex) {
                LOGGER.error("Failed to read rigging data {} from world path", id, ex);
            }
        }

        // Fall back to data packs / mod resources.
        ResourceLocation resourcePath = new ResourceLocation(id.getNamespace(), "rigging/" + id.getPath() + ".nbt");
        ResourceManager resourceManager = server.getServerResources().resourceManager();
        Optional<Resource> resourceOpt = resourceManager.getResource(resourcePath);
        if (resourceOpt.isPresent()) {
            try (InputStream stream = resourceOpt.get().open()) {
                return NbtIo.readCompressed(stream);
            } catch (IOException ex) {
                LOGGER.error("Failed to read rigging resource {}", resourcePath, ex);
            }
        }

        LOGGER.warn("Rigging data {} not found", id);
        return null;
    }

    private static int placeConnections(ServerLevel level, List<RiggingConnection> connections, BlockPos origin,
        Rotation rotation, Mirror mirror, RiggingBounds bounds, boolean heavy) {
        if (connections.isEmpty()) {
            return 0;
        }

        int created = 0;
        for (RiggingConnection connection : connections) {
            BlockPos fromWorld = origin.offset(transformRelative(connection.from(), rotation, mirror, bounds));
            BlockPos toWorld = origin.offset(transformRelative(connection.to(), rotation, mirror, bounds));
            boolean success = heavy
                ? HeavyLeadConnectionManager.createConnection(level, fromWorld, toWorld)
                : LeadConnectionManager.createConnection(level, fromWorld, toWorld);
            if (success) {
                created++;
            } else {
                logFailureDetails(level, fromWorld, toWorld, heavy);
            }
        }
        return created;
    }

    private static BlockPos transformRelative(BlockPos relative, Rotation rotation, Mirror mirror, RiggingBounds bounds) {
        if (bounds == null || (rotation == Rotation.NONE && mirror == Mirror.NONE)) {
            return StructureTemplate.transform(relative, mirror, rotation, BlockPos.ZERO);
        }

        BlockPos min = bounds.min();
        BlockPos pivot = bounds.sizeMinusOne();
        BlockPos adjusted = relative.subtract(min);
        BlockPos transformed = StructureTemplate.transform(adjusted, mirror, rotation, pivot);
        return transformed.offset(min);
    }

    private static void logFailureDetails(ServerLevel level, BlockPos from, BlockPos to, boolean heavy) {
        boolean fromLoaded = level.isLoaded(from);
        boolean toLoaded = level.isLoaded(to);
        boolean fromValid = fromLoaded && VSShipUtils.isPositionValid(level, from);
        boolean toValid = toLoaded && VSShipUtils.isPositionValid(level, to);
        double distance = Math.sqrt(from.distSqr(to));
        var fromState = fromLoaded ? level.getBlockState(from) : null;
        var toState = toLoaded ? level.getBlockState(to) : null;
        LOGGER.debug(
            "Failed to create {} rigging from {} (loaded={}, valid={}, state={}) to {} (loaded={}, valid={}, state={}), dist={}",
            heavy ? "heavy" : "light",
            from,
            fromLoaded,
            fromValid,
            fromState,
            to,
            toLoaded,
            toValid,
            toState,
            distance
        );
    }

    private static RiggingBounds calculateBounds(List<RiggingConnection> first, List<RiggingConnection> second) {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int minZ = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        int maxZ = Integer.MIN_VALUE;

        minX = updateExtents(first, minX, true, Axis.X);
        minY = updateExtents(first, minY, true, Axis.Y);
        minZ = updateExtents(first, minZ, true, Axis.Z);
        maxX = updateExtents(first, maxX, false, Axis.X);
        maxY = updateExtents(first, maxY, false, Axis.Y);
        maxZ = updateExtents(first, maxZ, false, Axis.Z);

        minX = updateExtents(second, minX, true, Axis.X);
        minY = updateExtents(second, minY, true, Axis.Y);
        minZ = updateExtents(second, minZ, true, Axis.Z);
        maxX = updateExtents(second, maxX, false, Axis.X);
        maxY = updateExtents(second, maxY, false, Axis.Y);
        maxZ = updateExtents(second, maxZ, false, Axis.Z);

        if (minX == Integer.MAX_VALUE) {
            return null;
        }

        BlockPos min = new BlockPos(minX, minY, minZ);
        BlockPos max = new BlockPos(maxX, maxY, maxZ);
        return new RiggingBounds(min, max);
    }

    private static int updateExtents(List<RiggingConnection> connections, int current, boolean findMin, Axis axis) {
        if (connections == null) {
            return current;
        }

        for (RiggingConnection connection : connections) {
            current = updateExtentForPos(connection.from(), current, findMin, axis);
            current = updateExtentForPos(connection.to(), current, findMin, axis);
        }
        return current;
    }

    private static int updateExtentForPos(BlockPos pos, int current, boolean findMin, Axis axis) {
        if (pos == null) {
            return current;
        }

        int value = axis.component(pos);
        if (findMin) {
            return Math.min(current, value);
        }
        return Math.max(current, value);
    }

    private enum Axis {
        X {
            @Override
            int component(BlockPos pos) {
                return pos.getX();
            }
        },
        Y {
            @Override
            int component(BlockPos pos) {
                return pos.getY();
            }
        },
        Z {
            @Override
            int component(BlockPos pos) {
                return pos.getZ();
            }
        };

        abstract int component(BlockPos pos);
    }

    private record RiggingBounds(BlockPos min, BlockPos max) {
        BlockPos sizeMinusOne() {
            return new BlockPos(max.getX() - min.getX(), max.getY() - min.getY(), max.getZ() - min.getZ());
        }

        @Override
        public String toString() {
            return "RiggingBounds{" + "min=" + min + ", max=" + max + '}';
        }
    }
}
