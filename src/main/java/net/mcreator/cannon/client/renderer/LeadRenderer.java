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
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;

public class LeadRenderer extends EntityRenderer<LeadEntity> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("minecraft", "textures/entity/lead_knot.png");

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

        Vec3 direction = end.subtract(start);
        Vec3 normal = safeNormalize(direction);
        float nx = (float) normal.x;
        float ny = (float) normal.y;
        float nz = (float) normal.z;

        Vec3 midWorld = worldFrom.add(worldTo).scale(0.5);
        Vec3 cameraPos = Minecraft.getInstance().gameRenderer.getMainCamera().getPosition();
        Vec3 viewVec = cameraPos.subtract(midWorld);
        Vec3 offsetNorm = safeNormalize(direction.cross(viewVec));
        if (offsetNorm.lengthSqr() < 1.0E-4) {
            offsetNorm = safeNormalize(direction.cross(new Vec3(0, 1, 0)));
        }
        if (offsetNorm.lengthSqr() < 1.0E-4) {
            offsetNorm = safeNormalize(direction.cross(new Vec3(1, 0, 0)));
        }
        if (offsetNorm.lengthSqr() < 1.0E-4) {
            poseStack.popPose();
            return;
        }

        double thickness = 0.045;
        Vec3 offset = offsetNorm.scale(thickness);

        addRibbonQuad(builder, matrix, start, end, offset, nx, ny, nz, packedLight);

        poseStack.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(LeadEntity entity) {
        return TEXTURE;
    }

    private static Vec3 safeNormalize(Vec3 vec) {
        double length = vec.length();
        return length < 1.0E-4 ? Vec3.ZERO : vec.scale(1.0 / length);
    }

    private static void addRibbonQuad(VertexConsumer builder, Matrix4f matrix, Vec3 start, Vec3 end,
                                      Vec3 offset, float nx, float ny, float nz, int packedLight) {
        Vec3 startA = start.add(offset);
        Vec3 startB = start.subtract(offset);
        Vec3 endA = end.add(offset);
        Vec3 endB = end.subtract(offset);

        final int r = 214;
        final int g = 188;
        final int b = 141;
        final int a = 255;

        // First triangle
        builder.vertex(matrix, (float) startA.x, (float) startA.y, (float) startA.z)
            .color(r, g, b, a).uv2(packedLight).normal(nx, ny, nz).endVertex();
        builder.vertex(matrix, (float) endA.x, (float) endA.y, (float) endA.z)
            .color(r, g, b, a).uv2(packedLight).normal(nx, ny, nz).endVertex();
        builder.vertex(matrix, (float) endB.x, (float) endB.y, (float) endB.z)
            .color(r, g, b, a).uv2(packedLight).normal(nx, ny, nz).endVertex();

        // Second triangle
        builder.vertex(matrix, (float) startA.x, (float) startA.y, (float) startA.z)
            .color(r, g, b, a).uv2(packedLight).normal(nx, ny, nz).endVertex();
        builder.vertex(matrix, (float) endB.x, (float) endB.y, (float) endB.z)
            .color(r, g, b, a).uv2(packedLight).normal(nx, ny, nz).endVertex();
        builder.vertex(matrix, (float) startB.x, (float) startB.y, (float) startB.z)
            .color(r, g, b, a).uv2(packedLight).normal(nx, ny, nz).endVertex();
    }
}
