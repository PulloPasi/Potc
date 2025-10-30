package net.mcreator.cannon.procedures;

import org.valkyrienskies.core.impl.shadow.z;
import org.valkyrienskies.core.impl.shadow.y;
import org.valkyrienskies.core.impl.shadow.x;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.particles.SimpleParticleType;

import net.mcreator.cannon.init.CannonModParticleTypes;

public class CannonballosProjectileWhileProjectileFlyingTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		world.addParticle((SimpleParticleType) (CannonModParticleTypes.CANNONTRAIL.get()), x, y, z, 0, 0, 0);
	}
}
