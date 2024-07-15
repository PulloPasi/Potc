package net.mcreator.cannon.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Entity;

import net.mcreator.cannon.init.CannonModItems;

public class PartOfCrewAttackConditionProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		if (!((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(CannonModItems.DAVY_JONES_HELMET.get())) : false)) {
			return true;
		}
		if (!((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(Blocks.TNT)) : false)) {
			return true;
		}
		return false;
	}
}
