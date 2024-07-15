package net.mcreator.cannon.procedures;

import net.minecraftforge.eventbus.api.Event;

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
