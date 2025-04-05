package net.mcreator.cannon.procedures;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceBlock;
import net.mcreator.cannon.init.CannonModBlocks;
import net.mcreator.cannon.init.CannonModEntities;
import net.mcreator.cannon.entity.LeashHolderEntity;

public class SpawnRopeBetweenFencesProcedure {
    public static void execute(ServerLevel serverWorld, BlockPos firstBlockPos, BlockPos secondBlockPos) {
        System.out.println("[DEBUG] SpawnRopeBetweenFencesProcedure triggered!");

        // Get the blocks at these positions
        Block firstBlock = serverWorld.getBlockState(firstBlockPos).getBlock();
        Block secondBlock = serverWorld.getBlockState(secondBlockPos).getBlock();

        // Ensure the blocks are valid for rope attachment
        boolean isFirstValid = firstBlock instanceof FenceBlock || 
                               firstBlock == CannonModBlocks.SWIRLWVY_ROPE.get() || 
                               firstBlock == CannonModBlocks.PULLEY.get();

        boolean isSecondValid = secondBlock instanceof FenceBlock || 
                                secondBlock == CannonModBlocks.SWIRLWVY_ROPE.get() || 
                                secondBlock == CannonModBlocks.PULLEY.get();

        if (!isFirstValid || !isSecondValid) {
            System.out.println("[DEBUG] One or both blocks are invalid. Aborting.");
            return;
        }

        // ✅ First leash holder height (-1.0, correct)
        double leashY1 = firstBlockPos.getY() - 1.0;
        // ✅ Second leash holder height (-0.5, raised by 0.5)
        double leashY2 = secondBlockPos.getY() - 0.5;

        // ✅ First leash holder (attached to first block)
        System.out.println("[DEBUG] Spawning first leash holder at: X=" + firstBlockPos.getX() + " Y=" + leashY1 + " Z=" + firstBlockPos.getZ());

        LeashHolderEntity leashHolder1 = new LeashHolderEntity(CannonModEntities.LEASH_HOLDER.get(), serverWorld);
        leashHolder1.moveTo(firstBlockPos.getX() + 0.5, leashY1, firstBlockPos.getZ() + 0.5);
        serverWorld.addFreshEntity(leashHolder1);
        System.out.println("[DEBUG] First leash holder spawned successfully!");

        // ✅ Second leash holder (attached to second block, 0.5 blocks higher)
        System.out.println("[DEBUG] Spawning second leash holder at: X=" + secondBlockPos.getX() + " Y=" + leashY2 + " Z=" + secondBlockPos.getZ());

        LeashHolderEntity leashHolder2 = new LeashHolderEntity(CannonModEntities.LEASH_HOLDER.get(), serverWorld);
        leashHolder2.moveTo(secondBlockPos.getX() + 0.5, leashY2, secondBlockPos.getZ() + 0.5);
        serverWorld.addFreshEntity(leashHolder2);
        System.out.println("[DEBUG] Second leash holder spawned successfully!");

        // ✅ Leash first holder to the second holder
        leashHolder1.setLeashedTo(leashHolder2, true);

        System.out.println("[DEBUG] Successfully leashed both holders together.");
    }
}
