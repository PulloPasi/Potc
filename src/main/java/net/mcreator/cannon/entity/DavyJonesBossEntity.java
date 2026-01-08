package net.mcreator.cannon.entity;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Nullable;

import software.bernie.geckolib.util.GeckoLibUtil;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.GeoEntity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.network.NetworkHooks;

import net.mcreator.cannon.CannonMod;
import net.mcreator.cannon.entity.MaccusEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.level.Level;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundStopSoundPacket;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.phys.Vec3;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.core.particles.ParticleTypes;

import net.mcreator.cannon.init.CannonModEntities;
import net.mcreator.cannon.init.CannonModItems;
import net.mcreator.cannon.init.CannonModSounds;
import net.mcreator.cannon.entity.FBulletEntity;

public class DavyJonesBossEntity extends Monster implements GeoEntity {
	public static final EntityDataAccessor<Boolean> SHOOT = SynchedEntityData.defineId(DavyJonesBossEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<String> ANIMATION = SynchedEntityData.defineId(DavyJonesBossEntity.class, EntityDataSerializers.STRING);
	public static final EntityDataAccessor<String> TEXTURE = SynchedEntityData.defineId(DavyJonesBossEntity.class, EntityDataSerializers.STRING);
	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
	public String animationprocedure = "empty";
	private final ServerBossEvent bossInfo = new ServerBossEvent(this.getDisplayName(), ServerBossEvent.BossBarColor.GREEN, ServerBossEvent.BossBarOverlay.PROGRESS);
	@Nullable
	private String activeAttackAnimation;
	private int attackAnimationEndTick;
	private int counterKickCooldown;
	private boolean keyDropped;
	private boolean heartLockBroken;
	public static final double HEART_KILL_RADIUS = 50.0D;
	private String prevAnim = "empty";
	private boolean maccusSummoned;
	private int rangedCooldown;
	private int volleyShotsRemaining;
	private int volleyShotDelay;
	private int shockwaveCooldown;
	private int shockwaveTimer;
	private int antiBurrowCooldown;
	private MusicPhase currentMusicPhase = MusicPhase.NONE;
	private int musicLoopTicks;
	private boolean introPlayed;
	private boolean battleStormTriggered;
	private int comboStartTick;
	private int comboSequence;
	private int finisherSequence;
	private int counterSequence;
	private int shockwaveSequence;
	private int volleySequence;

	private enum MusicPhase {
		NONE,
		PHASE1,
		PHASE2,
		DEATH
	}

	private void handleAmbientShockwave() {
		if (this.currentMusicPhase == MusicPhase.DEATH)
			return;
		if (this.shockwaveTimer > 0) {
			this.shockwaveTimer--;
		}
		if (this.shockwaveTimer <= 0 && this.getRandom().nextFloat() < 0.45F) {
			this.tryShockwave();
		} else if (this.shockwaveTimer <= 0) {
			this.resetShockwaveTimer();
		}
	}

	private void handleAntiBurrow() {
		if (this.level().isClientSide)
			return;
		if (this.antiBurrowCooldown > 0) {
			this.antiBurrowCooldown--;
			return;
		}
		LivingEntity target = this.getTarget();
		if (target == null)
			return;
		double distance = this.distanceTo(target);
		boolean targetAbove = target.getY() - this.getY() > 1.4D;
		boolean stuck = this.horizontalCollision || this.isInWall();
		if (this.isAttackAnimationActive())
			return;
		if (distance <= 6.0D && (stuck || targetAbove)) {
			Vec3 escape = this.findTeleportNearTarget(target);
			if (escape == null)
				escape = this.findRisePosition();
			if (escape != null) {
				this.playAnimation("teleport");
				this.teleportTo(escape.x, escape.y, escape.z);
				this.getNavigation().stop();
				this.antiBurrowCooldown = ANTI_BURROW_COOLDOWN_MAX;
			}
		}
	}

	@Nullable
	private Vec3 findRisePosition() {
		BlockPos base = this.blockPosition();
		for (int dy = 1; dy <= 4; dy++) {
			BlockPos pos = base.above(dy);
			if (this.level().isEmptyBlock(pos) && this.level().isEmptyBlock(pos.above())) {
				return new Vec3(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D);
			}
		}
		return null;
	}

	private void resetShockwaveTimer() {
		this.shockwaveTimer = SHOCKWAVE_BASE_INTERVAL + this.getRandom().nextInt(SHOCKWAVE_RANDOM_EXTRA + 1);
	}

	private static final int PHASE1_LOOP_TICKS = 20 * 64;
	private static final int PHASE2_LOOP_TICKS = 20 * 50;
	private static final int SHOCKWAVE_BASE_INTERVAL = 20 * 7;
	private static final int SHOCKWAVE_RANDOM_EXTRA = 20 * 5;
	private static final int SHOCKWAVE_RING_COUNT = 6;
	private static final int SHOCKWAVE_RING_DELAY = 10;
	private static final double SHOCKWAVE_RING_STEP = 2.5D;
	private static final double SHOCKWAVE_INITIAL_RADIUS = 0.75D;
	private static final int ANTI_BURROW_COOLDOWN_MAX = 100;
	private static final int[] COMBO_STRIKE_TICKS = {8, 15, 27};
	private static final int[] COMBO_STEP_INTERVAL_TICKS = {9, 13};
	private static final int COMBO_FINISHER_STRIKE_TICKS = 5;
	private static final int COUNTER_KICK_STRIKE_TICKS = 7;
	private static final int SHOCKWAVE_STRIKE_TICKS = 19;
	private static final int[] PISTOL_VOLLEY_STRIKE_TICKS = {27, 45, 67};
	private static final int ANIM_COMBOABC_TICKS = 36;
	private static final int ANIM_COMBO_FINISHER_TICKS = 15;
	private static final int ANIM_COUNTER_KICK_TICKS = 15;
	private static final int ANIM_SHOCKWAVE_TICKS = 25;
	private static final int ANIM_PISTOL_VOLLEY_TICKS = 87;
	private static final float[] COMBO_DAMAGE_MULTIPLIERS = {1.25F, 1.6F, 2.0F};
	private static final float FINISHER_DAMAGE_MULTIPLIER = 2.6F;
	private static final float SHOCKWAVE_DAMAGE = 9.0F;

	public DavyJonesBossEntity(PlayMessages.SpawnEntity packet, Level world) {
		this(CannonModEntities.DAVY_JONES_BOSS.get(), world);
	}

	private void trySummonMaccus() {
		if (!(this.level() instanceof ServerLevel serverLevel))
			return;
		if (this.isAttackAnimationActive())
			return;
		MaccusEntity maccus = CannonModEntities.MACCUS.get().create(serverLevel);
		if (maccus == null)
			return;
		Vec3 spawnPos = this.findAssistSpawnPosition();
		maccus.moveTo(spawnPos.x, spawnPos.y, spawnPos.z, this.getRandom().nextFloat() * 360.0F, 0.0F);
		maccus.finalizeSpawn(serverLevel, serverLevel.getCurrentDifficultyAt(maccus.blockPosition()), MobSpawnType.EVENT, (SpawnGroupData) null, null);
		if (this.getTarget() != null) {
			maccus.setTarget(this.getTarget());
		}
		serverLevel.addFreshEntity(maccus);
		this.maccusSummoned = true;
		this.playAnimation("summonmaccus");
	}

	private Vec3 findAssistSpawnPosition() {
		Vec3 origin = this.position();
		Vec3 offset = new Vec3(3.0D, 0.0D, 0.0D);
		for (int i = 0; i < 8; i++) {
			double angle = (Math.PI / 4) * i;
			Vec3 rotated = new Vec3(offset.x * Math.cos(angle) - offset.z * Math.sin(angle), 0.0D,
					offset.x * Math.sin(angle) + offset.z * Math.cos(angle));
			BlockPos tryPos = BlockPos.containing(origin.add(rotated));
			BlockPos ground = tryGround(tryPos);
			if (ground != null)
				return new Vec3(ground.getX() + 0.5D, ground.getY(), ground.getZ() + 0.5D);
		}
		return origin;
	}

	@Nullable
	private BlockPos tryGround(BlockPos pos) {
		Level level = this.level();
		BlockPos below = pos.below();
		if (level.isEmptyBlock(pos) && level.getBlockState(below).isFaceSturdy(level, below, Direction.UP))
			return pos;
		return null;
	}

	private void handleRangedFallback(LivingEntity target, double distanceSqr) {
		if (this.level().isClientSide)
			return;
		if (this.volleyShotsRemaining > 0) {
			this.getNavigation().stop();
			return;
		}
		if (this.isAttackAnimationActive())
			return;
		if (this.rangedCooldown > 0)
			return;
		double distance = Math.sqrt(distanceSqr);
		if (distance >= 12.0D) {
			Vec3 teleportSpot = this.findTeleportNearTarget(target);
			if (teleportSpot != null) {
				this.playAnimation("teleport");
				this.teleportTo(teleportSpot.x, teleportSpot.y, teleportSpot.z);
				this.getNavigation().stop();
				this.rangedCooldown = 60;
				return;
			}
		}
		if (distance >= 5.0D && distance <= 10.5D) {
			if (this.getRandom().nextFloat() < 0.4F) {
				this.startFBulletVolley();
				return;
			}
			this.rangedCooldown = 30;
		}
	}

	private void startFBulletVolley() {
		if (this.level().isClientSide)
			return;
		if (this.volleyShotsRemaining > 0)
			return;
		if (this.isAttackAnimationActive())
			return;
		this.volleySequence++;
		int volleyId = this.volleySequence;
		this.volleyShotsRemaining = PISTOL_VOLLEY_STRIKE_TICKS.length;
		this.volleyShotDelay = 0;
		this.rangedCooldown = 120;
		this.playAttackAnimation("pistolvolley", ANIM_PISTOL_VOLLEY_TICKS);
		for (int delay : PISTOL_VOLLEY_STRIKE_TICKS) {
			this.scheduleServerAction(delay, () -> this.fireVolleyShot(volleyId));
		}
	}

	private void shootFBullet(LivingEntity target) {
		if (this.level().isClientSide)
			return;
		FBulletEntity.shoot(this, target);
	}

	@Nullable
	private Vec3 findTeleportNearTarget(LivingEntity target) {
		Vec3 look = target.getLookAngle();
		if (look.lengthSqr() < 1.0E-4) {
			look = target.position().subtract(this.position()).normalize();
		}
		if (look.lengthSqr() < 1.0E-4) {
			look = new Vec3(0.0D, 0.0D, 1.0D);
		}
		double[] angles = {0.0D, 30.0D, -30.0D, 60.0D, -60.0D, 90.0D, -90.0D, 120.0D, -120.0D};
		for (double angle : angles) {
			Vec3 rotated = rotateAroundY(look, angle).normalize().scale(2.2D);
			Vec3 candidate = target.position().add(rotated);
			BlockPos blockPos = BlockPos.containing(candidate);
			Level level = this.level();
			if (!level.isEmptyBlock(blockPos) || !level.isEmptyBlock(blockPos.above()))
				continue;
			BlockPos below = blockPos.below();
			if (!level.getBlockState(below).isFaceSturdy(level, below, Direction.UP))
				continue;
			Vec3 destination = new Vec3(blockPos.getX() + 0.5D, blockPos.getY(), blockPos.getZ() + 0.5D);
			if (level.noCollision(this, this.getBoundingBox().move(destination.subtract(this.position())))) {
				return destination;
			}
		}
		return null;
	}

	private Vec3 rotateAroundY(Vec3 vector, double degrees) {
		double radians = Math.toRadians(degrees);
		double sin = Math.sin(radians);
		double cos = Math.cos(radians);
		double x = vector.x * cos - vector.z * sin;
		double z = vector.x * sin + vector.z * cos;
		return new Vec3(x, vector.y, z);
	}

	private boolean tryShockwave() {
		if (this.level().isClientSide)
			return false;
		if (this.shockwaveCooldown > 0)
			return false;
		if (!(this.level() instanceof ServerLevel serverLevel))
			return false;
		if (this.isAttackAnimationActive())
			return false;
		this.shockwaveCooldown = 200;
		this.resetShockwaveTimer();
		this.playAttackAnimation("shockwave", ANIM_SHOCKWAVE_TICKS);
		this.shockwaveSequence++;
		int shockwaveId = this.shockwaveSequence;
		this.scheduleServerAction(SHOCKWAVE_STRIKE_TICKS, () -> this.executeShockwave(serverLevel, shockwaveId));
		return true;
	}

	private void startShockwaveWave(ServerLevel serverLevel) {
		final Set<UUID> affectedPlayers = new HashSet<>();
		for (int ring = 0; ring < SHOCKWAVE_RING_COUNT; ring++) {
			int delay = ring * SHOCKWAVE_RING_DELAY;
			int ringIndex = ring;
			CannonMod.queueServerWork(delay, () -> this.emitShockwaveRing(serverLevel, ringIndex, affectedPlayers));
		}
	}

	private void emitShockwaveRing(ServerLevel serverLevel, int ringIndex, Set<UUID> affectedPlayers) {
		if (!this.isAlive())
			return;
		double baseRadius = SHOCKWAVE_INITIAL_RADIUS + ringIndex * SHOCKWAVE_RING_STEP;
		double innerRadius = Math.max(0.0D, baseRadius - SHOCKWAVE_RING_STEP);
		int particleCount = 48 + ringIndex * 12;
		double angleStep = (Math.PI * 2) / particleCount;
		for (int i = 0; i < particleCount; i++) {
			double angle = i * angleStep;
			double x = this.getX() + Math.cos(angle) * baseRadius;
			double z = this.getZ() + Math.sin(angle) * baseRadius;
			serverLevel.sendParticles(ParticleTypes.CLOUD, x, this.getY() + 0.2D, z, 1, 0.0D, 0.01D, 0.0D, 0.01D);
		}
		for (ServerPlayer player : serverLevel.players()) {
			if (player.isSpectator())
				continue;
			double distanceSqr = player.distanceToSqr(this);
			if (distanceSqr > baseRadius * baseRadius || distanceSqr < innerRadius * innerRadius)
				continue;
			if (!affectedPlayers.add(player.getUUID()))
				continue;
			Vec3 knock = player.position().subtract(this.position()).normalize();
			player.push(knock.x * 0.45D, 0.3D, knock.z * 0.45D);
			player.hurt(this.damageSources().mobAttack(this), 6.0F);
		}
	}

	private void performTentacleComboHit(LivingEntity target, int step) {
		if (target == null || this.level().isClientSide)
			return;
		if (step == 0 && this.isAttackAnimationActive())
			return;
		if (step == 0) {
			this.comboStartTick = this.tickCount;
			this.comboSequence++;
			this.playAttackAnimation("comboabc", ANIM_COMBOABC_TICKS);
		}
		int comboId = this.comboSequence;
		int desiredTick = COMBO_STRIKE_TICKS[Math.min(step, COMBO_STRIKE_TICKS.length - 1)];
		int elapsed = this.tickCount - this.comboStartTick;
		int delay = Math.max(0, desiredTick - elapsed);
		float baseDamage = (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
		float multiplier = COMBO_DAMAGE_MULTIPLIERS[Math.min(step, COMBO_DAMAGE_MULTIPLIERS.length - 1)];
		float damage = baseDamage * multiplier;
		double knockback = 0.3D + step * 0.15D;
		this.scheduleServerAction(delay, () -> this.executeComboHit(target, damage, knockback, comboId));
	}

	private void performComboFinisher(LivingEntity target) {
		if (target == null || this.level().isClientSide)
			return;
		if (!this.hasLineOfSight(target))
			return;
		if (this.isAttackAnimationActive())
			return;
		this.playAttackAnimation("combofinisher", ANIM_COMBO_FINISHER_TICKS);
		this.finisherSequence++;
		int finisherId = this.finisherSequence;
		float baseDamage = (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
		float damage = baseDamage * FINISHER_DAMAGE_MULTIPLIER;
		this.scheduleServerAction(COMBO_FINISHER_STRIKE_TICKS, () -> this.executeFinisher(target, damage, finisherId));
	}

	private void performCounterKick(LivingEntity attacker) {
		if (attacker == null || this.level().isClientSide)
			return;
		if (this.distanceToSqr(attacker) > 9.0D)
			return;
		if (this.isAttackAnimationActive())
			return;
		this.playAttackAnimation("counterkick", ANIM_COUNTER_KICK_TICKS);
		this.counterSequence++;
		int counterId = this.counterSequence;
		float damage = (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE) * 2.0F;
		double knockback = 1.8D;
		this.scheduleServerAction(COUNTER_KICK_STRIKE_TICKS, () -> this.executeCounterKick(attacker, damage, knockback, counterId));
	}

	private void executeComboHit(@Nullable LivingEntity target, float damage, double knockback, int comboId) {
		if (comboId != this.comboSequence)
			return;
		if (target == null || !target.isAlive())
			return;
		if (!this.hasLineOfSight(target))
			return;
		DamageSource damageSource = this.damageSources().mobAttack(this);
		this.swing(InteractionHand.MAIN_HAND);
		if (target.hurt(damageSource, damage)) {
			target.knockback(knockback, this.getX() - target.getX(), this.getZ() - target.getZ());
		}
	}

	private void executeFinisher(@Nullable LivingEntity target, float damage, int finisherId) {
		if (finisherId != this.finisherSequence)
			return;
		if (target == null || !target.isAlive())
			return;
		if (!this.hasLineOfSight(target))
			return;
		DamageSource damageSource = this.damageSources().mobAttack(this);
		this.swing(InteractionHand.MAIN_HAND);
		if (target.hurt(damageSource, damage)) {
			target.knockback(1.1D, this.getX() - target.getX(), this.getZ() - target.getZ());
			target.setDeltaMovement(target.getDeltaMovement().add(0.0D, 0.25D, 0.0D));
		}
	}

	private void executeCounterKick(@Nullable LivingEntity attacker, float damage, double knockback, int counterId) {
		if (counterId != this.counterSequence)
			return;
		if (attacker == null || !attacker.isAlive())
			return;
		DamageSource damageSource = this.damageSources().mobAttack(this);
		this.swing(InteractionHand.OFF_HAND);
		if (attacker.hurt(damageSource, damage)) {
			attacker.knockback(knockback, this.getX() - attacker.getX(), this.getZ() - attacker.getZ());
			attacker.setDeltaMovement(attacker.getDeltaMovement().add(0.0D, 0.3D, 0.0D));
			if (attacker instanceof Player player) {
				player.disableShield(true);
			}
		}
	}

	private void executeShockwave(ServerLevel serverLevel, int shockwaveId) {
		if (shockwaveId != this.shockwaveSequence)
			return;
		if (!this.isAlive())
			return;
		List<ServerPlayer> closePlayers = serverLevel.getPlayers(player -> !player.isSpectator() && player.distanceTo(this) <= 2.5D);
		for (ServerPlayer player : closePlayers) {
			Vec3 knock = player.position().subtract(this.position()).normalize();
			player.push(knock.x * 0.5D, 0.45D, knock.z * 0.5D);
			player.hurt(this.damageSources().mobAttack(this), SHOCKWAVE_DAMAGE);
		}
		this.startShockwaveWave(serverLevel);
	}

	private void fireVolleyShot(int volleyId) {
		if (volleyId != this.volleySequence)
			return;
		if (this.volleyShotsRemaining <= 0)
			return;
		LivingEntity target = this.getTarget();
		if (target == null || !target.isAlive()) {
			this.volleyShotsRemaining = 0;
			return;
		}
		if (this.level().isClientSide)
			return;
		FBulletEntity.shoot(this, target);
		this.volleyShotsRemaining--;
	}

	private void playAttackAnimation(String animation, int durationTicks) {
		this.activeAttackAnimation = animation;
		this.attackAnimationEndTick = this.tickCount + durationTicks;
		this.animationprocedure = animation;
		this.setAnimation(animation);
	}

	private void clearAttackAnimationLock() {
		this.activeAttackAnimation = null;
		this.attackAnimationEndTick = 0;
	}

	private void updateAttackAnimationState() {
		if (this.activeAttackAnimation == null)
			return;
		if (this.tickCount >= this.attackAnimationEndTick) {
			String finishedAnimation = this.activeAttackAnimation;
			this.clearAttackAnimationLock();
			if (this.animationprocedure.equals(finishedAnimation)) {
				this.animationprocedure = "empty";
				this.setAnimation("empty");
				this.prevAnim = "empty";
			}
		}
	}

	private boolean isAttackAnimationActive() {
		return this.activeAttackAnimation != null && this.tickCount < this.attackAnimationEndTick;
	}

	private void scheduleServerAction(int delayTicks, Runnable action) {
		if (delayTicks <= 0) {
			action.run();
		} else {
			CannonMod.queueServerWork(delayTicks, action);
		}
	}

	private static class TeleportToTargetGoal extends Goal {
		private static final double MIN_DISTANCE_SQR = 225.0D;
		private final DavyJonesBossEntity mob;
		private int cooldownTicks;
		@Nullable
		private Vec3 pendingTeleportPos;

		TeleportToTargetGoal(DavyJonesBossEntity mob) {
			this.mob = mob;
			this.setFlags(EnumSet.of(Goal.Flag.MOVE));
		}

		@Override
		public boolean canUse() {
			if (this.cooldownTicks > 0) {
				this.cooldownTicks--;
				return false;
			}
			LivingEntity target = this.mob.getTarget();
			if (target == null || !target.isAlive()) {
				return false;
			}

			double distanceSqr = this.mob.distanceToSqr(target);
			Path prospectivePath = this.mob.getNavigation().createPath(target, 0);
			boolean unreachable = prospectivePath == null || !prospectivePath.canReach();
			boolean tooFar = distanceSqr > MIN_DISTANCE_SQR;

			if (!tooFar && !unreachable) {
				return false;
			}

			Vec3 candidate = this.computeTeleportPos(target);
			if (candidate == null) {
				return false;
			}
			this.pendingTeleportPos = candidate;
			return true;
		}

		@Override
		public boolean canContinueToUse() {
			return false;
		}

		@Override
		public void start() {
			if (this.pendingTeleportPos != null) {
				this.mob.teleportTo(this.pendingTeleportPos.x, this.pendingTeleportPos.y, this.pendingTeleportPos.z);
				this.mob.getNavigation().stop();
				LivingEntity target = this.mob.getTarget();
				if (target != null) {
					this.mob.lookAt(target, 360.0F, 90.0F);
				}
			}
			this.pendingTeleportPos = null;
			this.cooldownTicks = Mth.nextInt(this.mob.getRandom(), 80, 140);
		}

		@Nullable
		private Vec3 computeTeleportPos(LivingEntity target) {
			Vec3 look = target.getLookAngle();
			if (look.lengthSqr() < 1.0E-4) {
				look = target.position().subtract(this.mob.position()).normalize();
			}
			if (look.lengthSqr() < 1.0E-4) {
				look = new Vec3(0.0D, 0.0D, 1.0D);
			}

			double[] angles = {0.0D, 30.0D, -30.0D, 60.0D, -60.0D, 90.0D, -90.0D, 120.0D, -120.0D, 150.0D, -150.0D, 180.0D};
			for (double angle : angles) {
				Vec3 rotated = this.rotateY(look, angle).normalize().scale(2.2D);
				Vec3 basePos = target.position().add(rotated);
				BlockPos blockPos = BlockPos.containing(basePos);
				BlockPos below = blockPos.below();
				Level level = this.mob.level();
				if (!this.isStandable(level.getBlockState(below), below)) {
					continue;
				}
				if (!level.isEmptyBlock(blockPos) || !level.isEmptyBlock(blockPos.above())) {
					continue;
				}
				Vec3 destination = new Vec3(blockPos.getX() + 0.5D, below.getY() + 1.0D, blockPos.getZ() + 0.5D);
				if (level.noCollision(this.mob, this.mob.getBoundingBox().move(destination.x - this.mob.getX(), destination.y - this.mob.getY(), destination.z - this.mob.getZ()))) {
					return destination;
				}
			}
			return null;
		}

		private Vec3 rotateY(Vec3 vector, double degrees) {
			double radians = Math.toRadians(degrees);
			double sin = Math.sin(radians);
			double cos = Math.cos(radians);
			double x = vector.x * cos - vector.z * sin;
			double z = vector.x * sin + vector.z * cos;
			return new Vec3(x, vector.y, z);
		}

		private boolean isStandable(BlockState state, BlockPos pos) {
			return state.isFaceSturdy(this.mob.level(), pos, Direction.UP);
		}
	}

	private static class RelentlessComboGoal extends Goal {
		private final DavyJonesBossEntity mob;
		private final double speedModifier;
		private int comboStep;
		private int ticksUntilNextStrike;
		private int postComboCooldown;

		RelentlessComboGoal(DavyJonesBossEntity mob, double speedModifier) {
			this.mob = mob;
			this.speedModifier = speedModifier;
			this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
		}

		@Override
		public boolean canUse() {
			LivingEntity target = this.mob.getTarget();
			return target != null && target.isAlive();
		}

		@Override
		public boolean canContinueToUse() {
			LivingEntity target = this.mob.getTarget();
			return target != null && target.isAlive();
		}

		@Override
		public void start() {
			this.comboStep = 0;
			this.ticksUntilNextStrike = 0;
			this.postComboCooldown = 0;
		}

		@Override
		public void stop() {
			this.mob.getNavigation().stop();
		}

		@Override
		public void tick() {
			LivingEntity target = this.mob.getTarget();
			if (target == null) {
				return;
			}

			this.mob.getLookControl().setLookAt(target, 60.0F, 30.0F);
			double distanceSqr = this.mob.distanceToSqr(target);
			this.mob.handleRangedFallback(target, distanceSqr);
			if (this.mob.volleyShotsRemaining > 0) {
				this.mob.getNavigation().stop();
				return;
			}
			double reach = this.getAttackReachSqr(target);
			if (distanceSqr > reach) {
				this.mob.getNavigation().moveTo(target, this.speedModifier);
				this.resetComboTiming();
				return;
			}

			this.mob.getNavigation().stop();
			if (this.postComboCooldown > 0) {
				this.postComboCooldown--;
				return;
			}

			if (this.ticksUntilNextStrike > 0) {
				this.ticksUntilNextStrike--;
				return;
			}

			if (this.comboStep < 3) {
				this.mob.performTentacleComboHit(target, this.comboStep);
				this.comboStep++;
				this.ticksUntilNextStrike = 5;
			} else {
				this.mob.performComboFinisher(target);
				this.comboStep = 0;
				this.postComboCooldown = 20 + this.mob.getRandom().nextInt(10);
			}
		}

		private void resetComboTiming() {
			this.comboStep = 0;
			this.ticksUntilNextStrike = 5;
		}

		private double getAttackReachSqr(LivingEntity target) {
			double baseReach = this.mob.getBbWidth() * this.mob.getBbWidth() + target.getBbWidth();
			return baseReach + 6.0D;
		}
	}

	public DavyJonesBossEntity(EntityType<DavyJonesBossEntity> type, Level world) {
		super(type, world);
		xpReward = 0;
		setNoAi(false);
		setMaxUpStep(0.6f);
		setPersistenceRequired();
		this.resetShockwaveTimer();
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(SHOOT, false);
		this.entityData.define(ANIMATION, "undefined");
		this.entityData.define(TEXTURE, "jonetsi_-_copy");
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
		this.goalSelector.addGoal(1, new RelentlessComboGoal(this, 1.15D));
		this.goalSelector.addGoal(2, new TeleportToTargetGoal(this));
		this.goalSelector.addGoal(3, new RandomStrollGoal(this, 1));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
		this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
		this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(5, new FloatGoal(this));
	}

	@Override
	public MobType getMobType() {
		return MobType.UNDEFINED;
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return false;
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
	public boolean hurt(DamageSource source, float amount) {
		if (source.is(DamageTypes.DROWN))
			return false;
		if (!this.level().isClientSide && !this.heartLockBroken) {
			float currentHealth = this.getHealth();
			float maxDeductible = currentHealth - 1.0F;
			if (maxDeductible <= 0.0F) {
				return false;
			}
			amount = Math.min(amount, maxDeductible);
		}
		boolean result = super.hurt(source, amount);
		if (result && !this.level().isClientSide) {
			if (!this.isDeadOrDying()) {
				this.playAnimation("hurt");
			}
			if (!this.keyDropped && this.getHealth() <= 1.0001F && !this.heartLockBroken) {
				this.keyDropped = true;
				this.spawnAtLocation(CannonModItems.KEY.get());
			}
			float halfHealthThreshold = this.getMaxHealth() * 0.5F;
			if (!this.maccusSummoned && this.getHealth() <= halfHealthThreshold) {
				this.trySummonMaccus();
			}
			LivingEntity attacker = null;
			if (source.getEntity() instanceof LivingEntity living) {
				attacker = living;
			} else if (source.getDirectEntity() instanceof LivingEntity living) {
				attacker = living;
			}
			if (attacker != null && this.counterKickCooldown <= 0 && this.random.nextFloat() < 0.35F) {
				this.performCounterKick(attacker);
				this.counterKickCooldown = 100;
			}
		}
		return result;
	}

	public void consumeHeartKill(@Nullable Player killer) {
		if (this.level().isClientSide || !this.isAlive())
			return;
		if (!this.heartLockBroken) {
		}
		this.heartLockBroken = true;
		float damage = this.getHealth() + 1000.0F;
		DamageSource source = killer != null ? this.damageSources().playerAttack(killer) : this.damageSources().magic();
		this.hurt(source, damage);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putString("Texture", this.getTexture());
		compound.putBoolean("KeyDropped", this.keyDropped);
		compound.putBoolean("HeartLockBroken", this.heartLockBroken);
		compound.putBoolean("MaccusSummoned", this.maccusSummoned);
		compound.putInt("RangedCooldown", this.rangedCooldown);
		compound.putInt("VolleyShotsRemaining", this.volleyShotsRemaining);
		compound.putInt("VolleyShotDelay", this.volleyShotDelay);
		compound.putInt("ShockwaveCooldown", this.shockwaveCooldown);
		compound.putInt("ShockwaveTimer", this.shockwaveTimer);
		compound.putInt("AntiBurrowCooldown", this.antiBurrowCooldown);
		compound.putInt("MusicPhase", this.currentMusicPhase.ordinal());
		compound.putInt("MusicLoopTicks", this.musicLoopTicks);
		compound.putBoolean("IntroPlayed", this.introPlayed);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("Texture")) {
			this.setTexture(compound.getString("Texture"));
		}
		if (compound.contains("KeyDropped")) {
			this.keyDropped = compound.getBoolean("KeyDropped");
		}
		if (compound.contains("HeartLockBroken")) {
			this.heartLockBroken = compound.getBoolean("HeartLockBroken");
		}
		if (compound.contains("MaccusSummoned")) {
			this.maccusSummoned = compound.getBoolean("MaccusSummoned");
		}
		this.rangedCooldown = compound.getInt("RangedCooldown");
		this.volleyShotsRemaining = compound.getInt("VolleyShotsRemaining");
		this.volleyShotDelay = compound.getInt("VolleyShotDelay");
		this.shockwaveCooldown = compound.getInt("ShockwaveCooldown");
		if (compound.contains("ShockwaveTimer")) {
			this.shockwaveTimer = compound.getInt("ShockwaveTimer");
		} else {
			this.resetShockwaveTimer();
		}
		this.antiBurrowCooldown = compound.getInt("AntiBurrowCooldown");
		if (compound.contains("MusicPhase")) {
			int ordinal = Mth.clamp(compound.getInt("MusicPhase"), 0, MusicPhase.values().length - 1);
			this.currentMusicPhase = MusicPhase.values()[ordinal];
		} else {
			this.currentMusicPhase = MusicPhase.NONE;
		}
		this.musicLoopTicks = compound.getInt("MusicLoopTicks");
		this.introPlayed = compound.getBoolean("IntroPlayed");
	}

	@Override
	public void aiStep() {
		super.aiStep();
		this.updateAttackAnimationState();
		if (this.counterKickCooldown > 0)
			this.counterKickCooldown--;
		if (this.rangedCooldown > 0)
			this.rangedCooldown--;
		if (this.volleyShotDelay > 0)
			this.volleyShotDelay--;
		if (this.shockwaveCooldown > 0)
			this.shockwaveCooldown--;
		if (!this.level().isClientSide) {
			Level level = this.level();
			if (!this.battleStormTriggered && level instanceof ServerLevel serverLevel) {
				LivingEntity target = this.getTarget();
				if (target instanceof Player player && player.isAlive()) {
					this.triggerBattleStorm(serverLevel);
				}
			}
			this.handleAmbientShockwave();
			this.handleAntiBurrow();
		}
	}

	@Override
	public void onAddedToWorld() {
		super.onAddedToWorld();
		if (!this.level().isClientSide && !this.introPlayed) {
			this.playAnimation("introduction");
			this.introPlayed = true;
		}
	}

	@Override
	public void startSeenByPlayer(ServerPlayer player) {
		super.startSeenByPlayer(player);
		this.bossInfo.addPlayer(player);
	}

	@Override
	public void stopSeenByPlayer(ServerPlayer player) {
		super.stopSeenByPlayer(player);
		this.bossInfo.removePlayer(player);
	}

	@Override
	public void customServerAiStep() {
		super.customServerAiStep();
		this.bossInfo.setProgress(this.getHealth() / this.getMaxHealth());
		this.updateBossMusic();
	}

	@Override
	public void die(DamageSource source) {
		this.clearAttackAnimationLock();
		this.playAnimation("deathcollapse");
		this.triggerDeathMusic();
		if (!this.level().isClientSide && this.battleStormTriggered && this.level() instanceof ServerLevel serverLevel) {
			CannonMod.queueServerWork(400, () -> serverLevel.setWeatherParameters(12000, 0, false, false));
		}
		super.die(source);
	}

	@Override
	protected void tickDeath() {
		++this.deathTime;
		if (this.deathTime == 120) {
			this.remove(RemovalReason.KILLED);
			this.dropExperience();
		}
	}

	public static void init() {
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3);
		builder = builder.add(Attributes.MAX_HEALTH, 500);
		builder = builder.add(Attributes.ARMOR, 0);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 3);
		builder = builder.add(Attributes.FOLLOW_RANGE, 32);
		builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 0.9);
		return builder;
	}

