package net.mcreator.cannon.block.entity;

import net.minecraftforge.items.wrapper.SidedInvWrapper;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.Capability;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Containers;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundStopSoundPacket;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;

import net.mcreator.cannon.init.CannonModBlockEntities;
import net.mcreator.cannon.init.CannonModItems;
import net.mcreator.cannon.init.CannonModSounds;

import javax.annotation.Nullable;

import net.minecraftforge.registries.ForgeRegistries;

public class OrganBlockEntity extends RandomizableContainerBlockEntity implements WorldlyContainer {
	private static final int ORGAN1_DURATION_TICKS = 20 * 120; // Approx. 2 minutes, adjust to match asset length
	private NonNullList<ItemStack> stacks = NonNullList.withSize(1, ItemStack.EMPTY);
	private final LazyOptional<? extends IItemHandler>[] handlers = SidedInvWrapper.create(this, Direction.values());
	private boolean playing;
	private int ticksRemaining;
	@Nullable
	private ResourceLocation activeSoundId;

	public OrganBlockEntity(BlockPos position, BlockState state) {
		super(CannonModBlockEntities.ORGAN.get(), position, state);
	}

	@Override
	public void load(CompoundTag compound) {
		super.load(compound);
		if (!this.tryLoadLootTable(compound))
			this.stacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		ContainerHelper.loadAllItems(compound, this.stacks);
		this.playing = compound.getBoolean("OrganPlaying");
		this.ticksRemaining = compound.getInt("OrganTicks");
		if (compound.contains("OrganSound", CompoundTag.TAG_STRING)) {
			this.activeSoundId = ResourceLocation.tryParse(compound.getString("OrganSound"));
		} else {
			this.activeSoundId = null;
		}
		if (this.playing) {
			// Resume state cleanly after reload; actual audio restart handled via interaction
			this.playing = false;
			this.ticksRemaining = 0;
			this.activeSoundId = null;
		}
	}

	@Override
	public void saveAdditional(CompoundTag compound) {
		super.saveAdditional(compound);
		if (!this.trySaveLootTable(compound)) {
			ContainerHelper.saveAllItems(compound, this.stacks);
		}
		compound.putBoolean("OrganPlaying", this.playing);
		compound.putInt("OrganTicks", this.ticksRemaining);
		if (this.activeSoundId != null)
			compound.putString("OrganSound", this.activeSoundId.toString());
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
		return Component.literal("organ");
	}

	@Override
	public int getMaxStackSize() {
		return 1;
	}

	@Override
	public AbstractContainerMenu createMenu(int id, Inventory inventory) {
		return ChestMenu.threeRows(id, inventory);
	}

	@Override
	public Component getDisplayName() {
		return Component.literal("Organ");
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
		return this.isValidSheet(stack);
	}

