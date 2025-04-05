package net.mcreator.cannon.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.AABB;

import net.mcreator.cannon.entity.LeashHolderEntity;
import net.minecraft.world.entity.decoration.ArmorStand;

@Mod.EventBusSubscriber
public class RemoveRopeOnFenceBreakProcedure {
    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        if (event.getLevel() instanceof Level level) {
            BlockPos pos = event.getPos();
            execute(level, pos);
        }
    }

    public static void execute(Level world, BlockPos pos) {
        if (world.isClientSide()) return; // Only run on the server

        // Find and remove LeashHolderEntities and rope armor stands near the broken fence
        for (Entity entity : world.getEntities(null, new AABB(pos))) {
            // Check if the entity is a LeashHolderEntity or ArmorStand, and it's close to the broken fence position
            if ((entity instanceof LeashHolderEntity || entity instanceof ArmorStand) && entity.blockPosition().distSqr(pos) <= 3.0 * 3.0) {
                entity.discard(); // Remove both leash holders and rope armor stands
            }
        }
    }
}
