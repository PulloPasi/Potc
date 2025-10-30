package net.mcreator.cannon.item;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.mcreator.cannon.util.LeadConnectionManager;

public class RiggingToolItem extends Item {
    public RiggingToolItem() {
        super(new Item.Properties().stacksTo(1).rarity(Rarity.COMMON));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        ItemStack stack = context.getItemInHand();
        
        if (world.isClientSide) {
            return InteractionResult.SUCCESS;
        }

        // Check if player is sneaking to break connection
        if (context.getPlayer() != null && context.getPlayer().isShiftKeyDown()) {
            if (LeadConnectionManager.isConnected(pos)) {
                LeadConnectionManager.breakConnection(pos);
                context.getPlayer().displayClientMessage(Component.literal("Connection removed"), true);
                return InteractionResult.SUCCESS;
            }
            return InteractionResult.PASS;
        }

        // Handle creating connection
        if (!stack.hasTag()) {
            // First click - store position
            CompoundTag nbt = new CompoundTag();
            nbt.put("FirstPos", NbtUtils.writeBlockPos(pos));
            stack.setTag(nbt);
            context.getPlayer().displayClientMessage(Component.literal("Selected first position. Right-click second block."), true);
        } else {
            // Second click - create connection
            try {
                CompoundTag tag = stack.getTag();
                if (tag == null || !tag.contains("FirstPos")) {
                    context.getPlayer().displayClientMessage(Component.literal("Error: Could not find first position"), true);
                    stack.setTag(null); // Reset the item
                    return InteractionResult.FAIL;
                }
                
                BlockPos firstPos = NbtUtils.readBlockPos(tag.getCompound("FirstPos"));
                
                if (firstPos == null) {
                    context.getPlayer().displayClientMessage(Component.literal("Error: Invalid first position"), true);
                    stack.setTag(null); // Reset the item
                    return InteractionResult.FAIL;
                }
                
                if (firstPos.equals(pos)) {
                    context.getPlayer().displayClientMessage(Component.literal("Cannot connect block to itself"), true);
                } else if (LeadConnectionManager.isConnected(pos) || LeadConnectionManager.isConnected(firstPos)) {
                    context.getPlayer().displayClientMessage(Component.literal("One or both blocks are already connected"), true);
                } else {
                    if (LeadConnectionManager.createConnection(world, firstPos, pos)) {
                        context.getPlayer().displayClientMessage(Component.literal("Connected blocks!"), true);
                    } else {
                        context.getPlayer().displayClientMessage(Component.literal("Failed to create connection. Blocks may be too far apart."), true);
                    }
                }
            } catch (Exception e) {
                context.getPlayer().displayClientMessage(Component.literal("Error creating connection: " + e.getMessage()), true);
                stack.setTag(null); // Reset the item on error
                return InteractionResult.FAIL;
            }
            
            // Clear the first position
            stack.setTag(null);
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        // Make the item glint when it has a position stored
        return stack.hasTag() && stack.getTag().contains("FirstPos");
    }
}
