package net.mcreator.cannon.entity.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.mcreator.cannon.entity.BlackBeardsCrewEntity;

public class BlackBeardsCrewModel extends GeoModel<BlackBeardsCrewEntity> {
	@Override
	public ResourceLocation getAnimationResource(BlackBeardsCrewEntity entity) {
		return new ResourceLocation("cannon", "animations/qarcrewmate_-_converted.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(BlackBeardsCrewEntity entity) {
		return new ResourceLocation("cannon", "geo/qarcrewmate_-_converted.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(BlackBeardsCrewEntity entity) {
		return new ResourceLocation("cannon", "textures/entities/" + entity.getTexture() + ".png");
	}

}
