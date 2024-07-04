package net.mcreator.cannon.procedures;

import net.minecraftforge.eventbus.api.Event;

public class FlintlockRightclickedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (world instanceof Level _level) {
			if (!_level.isClientSide()) {
				_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("cannon:cannonfire")), SoundSource.NEUTRAL, 5, 1);
			} else {
				_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("cannon:cannonfire")), SoundSource.NEUTRAL, 5, 1, false);
			}
		}
		if (world instanceof ServerLevel _level)
			_level.sendParticles((SimpleParticleType) (CannonModParticleTypes.CANNONTRAIL.get()), x, y, z, 5, 0.1, 0.1, 0.1, 0.1);
	}
}
