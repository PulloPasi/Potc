
package net.mcreator.cannon.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.HumanoidModel;

import net.mcreator.cannon.entity.LeashHolderEntity;

public class LeashHolderRenderer extends HumanoidMobRenderer<LeashHolderEntity, HumanoidModel<LeashHolderEntity>> {
	public LeashHolderRenderer(EntityRendererProvider.Context context) {
		super(context, new HumanoidModel<LeashHolderEntity>(context.bakeLayer(ModelLayers.PLAYER)), 0.5f);
		this.addLayer(new HumanoidArmorLayer(this, new HumanoidModel(context.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)), new HumanoidModel(context.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)), context.getModelManager()));
	}

	@Override
	public ResourceLocation getTextureLocation(LeashHolderEntity entity) {
		return new ResourceLocation("cannon:textures/entities/invis.png");
	}
}
