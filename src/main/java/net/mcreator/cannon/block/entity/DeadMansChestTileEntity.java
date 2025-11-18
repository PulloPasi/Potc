package net.mcreator.cannon.block.entity;

import software.bernie.geckolib.util.GeckoLibUtil;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.object.PlayState;

import net.minecraftforge.items.wrapper.SidedInvWrapper;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.Capability;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.ContainerHelper;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.network.chat.Component;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.mcreator.cannon.init.CannonModBlockEntities;
import net.mcreator.cannon.init.CannonModItems;
import net.mcreator.cannon.block.DeadMansChestBlock;

import javax.annotation.Nullable;

import java.util.stream.IntStream;

public class DeadMansChestTileEntity extends RandomizableContainerBlockEntity implements GeoBlockEntity, WorldlyContainer {
	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
	private NonNullList<ItemStack> stacks = NonNullList.<ItemStack>withSize(27, ItemStack.EMPTY);
	private final LazyOptional<? extends IItemHandler>[] handlers = SidedInvWrapper.create(this, Direction.values());
	private boolean unlocked;
	private boolean heartGranted;
	private int openCount;

	public DeadMansChestTileEntity(BlockPos pos, BlockState state) {
		super(CannonModBlockEntities.DEAD_MANS_CHEST.get(), pos, state);
	}

	private PlayState predicate(AnimationState event) {
		String animationprocedure = ("" + this.getBlockState().getValue(DeadMansChestBlock.ANIMATION));
		if (animationprocedure.equals("0")) {
			return event.setAndContinue(RawAnimation.begin().thenLoop(animationprocedure));
		}
		return PlayState.STOP;
	}

	String prevAnim = "0";

	private PlayState procedurePredicate(AnimationState event) {
		String animationprocedure = ("" + this.getBlockState().getValue(DeadMansChestBlock.ANIMATION));
		if (!animationprocedure.equals("0") && event.getController().getAnimationState() == AnimationController.State.STOPPED || (!animationprocedure.equals(prevAnim) && !animationprocedure.equals("0"))) {
			if (!animationprocedure.equals(prevAnim))
				event.getController().forceAnimationReset();
			event.getController().setAnimation(RawAnimation.begin().thenPlay(animationprocedure));
			if (event.getController().getAnimationState() == AnimationController.State.STOPPED) {
				if (this.getBlockState().getBlock().getStateDefinition().getProperty("animation") instanceof IntegerProperty _integerProp)
					level.setBlock(this.getBlockPos(), this.getBlockState().setValue(_integerProp, 0), 3);
				event.getController().forceAnimationReset();
			}
		} else if (animationprocedure.equals("0")) {
			prevAnim = "0";
			return PlayState.STOP;
		}
		prevAnim = animationprocedure;
		return PlayState.CONTINUE;
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar data) {
		data.add(new AnimationController<DeadMansChestTileEntity>(this, "controller", 0, this::predicate));
		data.add(new AnimationController<DeadMansChestTileEntity>(this, "procedurecontroller", 0, this::procedurePredicate));
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.cache;
	}

	@Override
	public void load(CompoundTag compound) {
		super.load(compound);
		if (!this.tryLoadLootTable(compound))
			this.stacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		ContainerHelper.loadAllItems(compound, this.stacks);
		this.unlocked = compound.getBoolean("Unlocked");
		this.heartGranted = compound.getBoolean("HeartGranted");
	}

	@Override
	public void saveAdditional(CompoundTag compound) {
		super.saveAdditional(compound);
		if (!this.trySaveLootTable(compound)) {
			ContainerHelper.saveAllItems(compound, this.stacks);
		}
		compound.putBoolean("Unlocked", this.unlocked);
		compound.putBoolean("HeartGranted", this.heartGranted);
	}

	@Override
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	public CompoundTag getUpdateTag() {
		return this.saveWithFullMetadata();
	}

