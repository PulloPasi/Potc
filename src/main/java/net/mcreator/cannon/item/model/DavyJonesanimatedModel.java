package net.mcreator.cannon.item.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.mcreator.cannon.item.DavyJonesanimatedItem;

public class DavyJonesanimatedModel extends GeoModel<DavyJonesanimatedItem> {
	@Override
	public ResourceLocation getAnimationResource(DavyJonesanimatedItem object) {
		return new ResourceLocation("cannon", "animations/jonetsiconverted.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(DavyJonesanimatedItem object) {
		return new ResourceLocation("cannon", "geo/jonetsiconverted.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(DavyJonesanimatedItem object) {
		return new ResourceLocation("cannon", "textures/item/jontestiitem.png");
	}
}
