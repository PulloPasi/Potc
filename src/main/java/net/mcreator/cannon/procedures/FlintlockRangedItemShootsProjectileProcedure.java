package net.mcreator.cannon.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.SimpleParticleType;

import net.mcreator.cannon.init.CannonModParticleTypes;

public class FlintlockRangedItemShootsProjectileProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (world instanceof ServerLevel _level)
			_level.sendParticles((SimpleParticleType) (CannonModParticleTypes.CANNONTRAIL.get()), (x + entity.getLookAngle().x), (y + 1), (z + entity.getLookAngle().z), 5, 0.1, 0.1, 0.1, 0);
	}
}
