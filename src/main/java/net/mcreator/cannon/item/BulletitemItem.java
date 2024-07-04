
package net.mcreator.cannon.item;

import net.minecraft.world.entity.ai.attributes.Attributes;

public class BulletitemItem extends Item {
	public BulletitemItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON));
	}
}