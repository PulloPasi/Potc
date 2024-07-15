package net.mcreator.cannon.block.renderer;

public class DeadMansChestDisplayItemRenderer extends GeoItemRenderer<DeadMansChestDisplayItem> {
	public DeadMansChestDisplayItemRenderer() {
		super(new DeadMansChestDisplayModel());
	}

	@Override
	public RenderType getRenderType(DeadMansChestDisplayItem animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}
}