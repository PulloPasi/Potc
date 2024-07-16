package net.mcreator.cannon.procedures;

import net.minecraftforge.eventbus.api.Event;

public class FountainOfYouthDripOnBlockRightClickedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == CannonModItems.CHALICE.get()) {
			if (entity instanceof Player _player) {
				ItemStack _stktoremove = new ItemStack(CannonModItems.CHALICE.get());
				_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
			}
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(CannonModItems.CHALICEFULL.get()).copy();
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		}
	}
}
