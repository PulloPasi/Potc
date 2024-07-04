package net.mcreator.cannon.entity.model;

import software.bernie.geckolib.model.data.EntityModelData;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.constant.DataTickets;

import net.minecraft.util.Mth;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.cannon.entity.PartOfTheCrewEntity;

public class PartOfTheCrewModel extends GeoModel<PartOfTheCrewEntity> {
	@Override
	public ResourceLocation getAnimationResource(PartOfTheCrewEntity entity) {
		return new ResourceLocation("cannon", "animations/hollantiorja.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(PartOfTheCrewEntity entity) {
		return new ResourceLocation("cannon", "geo/hollantiorja.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(PartOfTheCrewEntity entity) {
		return new ResourceLocation("cannon", "textures/entities/" + entity.getTexture() + ".png");
	}

	@Override
	public void setCustomAnimations(PartOfTheCrewEntity animatable, long instanceId, AnimationState animationState) {
		CoreGeoBone head = getAnimationProcessor().getBone("Head");
		if (head != null) {
			EntityModelData entityData = (EntityModelData) animationState.getData(DataTickets.ENTITY_MODEL_DATA);
			head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
			head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
		}

	}
}