	private PlayState lowerBodyPredicate(AnimationState<DavyJonesBossEntity> event) {
		if (this.animationprocedure.equals("counterkick") || this.animationprocedure.equals("shockwave"))
			return PlayState.STOP;
		if (event.isMoving()) {
			return event.setAndContinue(RawAnimation.begin().thenLoop("walk_lower"));
		}
		return event.setAndContinue(RawAnimation.begin().thenLoop("idle_lower"));
	}

	private PlayState movementPredicate(AnimationState<DavyJonesBossEntity> event) {
		if (this.animationprocedure.equals("counterkick") || this.animationprocedure.equals("shockwave"))
			return PlayState.STOP;
		if (this.animationprocedure.equals("empty")) {
			if (event.isMoving()) {
				return event.setAndContinue(RawAnimation.begin().thenLoop("walk"));
			}
			return event.setAndContinue(RawAnimation.begin().thenLoop("idle"));
		}
		return PlayState.STOP;
	}

	private PlayState procedurePredicate(AnimationState<DavyJonesBossEntity> event) {
		AnimationController<DavyJonesBossEntity> controller = event.getController();
		if ((!this.animationprocedure.equals("empty") && controller.getAnimationState() == AnimationController.State.STOPPED)
				|| (!this.animationprocedure.equals(this.prevAnim) && !this.animationprocedure.equals("empty"))) {
			if (!this.animationprocedure.equals(this.prevAnim))
				controller.forceAnimationReset();
			controller.setAnimation(RawAnimation.begin().thenPlay(this.animationprocedure));
			if (controller.getAnimationState() == AnimationController.State.STOPPED) {
				this.animationprocedure = "empty";
				controller.forceAnimationReset();
			}
		} else if (this.animationprocedure.equals("empty")) {
			this.prevAnim = "empty";
			return PlayState.STOP;
		}
		this.prevAnim = this.animationprocedure;
		return PlayState.CONTINUE;
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar data) {
		AnimationController<DavyJonesBossEntity> lowerBody = new AnimationController<>(this, "lowerbody", 4, this::lowerBodyPredicate);
		lowerBody.setAnimationSpeed(1.0F);
		data.add(lowerBody);
		AnimationController<DavyJonesBossEntity> movement = new AnimationController<>(this, "movement", 4, this::movementPredicate);
		movement.setAnimationSpeed(1.0F);
		data.add(movement);
		AnimationController<DavyJonesBossEntity> procedure = new AnimationController<>(this, "procedure", 4, this::procedurePredicate);
		procedure.setAnimationSpeed(1.0F);
		data.add(procedure);
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.cache;
	}

