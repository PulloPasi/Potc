package net.mcreator.cannon.util;

import net.mcreator.cannon.CannonMod;
import net.mcreator.cannon.entity.LeadEntity;
import net.mcreator.cannon.init.CannonModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import net.minecraftforge.fml.common.Mod;

import org.joml.Matrix4dc;
import org.joml.Vector3d;

import org.valkyrienskies.core.api.ships.properties.ShipTransform;
import org.valkyrienskies.core.impl.game.ships.ShipObjectServer;
import org.valkyrienskies.core.impl.game.ships.ShipObjectServerWorld;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber(modid = CannonMod.MODID)
public final class LeadConnectionManager {
    private static final Map<BlockPos, BlockPos> connections = new ConcurrentHashMap<>();
    private static final Map<BlockPos, LeadEntity> leadEntities = new ConcurrentHashMap<>();

    private LeadConnectionManager() {
    }

    public static boolean createConnection(Level level, BlockPos from, BlockPos to) {
        if (level == null || level.isClientSide || from == null || to == null || from.equals(to)) {
            return false;
        }

        BlockPos fromKey = from.immutable();
        BlockPos toKey = to.immutable();

        if (isConnected(fromKey) || isConnected(toKey)) {
            return false;
        }

        if (!level.isLoaded(fromKey) || !level.isLoaded(toKey)) {
            return false;
        }

        if (fromKey.distSqr(toKey) > 32 * 32) {
            return false;
        }

        if (!VSShipUtils.isPositionValid(level, fromKey) || !VSShipUtils.isPositionValid(level, toKey)) {
            return false;
        }

        connections.put(fromKey, toKey);
        spawnLeadEntity(level, fromKey, toKey);
        return true;
    }

    public static boolean isConnected(BlockPos pos) {
        if (pos == null) {
            return false;
        }
        return leadEntities.containsKey(pos.immutable());
    }

    public static void breakConnection(Level level, BlockPos pos) {
        if (level == null || pos == null) {
            return;
        }

        BlockPos key = pos.immutable();
        BlockPos other = connections.remove(key);
        if (other != null) {
            removeLeadEntity(level, key, other);
            return;
        }

        for (Map.Entry<BlockPos, BlockPos> entry : new HashMap<>(connections).entrySet()) {
            if (entry.getValue().equals(key)) {
                BlockPos start = entry.getKey();
                connections.remove(start);
                removeLeadEntity(level, start, key);
                break;
            }
        }
    }

    public static void breakConnection(Level level, BlockPos from, BlockPos to) {
        if (level == null || from == null || to == null) {
            return;
        }

        BlockPos fromKey = from.immutable();
        BlockPos toKey = to.immutable();

        if (connections.remove(fromKey, toKey)) {
            removeLeadEntity(level, fromKey, toKey);
            return;
        }

        if (connections.remove(toKey, fromKey)) {
            removeLeadEntity(level, toKey, fromKey);
        }
    }

    private static void spawnLeadEntity(Level level, BlockPos from, BlockPos to) {
        if (!(level instanceof ServerLevel serverLevel)) {
            return;
        }

        LeadEntity leadEntity = CannonModEntities.LEAD_ENTITY.get().create(serverLevel);
        if (leadEntity == null) {
            return;
        }

        leadEntity.setFromPos(from);
        leadEntity.setToPos(to);

        Vec3 midPoint = new Vec3(
            (from.getX() + to.getX()) / 2.0,
            (from.getY() + to.getY()) / 2.0,
            (from.getZ() + to.getZ()) / 2.0
        );

        leadEntity.setPos(midPoint.x, midPoint.y, midPoint.z);
        serverLevel.addFreshEntity(leadEntity);

        leadEntities.put(from, leadEntity);
        leadEntities.put(to, leadEntity);
    }

    public static List<RiggingConnection> collectConnections(BlockPos cornerA, BlockPos cornerB) {
        if (cornerA == null || cornerB == null) {
            return Collections.emptyList();
        }

        int minX = Math.min(cornerA.getX(), cornerB.getX());
        int minY = Math.min(cornerA.getY(), cornerB.getY());
        int minZ = Math.min(cornerA.getZ(), cornerB.getZ());
        int maxX = Math.max(cornerA.getX(), cornerB.getX());
        int maxY = Math.max(cornerA.getY(), cornerB.getY());
        int maxZ = Math.max(cornerA.getZ(), cornerB.getZ());

        List<RiggingConnection> results = new ArrayList<>();
        for (Map.Entry<BlockPos, BlockPos> entry : connections.entrySet()) {
            BlockPos from = entry.getKey();
            BlockPos to = entry.getValue();
            if (isWithin(from, minX, minY, minZ, maxX, maxY, maxZ) && isWithin(to, minX, minY, minZ, maxX, maxY, maxZ)) {
                results.add(new RiggingConnection(from.immutable(), to.immutable()));
            }
        }
        return results;
    }

