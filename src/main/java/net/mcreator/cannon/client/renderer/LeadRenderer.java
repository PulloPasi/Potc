package net.mcreator.cannon.client.renderer;

import net.mcreator.cannon.entity.LeadEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.minecraft.core.BlockPos;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class LeadRenderer extends EntityRenderer<LeadEntity> {
    public LeadRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.0F;
    }

    @Override
    public void render(LeadEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        BlockPos from = entity.getFrom();
        BlockPos to = entity.getTo();
        
        if (from == null || to == null) return;
        
        Vec3 start = new Vec3(from.getX() + 0.5, from.getY() + 0.5, from.getZ() + 0.5);
        Vec3 end = new Vec3(to.getX() + 0.5, to.getY() + 0.5, to.getZ() + 0.5);
        
        // Draw line between points
        VertexConsumer builder = buffer.getBuffer(RenderType.lines());
        poseStack.pushPose();
        
        // Calculate line direction
        Vec3 diff = end.subtract(start);
        diff.length(); // Normalize the direction
        
        // Draw the line
        drawLine(poseStack, builder, start, end, 0.05f, 0.7f, 0.2f, 1.0f);
        
        poseStack.popPose();
    }
    
    private void drawLine(PoseStack poseStack, VertexConsumer builder, Vec3 start, Vec3 end, float r, float g, float b, float a) {
        Vec3 normal = end.subtract(start).normalize();
        Vec3 perp = new Vec3(-normal.z, 0, normal.x).normalize().scale(0.1);
        
        // Draw a thicker line using quads
        poseStack.pushPose();
        
        // Create a box along the line
        Vec3 min = start.add(perp).add(0, -0.05, 0);
        Vec3 max = end.add(perp.scale(-1)).add(0, 0.05, 0);
        
        // Draw the line as a thin box
        VoxelShape shape = Shapes.box(
            Math.min(min.x, max.x), Math.min(min.y, max.y), Math.min(min.z, max.z),
            Math.max(min.x, max.x), Math.max(min.y, max.y), Math.max(min.z, max.z)
        );
        
        // Render the shape
        shape.forAllEdges((x1, y1, z1, x2, y2, z2) -> {
            float f = (float)(x2 - x1);
            float f1 = (float)(y2 - y1);
            float f2 = (float)(z2 - z1);
            float f3 = (float)Math.sqrt(f * f + f1 * f1 + f2 * f2);
            f = f / f3;
            f1 = f1 / f3;
            f2 = f2 / f3;
            
            builder.vertex(poseStack.last().pose(), (float)(x1), (float)(y1), (float)(z1))
                  .color(r, g, b, a)
                  .normal(poseStack.last().normal(), f, f1, f2)
                  .endVertex();
            builder.vertex(poseStack.last().pose(), (float)(x2), (float)(y2), (float)(z2))
                  .color(r, g, b, a)
                  .normal(poseStack.last().normal(), f, f1, f2)
                  .endVertex();
        });
        
        poseStack.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(LeadEntity entity) {
        return null; // No texture needed for line rendering
    }
}
