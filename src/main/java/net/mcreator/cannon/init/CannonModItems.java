
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

import net.mcreator.cannon.item.TalismanOfTheVoodooMisterItem;
import net.mcreator.cannon.item.TalismanOfTheBlackPearlItem;
import net.mcreator.cannon.item.SwordosItem;
import net.mcreator.cannon.item.SwordoftrtitonbombItem;
import net.mcreator.cannon.item.SwordOfTritonItem;
import net.mcreator.cannon.item.PipoItem;
import net.mcreator.cannon.item.MermaidtearItem;
import net.mcreator.cannon.item.LeStickOfFireItem;
import net.mcreator.cannon.item.KolmikulmaHatItem;
import net.mcreator.cannon.item.KeyItem;
import net.mcreator.cannon.item.JoshameeGibbsItem;
import net.mcreator.cannon.item.JacksCompassItem;
import net.mcreator.cannon.item.JackdosItem;
import net.mcreator.cannon.item.JackSparrowItem;
import net.mcreator.cannon.item.HeartOfDavidJonesItem;
import net.mcreator.cannon.item.HalfEatenBananItem;
import net.mcreator.cannon.item.FlintlockItem;
import net.mcreator.cannon.item.DutchmanpeaceItem;
import net.mcreator.cannon.item.DavyJonesItem;
import net.mcreator.cannon.item.ChalicewithtearItem;
import net.mcreator.cannon.item.ChalicefullItem;
import net.mcreator.cannon.item.ChaliceItem;
import net.mcreator.cannon.item.CannonballosItem;
import net.mcreator.cannon.item.BulletitemItem;
import net.mcreator.cannon.item.BlackBeardItem;
import net.mcreator.cannon.item.BillTurnerItem;
import net.mcreator.cannon.item.BarbossaItem;
import net.mcreator.cannon.item.BandanaItem;
import net.mcreator.cannon.item.BananItem;
import net.mcreator.cannon.item.AngelicaTeachItem;
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
	public static final RegistryObject<Item> MERMAID_SPAWN_EGG = REGISTRY.register("mermaid_spawn_egg", () -> new ForgeSpawnEggItem(CannonModEntities.MERMAID, -3578331, -2904696, new Item.Properties()));
	public static final RegistryObject<Item> FOUNTAIN_OF_YOUTH_DRIP = block(CannonModBlocks.FOUNTAIN_OF_YOUTH_DRIP);
	public static final RegistryObject<Item> MERMAIDTEAR = REGISTRY.register("mermaidtear", () -> new MermaidtearItem());
	public static final RegistryObject<Item> CHALICEWITHTEAR = REGISTRY.register("chalicewithtear", () -> new ChalicewithtearItem());
	public static final RegistryObject<Item> ANGELICA_TEACH_HELMET = REGISTRY.register("angelica_teach_helmet", () -> new AngelicaTeachItem.Helmet());
	public static final RegistryObject<Item> ANGELICA_TEACH_CHESTPLATE = REGISTRY.register("angelica_teach_chestplate", () -> new AngelicaTeachItem.Chestplate());
	public static final RegistryObject<Item> ANGELICA_TEACH_LEGGINGS = REGISTRY.register("angelica_teach_leggings", () -> new AngelicaTeachItem.Leggings());
	public static final RegistryObject<Item> ANGELICA_TEACH_BOOTS = REGISTRY.register("angelica_teach_boots", () -> new AngelicaTeachItem.Boots());
	public static final RegistryObject<Item> JOSHAMEE_GIBBS_HELMET = REGISTRY.register("joshamee_gibbs_helmet", () -> new JoshameeGibbsItem.Helmet());
	public static final RegistryObject<Item> JOSHAMEE_GIBBS_CHESTPLATE = REGISTRY.register("joshamee_gibbs_chestplate", () -> new JoshameeGibbsItem.Chestplate());
	public static final RegistryObject<Item> JOSHAMEE_GIBBS_LEGGINGS = REGISTRY.register("joshamee_gibbs_leggings", () -> new JoshameeGibbsItem.Leggings());
	public static final RegistryObject<Item> JOSHAMEE_GIBBS_BOOTS = REGISTRY.register("joshamee_gibbs_boots", () -> new JoshameeGibbsItem.Boots());
	public static final RegistryObject<Item> PLANKS_OF_THE_QUEEN_ANNES_REVENGE = block(CannonModBlocks.PLANKS_OF_THE_QUEEN_ANNES_REVENGE);
	public static final RegistryObject<Item> SLABS_OF_THE_QUEEN_ANNES_REVENGE = block(CannonModBlocks.SLABS_OF_THE_QUEEN_ANNES_REVENGE);
	public static final RegistryObject<Item> STAIRS_OF_THE_QUEEN_ANNES_REVENGE = block(CannonModBlocks.STAIRS_OF_THE_QUEEN_ANNES_REVENGE);
	public static final RegistryObject<Item> SIDE_FIGURE_HEAD_OF_THE_QUEEN_ANNES_REVENGE = block(CannonModBlocks.SIDE_FIGURE_HEAD_OF_THE_QUEEN_ANNES_REVENGE);
	public static final RegistryObject<Item> CANNON_HATCH_OF_THE_QUEEN_ANNES_REVENGE = block(CannonModBlocks.CANNON_HATCH_OF_THE_QUEEN_ANNES_REVENGE);
	public static final RegistryObject<Item> STYLISH_FENCE = block(CannonModBlocks.STYLISH_FENCE);
	public static final RegistryObject<Item> STYLISH_WALL = block(CannonModBlocks.STYLISH_WALL);
	public static final RegistryObject<Item> ROPE_BLOCK = block(CannonModBlocks.ROPE_BLOCK);
	public static final RegistryObject<Item> RED_SAIL = block(CannonModBlocks.RED_SAIL);
	public static final RegistryObject<Item> RED_SAIL_SLAB = block(CannonModBlocks.RED_SAIL_SLAB);
	public static final RegistryObject<Item> RED_SAIL_STAIR = block(CannonModBlocks.RED_SAIL_STAIR);
	public static final RegistryObject<Item> RED_SAIL_WALL = block(CannonModBlocks.RED_SAIL_WALL);
	public static final RegistryObject<Item> LIGHT_RED_SAIL = block(CannonModBlocks.LIGHT_RED_SAIL);
	public static final RegistryObject<Item> QUARTERMASTER_SPAWN_EGG = REGISTRY.register("quartermaster_spawn_egg", () -> new ForgeSpawnEggItem(CannonModEntities.QUARTERMASTER, -4025995, -12500928, new Item.Properties()));
	public static final RegistryObject<Item> TALISMAN_OF_THE_VOODOO_MISTER = REGISTRY.register("talisman_of_the_voodoo_mister", () -> new TalismanOfTheVoodooMisterItem());
	public static final RegistryObject<Item> TALISMAN_OF_THE_BLACK_PEARL = REGISTRY.register("talisman_of_the_black_pearl", () -> new TalismanOfTheBlackPearlItem());
	public static final RegistryObject<Item> BLACK_BEARDS_CREW_SPAWN_EGG = REGISTRY.register("black_beards_crew_spawn_egg", () -> new ForgeSpawnEggItem(CannonModEntities.BLACK_BEARDS_CREW, -12975100, -16777216, new Item.Properties()));
	public static final RegistryObject<Item> GREY_PLANKS = block(CannonModBlocks.GREY_PLANKS);
	public static final RegistryObject<Item> GREY_PLANK_SLAB = block(CannonModBlocks.GREY_PLANK_SLAB);
	public static final RegistryObject<Item> GREY_PLANK_STAIR = block(CannonModBlocks.GREY_PLANK_STAIR);
	public static final RegistryObject<Item> BARREL = block(CannonModBlocks.BARREL);
	public static final RegistryObject<Item> WINDOW_OF_THE_BLACK_PEARL = block(CannonModBlocks.WINDOW_OF_THE_BLACK_PEARL);
	public static final RegistryObject<Item> CHANDELIER = block(CannonModBlocks.CHANDELIER);
	public static final RegistryObject<Item> SLIM_FENCE = block(CannonModBlocks.SLIM_FENCE);
	public static final RegistryObject<Item> BLACK_PEARL_STATUE = block(CannonModBlocks.BLACK_PEARL_STATUE);
	public static final RegistryObject<Item> BARBOSSA_HELMET = REGISTRY.register("barbossa_helmet", () -> new BarbossaItem.Helmet());
	public static final RegistryObject<Item> BARBOSSA_CHESTPLATE = REGISTRY.register("barbossa_chestplate", () -> new BarbossaItem.Chestplate());
	public static final RegistryObject<Item> BARBOSSA_LEGGINGS = REGISTRY.register("barbossa_leggings", () -> new BarbossaItem.Leggings());
	public static final RegistryObject<Item> BARBOSSA_BOOTS = REGISTRY.register("barbossa_boots", () -> new BarbossaItem.Boots());
	public static final RegistryObject<Item> LOCKER_SAND = block(CannonModBlocks.LOCKER_SAND);
	public static final RegistryObject<Item> BLACK_PLANK_TRIM = block(CannonModBlocks.BLACK_PLANK_TRIM);
	public static final RegistryObject<Item> JACKS_COMPASS = REGISTRY.register("jacks_compass", () -> new JacksCompassItem());
	public static final RegistryObject<Item> JACKDOS_HELMET = REGISTRY.register("jackdos_helmet", () -> new JackdosItem.Helmet());
	public static final RegistryObject<Item> JACKDOS_CHESTPLATE = REGISTRY.register("jackdos_chestplate", () -> new JackdosItem.Chestplate());
	public static final RegistryObject<Item> BLACK_PEARL_MAST = block(CannonModBlocks.BLACK_PEARL_MAST);
	public static final RegistryObject<Item> HANGING_VINES_DUTCHMAN = block(CannonModBlocks.HANGING_VINES_DUTCHMAN);
	public static final RegistryObject<Item> DUTCHMAN_PLANK_1 = block(CannonModBlocks.DUTCHMAN_PLANK_1);
	public static final RegistryObject<Item> DUTCHMAN_PLANK_2 = block(CannonModBlocks.DUTCHMAN_PLANK_2);
	public static final RegistryObject<Item> DUTCHMAN_PLANK_3 = block(CannonModBlocks.DUTCHMAN_PLANK_3);
	public static final RegistryObject<Item> DUTCHMAN_PLANK_4 = block(CannonModBlocks.DUTCHMAN_PLANK_4);
	public static final RegistryObject<Item> DUTCHMAN_WALL_1 = block(CannonModBlocks.DUTCHMAN_WALL_1);
	public static final RegistryObject<Item> DUTCHMAN_WALL_2 = block(CannonModBlocks.DUTCHMAN_WALL_2);
	public static final RegistryObject<Item> DUTCHMAN_SLAB_1 = block(CannonModBlocks.DUTCHMAN_SLAB_1);
	public static final RegistryObject<Item> DUTCHMAN_SLAB_2 = block(CannonModBlocks.DUTCHMAN_SLAB_2);
	public static final RegistryObject<Item> DUTCHMAN_SLAB_3 = block(CannonModBlocks.DUTCHMAN_SLAB_3);
	public static final RegistryObject<Item> DUTCHMAN_STAIR = block(CannonModBlocks.DUTCHMAN_STAIR);
	public static final RegistryObject<Item> DUTCHMAN_STAIR_1 = block(CannonModBlocks.DUTCHMAN_STAIR_1);
	public static final RegistryObject<Item> DUTCHMAN_STAIR_2 = block(CannonModBlocks.DUTCHMAN_STAIR_2);
	public static final RegistryObject<Item> BLACK_PEARL_BOW_1 = block(CannonModBlocks.BLACK_PEARL_BOW_1);
	public static final RegistryObject<Item> BLACK_PEARL_BOW_2 = block(CannonModBlocks.BLACK_PEARL_BOW_2);
	public static final RegistryObject<Item> QUEEN_ANNES_REVENGE_WINDOW = block(CannonModBlocks.QUEEN_ANNES_REVENGE_WINDOW);
	public static final RegistryObject<Item> BLACK_PEARL_BIRD_TRIM = block(CannonModBlocks.BLACK_PEARL_BIRD_TRIM);
	public static final RegistryObject<Item> BLACK_PEARL_CABIN_ROOF = block(CannonModBlocks.BLACK_PEARL_CABIN_ROOF);
	public static final RegistryObject<Item> BLACK_PEARL_CABIN_ROOF_SLAB = block(CannonModBlocks.BLACK_PEARL_CABIN_ROOF_SLAB);
	public static final RegistryObject<Item> BLACK_PEARL_CABIN_ROOF_STAIR = block(CannonModBlocks.BLACK_PEARL_CABIN_ROOF_STAIR);
	public static final RegistryObject<Item> BLACK_PEARL_BACK_TRIM = block(CannonModBlocks.BLACK_PEARL_BACK_TRIM);

	// Start of user code block custom items
	// End of user code block custom items
	private static RegistryObject<Item> block(RegistryObject<Block> block) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
	}

	private static RegistryObject<Item> doubleBlock(RegistryObject<Block> block) {
		return REGISTRY.register(block.getId().getPath(), () -> new DoubleHighBlockItem(block.get(), new Item.Properties()));
	}
}
