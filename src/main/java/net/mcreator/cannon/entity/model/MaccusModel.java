package net.mcreator.cannon.entity.model;

import software.bernie.geckolib.model.data.EntityModelData;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.constant.DataTickets;

import net.minecraft.util.Mth;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.cannon.entity.MaccusEntity;

public class MaccusModel extends GeoModel<MaccusEntity> {
	@Override
	public ResourceLocation getAnimationResource(MaccusEntity entity) {
		return new ResourceLocation("cannon", "animations/maccus_-_converted.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(MaccusEntity entity) {
		return new ResourceLocation("cannon", "geo/maccus_-_converted.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(MaccusEntity entity) {
		return new ResourceLocation("cannon", "textures/entities/" + entity.getTexture() + ".png");
	}

	@Override
	public void setCustomAnimations(MaccusEntity animatable, long instanceId, AnimationState animationState) {
		CoreGeoBone head = getAnimationProcessor().getBone("Head");
		if (head != null) {
			EntityModelData entityData = (EntityModelData) animationState.getData(DataTickets.ENTITY_MODEL_DATA);
			head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
			head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
		}

	}
}
