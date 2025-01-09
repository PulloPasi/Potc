
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.cannon.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.block.Block;

import net.mcreator.cannon.block.WindowOfTheBlackPearlBlock;
import net.mcreator.cannon.block.StylishWallBlock;
import net.mcreator.cannon.block.StylishFenceBlock;
import net.mcreator.cannon.block.StairsOfTheQueenAnnesRevengeBlock;
import net.mcreator.cannon.block.StairsOfTheFlyingDutchmanBlock;
import net.mcreator.cannon.block.SlimFenceBlock;
import net.mcreator.cannon.block.SlabsOfTheQueenAnnesRevengeBlock;
import net.mcreator.cannon.block.SlabsOfTheFlyingDutchmanBlock;
import net.mcreator.cannon.block.SideFigureHeadOfTheQueenAnnesRevengeBlock;
import net.mcreator.cannon.block.SailsOfTheBlackPearlPatchBlock;
import net.mcreator.cannon.block.SailsOfTheBlackPearlBlock;
import net.mcreator.cannon.block.RopeBlockBlock;
import net.mcreator.cannon.block.RopeBlock;
import net.mcreator.cannon.block.RedSailWallBlock;
import net.mcreator.cannon.block.RedSailStairBlock;
import net.mcreator.cannon.block.RedSailSlabBlock;
import net.mcreator.cannon.block.RedSailBlock;
import net.mcreator.cannon.block.QueenAnnesRevengeWindowBlock;
import net.mcreator.cannon.block.PulleyBlock;
import net.mcreator.cannon.block.PlanksOfTheQueenAnnesRevengeBlock;
import net.mcreator.cannon.block.PlanksOfTheFlyingDutchmanBlock;
import net.mcreator.cannon.block.OrganBlock;
import net.mcreator.cannon.block.LockerSandBlock;
import net.mcreator.cannon.block.LightRedSailBlock;
import net.mcreator.cannon.block.LadderThatHasBeenStolenFromMikaelsBackyardBlock;
import net.mcreator.cannon.block.HangingVinesDutchmanBlock;
import net.mcreator.cannon.block.GunpowderbarrelBlock;
import net.mcreator.cannon.block.GreyPlanksBlock;
import net.mcreator.cannon.block.GreyPlankStairBlock;
import net.mcreator.cannon.block.GreyPlankSlabBlock;
import net.mcreator.cannon.block.GloriouslanternBlock;
import net.mcreator.cannon.block.FountainOfYouthDripBlock;
import net.mcreator.cannon.block.FerrymansChasingCannonBlock;
import net.mcreator.cannon.block.FerrymansCannonBlock;
import net.mcreator.cannon.block.FaceOfTheFerrymanBlock;
import net.mcreator.cannon.block.DutchmansailsBlock;
import net.mcreator.cannon.block.Dutchmansails3Block;
import net.mcreator.cannon.block.Dutchmansails2Block;
import net.mcreator.cannon.block.DutchmanWall2Block;
import net.mcreator.cannon.block.DutchmanWall1Block;
import net.mcreator.cannon.block.DutchmanStairBlock;
import net.mcreator.cannon.block.DutchmanStair2Block;
import net.mcreator.cannon.block.DutchmanStair1Block;
import net.mcreator.cannon.block.DutchmanSlab3Block;
import net.mcreator.cannon.block.DutchmanSlab2Block;
import net.mcreator.cannon.block.DutchmanSlab1Block;
import net.mcreator.cannon.block.DutchmanPlank4Block;
import net.mcreator.cannon.block.DutchmanPlank3Block;
import net.mcreator.cannon.block.DutchmanPlank2Block;
import net.mcreator.cannon.block.DutchmanPlank1Block;
import net.mcreator.cannon.block.DoorOfTheFlyingDutchmanBlock;
import net.mcreator.cannon.block.DeadMansChestBlock;
import net.mcreator.cannon.block.ChandelierBlock;
import net.mcreator.cannon.block.CannonHatchOfTheQueenAnnesRevengeBlock;
import net.mcreator.cannon.block.CannonBlock;
import net.mcreator.cannon.block.BlackPlanksBlock;
import net.mcreator.cannon.block.BlackPlankTrimBlock;
import net.mcreator.cannon.block.BlackPlankTrapDoorBlock;
import net.mcreator.cannon.block.BlackPlankStairsBlock;
import net.mcreator.cannon.block.BlackPlankSlabBlock;
import net.mcreator.cannon.block.BlackPearlStatueBlock;
import net.mcreator.cannon.block.BlackPearlMastBlock;
import net.mcreator.cannon.block.BlackPearlCabinRoofStairBlock;
import net.mcreator.cannon.block.BlackPearlCabinRoofSlabBlock;
import net.mcreator.cannon.block.BlackPearlCabinRoofBlock;
import net.mcreator.cannon.block.BlackPearlBow2Block;
import net.mcreator.cannon.block.BlackPearlBow1Block;
import net.mcreator.cannon.block.BlackPearlBirdTrimBlock;
import net.mcreator.cannon.block.BlackPearlBackTrimBlock;
import net.mcreator.cannon.block.BarrelBlock;
import net.mcreator.cannon.block.BarnaclesBlock;
import net.mcreator.cannon.CannonMod;

