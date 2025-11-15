package net.mcreator.cannon.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.network.NetworkHooks;

public class LeadAnchorEntity extends Entity {
    private static final EntityDataAccessor<BlockPos> BLOCK_POS = SynchedEntityData.defineId(LeadAnchorEntity.class, EntityDataSerializers.BLOCK_POS);
    
    public LeadAnchorEntity(EntityType<?> type, Level level) {
        super(type, level);
        this.noPhysics = true;
        this.setNoGravity(true);
        this.setInvulnerable(true);
        this.setInvisible(true);
    }

    public void setBlockPos(BlockPos pos) {
        this.entityData.set(BLOCK_POS, pos.immutable());
        updatePosition();
    }

    public BlockPos getTrackedPos() {
        return this.entityData.get(BLOCK_POS);
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide) {
            updatePosition();
        }
    }

    private void updatePosition() {
        BlockPos pos = getTrackedPos();
        if (pos != null) {
            // Center the entity in the block
            this.setPos(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
            // Set bounding box to be small but not zero
            this.setBoundingBox(new AABB(0, 0, 0, 0.1, 0.1, 0.1).move(this.position()));
        }
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(BLOCK_POS, BlockPos.ZERO);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        if (tag.contains("BlockPos")) {
            setBlockPos(BlockPos.of(tag.getLong("BlockPos")));
        }
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        tag.putLong("BlockPos", getTrackedPos().asLong());
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
