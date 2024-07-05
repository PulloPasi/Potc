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
public class Modelbillvkaks<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("cannon", "modelbillvkaks"), "main");
	public final ModelPart Helmet;
	public final ModelPart Chestplate;
	public final ModelPart RightPlate;
	public final ModelPart LeftPlate;
	public final ModelPart RightLegging;
	public final ModelPart LeftLegging;
	public final ModelPart LeftBoot;
	public final ModelPart RightBoot;

	public Modelbillvkaks(ModelPart root) {
		this.Helmet = root.getChild("Helmet");
		this.Chestplate = root.getChild("Chestplate");
		this.RightPlate = root.getChild("RightPlate");
		this.LeftPlate = root.getChild("LeftPlate");
		this.RightLegging = root.getChild("RightLegging");
		this.LeftLegging = root.getChild("LeftLegging");
		this.LeftBoot = root.getChild("LeftBoot");
		this.RightBoot = root.getChild("RightBoot");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition Helmet = partdefinition.addOrReplaceChild("Helmet",
				CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -7.0F, -4.0F, 8.0F, 7.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(0, 15).addBox(-4.0F, -9.0F, -4.1F, 8.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(50, 0)
						.addBox(-2.0F, -10.0F, -2.1F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(44, 26).addBox(-4.0F, -7.0F, -3.4F, 0.0F, 11.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 40)
						.addBox(4.1F, -7.0F, -3.4F, 0.0F, 11.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(38, 27).addBox(-4.0F, -3.0F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition cube_r1 = Helmet.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 3).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.4F, 0.2F, -3.9F, 0.6981F, 0.0F, 0.48F));
		PartDefinition cube_r2 = Helmet.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 7).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.6F, -0.2F, -4.4F, 0.4363F, 0.0F, 0.6109F));
		PartDefinition cube_r3 = Helmet.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(50, 3).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.0F, -9.0F, -3.2F, -0.2618F, 0.5672F, 0.0F));
		PartDefinition cube_r4 = Helmet.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(53, 1).addBox(1.0F, -3.0F, -1.0F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.1F, 3.0F, -4.4F, 0.0F, -1.5708F, 0.0F));
		PartDefinition cube_r5 = Helmet.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(14, 47).addBox(1.0F, -2.0F, -1.0F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.0F, 3.0F, -4.4F, 0.0F, -1.5708F, 0.0F));
		PartDefinition cube_r6 = Helmet.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(36, 17).addBox(0.0F, 0.0F, -1.0F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.1F, -7.0F, 0.8F, -0.0436F, -0.0873F, 0.1309F));
		PartDefinition cube_r7 = Helmet.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(42, 26).addBox(0.0F, 0.0F, -1.0F, 0.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.0F, -7.0F, 3.8F, -0.0436F, 0.0F, -0.0436F));
		PartDefinition cube_r8 = Helmet.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(34, 51).addBox(0.0F, 0.0F, -1.0F, 0.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.0F, -7.0F, 3.8F, -0.0436F, 0.0F, 0.0436F));
		PartDefinition cube_r9 = Helmet.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(32, 18).addBox(0.0F, 0.0F, -1.0F, 0.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.0F, -7.0F, 1.8F, 0.0F, 0.0F, 0.0873F));
		PartDefinition cube_r10 = Helmet.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(30, 56).addBox(0.0F, 0.0F, -1.0F, 0.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.1F, -7.0F, 2.3F, 0.0436F, 0.0436F, 0.0F));
		PartDefinition cube_r11 = Helmet.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(12, 47).addBox(0.0F, 0.0F, -1.0F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.1F, -7.0F, 0.9F, 0.0873F, 0.0436F, -0.0873F));
		PartDefinition cube_r12 = Helmet.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(20, 56).addBox(0.0F, 0.0F, -1.0F, 0.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.1F, -7.0F, -0.2F, -0.0436F, 0.0436F, -0.0873F));
		PartDefinition cube_r13 = Helmet.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(22, 56).addBox(0.0F, 0.0F, -1.0F, 0.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.0F, -7.0F, -0.2F, -0.0436F, 0.0436F, 0.0436F));
		PartDefinition cube_r14 = Helmet.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(16, 47).addBox(0.0F, 0.0F, -1.0F, 0.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.0F, -7.0F, -1.4F, -0.0873F, -0.0436F, -0.0436F));
		PartDefinition cube_r15 = Helmet.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(18, 56).addBox(0.0F, 0.0F, -1.0F, 0.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.0F, -7.0F, -1.4F, -0.0873F, 0.0436F, 0.0436F));
		PartDefinition cube_r16 = Helmet.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(50, 6).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.9F, -5.1F, -4.8F, 0.0F, 0.0F, 2.3998F));
		PartDefinition cube_r17 = Helmet.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(38, 0).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.6F, -6.0F, -4.8F, 0.0F, 0.0F, -2.0508F));
		PartDefinition cube_r18 = Helmet.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(49, 40).addBox(-1.0F, -3.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.4F, -2.7F, -5.0F, 0.0F, 0.0F, -0.5236F));
		PartDefinition cube_r19 = Helmet.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(50, 14).addBox(-1.0F, -3.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.7F, -2.3F, -4.9F, 0.0F, 0.0F, 0.5672F));
		PartDefinition cube_r20 = Helmet.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(18, 41).addBox(-1.0F, -3.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, -4.0F, -4.7F, 0.0F, 0.0F, -0.0436F));
		PartDefinition cube_r21 = Helmet.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(4, 20).addBox(0.0F, -1.0F, -1.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.5F, -9.9F, -0.1F, 0.0F, -0.2182F, 0.4363F));
		PartDefinition cube_r22 = Helmet.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(0, 20).addBox(0.0F, -1.0F, -1.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.9F, -9.8F, -0.1F, 0.0F, 0.3491F, -0.8727F));
		PartDefinition cube_r23 = Helmet.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(24, 20).addBox(0.0F, -1.0F, -1.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.0F, -10.0F, -0.1F, 0.0F, 0.0F, 0.6109F));
		PartDefinition Chestplate = partdefinition.addOrReplaceChild("Chestplate",
				CubeListBuilder.create().texOffs(18, 25).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(24, 18).addBox(-3.0F, 10.0F, -2.1F, 6.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition cube_r24 = Chestplate.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(24, 20).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.9F, 7.3F, -2.6F, 0.0873F, 0.0F, 0.0F));
		PartDefinition cube_r25 = Chestplate.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(44, 39).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.9F, 4.9F, -2.8F, 0.0873F, 0.0F, 0.0F));
		PartDefinition cube_r26 = Chestplate.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(45, 0).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.9F, 6.0F, -2.8F, 0.0873F, 0.0F, 0.0F));
		PartDefinition cube_r27 = Chestplate.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(32, 57).addBox(0.0F, -2.0F, 1.0F, 1.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.8F, 5.9F, -3.1F, -0.0873F, 0.0F, 0.0F));
		PartDefinition cube_r28 = Chestplate.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(55, 58).addBox(0.0F, -2.0F, 1.0F, 1.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.1F, 3.9F, -3.1F, -0.0873F, 0.0F, 0.0F));
		PartDefinition cube_r29 = Chestplate.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(16, 58).addBox(0.0F, -2.0F, 1.0F, 1.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.1F, 4.4F, -3.1F, -0.0873F, 0.0F, 0.0F));
		PartDefinition cube_r30 = Chestplate.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(30, 20).addBox(0.0F, -2.0F, 1.0F, 1.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.0F, 4.0F, -3.1F, -0.0873F, 0.0F, 0.0F));
		PartDefinition cube_r31 = Chestplate.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(37, 36).addBox(3.0F, 0.0F, -5.0F, 1.0F, 22.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(38, 0)
				.addBox(-6.0F, 0.0F, -5.0F, 1.0F, 22.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(0, 25).addBox(-5.0F, 0.0F, -1.0F, 8.0F, 22.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 3.0F, 0.0873F, 0.0F, 0.0F));
		PartDefinition cube_r32 = Chestplate.addOrReplaceChild("cube_r32",
				CubeListBuilder.create().texOffs(32, 0).addBox(3.0F, 0.0F, -1.0F, 2.0F, 17.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(49, 51).addBox(-5.0F, 0.0F, -1.0F, 2.0F, 17.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.5F, -2.0F, 0.0873F, 0.0F, 0.0F));
		PartDefinition cube_r33 = Chestplate.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(55, 51).addBox(0.0F, -4.0F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-5.2F, 1.5F, -0.2F, 0.0F, 0.0F, -0.9163F));
		PartDefinition cube_r34 = Chestplate.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(24, 0).addBox(0.0F, -4.0F, 0.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-5.2F, 0.9F, 0.8F, 0.0F, 0.0F, -0.4363F));
		PartDefinition cube_r35 = Chestplate.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(28, 0).addBox(0.0F, -6.0F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.2F, 0.9F, 0.0F, 0.0F, 0.0F, -0.5236F));
		PartDefinition RightPlate = partdefinition.addOrReplaceChild("RightPlate", CubeListBuilder.create().texOffs(46, 23).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(38, 3)
				.addBox(-4.0F, 5.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(51, 0).addBox(-3.4F, 0.8F, -1.6F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));
		PartDefinition cube_r36 = RightPlate.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(58, 22).addBox(1.0F, -2.0F, 2.0F, 3.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.0F, 9.9F, 0.4F, 0.2182F, 0.0F, 0.0F));
		PartDefinition cube_r37 = RightPlate.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(58, 24).addBox(1.0F, -2.0F, 2.0F, 3.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.0F, 8.9F, -4.3F, -0.2182F, 0.0F, 0.0F));
		PartDefinition cube_r38 = RightPlate.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(0, 16).addBox(1.0F, -2.0F, -2.0F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.3F, 9.0F, 0.0F, 0.0F, 0.0F, 0.2182F));
		PartDefinition LeftPlate = partdefinition.addOrReplaceChild("LeftPlate", CubeListBuilder.create().texOffs(18, 41).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 2.0F, 0.0F));
		PartDefinition cube_r39 = LeftPlate.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(30, 41).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.2F, 1.2F, -0.7F, 0.0F, 0.0F, 0.9599F));
		PartDefinition cube_r40 = LeftPlate.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(0, 48).addBox(-1.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.0F, 0.3F, 0.4F, 0.0F, 0.0F, 0.6109F));
		PartDefinition cube_r41 = LeftPlate.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(0, 14).addBox(1.0F, -2.0F, -2.0F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.4F, 9.7F, 0.0F, 0.0F, 0.0F, -0.2182F));
		PartDefinition cube_r42 = LeftPlate.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(45, 2).addBox(1.0F, -2.0F, 2.0F, 3.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, 9.7F, 0.4F, 0.2182F, 0.0F, 0.0F));
		PartDefinition cube_r43 = LeftPlate.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(24, 57).addBox(1.0F, -2.0F, 2.0F, 3.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, 8.9F, -4.3F, -0.2182F, 0.0F, 0.0F));
		PartDefinition RightLegging = partdefinition.addOrReplaceChild("RightLegging",
				CubeListBuilder.create().texOffs(49, 39).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(18, 25).addBox(-1.0F, 0.0F, -2.1F, 2.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(0, 0)
						.addBox(-2.0F, 5.0F, -2.1F, 4.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(0, 0).addBox(-2.0F, 5.0F, -2.1F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-1.9F, 12.0F, 0.0F));
		PartDefinition LeftLegging = partdefinition.addOrReplaceChild("LeftLegging", CubeListBuilder.create().texOffs(0, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(24, 15)
				.addBox(-2.0F, 5.0F, -2.1F, 4.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(0, 11).addBox(2.1F, 5.0F, -2.1F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, 12.0F, 0.0F));
		PartDefinition LeftBoot = partdefinition.addOrReplaceChild("LeftBoot", CubeListBuilder.create().texOffs(50, 14).addBox(-1.9F, 8.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, 12.0F, 0.0F));
		PartDefinition RightBoot = partdefinition.addOrReplaceChild("RightBoot", CubeListBuilder.create().texOffs(50, 6).addBox(-2.1F, 8.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, 12.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 16, 16);
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
		LeftBoot.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightBoot.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
