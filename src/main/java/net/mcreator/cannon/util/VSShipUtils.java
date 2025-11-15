package net.mcreator.cannon.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3d;
import org.valkyrienskies.core.api.ships.Ship;
import org.valkyrienskies.mod.common.VSGameUtilsKt;
import org.valkyrienskies.mod.common.util.VectorConversionsMCKt;

public final class VSShipUtils {
    private VSShipUtils() {
    }

    public static Vec3 getWorldBlockCenter(Level level, BlockPos pos) {
        if (pos == null) {
            return Vec3.ZERO;
        }

        Vec3 fallback = Vec3.atCenterOf(pos);
        if (level == null) {
            return fallback;
        }

        Ship ship = VSGameUtilsKt.getShipObjectManagingPos(level, pos);
        if (ship == null) {
            ship = VSGameUtilsKt.getShipManagingPos(level, pos);
        }
        if (ship == null) {
            return fallback;
        }

        Vector3d centerLocal = VectorConversionsMCKt.toJOMLD(pos).add(0.5, 0.5, 0.5);
        Vector3d world = ship.getTransform().getShipToWorld().transformPosition(centerLocal, new Vector3d());
        return new Vec3(world.x(), world.y(), world.z());
    }

    public static boolean hasSolidBlock(Level level, BlockPos pos) {
        if (level == null || pos == null) {
            return false;
        }

        if (!level.isLoaded(pos)) {
            return false;
        }

        if (!level.isEmptyBlock(pos)) {
            return true;
        }

        Ship ship = VSGameUtilsKt.getShipObjectManagingPos(level, pos);
        if (ship == null) {
            ship = VSGameUtilsKt.getShipManagingPos(level, pos);
        }
        return ship != null;
    }

    public static boolean isPositionValid(Level level, BlockPos pos) {
        return hasSolidBlock(level, pos);
    }
}
