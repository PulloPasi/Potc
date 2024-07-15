package net.mcreator.cannon.block.renderer;

import software.bernie.geckolib.renderer.GeoItemRenderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import net.mcreator.cannon.block.model.DeadMansChestDisplayModel;
import net.mcreator.cannon.block.display.DeadMansChestDisplayItem;

public class DeadMansChestDisplayItemRenderer extends GeoItemRenderer<DeadMansChestDisplayItem> {
	public DeadMansChestDisplayItemRenderer() {
		super(new DeadMansChestDisplayModel());
	}

	@Override
	public RenderType getRenderType(DeadMansChestDisplayItem animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}
}
