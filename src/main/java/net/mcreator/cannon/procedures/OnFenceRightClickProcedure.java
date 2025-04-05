package net.mcreator.cannon.procedures; // Ensure correct package name

import net.minecraft.world.level.Level;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;

import net.mcreator.cannon.init.CannonModBlocks; // Import modded blocks
import net.mcreator.cannon.init.CannonModItems;
import net.mcreator.cannon.procedures.SpawnRopeBetweenFencesProcedure; // Ensure this exists

public class OnFenceRightClickProcedure {
    public static InteractionResult execute(Level world, BlockPos pos, Player player, InteractionHand hand) {
        if (player instanceof ServerPlayer serverPlayer) {
            serverPlayer.displayClientMessage(Component.literal("Block clicked!"), true);
        }

        // Get the clicked block
        Block clickedBlock = world.getBlockState(pos).getBlock();

        // Allow fences, "swirlwvy_rope", and "pulley"
        boolean isValidBlock = clickedBlock instanceof FenceBlock || 
                               clickedBlock == CannonModBlocks.SWIRLWVY_ROPE.get() || 
                               clickedBlock == CannonModBlocks.PULLEY.get();

        if (!isValidBlock) {
            return InteractionResult.PASS;
        }

        // Debug message
        if (player instanceof ServerPlayer serverPlayer) {
            serverPlayer.displayClientMessage(Component.literal("Valid block selected!"), true);
        }

        ItemStack itemInHand = player.getItemInHand(hand);
        if (itemInHand.getItem() != CannonModItems.CUSTOM_LEAD_ITEM.get()) {
            return InteractionResult.PASS;
        }

        if (!world.isClientSide) {
            if (world instanceof ServerLevel serverWorld) {
                CompoundTag playerData = player.getPersistentData();

                if (!playerData.contains("firstBlockX")) {
                    // Store first block position
                    playerData.putInt("firstBlockX", pos.getX());
                    playerData.putInt("firstBlockY", pos.getY());
                    playerData.putInt("firstBlockZ", pos.getZ());

                    if (player instanceof ServerPlayer serverPlayer) {
                        serverPlayer.displayClientMessage(Component.literal("First block selected!"), true);
                    }
                } else {
                    // Second block clicked → Spawn Rope
                    int x1 = playerData.getInt("firstBlockX");
                    int y1 = playerData.getInt("firstBlockY");
                    int z1 = playerData.getInt("firstBlockZ");

                    BlockPos firstBlockPos = new BlockPos(x1, y1, z1);
                    BlockPos secondBlockPos = pos;

                    // Ensure both blocks are valid
                    Block firstBlock = world.getBlockState(firstBlockPos).getBlock();
                    Block secondBlock = world.getBlockState(secondBlockPos).getBlock();

                    boolean isFirstValid = firstBlock instanceof FenceBlock || 
                                           firstBlock == CannonModBlocks.SWIRLWVY_ROPE.get() || 
                                           firstBlock == CannonModBlocks.PULLEY.get();

                    boolean isSecondValid = secondBlock instanceof FenceBlock || 
                                            secondBlock == CannonModBlocks.SWIRLWVY_ROPE.get() || 
                                            secondBlock == CannonModBlocks.PULLEY.get();

                    if (isFirstValid && isSecondValid) {
                        if (player instanceof ServerPlayer serverPlayer) {
                            serverPlayer.displayClientMessage(Component.literal("Second block selected! Spawning rope..."), true);
                        }

                        SpawnRopeBetweenFencesProcedure.execute(serverWorld, firstBlockPos, secondBlockPos);
                    } else {
                        if (player instanceof ServerPlayer serverPlayer) {
                            serverPlayer.displayClientMessage(Component.literal("Invalid second block!"), true);
                        }
                    }

                    // Clear stored block position
                    playerData.remove("firstBlockX");
                    playerData.remove("firstBlockY");
                    playerData.remove("firstBlockZ");
                }
            }
        }
        return InteractionResult.SUCCESS;
    }
}

