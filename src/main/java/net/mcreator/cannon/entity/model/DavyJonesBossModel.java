package net.mcreator.cannon.entity.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.mcreator.cannon.entity.DavyJonesBossEntity;

public class DavyJonesBossModel extends GeoModel<DavyJonesBossEntity> {
	@Override
	public ResourceLocation getAnimationResource(DavyJonesBossEntity entity) {
		return new ResourceLocation("cannon", "animations/davyjonesboss.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(DavyJonesBossEntity entity) {
		return new ResourceLocation("cannon", "geo/davyjonesboss.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(DavyJonesBossEntity entity) {
		return new ResourceLocation("cannon", "textures/entities/jonesboss.png");
	}

}
