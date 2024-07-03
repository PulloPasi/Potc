
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.cannon.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;

import net.mcreator.cannon.item.LeStickOfFireItem;
import net.mcreator.cannon.item.DavyJonesItem;
import net.mcreator.cannon.item.CannonballosItem;
import net.mcreator.cannon.CannonMod;

public class CannonModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, CannonMod.MODID);
	public static final RegistryObject<Item> CANNON = block(CannonModBlocks.CANNON);
	public static final RegistryObject<Item> CANNONBALLOS = REGISTRY.register("cannonballos", () -> new CannonballosItem());
	public static final RegistryObject<Item> BLACK_PLANKS = block(CannonModBlocks.BLACK_PLANKS);
	public static final RegistryObject<Item> BLACK_PLANK_SLAB = block(CannonModBlocks.BLACK_PLANK_SLAB);
	public static final RegistryObject<Item> BLACK_PLANK_TRAP_DOOR = block(CannonModBlocks.BLACK_PLANK_TRAP_DOOR);
	public static final RegistryObject<Item> DUTCHMANSAILS = block(CannonModBlocks.DUTCHMANSAILS);
	public static final RegistryObject<Item> GLORIOUSLANTERN = block(CannonModBlocks.GLORIOUSLANTERN);
	public static final RegistryObject<Item> BLACK_PLANK_STAIRS = block(CannonModBlocks.BLACK_PLANK_STAIRS);
	public static final RegistryObject<Item> DAVY_JONES_HELMET = REGISTRY.register("davy_jones_helmet", () -> new DavyJonesItem.Helmet());
	public static final RegistryObject<Item> DAVY_JONES_CHESTPLATE = REGISTRY.register("davy_jones_chestplate", () -> new DavyJonesItem.Chestplate());
	public static final RegistryObject<Item> DAVY_JONES_LEGGINGS = REGISTRY.register("davy_jones_leggings", () -> new DavyJonesItem.Leggings());
	public static final RegistryObject<Item> DAVY_JONES_BOOTS = REGISTRY.register("davy_jones_boots", () -> new DavyJonesItem.Boots());
	public static final RegistryObject<Item> DUTCHMANSAILS_2 = block(CannonModBlocks.DUTCHMANSAILS_2);
	public static final RegistryObject<Item> DUTCHMANSAILS_3 = block(CannonModBlocks.DUTCHMANSAILS_3);
	public static final RegistryObject<Item> FACE_OF_THE_FERRYMAN = block(CannonModBlocks.FACE_OF_THE_FERRYMAN);
	public static final RegistryObject<Item> FERRYMANS_CANNON = block(CannonModBlocks.FERRYMANS_CANNON);
	public static final RegistryObject<Item> LE_STICK_OF_FIRE = REGISTRY.register("le_stick_of_fire", () -> new LeStickOfFireItem());

	// Start of user code block custom items
	// End of user code block custom items
	private static RegistryObject<Item> block(RegistryObject<Block> block) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
	}
}
