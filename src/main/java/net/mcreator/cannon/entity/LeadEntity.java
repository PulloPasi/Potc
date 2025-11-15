package net.mcreator.cannon.entity;

import net.mcreator.cannon.CannonMod;
import net.mcreator.cannon.init.CannonModEntities;
import net.mcreator.cannon.util.VSShipUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;

public class LeadEntity extends Entity {
    private static final EntityDataAccessor<BlockPos> FROM_POS = SynchedEntityData.defineId(LeadEntity.class, EntityDataSerializers.BLOCK_POS);
    private static final EntityDataAccessor<BlockPos> TO_POS = SynchedEntityData.defineId(LeadEntity.class, EntityDataSerializers.BLOCK_POS);

    private BlockPos fromPos = BlockPos.ZERO;
    private BlockPos toPos = BlockPos.ZERO;

    public LeadEntity(PlayMessages.SpawnEntity packet, Level level) {
        this(CannonModEntities.LEAD_ENTITY.get(), level);
    }

    public LeadEntity(EntityType<? extends LeadEntity> type, Level level) {
        super(type, level);
        this.noPhysics = true;
        this.setNoGravity(true);
    }

    public static LeadEntity create(Level level, BlockPos from, BlockPos to) {
        LeadEntity entity = CannonModEntities.LEAD_ENTITY.get().create(level);
        if (entity != null) {
            entity.setFromPos(from);
            entity.setToPos(to);
            entity.updatePositionAndBox();
        }
        return entity;
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(FROM_POS, BlockPos.ZERO);
        this.entityData.define(TO_POS, BlockPos.ZERO);
    }

    @Override
    public void tick() {
        super.tick();

        BlockPos from = getFromPos();
        BlockPos to = getToPos();

        if (from == null || to == null) {
            discard();
            return;
        }

        boolean fromLoaded = level().isLoaded(from);
        boolean toLoaded = level().isLoaded(to);

        if (!fromLoaded || !toLoaded) {
            // Wait for both chunks to finish loading before validating
            return;
        }

        boolean fromSolid = VSShipUtils.hasSolidBlock(level(), from);
        boolean toSolid = VSShipUtils.hasSolidBlock(level(), to);

        if (!fromSolid || !toSolid) {
            if (!level().isClientSide()) {
                CannonMod.LOGGER.debug("LeadEntity removed: fromSolid={}, toSolid={}, from={}, to={}",
                    fromSolid, toSolid, from, to);
            }
            discard();
            return;
        }

        updatePositionAndBox();
    }

    private void updatePositionAndBox() {
        BlockPos from = getFromPos();
        BlockPos to = getToPos();
        Vec3 start = VSShipUtils.getWorldBlockCenter(level(), from);
        Vec3 end = VSShipUtils.getWorldBlockCenter(level(), to);
        Vec3 mid = start.add(end).scale(0.5);
        setPos(mid.x, mid.y, mid.z);

        double padding = 0.2;
        double minX = Math.min(start.x, end.x) - padding;
        double minY = Math.min(start.y, end.y) - padding;
        double minZ = Math.min(start.z, end.z) - padding;
        double maxX = Math.max(start.x, end.x) + padding;
        double maxY = Math.max(start.y, end.y) + padding;
        double maxZ = Math.max(start.z, end.z) + padding;

        this.setBoundingBox(new AABB(minX, minY, minZ, maxX, maxY, maxZ));
    }

    public void setFromPos(BlockPos pos) {
        if (pos != null) {
            fromPos = pos;
            entityData.set(FROM_POS, pos);
        }
    }

    public void setToPos(BlockPos pos) {
        if (pos != null) {
            toPos = pos;
            entityData.set(TO_POS, pos);
        }
    }

    public BlockPos getFromPos() {
        BlockPos pos = entityData.get(FROM_POS);
        return pos != null ? pos : fromPos;
    }

    public BlockPos getToPos() {
        BlockPos pos = entityData.get(TO_POS);
        return pos != null ? pos : toPos;
    }

    public void refreshLifespan() {
        this.tickCount = 0;
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        if (tag.contains("From")) {
            setFromPos(NbtUtils.readBlockPos(tag.getCompound("From")));
        }
        if (tag.contains("To")) {
            setToPos(NbtUtils.readBlockPos(tag.getCompound("To")));
        }
        updatePositionAndBox();
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        tag.put("From", NbtUtils.writeBlockPos(getFromPos() == null ? BlockPos.ZERO : getFromPos()));
        tag.put("To", NbtUtils.writeBlockPos(getToPos() == null ? BlockPos.ZERO : getToPos()));
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public boolean isPickable() {
        return false;
    }

    @Override
    public boolean isInvulnerable() {
        return true;
    }
}
