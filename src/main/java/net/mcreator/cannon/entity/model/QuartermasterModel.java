package net.mcreator.cannon.entity.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.mcreator.cannon.entity.QuartermasterEntity;

public class QuartermasterModel extends GeoModel<QuartermasterEntity> {
	@Override
	public ResourceLocation getAnimationResource(QuartermasterEntity entity) {
		return new ResourceLocation("cannon", "animations/quartermaster.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(QuartermasterEntity entity) {
		return new ResourceLocation("cannon", "geo/quartermaster.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(QuartermasterEntity entity) {
		return new ResourceLocation("cannon", "textures/entities/" + entity.getTexture() + ".png");
	}

}
