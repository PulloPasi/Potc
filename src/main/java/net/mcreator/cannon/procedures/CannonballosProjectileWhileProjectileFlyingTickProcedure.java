package net.mcreator.cannon.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.SimpleParticleType;

import net.mcreator.cannon.init.CannonModParticleTypes;

public class CannonballosProjectileWhileProjectileFlyingTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (world instanceof ServerLevel _level)
			_level.sendParticles((SimpleParticleType) (CannonModParticleTypes.CANNONTRAIL.get()), x, y, z, 6, 0.2, 0.2, 0.2, 3);
	}
}
