
package net.mcreator.cannon.item;

import net.mcreator.cannon.util.LeadConnectionManager;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.FakePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RiggingToolItem extends Item {
    private static final String FIRST_POS_KEY = "FirstPos";
    private static final double MAX_CONNECTION_DISTANCE = 32.0D;

    public RiggingToolItem() {
        super(new Properties().stacksTo(1).durability(256));
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltip,
                                 @NotNull TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);

        BlockPos firstPos = getFirstPosition(stack);
        if (firstPos != null) {
            tooltip.add(Component.literal(String.format("First position: %d, %d, %d",
                firstPos.getX(), firstPos.getY(), firstPos.getZ())).withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("Right-click another block to connect").withStyle(ChatFormatting.YELLOW));
        } else {
            tooltip.add(Component.literal("Right-click a block to set the first position").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("Shift + Right-click to break connections").withStyle(ChatFormatting.GRAY));
        }
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Player player = context.getPlayer();
        ItemStack stack = context.getItemInHand();
        BlockPos pos = context.getClickedPos();

        if (player == null || player instanceof FakePlayer) {
            return InteractionResult.PASS;
        }

        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        }

        if (player.isShiftKeyDown()) {
            return handleBreak(level, pos, player);
        }

        if (hasFirstPosition(stack)) {
            return handleSecondClick(level, stack, pos, player);
        }

        return handleFirstClick(stack, pos, player);
    }

    private InteractionResult handleBreak(Level level, BlockPos pos, Player player) {
        if (LeadConnectionManager.isConnected(pos)) {
            LeadConnectionManager.breakConnection(level, pos);
            player.displayClientMessage(Component.literal("Connection broken!").withStyle(ChatFormatting.RED), true);
            level.playSound(null, pos, SoundEvents.LEASH_KNOT_BREAK, SoundSource.BLOCKS, 0.8F, 1.0F);
            return InteractionResult.SUCCESS;
        }

        player.displayClientMessage(Component.literal("No connection found at this position").withStyle(ChatFormatting.YELLOW), true);
        return InteractionResult.PASS;
    }

    private InteractionResult handleFirstClick(ItemStack stack, BlockPos pos, Player player) {
        setFirstPosition(stack, pos);
        player.displayClientMessage(Component.literal(String.format("First position set to: %d, %d, %d",
            pos.getX(), pos.getY(), pos.getZ())).withStyle(ChatFormatting.GREEN), true);
        player.level().playSound(null, pos, SoundEvents.LEASH_KNOT_PLACE, SoundSource.BLOCKS, 0.6F, 1.0F);
        return InteractionResult.SUCCESS;
    }

    private InteractionResult handleSecondClick(Level level, ItemStack stack, BlockPos secondPos, Player player) {
        BlockPos firstPos = getFirstPosition(stack);
        clearFirstPosition(stack);

        if (firstPos == null) {
            player.displayClientMessage(Component.literal("First position was lost; please select again")
                .withStyle(ChatFormatting.RED), true);
            return InteractionResult.FAIL;
        }

        if (firstPos.equals(secondPos)) {
            player.displayClientMessage(Component.literal("Cannot connect a block to itself")
                .withStyle(ChatFormatting.RED), true);
            return InteractionResult.FAIL;
        }

        double distance = Math.sqrt(firstPos.distSqr(secondPos));
        if (distance > MAX_CONNECTION_DISTANCE) {
            player.displayClientMessage(Component.literal(String.format(
                "Blocks are too far apart (%.1f > %.1f)", distance, MAX_CONNECTION_DISTANCE))
                .withStyle(ChatFormatting.RED), true);
            return InteractionResult.FAIL;
        }

        if (LeadConnectionManager.isConnected(firstPos) || LeadConnectionManager.isConnected(secondPos)) {
            player.displayClientMessage(Component.literal("One or both blocks are already connected")
                .withStyle(ChatFormatting.RED), true);
            return InteractionResult.FAIL;
        }

        if (LeadConnectionManager.createConnection(level, firstPos, secondPos)) {
            level.playSound(null, secondPos, SoundEvents.LEASH_KNOT_PLACE, SoundSource.BLOCKS, 0.8F, 1.0F);
            if (!player.isCreative()) {
                stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(InteractionHand.MAIN_HAND));
            }
            player.displayClientMessage(Component.literal("Rope placed!").withStyle(ChatFormatting.GREEN), true);
            return InteractionResult.SUCCESS;
        }

        player.displayClientMessage(Component.literal("Failed to place rope").withStyle(ChatFormatting.RED), true);
        return InteractionResult.FAIL;
    }

    private static boolean hasFirstPosition(ItemStack stack) {
        CompoundTag tag = stack.getTag();
        return tag != null && tag.contains(FIRST_POS_KEY);
    }

    @Nullable
    private static BlockPos getFirstPosition(ItemStack stack) {
        CompoundTag tag = stack.getTag();
        return tag != null && tag.contains(FIRST_POS_KEY)
            ? NbtUtils.readBlockPos(tag.getCompound(FIRST_POS_KEY))
            : null;
    }

    private static void setFirstPosition(ItemStack stack, BlockPos pos) {
        CompoundTag tag = stack.getOrCreateTag();
        tag.put(FIRST_POS_KEY, NbtUtils.writeBlockPos(pos));
    }

    private static void clearFirstPosition(ItemStack stack) {
        CompoundTag tag = stack.getTag();
        if (tag != null) {
            tag.remove(FIRST_POS_KEY);
            if (tag.isEmpty()) {
                stack.setTag(null);
            }
        }
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player,
                                                           @NotNull InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (player.isShiftKeyDown() && hasFirstPosition(stack)) {
            clearFirstPosition(stack);
            if (!level.isClientSide) {
                player.displayClientMessage(Component.literal("Selection cleared")
                    .withStyle(ChatFormatting.GRAY), true);
                level.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.UI_BUTTON_CLICK.value(), SoundSource.PLAYERS, 0.6F, 1.2F);
            }
            return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
        }

        return super.use(level, player, hand);
    }

    @Override
    public boolean isFoil(@NotNull ItemStack stack) {
        return hasFirstPosition(stack);
    }

    @Override
    public boolean isEnchantable(@NotNull ItemStack stack) {
        return false;
    }
}