	public String getSyncedAnimation() {
		return this.entityData.get(ANIMATION);
	}

	public void setAnimation(String animation) {
		this.entityData.set(ANIMATION, animation);
	}

	public void playAnimation(String animation) {
		if (this.isAttackAnimationActive() && (this.activeAttackAnimation == null || !this.activeAttackAnimation.equals(animation)))
			return;
		this.animationprocedure = animation;
		this.setAnimation(animation);
	}

	private void updateBossMusic() {
		if (this.level().isClientSide)
			return;
		if (!(this.level() instanceof ServerLevel serverLevel))
			return;
		if (!this.isAlive() || this.currentMusicPhase == MusicPhase.DEATH)
			return;
		if (this.bossInfo.getPlayers().isEmpty()) {
			if (this.currentMusicPhase != MusicPhase.NONE) {
				this.stopCurrentMusic(serverLevel);
				this.currentMusicPhase = MusicPhase.NONE;
				this.musicLoopTicks = 0;
			}
			return;
		}
		if (this.musicLoopTicks > 0) {
			this.musicLoopTicks--;
		}
		float healthRatio = this.getMaxHealth() <= 0.0F ? 1.0F : this.getHealth() / this.getMaxHealth();
		MusicPhase desiredPhase = healthRatio <= 0.4F ? MusicPhase.PHASE2 : MusicPhase.PHASE1;
		if (desiredPhase != this.currentMusicPhase) {
			this.switchMusicPhase(serverLevel, desiredPhase);
		} else if (desiredPhase != MusicPhase.NONE && this.musicLoopTicks <= 0) {
			SoundEvent phaseSound = this.getPhaseSound(desiredPhase);
			if (phaseSound != null) {
				this.stopCurrentMusic(serverLevel);
				this.playMusic(serverLevel, desiredPhase, phaseSound);
				this.musicLoopTicks = this.getLoopDuration(desiredPhase);
			}
		}
	}

