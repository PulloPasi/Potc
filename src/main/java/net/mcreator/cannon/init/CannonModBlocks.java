
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.cannon.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;

import net.mcreator.cannon.block.StairsOfTheFlyingDutchmanBlock;
import net.mcreator.cannon.block.SlabsOfTheFlyingDutchmanBlock;
import net.mcreator.cannon.block.SailsOfTheBlackPearlPatchBlock;
import net.mcreator.cannon.block.SailsOfTheBlackPearlBlock;
import net.mcreator.cannon.block.RopeBlock;
import net.mcreator.cannon.block.PulleyBlock;
import net.mcreator.cannon.block.PlanksOfTheFlyingDutchmanBlock;
import net.mcreator.cannon.block.LadderThatHasBeenStolenFromMikaelsBackyardBlock;
import net.mcreator.cannon.block.GloriouslanternBlock;
import net.mcreator.cannon.block.FerrymansChasingCannonBlock;
import net.mcreator.cannon.block.FerrymansCannonBlock;
import net.mcreator.cannon.block.FaceOfTheFerrymanBlock;
import net.mcreator.cannon.block.DutchmansailsBlock;
import net.mcreator.cannon.block.Dutchmansails3Block;
import net.mcreator.cannon.block.Dutchmansails2Block;
import net.mcreator.cannon.block.DoorOfTheFlyingDutchmanBlock;
import net.mcreator.cannon.block.CannonBlock;
import net.mcreator.cannon.block.BlackPlanksBlock;
import net.mcreator.cannon.block.BlackPlankTrapDoorBlock;
import net.mcreator.cannon.block.BlackPlankStairsBlock;
import net.mcreator.cannon.block.BlackPlankSlabBlock;
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
	// Start of user code block custom blocks
	// End of user code block custom blocks
}
