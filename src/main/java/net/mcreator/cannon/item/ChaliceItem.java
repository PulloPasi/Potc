
package net.mcreator.cannon.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class ChaliceItem extends Item {
	public ChaliceItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.RARE));
	}
}