    public static ListTag serializeConnections(Collection<RiggingConnection> connections, BlockPos origin) {
        ListTag list = new ListTag();
        if (connections == null || origin == null) {
            return list;
        }

        for (RiggingConnection connection : connections) {
            CompoundTag entry = new CompoundTag();
            entry.put("from", writeRelativePos(connection.from(), origin));
            entry.put("to", writeRelativePos(connection.to(), origin));
            list.add(entry);
        }
        return list;
    }

    public static List<RiggingConnection> deserializeConnections(ListTag listTag, BlockPos origin) {
        if (listTag == null || origin == null) {
            return Collections.emptyList();
        }

        List<RiggingConnection> results = new ArrayList<>(listTag.size());
        for (int i = 0; i < listTag.size(); i++) {
            if (!(listTag.get(i) instanceof CompoundTag compound)) {
                continue;
            }
            BlockPos from = readRelativePos(compound.getCompound("from"), origin);
            BlockPos to = readRelativePos(compound.getCompound("to"), origin);
            if (from != null && to != null) {
                results.add(new RiggingConnection(from, to));
            }
        }
        return results;
    }

    public static void recreateConnections(Level level, Collection<RiggingConnection> connections) {
        if (level == null || connections == null) {
            return;
        }
        for (RiggingConnection connection : connections) {
            createConnection(level, connection.from(), connection.to());
        }
    }

    private static boolean isWithin(BlockPos pos, int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
        return pos.getX() >= minX && pos.getX() <= maxX
            && pos.getY() >= minY && pos.getY() <= maxY
            && pos.getZ() >= minZ && pos.getZ() <= maxZ;
    }

    private static CompoundTag writeRelativePos(BlockPos absolute, BlockPos origin) {
        BlockPos relative = absolute.subtract(origin);
        return NbtUtils.writeBlockPos(relative);
    }

    @Nullable
    private static BlockPos readRelativePos(CompoundTag tag, BlockPos origin) {
        if (tag == null) {
            return null;
        }
        BlockPos stored = NbtUtils.readBlockPos(tag);
        return origin.offset(stored);
    }

    private static void removeLeadEntity(Level level, BlockPos from, BlockPos to) {
        LeadEntity entity = leadEntities.remove(from);
        if (entity != null) {
            leadEntities.remove(to, entity);
        } else {
            entity = leadEntities.remove(to);
        }

        if (entity == null && level instanceof ServerLevel serverLevel) {
            entity = findLeadEntity(serverLevel, from, to);
        }

        if (entity != null && !entity.isRemoved()) {
            entity.discard();
        }

        leadEntities.remove(from);
        leadEntities.remove(to);
    }

    private static LeadEntity findLeadEntity(ServerLevel level, BlockPos from, BlockPos to) {
        Vec3 midPoint = new Vec3(
            (from.getX() + to.getX()) / 2.0,
            (from.getY() + to.getY()) / 2.0,
            (from.getZ() + to.getZ()) / 2.0
        );

        double maxDist = Math.sqrt(from.distSqr(to)) / 2.0 + 1.0;
        AABB searchArea = new AABB(
            midPoint.x - maxDist, midPoint.y - maxDist, midPoint.z - maxDist,
            midPoint.x + maxDist, midPoint.y + maxDist, midPoint.z + maxDist
        );

        for (LeadEntity entity : level.getEntitiesOfClass(LeadEntity.class, searchArea)) {
            BlockPos entityFrom = entity.getFromPos();
            BlockPos entityTo = entity.getToPos();
            if ((entityFrom.equals(from) && entityTo.equals(to)) ||
                (entityFrom.equals(to) && entityTo.equals(from))) {
                return entity;
            }
        }

        return null;
    }

    public static void tick(Level level) {
        if (!(level instanceof ServerLevel serverLevel)) {
            return;
        }

        List<BlockPos> toRemove = new ArrayList<>();
        for (Map.Entry<BlockPos, BlockPos> entry : new HashMap<>(connections).entrySet()) {
            BlockPos from = entry.getKey();
            BlockPos to = entry.getValue();
            if (!isValidConnection(serverLevel, from, to)) {
                toRemove.add(from);
            }
        }

        for (BlockPos from : toRemove) {
            breakConnection(serverLevel, from);
        }

        if (serverLevel.getGameTime() % 200 == 0) {
            Set<LeadEntity> unique = new HashSet<>(leadEntities.values());
            for (LeadEntity lead : unique) {
                if (lead != null && !lead.isRemoved()) {
                    lead.refreshLifespan();
                }
            }
        }
    }

