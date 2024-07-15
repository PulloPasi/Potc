package net.mcreator.cannon.block.renderer;

import software.bernie.geckolib.renderer.GeoBlockRenderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import net.mcreator.cannon.block.model.DeadMansChestBlockModel;
import net.mcreator.cannon.block.entity.DeadMansChestTileEntity;

public class DeadMansChestTileRenderer extends GeoBlockRenderer<DeadMansChestTileEntity> {
	public DeadMansChestTileRenderer() {
		super(new DeadMansChestBlockModel());
	}

	@Override
	public RenderType getRenderType(DeadMansChestTileEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}
}
