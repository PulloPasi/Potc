
package net.mcreator.cannon.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class KeyItem extends Item {
	public KeyItem() {
		super(new Item.Properties().stacksTo(64).fireResistant().rarity(Rarity.EPIC));
	}
}