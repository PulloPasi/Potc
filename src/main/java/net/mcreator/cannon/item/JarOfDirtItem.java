
package net.mcreator.cannon.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class JarOfDirtItem extends Item {
	public JarOfDirtItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.RARE));
	}
}
