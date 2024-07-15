package net.mcreator.cannon.block.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.mcreator.cannon.block.entity.DeadMansChestTileEntity;

public class DeadMansChestBlockModel extends GeoModel<DeadMansChestTileEntity> {
	@Override
	public ResourceLocation getAnimationResource(DeadMansChestTileEntity animatable) {
		return new ResourceLocation("cannon", "animations/dedmanchest.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(DeadMansChestTileEntity animatable) {
		return new ResourceLocation("cannon", "geo/dedmanchest.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(DeadMansChestTileEntity animatable) {
		return new ResourceLocation("cannon", "textures/block/chest.png");
	}
}