	private void triggerDeathMusic() {
		if (this.level().isClientSide)
			return;
		if (!(this.level() instanceof ServerLevel serverLevel))
			return;
		if (this.currentMusicPhase == MusicPhase.DEATH)
			return;
		this.stopCurrentMusic(serverLevel);
		SoundEvent deathSound = this.getPhaseSound(MusicPhase.DEATH);
		if (deathSound != null) {
			this.playMusic(serverLevel, MusicPhase.DEATH, deathSound);
		}
		this.currentMusicPhase = MusicPhase.DEATH;
		this.musicLoopTicks = 0;
	}

	@Nullable
	private SoundEvent getPhaseSound(MusicPhase phase) {
		return switch (phase) {
		case PHASE1 -> CannonModSounds.JONESPHASE1.get();
		case PHASE2 -> CannonModSounds.JONESPHASE2.get();
		case DEATH -> CannonModSounds.DAVYSDEATH.get();
		default -> null;
		};
	}

	private static final float MUSIC_BASE_VOLUME = 6.0F;

	private void playMusic(ServerLevel serverLevel, MusicPhase phase, SoundEvent sound) {
		serverLevel.playSound(null, this.blockPosition(), sound, SoundSource.RECORDS, this.getPhaseVolume(phase), 1.0F);
	}

