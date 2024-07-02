package net.mcreator.cannon.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.server.level.ServerLevel;

import net.mcreator.cannon.init.CannonModEntities;
import net.mcreator.cannon.entity.CannonballosProjectileEntity;

public class CannonOnBlockRightClickedProcedure {
	public static void execute(LevelAccessor world) {
		double fromZ = 0;
		double fromX = 0;
		double fromY = 0;
		if (world instanceof ServerLevel projectileLevel) {
			Projectile _entityToSpawn = new Object() {
				public Projectile getArrow(Level level, float damage, int knockback) {
					AbstractArrow entityToSpawn = new CannonballosProjectileEntity(CannonModEntities.CANNONBALLOS_PROJECTILE.get(), level);
					entityToSpawn.setBaseDamage(damage);
					entityToSpawn.setKnockback(knockback);
					entityToSpawn.setSilent(true);
					return entityToSpawn;
				}
			}.getArrow(projectileLevel, 5, 1);
			_entityToSpawn.setPos(fromX, fromY, fromZ);
			_entityToSpawn.shoot(1, 1, 1, 1, 0);
			projectileLevel.addFreshEntity(_entityToSpawn);
		}
	}
}
