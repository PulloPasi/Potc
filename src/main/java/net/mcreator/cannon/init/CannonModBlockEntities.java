
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.cannon.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.Block;

import net.mcreator.cannon.block.entity.GunpowderbarrelBlockEntity;
import net.mcreator.cannon.block.entity.DeadMansChestTileEntity;
import net.mcreator.cannon.CannonMod;

public class CannonModBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, CannonMod.MODID);
	public static final RegistryObject<BlockEntityType<?>> GUNPOWDERBARREL = register("gunpowderbarrel", CannonModBlocks.GUNPOWDERBARREL, GunpowderbarrelBlockEntity::new);
	public static final RegistryObject<BlockEntityType<DeadMansChestTileEntity>> DEAD_MANS_CHEST = REGISTRY.register("dead_mans_chest",
			() -> BlockEntityType.Builder.of(DeadMansChestTileEntity::new, CannonModBlocks.DEAD_MANS_CHEST.get()).build(null));

	private static RegistryObject<BlockEntityType<?>> register(String registryname, RegistryObject<Block> block, BlockEntityType.BlockEntitySupplier<?> supplier) {
		return REGISTRY.register(registryname, () -> BlockEntityType.Builder.of(supplier, block.get()).build(null));
	}
}
