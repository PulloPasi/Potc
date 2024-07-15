package net.mcreator.cannon.block.model;

public class DeadMansChestDisplayModel extends GeoModel<DeadMansChestDisplayItem> {
	@Override
	public ResourceLocation getAnimationResource(DeadMansChestDisplayItem animatable) {
		return new ResourceLocation("cannon", "animations/dedmanchest.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(DeadMansChestDisplayItem animatable) {
		return new ResourceLocation("cannon", "geo/dedmanchest.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(DeadMansChestDisplayItem entity) {
		return new ResourceLocation("cannon", "textures/block/chest.png");
	}
}