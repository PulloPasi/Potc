package net.mcreator.cannon.procedures;

import net.minecraftforge.eventbus.api.Event;

public class GunpowderbarrelexplodeProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (world instanceof Level _level && !_level.isClientSide())
			_level.explode(null, x, y, z, 4, Level.ExplosionInteraction.TNT);
	}
}
