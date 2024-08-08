
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.cannon.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

import net.mcreator.cannon.CannonMod;

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
				tabData.accept(CannonModItems.DAVY_JONES_HELMET.get());
				tabData.accept(CannonModItems.DAVY_JONES_CHESTPLATE.get());
				tabData.accept(CannonModItems.DAVY_JONES_LEGGINGS.get());
				tabData.accept(CannonModItems.DAVY_JONES_BOOTS.get());
				tabData.accept(CannonModBlocks.DUTCHMANSAILS_2.get().asItem());
				tabData.accept(CannonModBlocks.DUTCHMANSAILS_3.get().asItem());
				tabData.accept(CannonModBlocks.FACE_OF_THE_FERRYMAN.get().asItem());
				tabData.accept(CannonModBlocks.FERRYMANS_CANNON.get().asItem());
				tabData.accept(CannonModItems.LE_STICK_OF_FIRE.get());
				tabData.accept(CannonModBlocks.FERRYMANS_CHASING_CANNON.get().asItem());
				tabData.accept(CannonModBlocks.PLANKS_OF_THE_FLYING_DUTCHMAN.get().asItem());
				tabData.accept(CannonModBlocks.DOOR_OF_THE_FLYING_DUTCHMAN.get().asItem());
				tabData.accept(CannonModBlocks.LADDER_THAT_HAS_BEEN_STOLEN_FROM_MIKAELS_BACKYARD.get().asItem());
				tabData.accept(CannonModBlocks.STAIRS_OF_THE_FLYING_DUTCHMAN.get().asItem());
				tabData.accept(CannonModBlocks.BARNACLES.get().asItem());
				tabData.accept(CannonModBlocks.ROPE.get().asItem());
				tabData.accept(CannonModItems.PART_OF_THE_CREW_SPAWN_EGG.get());
				tabData.accept(CannonModItems.FLINTLOCK.get());
				tabData.accept(CannonModItems.BULLETITEM.get());
				tabData.accept(CannonModBlocks.SLABS_OF_THE_FLYING_DUTCHMAN.get().asItem());
				tabData.accept(CannonModItems.BILL_TURNER_HELMET.get());
				tabData.accept(CannonModItems.BILL_TURNER_CHESTPLATE.get());
				tabData.accept(CannonModItems.BILL_TURNER_LEGGINGS.get());
				tabData.accept(CannonModItems.BILL_TURNER_BOOTS.get());
				tabData.accept(CannonModItems.KARB_SPAWN_EGG.get());
				tabData.accept(CannonModItems.PART_OF_THE_CREW_STRONG_SPAWN_EGG.get());
				tabData.accept(CannonModItems.SWORDOS.get());
				tabData.accept(CannonModBlocks.SAILS_OF_THE_BLACK_PEARL.get().asItem());
				tabData.accept(CannonModBlocks.SAILS_OF_THE_BLACK_PEARL_PATCH.get().asItem());
				tabData.accept(CannonModItems.JACK_SPARROW_HELMET.get());
				tabData.accept(CannonModItems.JACK_SPARROW_CHESTPLATE.get());
				tabData.accept(CannonModItems.JACK_SPARROW_LEGGINGS.get());
				tabData.accept(CannonModItems.JACK_SPARROW_BOOTS.get());
				tabData.accept(CannonModBlocks.PULLEY.get().asItem());
				tabData.accept(CannonModBlocks.GUNPOWDERBARREL.get().asItem());
				tabData.accept(CannonModBlocks.DEAD_MANS_CHEST.get().asItem());
				tabData.accept(CannonModItems.KEY.get());
				tabData.accept(CannonModItems.HEART_OF_DAVID_JONES.get());
				tabData.accept(CannonModItems.BANAN.get());
				tabData.accept(CannonModItems.HALF_EATEN_BANAN.get());
				tabData.accept(CannonModItems.BANDANA_HELMET.get());
				tabData.accept(CannonModItems.KOLMIKULMA_HAT_HELMET.get());
				tabData.accept(CannonModItems.PIPO_HELMET.get());
				tabData.accept(CannonModItems.DUTCHMANPEACE.get());
				tabData.accept(CannonModItems.BLACK_BEARD_HELMET.get());
				tabData.accept(CannonModItems.BLACK_BEARD_CHESTPLATE.get());
				tabData.accept(CannonModItems.BLACK_BEARD_LEGGINGS.get());
				tabData.accept(CannonModItems.BLACK_BEARD_BOOTS.get());
				tabData.accept(CannonModItems.BRIISH_SPAWN_EGG.get());
				tabData.accept(CannonModItems.SWORD_OF_TRITON.get());
				tabData.accept(CannonModItems.CHALICE.get());
				tabData.accept(CannonModItems.CHALICEFULL.get());
				tabData.accept(CannonModItems.MERMAID_SPAWN_EGG.get());
				tabData.accept(CannonModItems.MERMAIDTEAR.get());
				tabData.accept(CannonModBlocks.FOUNTAIN_OF_YOUTH_DRIP.get().asItem());
				tabData.accept(CannonModItems.CHALICEWITHTEAR.get());
				tabData.accept(CannonModItems.ANGELICA_TEACH_HELMET.get());
				tabData.accept(CannonModItems.ANGELICA_TEACH_CHESTPLATE.get());
				tabData.accept(CannonModItems.ANGELICA_TEACH_LEGGINGS.get());
				tabData.accept(CannonModItems.ANGELICA_TEACH_BOOTS.get());
				tabData.accept(CannonModItems.JOSHAMEE_GIBBS_HELMET.get());
				tabData.accept(CannonModItems.JOSHAMEE_GIBBS_CHESTPLATE.get());
				tabData.accept(CannonModItems.JOSHAMEE_GIBBS_LEGGINGS.get());
				tabData.accept(CannonModItems.JOSHAMEE_GIBBS_BOOTS.get());
				tabData.accept(CannonModBlocks.PLANKS_OF_THE_QUEEN_ANNES_REVENGE.get().asItem());
				tabData.accept(CannonModBlocks.SLABS_OF_THE_QUEEN_ANNES_REVENGE.get().asItem());
				tabData.accept(CannonModBlocks.STAIRS_OF_THE_QUEEN_ANNES_REVENGE.get().asItem());
				tabData.accept(CannonModBlocks.SIDE_FIGURE_HEAD_OF_THE_QUEEN_ANNES_REVENGE.get().asItem());
				tabData.accept(CannonModBlocks.CANNON_HATCH_OF_THE_QUEEN_ANNES_REVENGE.get().asItem());
				tabData.accept(CannonModBlocks.STYLISH_FENCE.get().asItem());
				tabData.accept(CannonModBlocks.STYLISH_WALL.get().asItem());
				tabData.accept(CannonModBlocks.ROPE_BLOCK.get().asItem());
				tabData.accept(CannonModBlocks.RED_SAIL.get().asItem());
				tabData.accept(CannonModBlocks.RED_SAIL_SLAB.get().asItem());
				tabData.accept(CannonModBlocks.RED_SAIL_STAIR.get().asItem());
				tabData.accept(CannonModBlocks.RED_SAIL_WALL.get().asItem());
				tabData.accept(CannonModBlocks.LIGHT_RED_SAIL.get().asItem());
				tabData.accept(CannonModItems.QUARTERMASTER_SPAWN_EGG.get());
				tabData.accept(CannonModItems.TALISMAN_OF_THE_VOODOO_MISTER.get());
				tabData.accept(CannonModItems.TALISMAN_OF_THE_BLACK_PEARL.get());
				tabData.accept(CannonModItems.BLACK_BEARDS_CREW_SPAWN_EGG.get());
				tabData.accept(CannonModBlocks.GREY_PLANKS.get().asItem());
				tabData.accept(CannonModBlocks.GREY_PLANK_SLAB.get().asItem());
				tabData.accept(CannonModBlocks.GREY_PLANK_STAIR.get().asItem());
				tabData.accept(CannonModBlocks.BARREL.get().asItem());
				tabData.accept(CannonModBlocks.WINDOW_OF_THE_BLACK_PEARL.get().asItem());
				tabData.accept(CannonModBlocks.CHANDELIER.get().asItem());
			})

					.build());
}
