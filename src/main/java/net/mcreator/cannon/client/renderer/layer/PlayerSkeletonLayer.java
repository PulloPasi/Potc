package net.mcreator.cannon.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import net.mcreator.cannon.client.model.Modelplayerskeleton;
import net.mcreator.cannon.init.CannonModItems;

public class PlayerSkeletonLayer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("cannon", "textures/entities/playerskeleton.png");

    private final Modelplayerskeleton<AbstractClientPlayer> skeletonModel;

    public PlayerSkeletonLayer(PlayerRenderer renderer, EntityModelSet modelSet, boolean slim) {
        super(renderer);
        this.skeletonModel = new Modelplayerskeleton<>(modelSet.bakeLayer(Modelplayerskeleton.LAYER_LOCATION));
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!hasAztecCoin(player)) {
            return;
        }

        PlayerModel<AbstractClientPlayer> parentModel = getParentModel();
        parentModel.setAllVisible(false);

        applyPlayerPose(parentModel);

        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityTranslucent(TEXTURE));
        skeletonModel.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

        parentModel.setAllVisible(true);
    }

    private void applyPlayerPose(PlayerModel<AbstractClientPlayer> parentModel) {
        copyPose(skeletonModel.Head, parentModel.head);
        copyPose(skeletonModel.Body, parentModel.body);
        copyPose(skeletonModel.RightArm, parentModel.rightArm);
        copyPose(skeletonModel.LeftArm, parentModel.leftArm);
        copyPose(skeletonModel.RightLeg, parentModel.rightLeg);
        copyPose(skeletonModel.LeftLeg, parentModel.leftLeg);

        skeletonModel.Waist.xRot = parentModel.body.xRot;
        skeletonModel.Waist.yRot = parentModel.body.yRot;
        skeletonModel.Waist.zRot = parentModel.body.zRot;
    }

    private static void copyPose(ModelPart target, ModelPart source) {
        target.xRot = source.xRot;
        target.yRot = source.yRot;
        target.zRot = source.zRot;
        target.x = source.x;
        target.y = source.y;
        target.z = source.z;
    }

    private static boolean hasAztecCoin(AbstractClientPlayer player) {
        for (ItemStack stack : player.getInventory().items) {
            if (stack.is(CannonModItems.AZTEC_COIN.get())) {
                return true;
            }
        }
        return false;
    }
}
