package net.mcreator.cannon.item.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

import net.mcreator.cannon.item.JacksCompassItem;
import net.mcreator.cannon.item.model.JacksCompassModel;

import software.bernie.geckolib.renderer.GeoItemRenderer;

public class JacksCompassRenderer extends GeoItemRenderer<JacksCompassItem> {
    private static final float UNIT = 1.0F / 16.0F;

    public JacksCompassRenderer() {
        super(new JacksCompassModel());
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext displayContext, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        applyDisplayTransforms(displayContext, poseStack);
        super.renderByItem(stack, displayContext, poseStack, bufferSource, packedLight, packedOverlay);
    }

    private static void applyDisplayTransforms(ItemDisplayContext context, PoseStack poseStack) {
        switch (context) {
            case THIRD_PERSON_RIGHT_HAND, THIRD_PERSON_LEFT_HAND -> {
                poseStack.translate(0.2F * UNIT, -3.0F * UNIT, 0.85F * UNIT);
                poseStack.mulPose(Axis.YP.rotationDegrees(162.0F));
                poseStack.mulPose(Axis.ZP.rotationDegrees(3.0F));
                poseStack.scale(0.72F, 0.71F, 0.71F);
            }
            case FIRST_PERSON_RIGHT_HAND -> {
                poseStack.translate(0.45F * UNIT, -0.05F * UNIT, 0.55F * UNIT);
                poseStack.mulPose(Axis.XP.rotationDegrees(32.0F));
                poseStack.mulPose(Axis.YP.rotationDegrees(156.0F));
                poseStack.mulPose(Axis.ZP.rotationDegrees(-2.0F));
                poseStack.scale(0.82F, 0.8F, 0.78F);
            }
            case FIRST_PERSON_LEFT_HAND -> {
                poseStack.translate(-0.3F * UNIT, -0.05F * UNIT, -2.2F * UNIT);
                poseStack.mulPose(Axis.XP.rotationDegrees(35.0F));
                poseStack.mulPose(Axis.YP.rotationDegrees(148.0F));
                poseStack.mulPose(Axis.ZP.rotationDegrees(-6.0F));
                poseStack.scale(0.82F, 0.8F, 0.78F);
            }
            case GUI -> {
                poseStack.translate(1.4F * UNIT, -5.2F * UNIT, 0.0F);
                poseStack.mulPose(Axis.XP.rotationDegrees(56.0F));
                poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
                poseStack.scale(0.9F, 0.9F, 0.9F);
            }
            case GROUND -> {
                poseStack.translate(0.0F, -7.8F * UNIT, 0.2F * UNIT);
                poseStack.scale(0.82F, 0.82F, 0.82F);
            }
            case FIXED -> {
                poseStack.translate(0.0F, -5.8F * UNIT, 3.9F * UNIT);
                poseStack.mulPose(Axis.XP.rotationDegrees(-56.0F));
                poseStack.scale(0.86F, 0.86F, 0.86F);
            }
            default -> {
            }
        }
    }

}
