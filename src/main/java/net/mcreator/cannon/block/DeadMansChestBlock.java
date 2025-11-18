
package net.mcreator.cannon.block;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.MenuProvider;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.network.chat.Component;
import net.minecraft.nbt.CompoundTag;

import net.mcreator.cannon.init.CannonModBlockEntities;
import net.mcreator.cannon.block.entity.DeadMansChestTileEntity;
import net.mcreator.cannon.init.CannonModItems;

import javax.annotation.Nullable;

import java.util.List;
import java.util.Collections;

public class DeadMansChestBlock extends BaseEntityBlock implements EntityBlock {
	public static final IntegerProperty ANIMATION = IntegerProperty.create("animation", 0, 3);
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	public static final BooleanProperty LOCKED = BooleanProperty.create("locked");
	public static final int ANIMATION_TICKS = 20;

	public DeadMansChestBlock() {
		super(BlockBehaviour.Properties.of()

				.sound(SoundType.POLISHED_DEEPSLATE).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(ANIMATION, 0).setValue(LOCKED, Boolean.TRUE));
	}

	@Override
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.ENTITYBLOCK_ANIMATED;
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
		return CannonModBlockEntities.DEAD_MANS_CHEST.get().create(blockPos, blockState);
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return true;
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {

		return switch (state.getValue(FACING)) {
			default -> box(1, 0, 4, 15, 10, 12);
			case NORTH -> box(1, 0, 4, 15, 10, 12);
			case EAST -> box(4, 0, 1, 12, 10, 15);
			case WEST -> box(4, 0, 1, 12, 10, 15);
		};
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(ANIMATION, FACING, LOCKED);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(LOCKED, Boolean.TRUE);
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}

	@Override
	public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
		BlockEntity blockEntity = builder.getOptionalParameter(LootContextParams.BLOCK_ENTITY);
		if (blockEntity instanceof DeadMansChestTileEntity chest) {
			ItemStack stack = new ItemStack(this);
			chest.saveToItem(stack);
			return Collections.singletonList(stack);
		}
		return Collections.singletonList(new ItemStack(this));
	}

	@Override
	public MenuProvider getMenuProvider(BlockState state, Level worldIn, BlockPos pos) {
		BlockEntity tileEntity = worldIn.getBlockEntity(pos);
		return tileEntity instanceof MenuProvider menuProvider ? menuProvider : null;
	}

	@Override
	public boolean triggerEvent(BlockState state, Level world, BlockPos pos, int eventID, int eventParam) {
		super.triggerEvent(state, world, pos, eventID, eventParam);
		BlockEntity blockEntity = world.getBlockEntity(pos);
		return blockEntity == null ? false : blockEntity.triggerEvent(eventID, eventParam);
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		boolean isLocked = state.getValue(LOCKED);
		if (isLocked) {
			ItemStack keyInHand = findKey(player);
			if (!keyInHand.isEmpty()) {
				if (!level.isClientSide) {
					if (!player.isCreative()) {
						keyInHand.shrink(1);
					}
					BlockState unlockingState = state.setValue(LOCKED, Boolean.FALSE);
					level.setBlock(pos, unlockingState, 3);
					level.sendBlockUpdated(pos, state, unlockingState, 3);
					BlockEntity blockEntity = level.getBlockEntity(pos);
					if (blockEntity instanceof DeadMansChestTileEntity chest) {
						chest.setUnlocked(true);
					}
					if (blockEntity instanceof MenuProvider menuProvider) {
						player.openMenu(menuProvider);
					}
					level.playSound(null, pos, SoundEvents.CHEST_OPEN, SoundSource.BLOCKS, 1.0F, 1.0F);
					player.displayClientMessage(Component.literal("The lock snaps open."), true);
				}
				return InteractionResult.sidedSuccess(level.isClientSide);
			}
			if (!level.isClientSide) {
				level.playSound(null, pos, SoundEvents.IRON_TRAPDOOR_CLOSE, SoundSource.BLOCKS, 0.8F, 0.8F);
				player.displayClientMessage(Component.literal("It won't budge without the heart key."), true);
			}
			return InteractionResult.sidedSuccess(level.isClientSide);
		}

		if (!level.isClientSide) {
			level.setBlock(pos, state.setValue(ANIMATION, 2), 3);
			if (level instanceof ServerLevel serverLevel) {
				serverLevel.scheduleTick(pos, this, ANIMATION_TICKS);
			}
			BlockEntity blockEntity = level.getBlockEntity(pos);
			if (blockEntity instanceof MenuProvider menuProvider) {
				player.openMenu(menuProvider);
			}
			level.playSound(null, pos, SoundEvents.CHEST_OPEN, SoundSource.BLOCKS, 0.9F, 1.0F);
		}
		return InteractionResult.sidedSuccess(level.isClientSide);
	}

	@Override
	public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		int animationState = state.getValue(ANIMATION);
		if (animationState == 3) {
			BlockState newState = state.setValue(ANIMATION, 0);
			level.setBlock(pos, newState, 3);
			level.sendBlockUpdated(pos, state, newState, 3);
		} else if (animationState == 2) {
			BlockState newState = state.setValue(ANIMATION, 1);
			level.setBlock(pos, newState, 3);
			level.sendBlockUpdated(pos, state, newState, 3);
		}
	}

	@Override
	public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		super.setPlacedBy(level, pos, state, placer, stack);
		if (!(level instanceof ServerLevel serverLevel)) {
			return;
		}
		BlockEntity blockEntity = serverLevel.getBlockEntity(pos);
		if (blockEntity instanceof DeadMansChestTileEntity chest) {
			CompoundTag tag = stack.getTagElement("BlockEntityTag");
			if (tag != null) {
				chest.load(tag);
			}
			if (chest.isUnlocked()) {
				chest.setUnlocked(true);
				serverLevel.setBlock(pos, state.setValue(LOCKED, Boolean.FALSE).setValue(ANIMATION, 0), 3);
			}
		}
	}

	private static ItemStack findKey(Player player) {
		ItemStack mainHand = player.getMainHandItem();
		if (mainHand.is(CannonModItems.KEY.get())) {
			return mainHand;
		}
		ItemStack offHand = player.getOffhandItem();
		if (offHand.is(CannonModItems.KEY.get())) {
			return offHand;
		}
		return ItemStack.EMPTY;
	}

	@Override
	public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean isMoving) {
		if (state.getBlock() != newState.getBlock()) {
			BlockEntity blockEntity = world.getBlockEntity(pos);
			if (blockEntity instanceof DeadMansChestTileEntity) {
				world.updateNeighbourForOutputSignal(pos, this);
			}
			super.onRemove(state, world, pos, newState, isMoving);
		}
	}

	@Override
	public boolean hasAnalogOutputSignal(BlockState state) {
		return true;
	}

	@Override
	public int getAnalogOutputSignal(BlockState blockState, Level world, BlockPos pos) {
		BlockEntity tileentity = world.getBlockEntity(pos);
		if (tileentity instanceof DeadMansChestTileEntity be)
			return AbstractContainerMenu.getRedstoneSignalFromContainer(be);
		else
			return 0;
	}
}
