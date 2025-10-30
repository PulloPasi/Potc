
package net.mcreator.cannon.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mcreator.cannon.init.CannonModEntities;
import net.mcreator.cannon.util.LeadConnectionManager;
import org.valkyrienskies.core.api.ships.Ship;
import org.valkyrienskies.core.api.ships.ShipManager;
import org.valkyrienskies.mod.common.VSGameUtilsKt;

import java.util.*;

@Mod.EventBusSubscriber

public class LeadEntity extends Mob {
    private BlockPos from;
    private BlockPos to;
    private int lifespan = 20 * 60 * 10; // 10 minutes

    public LeadEntity(EntityType<? extends Mob> type, Level world) {
        super(type, world);
        this.noCulling = true;
        this.setNoGravity(true);
        this.noPhysics = true;
        this.setNoAi(true);
    }

    public LeadEntity(PlayMessages.SpawnEntity packet, Level world) {
        this(CannonModEntities.LEAD.get(), world);
    }

    public static LeadEntity create(Level world, BlockPos from, BlockPos to) {
        LeadEntity entity = new LeadEntity(CannonModEntities.LEAD.get(), world);
        entity.from = from;
        entity.to = to;
        entity.setPos((from.getX() + to.getX()) / 2.0, 
                     (from.getY() + to.getY()) / 2.0, 
                     (from.getZ() + to.getZ()) / 2.0);
        return entity;
    }
    
    @Deprecated // Use create() instead
    public LeadEntity(Level world, BlockPos from, BlockPos to) {
        this(CannonModEntities.LEAD.get(), world);
        this.from = from;
        this.to = to;
        this.setPos((from.getX() + to.getX()) / 2.0, 
                   (from.getY() + to.getY()) / 2.0, 
                   (from.getZ() + to.getZ()) / 2.0);
    }

    // Store last known positions to detect movement
    private BlockPos lastFromPos;
    private BlockPos lastToPos;
    
    @Override
    public void tick() {
        super.tick();
        
        if (this.level().isClientSide) {
            return; // Only run on server side
        }
        
        // Decrease lifespan and remove if expired
        if (--this.lifespan <= 0) {
            this.discard();
            return;
        }
        
        // Check if the blocks at both ends still exist and are loaded
        if (from == null || to == null) {
            this.discard();
            return;
        }
        
        // Check if either block has been removed or is too far
        if (!level().isLoaded(from) || !level().isLoaded(to) ||
            level().isEmptyBlock(from) || level().isEmptyBlock(to) ||
            from.distSqr(to) > 32 * 32) {
            // Let the LeadConnectionManager handle the cleanup
            LeadConnectionManager.breakConnection(from);
            this.discard();
            return;
        }
        
        // Update position to be between the two blocks
        this.updatePosition();
        
        // Check for block movement
        if (lastFromPos == null) lastFromPos = from;
        if (lastToPos == null) lastToPos = to;
        
        // Update connection if blocks have moved
        if (!lastFromPos.equals(from) || !lastToPos.equals(to)) {
            LeadConnectionManager.updateConnection(this, from, to);
            lastFromPos = from.immutable();
            lastToPos = to.immutable();
        }
    }
    
    private void updatePosition() {
        // Get the actual positions (accounting for ship movement)
        BlockPos actualFrom = getActualPosition(from);
        BlockPos actualTo = getActualPosition(to);
        
        // Update position to be between the two blocks
        this.setPos((actualFrom.getX() + actualTo.getX()) / 2.0, 
                   (actualFrom.getY() + actualTo.getY()) / 2.0, 
                   (actualFrom.getZ() + actualTo.getZ()) / 2.0);
    }
    
    private BlockPos getActualPosition(BlockPos pos) {
        // If Valkyrien Skies is present, get the transformed position
        try {
            ShipManager manager = VSGameUtilsKt.getShipObjectWorld(level()).getShipManager();
            Optional<Ship> ship = manager.getShipFromBlock(pos);
            if (ship.isPresent()) {
                return ship.get().getShipToWorld().transformPosition(pos);
            }
        } catch (Exception e) {
            // Valkyrien Skies not available or error occurred
        }
        return pos;
    }
    
    @SubscribeEvent
    public static void onServerTick(TickEvent.LevelTickEvent event) {
        if (event.phase != TickEvent.Phase.END || event.level.isClientSide()) {
            return;
        }
        
        // Update all lead entities in the level
        event.level.getEntitiesOfClass(LeadEntity.class, entity -> true)
            .forEach(LeadEntity::updatePosition);
    }
    }

    @Override
    protected void defineSynchedData() {
        // No synced data needed
    }
    
    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        if (tag.contains("From")) {
            this.from = NbtUtils.readBlockPos(tag.getCompound("From"));
        }
        if (tag.contains("To")) {
            this.to = NbtUtils.readBlockPos(tag.getCompound("To"));
        }
        this.lifespan = tag.getInt("Lifespan");
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        if (from != null) {
            tag.put("From", NbtUtils.writeBlockPos(from));
        }
        if (to != null) {
            tag.put("To", NbtUtils.writeBlockPos(to));
        }
        tag.putInt("Lifespan", lifespan);
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public BlockPos getFrom() {
        return from;
    }
    
    public void setFrom(BlockPos from) {
        this.from = from.immutable();
    }
    
    public void setTo(BlockPos to) {
        this.to = to.immutable();
    }

    public BlockPos getTo() {
        return to;
    }

    public void refreshLifespan() { 
        this.lifespan = 20 * 60 * 10; 
    }

    // Required for MCreator
    public static void init() {}
    
    // Required for MCreator's attribute registration
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
            .add(Attributes.MAX_HEALTH, 1.0D)
            .add(Attributes.MOVEMENT_SPEED, 0.0D)
            .add(Attributes.FOLLOW_RANGE, 0.0D);
    }
    
    @Override
    public Iterable<ItemStack> getArmorSlots() {
        return Collections.emptyList();
    }

    @Override
    public ItemStack getItemBySlot(EquipmentSlot p_21127_) {
        return ItemStack.EMPTY;
    }

    @Override
    public void setItemSlot(EquipmentSlot p_21036_, ItemStack p_21037_) {
    }

    @Override
    public HumanoidArm getMainArm() {
        return HumanoidArm.RIGHT;
    }
    
    // Factory method for entity creation
    public static EntityType.EntityFactory<LeadEntity> factory() {
        return LeadEntity::new;
    }
    
}