	@Override
	public int[] getSlotsForFace(Direction side) {
		return new int[] {0};
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

	@Override
	public void setRemoved() {
		super.setRemoved();
		for (LazyOptional<? extends IItemHandler> handler : handlers)
			handler.invalidate();
	}

	public boolean tryInsertSheet(Player player, ItemStack stack) {
		if (!this.isValidSheet(stack) || this.hasSheet())
			return false;
		ItemStack toStore = stack.copy();
		toStore.setCount(1);
		this.stacks.set(0, toStore);
		this.setChanged();
		this.notifyStateChanged();
		if (!player.getAbilities().instabuild)
			stack.shrink(1);
		if (this.startPlaybackInternal()) {
			return true;
		}
		// Playback failed, return item
		if (!player.getAbilities().instabuild)
			player.addItem(toStore);
		this.stacks.set(0, ItemStack.EMPTY);
		this.setChanged();
		this.notifyStateChanged();
		return false;
	}

	public void stopAndReturnSheet(Player player) {
		this.stopPlayback();
		ItemStack sheet = this.removeSheet();
		if (!sheet.isEmpty()) {
			if (!player.addItem(sheet))
				player.drop(sheet, false);
		}
	}

	public void ejectSheet(Player player) {
		this.stopPlayback();
		ItemStack sheet = this.removeSheet();
		if (!sheet.isEmpty()) {
			if (!player.addItem(sheet))
				player.drop(sheet, false);
		}
	}

	public boolean hasSheet() {
		return !this.stacks.get(0).isEmpty();
	}

	public boolean isPlaying() {
		return this.playing;
	}

	public void serverTick() {
		if (this.level == null || this.level.isClientSide)
			return;
		if (!this.playing) {
			if (this.hasSheet())
				this.startPlaybackInternal();
			return;
		}
		if (this.ticksRemaining > 0) {
			this.ticksRemaining--;
			if (this.ticksRemaining > 0)
				return;
		}
		this.finishPlayback();
	}

	public void stopPlayback() {
		if (!this.playing)
			return;
		this.playing = false;
		this.ticksRemaining = 0;
		this.broadcastStopSound();
		this.activeSoundId = null;
		this.setChanged();
		this.notifyStateChanged();
	}

	private void broadcastStopSound() {
		if (!(this.level instanceof ServerLevel server) || this.activeSoundId == null)
			return;
		ClientboundStopSoundPacket packet = new ClientboundStopSoundPacket(this.activeSoundId, SoundSource.RECORDS);
		double x = this.worldPosition.getX() + 0.5D;
		double y = this.worldPosition.getY() + 0.5D;
		double z = this.worldPosition.getZ() + 0.5D;
		for (ServerPlayer player : server.players()) {
			if (player.distanceToSqr(x, y, z) <= 256.0D)
				player.connection.send(packet);
		}
	}

	private boolean startPlaybackInternal() {
		if (this.level == null || this.level.isClientSide)
			return false;
		SoundEvent sound = this.getCurrentSound();
		if (sound == null)
			return false;
		ResourceLocation soundId = ForgeRegistries.SOUND_EVENTS.getKey(sound);
		if (soundId == null)
			return false;
		this.playing = true;
		this.activeSoundId = soundId;
		this.ticksRemaining = this.getDurationFor(soundId);
		if (this.ticksRemaining <= 0)
			this.ticksRemaining = ORGAN1_DURATION_TICKS;
		((ServerLevel) this.level).playSound(null, this.worldPosition, sound, SoundSource.RECORDS, 4.0F, 1.0F);
		this.setChanged();
		this.notifyStateChanged();
		return true;
	}

	@Nullable
	private SoundEvent getCurrentSound() {
		ItemStack stack = this.stacks.get(0);
		if (stack.is(CannonModItems.DAVYJONESSHEET.get()))
			return CannonModSounds.ORGAN1.get();
		return null;
	}

	private int getDurationFor(ResourceLocation soundId) {
		ResourceLocation organ1Id = ForgeRegistries.SOUND_EVENTS.getKey(CannonModSounds.ORGAN1.get());
		if (organ1Id != null && organ1Id.equals(soundId))
			return ORGAN1_DURATION_TICKS;
		return ORGAN1_DURATION_TICKS;
	}

	private ItemStack removeSheet() {
		ItemStack stored = this.stacks.get(0);
		if (stored.isEmpty())
			return ItemStack.EMPTY;
		this.stacks.set(0, ItemStack.EMPTY);
		this.setChanged();
		this.notifyStateChanged();
		return stored;
	}

	private void finishPlayback() {
		this.stopPlayback();
		ItemStack sheet = this.removeSheet();
		if (!sheet.isEmpty() && this.level != null) {
			Containers.dropItemStack(this.level, this.worldPosition.getX() + 0.5D, this.worldPosition.getY() + 0.5D, this.worldPosition.getZ() + 0.5D, sheet);
		}
	}

	private boolean isValidSheet(ItemStack stack) {
		return stack.is(CannonModItems.DAVYJONESSHEET.get());
	}

	private void notifyStateChanged() {
		if (this.level == null)
			return;
		BlockState state = this.getBlockState();
		this.level.sendBlockUpdated(this.worldPosition, state, state, Block.UPDATE_ALL);
		this.level.updateNeighbourForOutputSignal(this.worldPosition, state.getBlock());
	}

	public static void tick(Level level, BlockPos pos, BlockState state, OrganBlockEntity organ) {
		if (!level.isClientSide)
			organ.serverTick();
	}
}
