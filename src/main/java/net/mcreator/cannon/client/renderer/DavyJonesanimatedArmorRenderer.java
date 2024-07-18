package net.mcreator.cannon.client.renderer;

import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.cache.object.GeoBone;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import net.mcreator.cannon.item.model.DavyJonesanimatedModel;
import net.mcreator.cannon.item.DavyJonesanimatedItem;

public class DavyJonesanimatedArmorRenderer extends GeoArmorRenderer<DavyJonesanimatedItem> {
	public DavyJonesanimatedArmorRenderer() {
		super(new DavyJonesanimatedModel());
		this.head = new GeoBone(null, "Helmet", false, (double) 0, false, false);
		this.body = new GeoBone(null, "Chestplate", false, (double) 0, false, false);
		this.rightArm = new GeoBone(null, "RigthPlate", false, (double) 0, false, false);
		this.leftArm = new GeoBone(null, "LeftPlate", false, (double) 0, false, false);
		this.rightLeg = new GeoBone(null, "RightLegging", false, (double) 0, false, false);
		this.leftLeg = new GeoBone(null, "LeftLegging", false, (double) 0, false, false);
		this.rightBoot = new GeoBone(null, "RightBoot", false, (double) 0, false, false);
		this.leftBoot = new GeoBone(null, "LeftBoot", false, (double) 0, false, false);
	}

	@Override
	public RenderType getRenderType(DavyJonesanimatedItem animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}
}
