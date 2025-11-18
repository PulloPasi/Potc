package net.mcreator.cannon.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.mcreator.cannon.entity.LeadEntity;
import net.mcreator.cannon.util.VSShipUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;

public class LeadRenderer extends EntityRenderer<LeadEntity> {
    private static final double THICKNESS = 0.02;
    private static final int ROPE_RED = 214;
    private static final int ROPE_GREEN = 188;
    private static final int ROPE_BLUE = 141;
    private static final int ROPE_ALPHA = 255;

    public LeadRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(LeadEntity entity, float entityYaw, float partialTicks, PoseStack poseStack,
                       MultiBufferSource buffer, int packedLight) {
        BlockPos fromPos = entity.getFromPos();
        BlockPos toPos = entity.getToPos();
        if (fromPos == null || toPos == null) {
            return;
        }

        Vec3 worldFrom = VSShipUtils.getWorldBlockCenter(entity.level(), fromPos);
        Vec3 worldTo = VSShipUtils.getWorldBlockCenter(entity.level(), toPos);

        double interpX = Mth.lerp(partialTicks, entity.xo, entity.getX());
        double interpY = Mth.lerp(partialTicks, entity.yo, entity.getY());
        double interpZ = Mth.lerp(partialTicks, entity.zo, entity.getZ());
        Vec3 entityPos = new Vec3(interpX, interpY, interpZ);
        Vec3 start = worldFrom.subtract(entityPos);
        Vec3 end = worldTo.subtract(entityPos);

        poseStack.pushPose();
        VertexConsumer builder = buffer.getBuffer(RenderType.leash());
        Matrix4f matrix = poseStack.last().pose();

        Vec3 cameraPos = Minecraft.getInstance().gameRenderer.getMainCamera().getPosition();

        Vec3 direction = end.subtract(start);
        if (direction.lengthSqr() < 1.0E-6) {
            poseStack.popPose();
            return;
        }

        Vec3 ropeMidWorld = entityPos.add(start).add(entityPos.add(end)).scale(0.5);
        Vec3 viewVector = cameraPos.subtract(ropeMidWorld);
        Vec3 offsetDir = safeNormalize(direction.cross(viewVector));
        if (offsetDir == Vec3.ZERO || offsetDir.lengthSqr() < 1.0E-6) {
            offsetDir = safeNormalize(direction.cross(new Vec3(0, 1, 0)));
        }
        if (offsetDir == Vec3.ZERO || offsetDir.lengthSqr() < 1.0E-6) {
            offsetDir = safeNormalize(direction.cross(new Vec3(1, 0, 0)));
        }

        Vec3 offset = offsetDir == Vec3.ZERO ? new Vec3(THICKNESS, 0, 0) : offsetDir.scale(THICKNESS);

        Vec3 startA = start.add(offset);
        Vec3 startB = start.subtract(offset);
        Vec3 endA = end.add(offset);
        Vec3 endB = end.subtract(offset);

        Vec3 normalVec = safeNormalize(offset.cross(direction));
        float nx = (float) normalVec.x;
        float ny = (float) normalVec.y;
        float nz = (float) normalVec.z;

        addRibbonQuad(builder, matrix, startA, endA, endB, startB, nx, ny, nz, packedLight);

        poseStack.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(LeadEntity entity) {
        return TextureAtlas.LOCATION_BLOCKS;
    }

    private static Vec3 safeNormalize(Vec3 vec) {
        double length = vec.length();
        return length < 1.0E-4 ? Vec3.ZERO : vec.scale(1.0 / length);
    }

    private static void addRibbonQuad(VertexConsumer builder, Matrix4f matrix,
                                      Vec3 startA, Vec3 endA, Vec3 endB, Vec3 startB,
                                      float nx, float ny, float nz, int packedLight) {
        builder.vertex(matrix, (float) startA.x, (float) startA.y, (float) startA.z)
            .color(ROPE_RED, ROPE_GREEN, ROPE_BLUE, ROPE_ALPHA)
            .uv2(packedLight)
            .endVertex();
        builder.vertex(matrix, (float) endA.x, (float) endA.y, (float) endA.z)
            .color(ROPE_RED, ROPE_GREEN, ROPE_BLUE, ROPE_ALPHA)
            .uv2(packedLight)
            .endVertex();
        builder.vertex(matrix, (float) endB.x, (float) endB.y, (float) endB.z)
            .color(ROPE_RED, ROPE_GREEN, ROPE_BLUE, ROPE_ALPHA)
            .uv2(packedLight)
            .endVertex();

        builder.vertex(matrix, (float) startA.x, (float) startA.y, (float) startA.z)
            .color(ROPE_RED, ROPE_GREEN, ROPE_BLUE, ROPE_ALPHA)
            .uv2(packedLight)
            .endVertex();
        builder.vertex(matrix, (float) endB.x, (float) endB.y, (float) endB.z)
            .color(ROPE_RED, ROPE_GREEN, ROPE_BLUE, ROPE_ALPHA)
            .uv2(packedLight)
            .endVertex();
        builder.vertex(matrix, (float) startB.x, (float) startB.y, (float) startB.z)
            .color(ROPE_RED, ROPE_GREEN, ROPE_BLUE, ROPE_ALPHA)
            .uv2(packedLight)
            .endVertex();
    }
}