	@Override
	public int getContainerSize() {
		return stacks.size();
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack itemstack : this.stacks)
			if (!itemstack.isEmpty())
				return false;
		return true;
	}

	@Override
	public Component getDefaultName() {
		return Component.literal("dead_mans_chest");
	}

	@Override
	public int getMaxStackSize() {
		return 64;
	}

	@Override
	public AbstractContainerMenu createMenu(int id, Inventory inventory) {
		return ChestMenu.threeRows(id, inventory, this);
	}

	@Override
	public Component getDisplayName() {
		return Component.literal("Dead Mans Chest");
	}

	@Override
	protected NonNullList<ItemStack> getItems() {
		return this.stacks;
	}

	@Override
	protected void setItems(NonNullList<ItemStack> stacks) {
		this.stacks = stacks;
	}

	@Override
	public boolean canPlaceItem(int index, ItemStack stack) {
		return true;
	}

	@Override
	public int[] getSlotsForFace(Direction side) {
		return IntStream.range(0, this.getContainerSize()).toArray();
	}

	@Override
	public boolean canPlaceItemThroughFace(int index, ItemStack stack, @Nullable Direction direction) {
		return this.canPlaceItem(index, stack);
	}

	@Override
	public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
		return true;
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
		if (!this.remove && facing != null && capability == ForgeCapabilities.ITEM_HANDLER)
			return handlers[facing.ordinal()].cast();
		return super.getCapability(capability, facing);
	}

	public boolean isUnlocked() {
		return this.unlocked;
	}

	public void setUnlocked(boolean unlocked) {
		if (this.unlocked != unlocked) {
			this.unlocked = unlocked;
			setChanged();
		}
		if (unlocked && !this.heartGranted) {
			ensureHeartPresent();
		}
	}

	@Override
	public void onLoad() {
		super.onLoad();
		if (!this.level.isClientSide && this.unlocked && !this.heartGranted) {
			ensureHeartPresent();
		}
	}

	@Override
	public void saveToItem(ItemStack stack) {
		CompoundTag beTag = this.saveWithoutMetadata();
		if (!beTag.isEmpty()) {
			stack.addTagElement("BlockEntityTag", beTag);
		}
	}

	@Override
	public void startOpen(Player player) {
		super.startOpen(player);
		if (player.isSpectator()) {
			return;
		}
		if (this.level == null || this.level.isClientSide) {
			return;
		}
		if (this.openCount < 0) {
			this.openCount = 0;
		}
		this.openCount++;
		setAnimatingState(DeadMansChestBlock.ANIMATION, 2);
	}

	@Override
	public void stopOpen(Player player) {
		super.stopOpen(player);
		if (player.isSpectator()) {
			return;
		}
		if (this.level == null || this.level.isClientSide) {
			return;
		}
		this.openCount = Math.max(0, this.openCount - 1);
		if (this.openCount <= 0) {
			setAnimatingState(DeadMansChestBlock.ANIMATION, 3);
		}
	}

	private void setAnimatingState(IntegerProperty property, int value) {
		if (this.level == null) {
			return;
		}
		BlockState state = this.getBlockState();
		if (!state.hasProperty(property)) {
			return;
		}
		if (state.getValue(property) == value) {
			return;
		}
		BlockPos pos = this.getBlockPos();
		BlockState newState = state.setValue(property, value);
		this.level.setBlock(pos, newState, 3);
		this.level.sendBlockUpdated(pos, state, newState, 3);
		this.setChanged();
		if ((value == 2 || value == 3) && this.level instanceof ServerLevel serverLevel) {
			serverLevel.scheduleTick(pos, newState.getBlock(), DeadMansChestBlock.ANIMATION_TICKS);
		}
	}

	private void ensureHeartPresent() {
		if (this.level == null || this.level.isClientSide) {
			return;
		}
		if (this.heartGranted) {
			return;
		}
		if (containsHeart()) {
			this.heartGranted = true;
			setChanged();
			return;
		}
		for (int i = 0; i < this.stacks.size(); i++) {
			ItemStack stack = this.stacks.get(i);
			if (stack.isEmpty()) {
				this.stacks.set(i, new ItemStack(CannonModItems.HEART_OF_DAVID_JONES.get()));
				this.heartGranted = true;
				setChanged();
				BlockState state = getBlockState();
				this.level.sendBlockUpdated(this.worldPosition, state, state, 3);
				return;
			}
		}
	}

	private boolean containsHeart() {
		for (ItemStack stack : this.stacks) {
			if (stack.is(CannonModItems.HEART_OF_DAVID_JONES.get())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void setRemoved() {
		super.setRemoved();
		for (LazyOptional<? extends IItemHandler> handler : handlers)
			handler.invalidate();
	}
}
