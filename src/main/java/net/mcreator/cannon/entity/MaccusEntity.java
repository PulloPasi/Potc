
package net.mcreator.cannon.entity;

import software.bernie.geckolib.util.GeckoLibUtil;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.animatable.GeoEntity;

import org.valkyrienskies.core.impl.shadow.ds;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.network.NetworkHooks;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

import net.mcreator.cannon.init.CannonModEntities;

public class MaccusEntity extends Monster implements GeoEntity {
	public static final EntityDataAccessor<Boolean> SHOOT = SynchedEntityData.defineId(MaccusEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<String> ANIMATION = SynchedEntityData.defineId(MaccusEntity.class, EntityDataSerializers.STRING);
	public static final EntityDataAccessor<String> TEXTURE = SynchedEntityData.defineId(MaccusEntity.class, EntityDataSerializers.STRING);
	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
	private boolean swinging;
	private boolean lastloop;
	private long lastSwing;
	public String animationprocedure = "empty";
	private int actionTicks;
	private int roarCooldown;
	private int callCooldown;
	private LivingEntity pendingAttackTarget;
	private int attackDamageDelay;
	private int pendingSummons;
	private int summonDelay;
	private static final double BASE_SPEED = 0.3D;
	private static final double AGGRO_SPEED = 0.36D;

	public MaccusEntity(PlayMessages.SpawnEntity packet, Level world) {
		this(CannonModEntities.MACCUS.get(), world);
	}

	public MaccusEntity(EntityType<MaccusEntity> type, Level world) {
		super(type, world);
		xpReward = 100;
		setNoAi(false);
		setMaxUpStep(0.6f);
		setPersistenceRequired();
		this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
		this.setPathfindingMalus(BlockPathTypes.WATER_BORDER, 0.0F);
		this.roarCooldown = 80;
		this.callCooldown = 60;
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(SHOOT, false);
		this.entityData.define(ANIMATION, "undefined");
		this.entityData.define(TEXTURE, "maccustexture");
	}

	public void setTexture(String texture) {
		this.entityData.set(TEXTURE, texture);
	}

	public String getTexture() {
		return this.entityData.get(TEXTURE);
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new MaccusMeleeAttackGoal(this));
		this.goalSelector.addGoal(2, new RoarGoal());
		this.goalSelector.addGoal(3, new CallCrewGoal());
		this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 0.8));
		this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
	}

	@Override
	public MobType getMobType() {
		return MobType.UNDEFINED;
	}

	protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHitIn) {
		super.dropCustomDeathLoot(source, looting, recentlyHitIn);
		this.spawnAtLocation(new ItemStack(Blocks.DEAD_HORN_CORAL_BLOCK));
	}

	@Override
	public SoundEvent getAmbientSound() {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.drowned.ambient_water"));
	}

	@Override
	public void playStepSound(BlockPos pos, BlockState blockIn) {
		this.playSound(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.nether_wood.step")), 0.15f, 1);
	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.drowned.hurt"));
	}

	@Override
	public SoundEvent getDeathSound() {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.drowned.death"));
	}

	@Override
	public boolean isInvulnerableTo(DamageSource source) {
		if (source.is(DamageTypes.DROWN))
			return true;
		return super.isInvulnerableTo(source);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putString("Texture", this.getTexture());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("Texture"))
			this.setTexture(compound.getString("Texture"));
	}

	@Override
	public void baseTick() {
		super.baseTick();
		this.refreshDimensions();
	}

	@Override
	public void aiStep() {
		super.aiStep();
		if (this.actionTicks > 0) {
			this.actionTicks--;
		}
		if (this.attackDamageDelay > 0) {
			this.attackDamageDelay--;
			if (this.attackDamageDelay == 0 && !this.level().isClientSide) {
				this.finishPendingAttack();
			}
		}
		if (!this.level().isClientSide) {
			this.updateAggroSpeed();
		}
		if (!this.level().isClientSide) {
			if (this.roarCooldown > 0) {
				this.roarCooldown--;
			}
			if (this.callCooldown > 0) {
				this.callCooldown--;
			}
			if (this.pendingSummons > 0) {
				if (this.summonDelay > 0) {
					this.summonDelay--;
				} else {
					ServerLevel serverLevel = (ServerLevel) this.level();
					this.summonCrewMember(serverLevel);
					this.pendingSummons--;
					if (this.pendingSummons > 0) {
						this.summonDelay = 10;
					}
				}
			}
		}
	}

	private void updateAggroSpeed() {
		var speedAttr = this.getAttribute(Attributes.MOVEMENT_SPEED);
		if (speedAttr == null) {
			return;
		}
		boolean aggro = this.getTarget() != null && (!this.isPerformingAction() || this.pendingAttackTarget != null);
		double desired = aggro ? AGGRO_SPEED : BASE_SPEED;
		if (Math.abs(speedAttr.getBaseValue() - desired) > 1.0E-4D) {
			speedAttr.setBaseValue(desired);
		}
	}

	@Override
	public EntityDimensions getDimensions(Pose p_33597_) {
		return super.getDimensions(p_33597_).scale((float) 1);
	}

	public static void init() {
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, BASE_SPEED);
		builder = builder.add(Attributes.MAX_HEALTH, 120);
		builder = builder.add(Attributes.ARMOR, 6);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 30);
		builder = builder.add(Attributes.ATTACK_KNOCKBACK, 1.2);
		builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 0.7);
		builder = builder.add(Attributes.FOLLOW_RANGE, 32);
		return builder;
	}

	private PlayState movementPredicate(AnimationState event) {
		if (!this.animationprocedure.equals("empty")) {
			return PlayState.STOP;
		}

		boolean aggro = this.getTarget() != null;
		boolean moving = this.getDeltaMovement().horizontalDistanceSqr() > 0.0025D;
		if (aggro) {
			if (moving) {
				event.setAndContinue(RawAnimation.begin().thenLoop("run"));
			} else {
				event.setAndContinue(RawAnimation.begin().thenLoop("idle"));
			}
		} else if (event.isMoving()) {
			event.setAndContinue(RawAnimation.begin().thenLoop("walk"));
		} else {
			event.setAndContinue(RawAnimation.begin().thenLoop("idle"));
		}
		return PlayState.CONTINUE;
	}

	String prevAnim = "empty";

	private PlayState procedurePredicate(AnimationState event) {
		if (!animationprocedure.equals("empty") && event.getController().getAnimationState() == AnimationController.State.STOPPED || (!this.animationprocedure.equals(prevAnim) && !this.animationprocedure.equals("empty"))) {
			if (!this.animationprocedure.equals(prevAnim))
				event.getController().forceAnimationReset();
			event.getController().setAnimation(RawAnimation.begin().thenPlay(this.animationprocedure));
			if (event.getController().getAnimationState() == AnimationController.State.STOPPED) {
				this.animationprocedure = "empty";
				event.getController().forceAnimationReset();
			}
		} else if (animationprocedure.equals("empty")) {
			prevAnim = "empty";
			return PlayState.STOP;
		}
		prevAnim = this.animationprocedure;
		return PlayState.CONTINUE;
	}

	@Override
	protected void tickDeath() {
		++this.deathTime;
		if (this.deathTime == 20) {
			this.remove(MaccusEntity.RemovalReason.KILLED);
			this.dropExperience();
		}
	}

	public String getSyncedAnimation() {
		return this.entityData.get(ANIMATION);
	}

	public void setAnimation(String animation) {
		this.entityData.set(ANIMATION, animation);
	}

	private void setAnimationProcedure(String animation) {
		this.animationprocedure = animation;
		this.setAnimation(animation);
	}

	private void startAction(int ticks) {
		this.actionTicks = Math.max(this.actionTicks, ticks);
	}

	public boolean isPerformingAction() {
		return this.actionTicks > 0;
	}

	private void initiateAttack(LivingEntity target) {
		boolean attackOne = this.getRandom().nextBoolean();
		String anim = attackOne ? "attack1" : "attack2";
		int damageDelay = attackOne ? 28 : 26;
		int duration = damageDelay + 4;

		this.pendingAttackTarget = target;
		this.attackDamageDelay = damageDelay;
		setAnimationProcedure(anim);
		startAction(duration);
		this.getNavigation().stop();
	}

	private void finishPendingAttack() {
		if (this.pendingAttackTarget == null) {
			return;
		}
		LivingEntity target = this.pendingAttackTarget;
		this.pendingAttackTarget = null;
		if (!target.isAlive()) {
			return;
		}
		if (this.distanceToSqr(target) > this.getAttackReachSqr(target)) {
			return;
		}
		this.doHurtTarget(target);
		if (target instanceof Player player) {
			player.disableShield(true);
		}
		this.level().broadcastEntityEvent(this, (byte) 4);
		this.actionTicks = 0;
		if (this.getTarget() != null) {
			this.getNavigation().moveTo(this.getTarget(), 1.35D);
		}
	}

	private double getAttackReachSqr(LivingEntity target) {
		return (double) (this.getBbWidth() * this.getBbWidth() * 2.0F) + target.getBbWidth();
	}

	private void performRoar() {
		if (this.level().isClientSide) {
			return;
		}
		setAnimationProcedure("roar");
		startAction(32);
		this.roarCooldown = 200;
		ServerLevel serverLevel = (ServerLevel) this.level();
		serverLevel.playSound(null, this.blockPosition(), SoundEvents.WARDEN_ROAR, SoundSource.HOSTILE, 1.6F, 0.85F + this.getRandom().nextFloat() * 0.2F);
		for (LivingEntity target : serverLevel.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(6.0D, 2.0D, 6.0D), e -> e != this && !(e instanceof MaccusEntity))) {
			if (this.isAlliedTo(target)) {
				continue;
			}
			target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 80, 1, false, true));
			Vec3 push = target.position().subtract(this.position()).normalize().scale(0.5D);
			target.push(push.x, 0.25D, push.z);
		}
	}

	private void performCrewCall() {
		if (this.level().isClientSide) {
			return;
		}
		setAnimationProcedure("call");
		startAction(50);
		this.callCooldown = 260;
		this.pendingSummons = 2;
		this.summonDelay = 20;
		ServerLevel serverLevel = (ServerLevel) this.level();
		serverLevel.playSound(null, this.blockPosition(), SoundEvents.EVOKER_PREPARE_SUMMON, SoundSource.HOSTILE, 1.5F, 0.8F + this.getRandom().nextFloat() * 0.2F);
	}

	private void summonCrewMember(ServerLevel serverLevel) {
		double offsetRadius = 2.5D + this.getRandom().nextDouble() * 1.5D;
		double angle = this.getRandom().nextDouble() * Math.PI * 2;
		double spawnX = this.getX() + Math.cos(angle) * offsetRadius;
		double spawnZ = this.getZ() + Math.sin(angle) * offsetRadius;
		double spawnY = this.getY();

		var crewType = this.getRandom().nextFloat() < 0.25F ? CannonModEntities.PART_OF_THE_CREW_STRONG : CannonModEntities.PART_OF_THE_CREW;
		Monster crew = crewType.get().create(serverLevel);
		if (crew == null) {
			return;
		}

		crew.moveTo(spawnX, spawnY, spawnZ, this.getRandom().nextFloat() * 360.0F, 0.0F);
		crew.finalizeSpawn(serverLevel, serverLevel.getCurrentDifficultyAt(crew.blockPosition()), MobSpawnType.EVENT, (SpawnGroupData) null, null);
		crew.setTarget(this.getTarget());
		serverLevel.addFreshEntity(crew);
	}

	public boolean canRoar() {
		return this.roarCooldown <= 0 && !this.isPerformingAction();
	}

	public boolean canCallCrew() {
		return this.callCooldown <= 0 && !this.isPerformingAction() && this.getTarget() != null;
	}

	public void resetRoarCooldown() {
		this.roarCooldown = 200;
	}

	public void resetCallCooldown() {
		this.callCooldown = 400;
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar data) {
		data.add(new AnimationController<>(this, "movement", 4, this::movementPredicate));
		data.add(new AnimationController<>(this, "procedure", 4, this::procedurePredicate));
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.cache;
	}

	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}

	@Override
	public boolean isPushedByFluid() {
		return false;
	}

	@Override
	public void travel(Vec3 travelVector) {
		if (this.isEffectiveAi() && this.isInWater()) {
			this.moveRelative(0.08F, travelVector);
			this.move(MoverType.SELF, this.getDeltaMovement());
			this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
		} else {
			super.travel(travelVector);
		}
	}

	private class MaccusMeleeAttackGoal extends MeleeAttackGoal {
		private final MaccusEntity maccus;

		public MaccusMeleeAttackGoal(MaccusEntity mob) {
			super(mob, 1.0D, true);
			this.maccus = mob;
		}

		@Override
		public boolean canUse() {
			return !this.maccus.isPerformingAction() && !this.maccus.canCallCrew() && super.canUse();
		}

		@Override
		public boolean canContinueToUse() {
			return !this.maccus.isPerformingAction() && !this.maccus.canCallCrew() && super.canContinueToUse();
		}

		@Override
		protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr) {
			double reach = this.getAttackReachSqr(enemy);
			if (distToEnemySqr <= reach && this.isTimeToAttack()) {
				this.resetAttackCooldown();
				this.maccus.initiateAttack(enemy);
			}
		}

		@Override
		public void start() {
			super.start();
			this.maccus.setAggressive(true);
		}

		@Override
		public void stop() {
			super.stop();
			this.maccus.setAggressive(false);
		}

		@Override
		protected double getAttackReachSqr(LivingEntity entity) {
			return (double) (this.maccus.getBbWidth() * this.maccus.getBbWidth() * 2.0F) + entity.getBbWidth();
		}
	}

	private class RoarGoal extends Goal {
		private static final int WINDUP_TICKS = 20;
		private int ticks;

		public RoarGoal() {
			this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK, Flag.JUMP));
		}

		@Override
		public boolean canUse() {
			return MaccusEntity.this.getTarget() != null && MaccusEntity.this.canRoar();
		}

		@Override
		public boolean canContinueToUse() {
			return this.ticks > 0;
		}

		@Override
		public void start() {
			this.ticks = WINDUP_TICKS;
			MaccusEntity.this.getNavigation().stop();
			MaccusEntity.this.startAction(WINDUP_TICKS + 12);
			MaccusEntity.this.setAnimationProcedure("roar");
			MaccusEntity.this.resetRoarCooldown();
			if (!MaccusEntity.this.level().isClientSide) {
				((ServerLevel) MaccusEntity.this.level()).playSound(null, MaccusEntity.this.blockPosition(), SoundEvents.WARDEN_ROAR, SoundSource.HOSTILE, 1.6F, 0.85F + MaccusEntity.this.getRandom().nextFloat() * 0.2F);
			}
		}

		@Override
		public void tick() {
			LivingEntity target = MaccusEntity.this.getTarget();
			if (target != null) {
				MaccusEntity.this.getLookControl().setLookAt(target);
				if (this.ticks == 5 && !MaccusEntity.this.level().isClientSide) {
					MaccusEntity.this.performRoar();
				}
			}
			this.ticks--;
		}
	}

	private class CallCrewGoal extends Goal {
		private int timeRemaining;

		public CallCrewGoal() {
			this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
		}

		@Override
		public boolean canUse() {
			return MaccusEntity.this.canCallCrew();
		}

		@Override
		public boolean canContinueToUse() {
			return this.timeRemaining > 0 && MaccusEntity.this.isPerformingAction();
		}

		@Override
		public void start() {
			this.timeRemaining = 50;
			MaccusEntity.this.getNavigation().stop();
			MaccusEntity.this.performCrewCall();
		}

		@Override
		public void tick() {
			LivingEntity target = MaccusEntity.this.getTarget();
			if (target != null) {
				MaccusEntity.this.getLookControl().setLookAt(target);
			}
			if (this.timeRemaining > 0) {
				this.timeRemaining--;
			}
		}
	}
}