	private float getPhaseVolume(MusicPhase phase) {
		return switch (phase) {
		case PHASE1 -> MUSIC_BASE_VOLUME * 0.8F;
		case PHASE2 -> MUSIC_BASE_VOLUME * 0.6F;
		case DEATH -> MUSIC_BASE_VOLUME * 1.3F;
		default -> MUSIC_BASE_VOLUME;
		};
	}

	private void switchMusicPhase(ServerLevel serverLevel, MusicPhase nextPhase) {
		this.stopCurrentMusic(serverLevel);
		SoundEvent nextSound = this.getPhaseSound(nextPhase);
		if (nextSound != null) {
			this.playMusic(serverLevel, nextPhase, nextSound);
			this.musicLoopTicks = this.getLoopDuration(nextPhase);
		} else {
			this.musicLoopTicks = 0;
		}
		this.currentMusicPhase = nextPhase;
	}

	private void stopCurrentMusic(ServerLevel serverLevel) {
		SoundEvent currentSound = this.getPhaseSound(this.currentMusicPhase);
		if (currentSound == null)
			return;
		ClientboundStopSoundPacket packet = new ClientboundStopSoundPacket(currentSound.getLocation(), SoundSource.RECORDS);
		for (ServerPlayer listener : serverLevel.players()) {
			listener.connection.send(packet);
		}
	}

	private void triggerBattleStorm(ServerLevel serverLevel) {
		if (this.battleStormTriggered)
			return;
		this.battleStormTriggered = true;
		serverLevel.setWeatherParameters(0, 6000, true, true);
	}

	private int getLoopDuration(MusicPhase phase) {
		return switch (phase) {
		case PHASE1 -> PHASE1_LOOP_TICKS;
		case PHASE2 -> PHASE2_LOOP_TICKS;
		default -> 0;
		};
	}
}
