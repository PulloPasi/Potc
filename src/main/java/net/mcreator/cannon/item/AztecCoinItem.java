
package net.mcreator.cannon.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class AztecCoinItem extends Item {
	public AztecCoinItem() {
		super(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON));
	}
}
