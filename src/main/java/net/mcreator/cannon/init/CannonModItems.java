
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.cannon.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.ForgeSpawnEggItem;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.BlockItem;

import net.mcreator.cannon.item.SwordosItem;
import net.mcreator.cannon.item.SwordoftrtitonbombItem;
import net.mcreator.cannon.item.SwordOfTritonItem;
import net.mcreator.cannon.item.PipoItem;
import net.mcreator.cannon.item.LeStickOfFireItem;
import net.mcreator.cannon.item.KolmikulmaHatItem;
import net.mcreator.cannon.item.KeyItem;
import net.mcreator.cannon.item.JackSparrowItem;
import net.mcreator.cannon.item.HeartOfDavidJonesItem;
import net.mcreator.cannon.item.HalfEatenBananItem;
import net.mcreator.cannon.item.FlintlockItem;
import net.mcreator.cannon.item.DutchmanpeaceItem;
import net.mcreator.cannon.item.DavyJonesItem;
import net.mcreator.cannon.item.ChalicefullItem;
import net.mcreator.cannon.item.ChaliceItem;
import net.mcreator.cannon.item.CannonballosItem;
import net.mcreator.cannon.item.BulletitemItem;
import net.mcreator.cannon.item.BlackBeardItem;
import net.mcreator.cannon.item.BillTurnerItem;
import net.mcreator.cannon.item.BandanaItem;
import net.mcreator.cannon.item.BananItem;
import net.mcreator.cannon.block.display.DeadMansChestDisplayItem;
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
	public static final RegistryObject<Item> LADDER_THAT_HAS_BEEN_STOLEN_FROM_MIKAELS_BACKYARD = block(CannonModBlocks.LADDER_THAT_HAS_BEEN_STOLEN_FROM_MIKAELS_BACKYARD);
	public static final RegistryObject<Item> PLANKS_OF_THE_FLYING_DUTCHMAN = block(CannonModBlocks.PLANKS_OF_THE_FLYING_DUTCHMAN);
	public static final RegistryObject<Item> FERRYMANS_CHASING_CANNON = block(CannonModBlocks.FERRYMANS_CHASING_CANNON);
	public static final RegistryObject<Item> DOOR_OF_THE_FLYING_DUTCHMAN = doubleBlock(CannonModBlocks.DOOR_OF_THE_FLYING_DUTCHMAN);
	public static final RegistryObject<Item> STAIRS_OF_THE_FLYING_DUTCHMAN = block(CannonModBlocks.STAIRS_OF_THE_FLYING_DUTCHMAN);
	public static final RegistryObject<Item> BARNACLES = block(CannonModBlocks.BARNACLES);
	public static final RegistryObject<Item> ROPE = block(CannonModBlocks.ROPE);
	public static final RegistryObject<Item> PART_OF_THE_CREW_SPAWN_EGG = REGISTRY.register("part_of_the_crew_spawn_egg", () -> new ForgeSpawnEggItem(CannonModEntities.PART_OF_THE_CREW, -13151692, -1, new Item.Properties()));
	public static final RegistryObject<Item> BULLETITEM = REGISTRY.register("bulletitem", () -> new BulletitemItem());
	public static final RegistryObject<Item> FLINTLOCK = REGISTRY.register("flintlock", () -> new FlintlockItem());
	public static final RegistryObject<Item> SLABS_OF_THE_FLYING_DUTCHMAN = block(CannonModBlocks.SLABS_OF_THE_FLYING_DUTCHMAN);
	public static final RegistryObject<Item> BILL_TURNER_HELMET = REGISTRY.register("bill_turner_helmet", () -> new BillTurnerItem.Helmet());
	public static final RegistryObject<Item> BILL_TURNER_CHESTPLATE = REGISTRY.register("bill_turner_chestplate", () -> new BillTurnerItem.Chestplate());
	public static final RegistryObject<Item> BILL_TURNER_LEGGINGS = REGISTRY.register("bill_turner_leggings", () -> new BillTurnerItem.Leggings());
	public static final RegistryObject<Item> BILL_TURNER_BOOTS = REGISTRY.register("bill_turner_boots", () -> new BillTurnerItem.Boots());
	public static final RegistryObject<Item> KARB_SPAWN_EGG = REGISTRY.register("karb_spawn_egg", () -> new ForgeSpawnEggItem(CannonModEntities.KARB, -373243, -1, new Item.Properties()));
	public static final RegistryObject<Item> PART_OF_THE_CREW_STRONG_SPAWN_EGG = REGISTRY.register("part_of_the_crew_strong_spawn_egg",
			() -> new ForgeSpawnEggItem(CannonModEntities.PART_OF_THE_CREW_STRONG, -15196647, -16775168, new Item.Properties()));
	public static final RegistryObject<Item> SWORDOS = REGISTRY.register("swordos", () -> new SwordosItem());
	public static final RegistryObject<Item> SAILS_OF_THE_BLACK_PEARL = block(CannonModBlocks.SAILS_OF_THE_BLACK_PEARL);
	public static final RegistryObject<Item> SAILS_OF_THE_BLACK_PEARL_PATCH = block(CannonModBlocks.SAILS_OF_THE_BLACK_PEARL_PATCH);
	public static final RegistryObject<Item> JACK_SPARROW_HELMET = REGISTRY.register("jack_sparrow_helmet", () -> new JackSparrowItem.Helmet());
	public static final RegistryObject<Item> JACK_SPARROW_CHESTPLATE = REGISTRY.register("jack_sparrow_chestplate", () -> new JackSparrowItem.Chestplate());
	public static final RegistryObject<Item> JACK_SPARROW_LEGGINGS = REGISTRY.register("jack_sparrow_leggings", () -> new JackSparrowItem.Leggings());
	public static final RegistryObject<Item> JACK_SPARROW_BOOTS = REGISTRY.register("jack_sparrow_boots", () -> new JackSparrowItem.Boots());
	public static final RegistryObject<Item> PULLEY = block(CannonModBlocks.PULLEY);
	public static final RegistryObject<Item> GUNPOWDERBARREL = block(CannonModBlocks.GUNPOWDERBARREL);
	public static final RegistryObject<Item> DEAD_MANS_CHEST = REGISTRY.register(CannonModBlocks.DEAD_MANS_CHEST.getId().getPath(), () -> new DeadMansChestDisplayItem(CannonModBlocks.DEAD_MANS_CHEST.get(), new Item.Properties()));
	public static final RegistryObject<Item> KEY = REGISTRY.register("key", () -> new KeyItem());
	public static final RegistryObject<Item> HEART_OF_DAVID_JONES = REGISTRY.register("heart_of_david_jones", () -> new HeartOfDavidJonesItem());
	public static final RegistryObject<Item> BANAN = REGISTRY.register("banan", () -> new BananItem());
	public static final RegistryObject<Item> HALF_EATEN_BANAN = REGISTRY.register("half_eaten_banan", () -> new HalfEatenBananItem());
	public static final RegistryObject<Item> BANDANA_HELMET = REGISTRY.register("bandana_helmet", () -> new BandanaItem.Helmet());
	public static final RegistryObject<Item> KOLMIKULMA_HAT_HELMET = REGISTRY.register("kolmikulma_hat_helmet", () -> new KolmikulmaHatItem.Helmet());
	public static final RegistryObject<Item> PIPO_HELMET = REGISTRY.register("pipo_helmet", () -> new PipoItem.Helmet());
	public static final RegistryObject<Item> DUTCHMANPEACE = REGISTRY.register("dutchmanpeace", () -> new DutchmanpeaceItem());
	public static final RegistryObject<Item> BLACK_BEARD_HELMET = REGISTRY.register("black_beard_helmet", () -> new BlackBeardItem.Helmet());
	public static final RegistryObject<Item> BLACK_BEARD_CHESTPLATE = REGISTRY.register("black_beard_chestplate", () -> new BlackBeardItem.Chestplate());
	public static final RegistryObject<Item> BLACK_BEARD_LEGGINGS = REGISTRY.register("black_beard_leggings", () -> new BlackBeardItem.Leggings());
	public static final RegistryObject<Item> BLACK_BEARD_BOOTS = REGISTRY.register("black_beard_boots", () -> new BlackBeardItem.Boots());
	public static final RegistryObject<Item> BRIISH_SPAWN_EGG = REGISTRY.register("briish_spawn_egg", () -> new ForgeSpawnEggItem(CannonModEntities.BRIISH, -3407872, -1, new Item.Properties()));
	public static final RegistryObject<Item> SWORD_OF_TRITON = REGISTRY.register("sword_of_triton", () -> new SwordOfTritonItem());
	public static final RegistryObject<Item> CHALICE = REGISTRY.register("chalice", () -> new ChaliceItem());
	public static final RegistryObject<Item> CHALICEFULL = REGISTRY.register("chalicefull", () -> new ChalicefullItem());
	public static final RegistryObject<Item> SWORDOFTRTITONBOMB = REGISTRY.register("swordoftrtitonbomb", () -> new SwordoftrtitonbombItem());

	// Start of user code block custom items
	// End of user code block custom items
	private static RegistryObject<Item> block(RegistryObject<Block> block) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
	}

	private static RegistryObject<Item> doubleBlock(RegistryObject<Block> block) {
		return REGISTRY.register(block.getId().getPath(), () -> new DoubleHighBlockItem(block.get(), new Item.Properties()));
	}
}
