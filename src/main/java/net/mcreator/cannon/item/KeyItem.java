
package net.mcreator.cannon.item;

import net.minecraft.world.entity.ai.attributes.Attributes;

public class KeyItem extends Item {
	public KeyItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.EPIC));
	}
}