    private static boolean isValidConnection(ServerLevel level, BlockPos from, BlockPos to) {
        boolean fromLoaded = level.isLoaded(from);
        boolean toLoaded = level.isLoaded(to);

        if (!fromLoaded || !toLoaded) {
            // Skip validation while one of the chunks is unloaded; we'll revisit once it reloads.
            return true;
        }

        boolean fromSolid = VSShipUtils.hasSolidBlock(level, from);
        boolean toSolid = VSShipUtils.hasSolidBlock(level, to);
        double distSq = from.distSqr(to);

        boolean valid = fromSolid && toSolid && distSq <= 32 * 32;
        if (!valid) {
            CannonMod.LOGGER.debug("Invalid lead connection detected: fromSolid={}, toSolid={}, distSq={}, from={}, to={}",
                fromSolid, toSolid, distSq, from, to);
        }

        return valid;
    }

    public static void onShipMove(BlockPos oldPos, BlockPos newPos) {
        if (oldPos == null || newPos == null) {
            return;
        }

        BlockPos oldKey = oldPos.immutable();
        BlockPos newKey = newPos.immutable();

        if (connections.containsKey(oldKey)) {
            BlockPos other = connections.remove(oldKey);
            connections.put(newKey, other);
            updateLeadMapping(oldKey, newKey);
            return;
        }

        for (Map.Entry<BlockPos, BlockPos> entry : new HashMap<>(connections).entrySet()) {
            if (entry.getValue().equals(oldKey)) {
                connections.put(entry.getKey(), newKey);
                updateLeadMapping(oldKey, newKey);
                break;
            }
        }
    }

    private static void updateLeadMapping(BlockPos oldPos, BlockPos newPos) {
        LeadEntity entity = leadEntities.remove(oldPos);
        if (entity != null) {
            leadEntities.put(newPos, entity);
            if (entity.getFromPos().equals(oldPos)) {
                entity.setFromPos(newPos);
            } else if (entity.getToPos().equals(oldPos)) {
                entity.setToPos(newPos);
            }
        }
    }

    public static void handleShipWorldTick(ShipObjectServerWorld shipWorld) {
        if (shipWorld == null) {
            return;
        }

        Collection<ShipObjectServer> ships = shipWorld.getShipObjects().values();
        if (ships.isEmpty()) {
            return;
        }

        Map<BlockPos, BlockPos> snapshot = new HashMap<>(connections);
        for (Map.Entry<BlockPos, BlockPos> entry : snapshot.entrySet()) {
            BlockPos oldFrom = entry.getKey();
            BlockPos oldTo = entry.getValue();

            BlockPos updatedFrom = findUpdatedPosition(ships, oldFrom);
            BlockPos updatedTo = findUpdatedPosition(ships, oldTo);

            BlockPos newFromKey = updatedFrom != null ? updatedFrom.immutable() : oldFrom;
            BlockPos newToKey = updatedTo != null ? updatedTo.immutable() : oldTo;

            if (newFromKey.equals(oldFrom) && newToKey.equals(oldTo)) {
                continue;
            }

            connections.remove(oldFrom);
            connections.put(newFromKey, newToKey);

            if (!newFromKey.equals(oldFrom)) {
                updateLeadMapping(oldFrom, newFromKey);
            }
            if (!newToKey.equals(oldTo)) {
                updateLeadMapping(oldTo, newToKey);
            }
        }
    }

    private static BlockPos findUpdatedPosition(Collection<ShipObjectServer> ships, BlockPos worldPos) {
        Vector3d worldCenter = new Vector3d(worldPos.getX() + 0.5D, worldPos.getY() + 0.5D, worldPos.getZ() + 0.5D);

        for (ShipObjectServer ship : ships) {
            ShipTransform previous = ship.getPrevTickShipTransform();
            ShipTransform current = ship.getShipTransform();
            if (previous == null || current == null) {
                continue;
            }

            Matrix4dc worldToShip = previous.getWorldToShip();
            Matrix4dc shipToWorld = current.getShipToWorld();
            if (worldToShip == null || shipToWorld == null) {
                continue;
            }

            Vector3d localPos = worldToShip.transformPosition(worldCenter, new Vector3d());
            if (!isApproximatelyBlockCenter(localPos)) {
                continue;
            }

            Vector3d newWorldCenter = shipToWorld.transformPosition(localPos, new Vector3d());
            BlockPos newPos = BlockPos.containing(newWorldCenter.x(), newWorldCenter.y(), newWorldCenter.z());
            if (!newPos.equals(worldPos)) {
                return newPos;
            }
            return null;
        }

        return null;
    }

    private static boolean isApproximatelyBlockCenter(Vector3d localPos) {
        return isApproximatelyHalf(localPos.x())
            && isApproximatelyHalf(localPos.y())
            && isApproximatelyHalf(localPos.z());
    }

    private static boolean isApproximatelyHalf(double value) {
        double fractional = value - Math.floor(value);
        return Math.abs(fractional - 0.5D) < 0.01D;
    }
}
