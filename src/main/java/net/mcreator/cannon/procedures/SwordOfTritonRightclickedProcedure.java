package net.mcreator.cannon.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;

import net.mcreator.cannon.init.CannonModItems;
import net.mcreator.cannon.init.CannonModEntities;
import net.mcreator.cannon.entity.SwordoftritonprojectileEntity;

public class SwordOfTritonRightclickedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (world instanceof ServerLevel projectileLevel) {
			Projectile _entityToSpawn = new Object() {
				public Projectile getArrow(Level level, Entity shooter, float damage, int knockback) {
					AbstractArrow entityToSpawn = new SwordoftritonprojectileEntity(CannonModEntities.SWORDOFTRITONPROJECTILE.get(), level);
					entityToSpawn.setOwner(shooter);
					entityToSpawn.setBaseDamage(damage);
					entityToSpawn.setKnockback(knockback);
					entityToSpawn.setSilent(true);
					return entityToSpawn;
				}
			}.getArrow(projectileLevel, entity, 5, 1);
			_entityToSpawn.setPos(x, y, z);
			_entityToSpawn.shoot(x, y, z, 3, 0);
			projectileLevel.addFreshEntity(_entityToSpawn);
		}
		if (entity instanceof Player _player)
			_player.getCooldowns().addCooldown(CannonModItems.SWORD_OF_TRITON.get(), 60);
	}
}
