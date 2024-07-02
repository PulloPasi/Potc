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

// Made with Blockbench 4.10.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
public class ModelUUsikoklku<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("cannon", "model_u_usikoklku"), "main");
	public final ModelPart Helmet;
	public final ModelPart Chestplate;
	public final ModelPart RightPlate;
	public final ModelPart LeftPlate;
	public final ModelPart RightLegging;
	public final ModelPart LeftLegging;
	public final ModelPart RightBoot;
	public final ModelPart LeftBoot;

	public ModelUUsikoklku(ModelPart root) {
		this.Helmet = root.getChild("Helmet");
		this.Chestplate = root.getChild("Chestplate");
		this.RightPlate = root.getChild("RightPlate");
		this.LeftPlate = root.getChild("LeftPlate");
		this.RightLegging = root.getChild("RightLegging");
		this.LeftLegging = root.getChild("LeftLegging");
		this.RightBoot = root.getChild("RightBoot");
		this.LeftBoot = root.getChild("LeftBoot");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition Helmet = partdefinition.addOrReplaceChild("Helmet",
				CubeListBuilder.create().texOffs(12, 32).addBox(-2.6F, -7.8F, -3.0F, 5.2F, 7.8F, 5.0F, new CubeDeformation(0.0F)).texOffs(20, 0).addBox(1.5F, -5.4F, -2.0F, 1.5F, 5.4F, 4.0F, new CubeDeformation(0.0F)).texOffs(20, 0)
						.addBox(-3.0F, -5.0F, -2.0F, 1.3F, 5.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(24, 48).addBox(-2.2F, -3.8F, 2.0F, 4.5F, 3.8F, 2.0F, new CubeDeformation(0.0F)).texOffs(22, 1)
						.addBox(-0.2F, -3.8F, -3.4F, 1.5F, 6.8F, 0.8F, new CubeDeformation(0.0F)).texOffs(39, 7).addBox(-4.0F, -8.0F, -6.0F, 8.0F, 8.0F, 1.0F, new CubeDeformation(0.5F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition tentacle_r1 = Helmet.addOrReplaceChild("tentacle_r1", CubeListBuilder.create().texOffs(23, 4).addBox(9.5F, -9.5F, -4.3F, 1.5F, 3.7F, 0.8F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 16.0F, 1.0F, 0.0F, 0.0F, -0.829F));
		PartDefinition tentacle_r2 = Helmet.addOrReplaceChild("tentacle_r2", CubeListBuilder.create().texOffs(22, 3).addBox(-2.6F, -15.2F, -3.5F, 1.5F, 4.8F, 0.8F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 16.0F, 1.0F, 0.0873F, 0.0436F, 0.1047F));
		PartDefinition tentacle_r3 = Helmet.addOrReplaceChild("tentacle_r3", CubeListBuilder.create().texOffs(23, 4).addBox(-6.7F, -10.5F, -5.4F, 1.5F, 3.9F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.4F, 15.7F, 1.3F, 0.0F, 0.0436F, 0.4974F));
		PartDefinition tentacle_r4 = Helmet.addOrReplaceChild("tentacle_r4", CubeListBuilder.create().texOffs(23, 4).addBox(-0.2F, -15.1F, -5.2F, 1.5F, 3.9F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.4F, 15.7F, 1.3F, 0.0F, 0.0436F, -0.1134F));
		PartDefinition tentacle_r5 = Helmet.addOrReplaceChild("tentacle_r5", CubeListBuilder.create().texOffs(22, 4).addBox(5.4F, -10.7F, -4.6F, 1.5F, 4.2F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 16.0F, 1.0F, 0.0F, 0.0436F, -0.4625F));
		PartDefinition tentacle_r6 = Helmet.addOrReplaceChild("tentacle_r6", CubeListBuilder.create().texOffs(22, 3).addBox(-2.8F, -15.1F, -5.0F, 1.5F, 4.2F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 16.0F, 1.0F, 0.0F, 0.0436F, 0.2793F));
		PartDefinition tentacle_r7 = Helmet.addOrReplaceChild("tentacle_r7", CubeListBuilder.create().texOffs(0, 20).addBox(5.8F, -14.1F, -6.8F, 1.3F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 16.0F, 1.0F, -0.1309F, -0.1309F, -0.288F));
		PartDefinition tentacle_r8 = Helmet.addOrReplaceChild("tentacle_r8", CubeListBuilder.create().texOffs(12, 25).addBox(2.6F, -20.1F, -6.6F, 1.4F, 5.8F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 16.0F, 1.0F, -0.1309F, -0.1309F, -0.0698F));
		PartDefinition tentacle_r9 = Helmet.addOrReplaceChild("tentacle_r9", CubeListBuilder.create().texOffs(20, 0).addBox(2.8F, -20.5F, -2.5F, 1.5F, 3.2F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.7F, 15.4F, 4.8F, 0.1309F, 0.0436F, -0.1134F));
		PartDefinition tentacle_r10 = Helmet.addOrReplaceChild("tentacle_r10", CubeListBuilder.create().texOffs(26, 0).addBox(13.0F, -12.3F, -3.1F, 1.5F, 2.4F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.7F, 15.4F, 4.8F, 0.1309F, 0.0436F, -0.8116F));
		PartDefinition tentacle_r11 = Helmet.addOrReplaceChild("tentacle_r11", CubeListBuilder.create().texOffs(26, 0).addBox(15.6F, -5.4F, -1.6F, 1.5F, 2.4F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.7F, 15.7F, 1.0F, 0.0F, 0.0436F, -1.117F));
		PartDefinition tentacle_r12 = Helmet.addOrReplaceChild("tentacle_r12", CubeListBuilder.create().texOffs(26, 0).addBox(13.1F, -12.3F, -1.8F, 1.5F, 2.4F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.7F, 15.7F, 1.0F, 0.0F, 0.0436F, -0.7679F));
		PartDefinition tentacle_r13 = Helmet.addOrReplaceChild("tentacle_r13", CubeListBuilder.create().texOffs(20, 0).addBox(2.7F, -6.5F, 14.6F, 1.5F, 3.5F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.7F, 15.7F, 1.0F, 1.1781F, 0.0436F, -0.0698F));
		PartDefinition tentacle_r14 = Helmet.addOrReplaceChild("tentacle_r14", CubeListBuilder.create().texOffs(0, 20).addBox(2.8F, -20.1F, 2.5F, 1.5F, 4.5F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.7F, 15.7F, 1.0F, 0.1309F, 0.0436F, -0.0698F));
		PartDefinition tentacle_r15 = Helmet.addOrReplaceChild("tentacle_r15", CubeListBuilder.create().texOffs(24, 4).addBox(0.5F, -14.2F, -5.3F, 1.5F, 3.5F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.7F, 15.7F, 1.0F, -0.0873F, 0.0F, -0.3752F));
		PartDefinition tentacle_r16 = Helmet.addOrReplaceChild("tentacle_r16", CubeListBuilder.create().texOffs(23, 4).addBox(-6.5F, -15.2F, -5.3F, 1.5F, 3.5F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.7F, 15.7F, 1.0F, -0.0873F, 0.0F, 0.1484F));
		PartDefinition tentacle_r17 = Helmet.addOrReplaceChild("tentacle_r17", CubeListBuilder.create().texOffs(12, 25).addBox(-5.2F, -19.8F, -5.4F, 1.5F, 5.3F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.7F, 15.7F, 1.0F, -0.0873F, 0.0F, 0.0611F));
		PartDefinition tentacle_r18 = Helmet.addOrReplaceChild("tentacle_r18", CubeListBuilder.create().texOffs(23, 5).addBox(-16.5F, -7.9F, -3.7F, 1.5F, 3.2F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.7F, 15.7F, 3.5F, 0.3927F, 0.1745F, 1.021F));
		PartDefinition tentacle_r19 = Helmet.addOrReplaceChild("tentacle_r19", CubeListBuilder.create().texOffs(25, 4).addBox(-7.3F, -19.1F, 2.0F, 1.5F, 3.1F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.7F, 15.7F, 3.5F, 0.3927F, 0.1745F, 0.192F));
		PartDefinition tentacle_r20 = Helmet.addOrReplaceChild("tentacle_r20", CubeListBuilder.create().texOffs(24, 4).addBox(-16.5F, -7.9F, -3.7F, 1.5F, 3.2F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.7F, 15.7F, 1.0F, -0.0873F, 0.0436F, 0.9338F));
		PartDefinition tentacle_r21 = Helmet.addOrReplaceChild("tentacle_r21", CubeListBuilder.create().texOffs(24, 4).addBox(-6.2F, -19.5F, -4.2F, 1.5F, 3.2F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.7F, 15.7F, 1.0F, -0.0873F, 0.0436F, 0.1047F));
		PartDefinition tentacle_r22 = Helmet.addOrReplaceChild("tentacle_r22", CubeListBuilder.create().texOffs(20, 0).addBox(2.8F, -20.5F, -2.2F, 1.5F, 3.2F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.7F, 15.7F, 1.0F, 0.0F, 0.0436F, -0.0698F));
		PartDefinition tentacle_r23 = Helmet.addOrReplaceChild("tentacle_r23", CubeListBuilder.create().texOffs(12, 25).addBox(2.2F, -19.8F, -4.9F, 1.5F, 5.8F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 16.0F, 1.0F, 0.0F, 0.0436F, -0.0698F));
		PartDefinition tentacle_r24 = Helmet.addOrReplaceChild("tentacle_r24", CubeListBuilder.create().texOffs(12, 25).addBox(-2.7F, -19.8F, -5.2F, 1.5F, 5.8F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.4F, 15.7F, 1.3F, 0.0F, 0.0436F, 0.0611F));
		PartDefinition tentacle_r25 = Helmet.addOrReplaceChild("tentacle_r25", CubeListBuilder.create().texOffs(14, 31).addBox(-1.4F, -19.8F, -4.7F, 1.5F, 5.8F, 0.8F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 16.0F, 1.0F, 0.0F, 0.0436F, 0.0175F));
		PartDefinition cube_r1 = Helmet.addOrReplaceChild("cube_r1",
				CubeListBuilder.create().texOffs(38, 24).addBox(-4.2F, -37.0F, -11.3F, 8.5F, 1.8F, 2.0F, new CubeDeformation(0.0F)).texOffs(37, 8).addBox(-2.2F, -40.0F, -15.3F, 4.5F, 3.8F, 5.0F, new CubeDeformation(0.0F)).texOffs(38, 27)
						.addBox(-3.2F, -37.0F, -14.3F, 6.5F, 1.8F, 3.0F, new CubeDeformation(0.0F)).texOffs(28, 13).addBox(-1.6F, -37.6F, -17.9F, 3.3F, 1.4F, 1.3F, new CubeDeformation(0.0F)).texOffs(44, 31)
						.addBox(-2.3F, -37.0F, -17.3F, 4.6F, 1.8F, 3.0F, new CubeDeformation(0.0F)).texOffs(34, 16).addBox(-5.2F, -39.3F, -9.3F, 10.5F, 3.8F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 32.0F, 1.0F, -0.3054F, 0.0F, 0.0F));
		PartDefinition cube_r2 = Helmet.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(20, 0).addBox(-10.2F, -37.8F, -21.0F, 1.5F, 3.8F, 10.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 32.0F, 1.0F, -0.2182F, 0.5236F, 0.3927F));
		PartDefinition cube_r3 = Helmet.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(20, 0).addBox(8.8F, -37.8F, -21.0F, 1.5F, 3.8F, 10.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 32.0F, 1.0F, -0.2182F, -0.5236F, -0.3927F));
		PartDefinition Chestplate = partdefinition.addOrReplaceChild("Chestplate", CubeListBuilder.create().texOffs(16, 16).addBox(-3.9F, 0.0F, -2.4F, 7.8F, 12.0F, 4.8F, new CubeDeformation(0.0F)).texOffs(0, 0)
				.addBox(-4.9F, 0.4F, 2.5F, 9.9F, 19.3F, 1.0F, new CubeDeformation(0.0F)).texOffs(16, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition cube_r4 = Chestplate.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 20).addBox(-4.9F, 0.4F, -2.4F, 1.0F, 19.3F, 5.7F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0436F, -0.0873F, 0.0436F));
		PartDefinition cube_r5 = Chestplate.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 20).addBox(4.0F, 0.4F, -2.4F, 1.0F, 19.3F, 5.7F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.0436F));
		PartDefinition RightPlate = partdefinition.addOrReplaceChild("RightPlate", CubeListBuilder.create().texOffs(0, 44).addBox(-2.9F, -2.0F, -1.9F, 3.9F, 10.1F, 3.4F, new CubeDeformation(0.0F)).texOffs(7, 20)
				.addBox(-2.3F, 7.8F, -1.3F, 2.7F, 3.7F, 2.8F, new CubeDeformation(0.0F)).texOffs(40, 32).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(-5.0F, 2.0F, 0.0F));
		PartDefinition cube_r6 = RightPlate.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(22, 4).addBox(7.0F, 2.2F, -11.4F, 2.7F, 1.3F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 2.2689F, -1.1781F));
		PartDefinition cube_r7 = RightPlate.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(20, 9).addBox(-7.9F, -3.8F, -9.2F, 2.3F, 1.3F, 1.1F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.829F, -1.8762F));
		PartDefinition cube_r8 = RightPlate.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(32, 6).addBox(-14.0F, 2.0F, -4.1F, 5.1F, 1.3F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.2182F, -1.1781F));
		PartDefinition LeftPlate = partdefinition.addOrReplaceChild("LeftPlate",
				CubeListBuilder.create().texOffs(12, 44).addBox(-1.0F, -2.0F, -1.4F, 3.4F, 9.0F, 3.4F, new CubeDeformation(0.0F)).texOffs(48, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)),
				PartPose.offset(5.0F, 2.0F, 0.0F));
		PartDefinition cube_r9 = LeftPlate.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(48, 41).addBox(-0.5F, 8.0F, 1.9F, 3.1F, 2.2F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 2.0F, 0.0F, -0.1745F, 0.0F, 0.0F));
		PartDefinition cube_r10 = LeftPlate.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(58, 47).addBox(-0.4F, 7.8F, -2.2F, 2.9F, 3.7F, 1.7F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 2.0F, -0.2F, 0.0436F, 0.0F, 0.0F));
		PartDefinition cube_r11 = LeftPlate.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(56, 38).addBox(-0.6F, 4.7F, -1.3F, 3.4F, 3.3F, 1.7F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 2.0F, -0.2F, -0.0873F, 0.0F, 0.0F));
		PartDefinition cube_r12 = LeftPlate.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(48, 35).addBox(-0.7F, 5.0F, -0.3F, 3.5F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 2.0F, 0.0F, 0.0873F, 0.0F, 0.0F));
		PartDefinition cube_r13 = LeftPlate.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(32, 0).addBox(-3.5F, -3.0F, 0.4F, 6.3F, 1.0F, 5.2F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.4835F, 0.0F, 0.0F));
		PartDefinition cube_r14 = LeftPlate.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(17, 13).addBox(-2.0F, -3.1F, -1.0F, 4.0F, 1.1F, 3.4F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0436F, 1.4835F, 0.0F));
		PartDefinition cube_r15 = LeftPlate.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(44, 31).addBox(-2.0F, -3.4F, -1.5F, 4.0F, 1.0F, 3.4F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.2654F, 1.4835F, 0.0F));
		PartDefinition cube_r16 = LeftPlate.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(38, 20).addBox(-4.5F, -3.0F, -1.6F, 7.5F, 1.0F, 3.4F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.2654F, 0.0F, 0.0F));
		PartDefinition RightLegging = partdefinition.addOrReplaceChild("RightLegging", CubeListBuilder.create().texOffs(45, 45).addBox(-1.9F, -0.1F, -1.8F, 3.6F, 6.0F, 3.7F, new CubeDeformation(0.0F)).texOffs(36, 48)
				.addBox(-1.4F, 6.1F, -1.5F, 2.5F, 2.0F, 2.9F, new CubeDeformation(0.0F)).texOffs(0, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(-1.9F, 12.0F, 0.0F));
		PartDefinition LeftLegging = partdefinition.addOrReplaceChild("LeftLegging",
				CubeListBuilder.create().texOffs(0, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(0, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.25F)),
				PartPose.offset(1.9F, 12.0F, 0.0F));
		PartDefinition RightBoot = partdefinition.addOrReplaceChild("RightBoot", CubeListBuilder.create().texOffs(36, 48).addBox(-3.3F, -3.9F, -1.5F, 2.5F, 4.0F, 2.9F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));
		PartDefinition LeftBoot = partdefinition.addOrReplaceChild("LeftBoot", CubeListBuilder.create().texOffs(0, 48).addBox(-0.1F, -4.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Helmet.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Chestplate.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightPlate.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftPlate.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightLegging.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftLegging.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightBoot.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftBoot.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
