
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.cannon.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

import net.mcreator.cannon.CannonMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CannonModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CannonMod.MODID);
	public static final RegistryObject<CreativeModeTab> POTC = REGISTRY.register("potc",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.cannon.potc")).icon(() -> new ItemStack(CannonModItems.CANNONBALLOS.get())).displayItems((parameters, tabData) -> {
				tabData.accept(CannonModBlocks.CANNON.get().asItem());
				tabData.accept(CannonModItems.CANNONBALLOS.get());
				tabData.accept(CannonModBlocks.BLACK_PLANKS.get().asItem());
				tabData.accept(CannonModBlocks.BLACK_PLANK_SLAB.get().asItem());
				tabData.accept(CannonModBlocks.BLACK_PLANK_TRAP_DOOR.get().asItem());
				tabData.accept(CannonModBlocks.DUTCHMANSAILS.get().asItem());
				tabData.accept(CannonModBlocks.GLORIOUSLANTERN.get().asItem());
				tabData.accept(CannonModBlocks.BLACK_PLANK_STAIRS.get().asItem());
				tabData.accept(CannonModBlocks.DUTCHMANSAILS_2.get().asItem());
				tabData.accept(CannonModBlocks.DUTCHMANSAILS_3.get().asItem());
				tabData.accept(CannonModBlocks.FACE_OF_THE_FERRYMAN.get().asItem());
				tabData.accept(CannonModBlocks.FERRYMANS_CANNON.get().asItem());
				tabData.accept(CannonModBlocks.FERRYMANS_CHASING_CANNON.get().asItem());
				tabData.accept(CannonModBlocks.PLANKS_OF_THE_FLYING_DUTCHMAN.get().asItem());
				tabData.accept(CannonModBlocks.SLABS_OF_THE_FLYING_DUTCHMAN.get().asItem());
				tabData.accept(CannonModBlocks.DOOR_OF_THE_FLYING_DUTCHMAN.get().asItem());
				tabData.accept(CannonModBlocks.LADDER_THAT_HAS_BEEN_STOLEN_FROM_MIKAELS_BACKYARD.get().asItem());
				tabData.accept(CannonModBlocks.STAIRS_OF_THE_FLYING_DUTCHMAN.get().asItem());
				tabData.accept(CannonModBlocks.BARNACLES.get().asItem());
				tabData.accept(CannonModBlocks.ROPE.get().asItem());
			})

					.build());

	@SubscribeEvent
	public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {
		if (tabData.getTabKey() == CreativeModeTabs.COMBAT) {
			tabData.accept(CannonModItems.DAVY_JONES_HELMET.get());
			tabData.accept(CannonModItems.DAVY_JONES_CHESTPLATE.get());
			tabData.accept(CannonModItems.DAVY_JONES_LEGGINGS.get());
			tabData.accept(CannonModItems.DAVY_JONES_BOOTS.get());
		} else if (tabData.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
			tabData.accept(CannonModItems.LE_STICK_OF_FIRE.get());
		}
	}
}
