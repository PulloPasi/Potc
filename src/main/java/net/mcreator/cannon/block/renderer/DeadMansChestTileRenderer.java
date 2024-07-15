package net.mcreator.cannon.block.renderer;

public class DeadMansChestTileRenderer extends GeoBlockRenderer<DeadMansChestTileEntity> {
	public DeadMansChestTileRenderer() {
		super(new DeadMansChestBlockModel());
	}

	@Override
	public RenderType getRenderType(DeadMansChestTileEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}
}