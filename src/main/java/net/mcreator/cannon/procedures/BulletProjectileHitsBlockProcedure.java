package net.mcreator.cannon.procedures;

import net.minecraftforge.eventbus.api.Event;

public class BulletProjectileHitsBlockProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (world instanceof Level _level) {
			if (!_level.isClientSide()) {
				_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.deepslate.break")), SoundSource.NEUTRAL, 2, 1);
			} else {
				_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.deepslate.break")), SoundSource.NEUTRAL, 2, 1, false);
			}
		}
		if (world instanceof ServerLevel _level)
			_level.sendParticles(ParticleTypes.FLAME, x, y, z, 5, 0.3, 0.3, 0.3, 0.2);
		if (world instanceof Level _level && !_level.isClientSide())
			_level.explode(null, x, y, z, (float) 0.2, Level.ExplosionInteraction.TNT);
	}
}
