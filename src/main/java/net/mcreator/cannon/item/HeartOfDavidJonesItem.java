
package net.mcreator.cannon.item;

import java.util.List;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;

import net.mcreator.cannon.entity.DavyJonesBossEntity;

public class HeartOfDavidJonesItem extends Item {
	public HeartOfDavidJonesItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.EPIC));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		if (!level.isClientSide) {
			List<DavyJonesBossEntity> bosses = level.getEntitiesOfClass(DavyJonesBossEntity.class,
					player.getBoundingBox().inflate(DavyJonesBossEntity.HEART_KILL_RADIUS));
			boolean used = false;
			for (DavyJonesBossEntity boss : bosses) {
				if (!boss.isAlive())
					continue;
				boss.consumeHeartKill(player);
				used = true;
			}
			if (used && !player.getAbilities().instabuild) {
				stack.shrink(1);
			}
		}
		return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
	}
}
