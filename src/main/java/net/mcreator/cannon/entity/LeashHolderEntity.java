package net.mcreator.cannon.entity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.network.NetworkHooks;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

// ✅ Ensure correct import
import net.mcreator.cannon.init.CannonModEntities;

public class LeashHolderEntity extends Mob {

    public LeashHolderEntity(PlayMessages.SpawnEntity packet, Level world) {
        super(CannonModEntities.LEASH_HOLDER.get(), world);
    }

    public LeashHolderEntity(EntityType<? extends Mob> type, Level world) {
        super(type, world);
        this.setNoAi(true);        // Prevent AI movement
        this.setNoGravity(true);   // Prevent falling
        this.setPersistenceRequired(); // Keep it in memory
        this.noPhysics = true; // ✅ Prevent suffocation by enabling no-clip
    }

    // ✅ Prevent suffocation damage
    @Override
    public boolean hurt(DamageSource source, float amount) {
        // Allow damage only from players
        if (source.getEntity() instanceof Player) {
            return super.hurt(source, amount);
        }
        return false; // Ignore other damage (e.g., suffocation)
    }

    @Override
    public boolean canBeCollidedWith() {
        return false; // Disables collision
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public SoundEvent getHurtSound(DamageSource ds) {
        return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.hurt"));
    }

    @Override
    public SoundEvent getDeathSound() {
        return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.death"));
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        return EntityDimensions.fixed(0.01F, 0.01F);
    }

    @Override
    public void push(double x, double y, double z) {
        // Prevent physical collision behavior
    }

    @Override
    public void die(DamageSource source) {
        super.die(source);
        // If this entity dies, find and kill the other LeashHolderEntity nearby
        Level world = this.level();
        world.getEntities(this, this.getBoundingBox().inflate(2.0)) // Find entities in a small radius
            .stream()
            .filter(entity -> entity instanceof LeashHolderEntity && entity != this)
            .forEach(entity -> entity.kill()); // Kill the other leash holder
    }

    public static void init() {
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder.add(Attributes.MAX_HEALTH, 1.0); // 1 HP - dies in one hit
        builder.add(Attributes.MOVEMENT_SPEED, 0.0);
        return builder;
    }
}