public class CannonModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, CannonMod.MODID);
	public static final RegistryObject<Block> CANNON = REGISTRY.register("cannon", () -> new CannonBlock());
	public static final RegistryObject<Block> BLACK_PLANKS = REGISTRY.register("black_planks", () -> new BlackPlanksBlock());
	public static final RegistryObject<Block> BLACK_PLANK_SLAB = REGISTRY.register("black_plank_slab", () -> new BlackPlankSlabBlock());
	public static final RegistryObject<Block> BLACK_PLANK_TRAP_DOOR = REGISTRY.register("black_plank_trap_door", () -> new BlackPlankTrapDoorBlock());
	public static final RegistryObject<Block> DUTCHMANSAILS = REGISTRY.register("dutchmansails", () -> new DutchmansailsBlock());
	public static final RegistryObject<Block> GLORIOUSLANTERN = REGISTRY.register("gloriouslantern", () -> new GloriouslanternBlock());
	public static final RegistryObject<Block> BLACK_PLANK_STAIRS = REGISTRY.register("black_plank_stairs", () -> new BlackPlankStairsBlock());
	public static final RegistryObject<Block> DUTCHMANSAILS_2 = REGISTRY.register("dutchmansails_2", () -> new Dutchmansails2Block());
	public static final RegistryObject<Block> DUTCHMANSAILS_3 = REGISTRY.register("dutchmansails_3", () -> new Dutchmansails3Block());
	public static final RegistryObject<Block> FACE_OF_THE_FERRYMAN = REGISTRY.register("face_of_the_ferryman", () -> new FaceOfTheFerrymanBlock());
	public static final RegistryObject<Block> FERRYMANS_CANNON = REGISTRY.register("ferrymans_cannon", () -> new FerrymansCannonBlock());
	public static final RegistryObject<Block> LADDER_THAT_HAS_BEEN_STOLEN_FROM_MIKAELS_BACKYARD = REGISTRY.register("ladder_that_has_been_stolen_from_mikaels_backyard", () -> new LadderThatHasBeenStolenFromMikaelsBackyardBlock());
	public static final RegistryObject<Block> PLANKS_OF_THE_FLYING_DUTCHMAN = REGISTRY.register("planks_of_the_flying_dutchman", () -> new PlanksOfTheFlyingDutchmanBlock());
	public static final RegistryObject<Block> FERRYMANS_CHASING_CANNON = REGISTRY.register("ferrymans_chasing_cannon", () -> new FerrymansChasingCannonBlock());
	public static final RegistryObject<Block> DOOR_OF_THE_FLYING_DUTCHMAN = REGISTRY.register("door_of_the_flying_dutchman", () -> new DoorOfTheFlyingDutchmanBlock());
	public static final RegistryObject<Block> STAIRS_OF_THE_FLYING_DUTCHMAN = REGISTRY.register("stairs_of_the_flying_dutchman", () -> new StairsOfTheFlyingDutchmanBlock());
	public static final RegistryObject<Block> BARNACLES = REGISTRY.register("barnacles", () -> new BarnaclesBlock());
	public static final RegistryObject<Block> ROPE = REGISTRY.register("rope", () -> new RopeBlock());
	public static final RegistryObject<Block> SLABS_OF_THE_FLYING_DUTCHMAN = REGISTRY.register("slabs_of_the_flying_dutchman", () -> new SlabsOfTheFlyingDutchmanBlock());
	public static final RegistryObject<Block> SAILS_OF_THE_BLACK_PEARL = REGISTRY.register("sails_of_the_black_pearl", () -> new SailsOfTheBlackPearlBlock());
	public static final RegistryObject<Block> SAILS_OF_THE_BLACK_PEARL_PATCH = REGISTRY.register("sails_of_the_black_pearl_patch", () -> new SailsOfTheBlackPearlPatchBlock());
	public static final RegistryObject<Block> PULLEY = REGISTRY.register("pulley", () -> new PulleyBlock());
	public static final RegistryObject<Block> GUNPOWDERBARREL = REGISTRY.register("gunpowderbarrel", () -> new GunpowderbarrelBlock());
	public static final RegistryObject<Block> DEAD_MANS_CHEST = REGISTRY.register("dead_mans_chest", () -> new DeadMansChestBlock());
	public static final RegistryObject<Block> FOUNTAIN_OF_YOUTH_DRIP = REGISTRY.register("fountain_of_youth_drip", () -> new FountainOfYouthDripBlock());
	public static final RegistryObject<Block> PLANKS_OF_THE_QUEEN_ANNES_REVENGE = REGISTRY.register("planks_of_the_queen_annes_revenge", () -> new PlanksOfTheQueenAnnesRevengeBlock());
	public static final RegistryObject<Block> SLABS_OF_THE_QUEEN_ANNES_REVENGE = REGISTRY.register("slabs_of_the_queen_annes_revenge", () -> new SlabsOfTheQueenAnnesRevengeBlock());
	public static final RegistryObject<Block> STAIRS_OF_THE_QUEEN_ANNES_REVENGE = REGISTRY.register("stairs_of_the_queen_annes_revenge", () -> new StairsOfTheQueenAnnesRevengeBlock());
	public static final RegistryObject<Block> SIDE_FIGURE_HEAD_OF_THE_QUEEN_ANNES_REVENGE = REGISTRY.register("side_figure_head_of_the_queen_annes_revenge", () -> new SideFigureHeadOfTheQueenAnnesRevengeBlock());
	public static final RegistryObject<Block> CANNON_HATCH_OF_THE_QUEEN_ANNES_REVENGE = REGISTRY.register("cannon_hatch_of_the_queen_annes_revenge", () -> new CannonHatchOfTheQueenAnnesRevengeBlock());
	public static final RegistryObject<Block> STYLISH_FENCE = REGISTRY.register("stylish_fence", () -> new StylishFenceBlock());
	public static final RegistryObject<Block> STYLISH_WALL = REGISTRY.register("stylish_wall", () -> new StylishWallBlock());
	public static final RegistryObject<Block> ROPE_BLOCK = REGISTRY.register("rope_block", () -> new RopeBlockBlock());
	public static final RegistryObject<Block> RED_SAIL = REGISTRY.register("red_sail", () -> new RedSailBlock());
	public static final RegistryObject<Block> RED_SAIL_SLAB = REGISTRY.register("red_sail_slab", () -> new RedSailSlabBlock());
	public static final RegistryObject<Block> RED_SAIL_STAIR = REGISTRY.register("red_sail_stair", () -> new RedSailStairBlock());
	public static final RegistryObject<Block> RED_SAIL_WALL = REGISTRY.register("red_sail_wall", () -> new RedSailWallBlock());
	public static final RegistryObject<Block> LIGHT_RED_SAIL = REGISTRY.register("light_red_sail", () -> new LightRedSailBlock());
	public static final RegistryObject<Block> GREY_PLANKS = REGISTRY.register("grey_planks", () -> new GreyPlanksBlock());
	public static final RegistryObject<Block> GREY_PLANK_SLAB = REGISTRY.register("grey_plank_slab", () -> new GreyPlankSlabBlock());
	public static final RegistryObject<Block> GREY_PLANK_STAIR = REGISTRY.register("grey_plank_stair", () -> new GreyPlankStairBlock());
	public static final RegistryObject<Block> BARREL = REGISTRY.register("barrel", () -> new BarrelBlock());
	public static final RegistryObject<Block> WINDOW_OF_THE_BLACK_PEARL = REGISTRY.register("window_of_the_black_pearl", () -> new WindowOfTheBlackPearlBlock());
	public static final RegistryObject<Block> CHANDELIER = REGISTRY.register("chandelier", () -> new ChandelierBlock());
	public static final RegistryObject<Block> SLIM_FENCE = REGISTRY.register("slim_fence", () -> new SlimFenceBlock());
	public static final RegistryObject<Block> BLACK_PEARL_STATUE = REGISTRY.register("black_pearl_statue", () -> new BlackPearlStatueBlock());
	public static final RegistryObject<Block> LOCKER_SAND = REGISTRY.register("locker_sand", () -> new LockerSandBlock());
	public static final RegistryObject<Block> BLACK_PLANK_TRIM = REGISTRY.register("black_plank_trim", () -> new BlackPlankTrimBlock());
	public static final RegistryObject<Block> BLACK_PEARL_MAST = REGISTRY.register("black_pearl_mast", () -> new BlackPearlMastBlock());
	public static final RegistryObject<Block> HANGING_VINES_DUTCHMAN = REGISTRY.register("hanging_vines_dutchman", () -> new HangingVinesDutchmanBlock());
	public static final RegistryObject<Block> DUTCHMAN_PLANK_1 = REGISTRY.register("dutchman_plank_1", () -> new DutchmanPlank1Block());
	public static final RegistryObject<Block> DUTCHMAN_PLANK_2 = REGISTRY.register("dutchman_plank_2", () -> new DutchmanPlank2Block());
	public static final RegistryObject<Block> DUTCHMAN_PLANK_3 = REGISTRY.register("dutchman_plank_3", () -> new DutchmanPlank3Block());
	public static final RegistryObject<Block> DUTCHMAN_PLANK_4 = REGISTRY.register("dutchman_plank_4", () -> new DutchmanPlank4Block());
	public static final RegistryObject<Block> DUTCHMAN_WALL_1 = REGISTRY.register("dutchman_wall_1", () -> new DutchmanWall1Block());
	public static final RegistryObject<Block> DUTCHMAN_WALL_2 = REGISTRY.register("dutchman_wall_2", () -> new DutchmanWall2Block());
	public static final RegistryObject<Block> DUTCHMAN_SLAB_1 = REGISTRY.register("dutchman_slab_1", () -> new DutchmanSlab1Block());
	public static final RegistryObject<Block> DUTCHMAN_SLAB_2 = REGISTRY.register("dutchman_slab_2", () -> new DutchmanSlab2Block());
	public static final RegistryObject<Block> DUTCHMAN_SLAB_3 = REGISTRY.register("dutchman_slab_3", () -> new DutchmanSlab3Block());
	public static final RegistryObject<Block> DUTCHMAN_STAIR = REGISTRY.register("dutchman_stair", () -> new DutchmanStairBlock());
	public static final RegistryObject<Block> DUTCHMAN_STAIR_1 = REGISTRY.register("dutchman_stair_1", () -> new DutchmanStair1Block());
	public static final RegistryObject<Block> DUTCHMAN_STAIR_2 = REGISTRY.register("dutchman_stair_2", () -> new DutchmanStair2Block());
	public static final RegistryObject<Block> BLACK_PEARL_BOW_1 = REGISTRY.register("black_pearl_bow_1", () -> new BlackPearlBow1Block());
	public static final RegistryObject<Block> BLACK_PEARL_BOW_2 = REGISTRY.register("black_pearl_bow_2", () -> new BlackPearlBow2Block());
	public static final RegistryObject<Block> QUEEN_ANNES_REVENGE_WINDOW = REGISTRY.register("queen_annes_revenge_window", () -> new QueenAnnesRevengeWindowBlock());
	public static final RegistryObject<Block> BLACK_PEARL_BIRD_TRIM = REGISTRY.register("black_pearl_bird_trim", () -> new BlackPearlBirdTrimBlock());
	public static final RegistryObject<Block> BLACK_PEARL_CABIN_ROOF = REGISTRY.register("black_pearl_cabin_roof", () -> new BlackPearlCabinRoofBlock());
	public static final RegistryObject<Block> BLACK_PEARL_CABIN_ROOF_SLAB = REGISTRY.register("black_pearl_cabin_roof_slab", () -> new BlackPearlCabinRoofSlabBlock());
	public static final RegistryObject<Block> BLACK_PEARL_CABIN_ROOF_STAIR = REGISTRY.register("black_pearl_cabin_roof_stair", () -> new BlackPearlCabinRoofStairBlock());
	public static final RegistryObject<Block> BLACK_PEARL_BACK_TRIM = REGISTRY.register("black_pearl_back_trim", () -> new BlackPearlBackTrimBlock());
	public static final RegistryObject<Block> ORGAN = REGISTRY.register("organ", () -> new OrganBlock());

	// Start of user code block custom blocks
	// End of user code block custom blocks
	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static class ClientSideHandler {
		@SubscribeEvent
		public static void blockColorLoad(RegisterColorHandlersEvent.Block event) {
			CannonBlock.blockColorLoad(event);
			FerrymansCannonBlock.blockColorLoad(event);
			FerrymansChasingCannonBlock.blockColorLoad(event);
		}

		@SubscribeEvent
		public static void itemColorLoad(RegisterColorHandlersEvent.Item event) {
			CannonBlock.itemColorLoad(event);
			FerrymansCannonBlock.itemColorLoad(event);
			FerrymansChasingCannonBlock.itemColorLoad(event);
		}
	}
}
