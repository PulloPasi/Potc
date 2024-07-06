package net.mcreator.cannon.entity.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.mcreator.cannon.entity.PartOfTheCrewStrongEntity;

public class PartOfTheCrewStrongModel extends GeoModel<PartOfTheCrewStrongEntity> {
	@Override
	public ResourceLocation getAnimationResource(PartOfTheCrewStrongEntity entity) {
		return new ResourceLocation("cannon", "animations/herra.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(PartOfTheCrewStrongEntity entity) {
		return new ResourceLocation("cannon", "geo/herra.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(PartOfTheCrewStrongEntity entity) {
		return new ResourceLocation("cannon", "textures/entities/" + entity.getTexture() + ".png");
	}

}
