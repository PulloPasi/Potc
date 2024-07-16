package net.mcreator.cannon.entity.model;

import software.bernie.geckolib.core.animation.AnimationState;

public class MermaidModel extends GeoModel<MermaidEntity> {
	@Override
	public ResourceLocation getAnimationResource(MermaidEntity entity) {
		return new ResourceLocation("cannon", "animations/mermaid.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(MermaidEntity entity) {
		return new ResourceLocation("cannon", "geo/mermaid.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(MermaidEntity entity) {
		return new ResourceLocation("cannon", "textures/entities/" + entity.getTexture() + ".png");
	}

}