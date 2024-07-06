package net.mcreator.cannon.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.cannon.entity.PartOfTheCrewStrongEntity;

public class PartOfTheCrewStrongEntityIsHurtProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof PartOfTheCrewStrongEntity) {
			((PartOfTheCrewStrongEntity) entity).setAnimation("hurt");
		}
	}
}
