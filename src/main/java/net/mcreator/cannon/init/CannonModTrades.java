
/*
*	MCreator note: This file will be REGENERATED on each build.
*/
package net.mcreator.cannon.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.common.BasicItemListing;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.npc.VillagerProfession;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CannonModTrades {
	@SubscribeEvent
	public static void registerTrades(VillagerTradesEvent event) {
		if (event.getType() == VillagerProfession.WEAPONSMITH) {
			event.getTrades().get(2).add(new BasicItemListing(new ItemStack(Items.GOLD_INGOT, 15),

					new ItemStack(CannonModBlocks.CANNON.get()), 10, 5, 0.1f));
		}
		if (event.getType() == VillagerProfession.ARMORER) {
			event.getTrades().get(5).add(new BasicItemListing(new ItemStack(Blocks.GOLD_BLOCK, 5), new ItemStack(CannonModItems.SWORDOS.get()), new ItemStack(CannonModItems.SWORD_OF_TRITON.get()), 1, 5, 0.2f));
		}
	}
}
