package net.mcreator.cannon.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.particles.ParticleTypes;

import net.mcreator.cannon.CannonMod;

public class SideFigureHeadOfTheQueenAnnesRevengeOnTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		boolean posY = false;
		boolean posX = false;
		boolean posZ = false;
		for (int index0 = 0; index0 < 20000; index0++) {
			CannonMod.queueServerWork(20, () -> {
				world.addParticle(ParticleTypes.SMALL_FLAME, x, y, z, 0, 1, 0);
			});
		}
	}
}
