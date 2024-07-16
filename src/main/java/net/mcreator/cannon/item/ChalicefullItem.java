
package net.mcreator.cannon.item;

import net.minecraft.world.entity.ai.attributes.Attributes;

public class ChalicefullItem extends Item {
	public ChalicefullItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.RARE));
	}

	@Override
	public int getUseDuration(ItemStack itemstack) {
		return 32;
	}
}