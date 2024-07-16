
package net.mcreator.cannon.item;

import net.minecraft.world.entity.ai.attributes.Attributes;

public class ChalicewithtearItem extends Item {
	public ChalicewithtearItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.EPIC).food((new FoodProperties.Builder()).nutrition(4).saturationMod(0.3f).build()));
	}

	@Override
	public UseAnim getUseAnimation(ItemStack itemstack) {
		return UseAnim.DRINK;
	}

	@Override
	public int getUseDuration(ItemStack itemstack) {
		return 0;
	}
}