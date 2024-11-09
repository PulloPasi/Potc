package net.mcreator.cannon.client.model;

import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.EntityModel;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
public class Modelbarbossakaks<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("cannon", "modelbarbossakaks"), "main");
	public final ModelPart Head;
	public final ModelPart Body;
	public final ModelPart RightArm;
	public final ModelPart LeftArm;
	public final ModelPart RightLeg;
	public final ModelPart LeftLeg;
	public final ModelPart rightboot;
	public final ModelPart lebtboot;

	public Modelbarbossakaks(ModelPart root) {
		this.Head = root.getChild("Head");
		this.Body = root.getChild("Body");
		this.RightArm = root.getChild("RightArm");
		this.LeftArm = root.getChild("LeftArm");
		this.RightLeg = root.getChild("RightLeg");
		this.LeftLeg = root.getChild("LeftLeg");
		this.rightboot = root.getChild("rightboot");
		this.lebtboot = root.getChild("lebtboot");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition Head = partdefinition.addOrReplaceChild("Head",
				CubeListBuilder.create().texOffs(60, 0).addBox(-3.0F, -0.8F, -4.3F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 15).addBox(-4.0F, -6.0F, -4.0F, 8.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(0, 0)
						.addBox(3.8F, -8.0F, -1.1F, 1.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(11, 59).addBox(-4.7F, -6.0F, -1.2F, 1.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(24, 15)
						.addBox(-3.0F, -7.0F, -4.0F, 7.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)).texOffs(45, 15).addBox(-1.0F, -8.0F, -4.0F, 5.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition cube_r1 = Head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(42, 0).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.3F, -7.8F, 0.0F, -0.1309F, 0.0F, -0.2182F));
		PartDefinition cube_r2 = Head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -1.0F, -7.0F, 14.0F, 1.0F, 14.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.1F, -6.8F, -0.2F, -0.1309F, 0.0F, -0.2182F));
		PartDefinition Body = partdefinition.addOrReplaceChild("Body",
				CubeListBuilder.create().texOffs(28, 25).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(34, 41).addBox(-5.0F, 0.0F, -3.0F, 1.0F, 18.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(18, 48)
						.addBox(-4.0F, 0.0F, -3.0F, 1.0F, 15.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(22, 29).addBox(3.0F, 0.0F, -3.0F, 1.0F, 15.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(42, 9)
						.addBox(-3.0F, 10.0F, -2.3F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 29).addBox(-5.0F, 0.0F, 2.0F, 10.0F, 18.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(22, 41)
						.addBox(4.0F, 0.0F, -3.0F, 1.0F, 18.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition Body_r1 = Body.addOrReplaceChild("Body_r1", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -12.0F, -3.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.8F, 10.7F, 0.3F, 0.0F, 0.0F, 1.3526F));
		PartDefinition Body_r2 = Body.addOrReplaceChild("Body_r2", CubeListBuilder.create().texOffs(0, 15).addBox(0.0F, -14.0F, -3.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.9F, 20.0F, 0.2F, 0.0F, 0.0F, 0.6109F));
		PartDefinition Body_r3 = Body.addOrReplaceChild("Body_r3", CubeListBuilder.create().texOffs(0, 64).addBox(0.0F, -24.0F, 1.0F, 2.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(8.0F, 21.9F, -3.2F, 0.0F, 0.0F, -0.5672F));
		PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm",
				CubeListBuilder.create().texOffs(0, 48).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(52, 34).addBox(-3.5F, 5.0F, -2.5F, 5.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-5.0F, 2.0F, 0.0F));
		PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm",
				CubeListBuilder.create().texOffs(46, 41).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(58, 52).addBox(-1.6F, 5.0F, -2.5F, 5.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offset(5.0F, 2.0F, 0.0F));
		PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg",
				CubeListBuilder.create().texOffs(23, 64).addBox(-2.7F, 0.0F, -2.2F, 2.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(46, 57).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-1.9F, 12.0F, 0.0F));
		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg",
				CubeListBuilder.create().texOffs(52, 22).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(62, 59).addBox(0.4F, 0.0F, -2.2F, 2.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(1.9F, 12.0F, 0.0F));
		PartDefinition rightboot = partdefinition.addOrReplaceChild("rightboot", CubeListBuilder.create().texOffs(62, 41).addBox(-2.1F, 8.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, 12.0F, 0.0F));
		PartDefinition lebtboot = partdefinition.addOrReplaceChild("lebtboot", CubeListBuilder.create().texOffs(61, 9).addBox(-1.9F, 8.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, 12.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		rightboot.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		lebtboot.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
