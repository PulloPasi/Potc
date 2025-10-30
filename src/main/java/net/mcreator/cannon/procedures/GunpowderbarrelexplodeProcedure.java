package net.mcreator.cannon.procedures;

import org.valkyrienskies.core.impl.shadow.z;
import org.valkyrienskies.core.impl.shadow.y;
import org.valkyrienskies.core.impl.shadow.x;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;

public class GunpowderbarrelexplodeProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (world instanceof Level _level && !_level.isClientSide())
			_level.explode(null, x, y, z, 3, Level.ExplosionInteraction.TNT);
	}
}
