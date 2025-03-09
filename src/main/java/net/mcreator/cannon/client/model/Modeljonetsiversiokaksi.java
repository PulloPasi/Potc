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
public class Modeljonetsiversiokaksi<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("cannon", "modeljonetsiversiokaksi"), "main");
	public final ModelPart Helmet;
	public final ModelPart Chestplate;
	public final ModelPart RightPlate;
	public final ModelPart LeftPlate;
	public final ModelPart RightLegging;
	public final ModelPart LeftLegging;
	public final ModelPart LeftBoot;
	public final ModelPart RightBoot;

	public Modeljonetsiversiokaksi(ModelPart root) {
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
				CubeListBuilder.create().texOffs(0, 25).addBox(-1.0F, 1.0F, 0.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.5F)).texOffs(0, 52).addBox(-3.0F, -5.0F, -3.0F, 6.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(0, 63)
						.addBox(-3.0F, -7.0F, -3.0F, 6.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(66, 66).addBox(-1.3F, -2.0F, -4.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(24, 34)
						.addBox(-2.0F, -2.0F, 3.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition cube_r1 = Helmet.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(24, 41).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.3F, 2.4F, -4.6F, 0.2618F, 0.0F, -0.2618F));
		PartDefinition cube_r2 = Helmet.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 52).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.9F, -2.2F, -3.8F, -0.1745F, 0.0F, 0.0873F));
		PartDefinition cube_r3 = Helmet.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(43, 41).addBox(0.0F, 0.0F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.0F, -0.2F, -3.9F, 0.1309F, -0.3054F, 0.0436F));
		PartDefinition cube_r4 = Helmet.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(44, 33).addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.0F, -2.0F, -3.9F, -0.0436F, -0.0436F, 0.0436F));
		PartDefinition cube_r5 = Helmet.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(44, 37).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.5F, -3.4F, -4.0F, 0.0F, 0.0F, -0.8727F));
		PartDefinition cube_r6 = Helmet.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(25, 14).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.3F, 1.9F, -2.9F, -0.3491F, 0.0873F, -0.1745F));
		PartDefinition cube_r7 = Helmet.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(36, 67).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.0F, -1.4F, -2.6F, -0.1309F, 0.0436F, 0.0873F));
		PartDefinition cube_r8 = Helmet.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(67, 26).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.4F, -1.6F, -0.5F, -0.9599F, -0.1745F, 0.7418F));
		PartDefinition cube_r9 = Helmet.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(48, 33).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.6F, -2.6F, -0.5F, -0.3491F, -0.0436F, 0.7418F));
		PartDefinition cube_r10 = Helmet.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(36, 52).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.9F, -2.1F, 0.3F, 0.1745F, 0.1745F, 0.9599F));
		PartDefinition cube_r11 = Helmet.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(16, 63).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.4F, -5.0F, 0.0F, 0.1745F, 0.0F, 0.2182F));
		PartDefinition cube_r12 = Helmet.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(17, 14).addBox(0.0F, -1.0F, 0.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.9F, 2.4F, -4.1F, 0.1309F, 0.0436F, 0.0F));
		PartDefinition cube_r13 = Helmet.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(34, 7).addBox(0.0F, -1.0F, 0.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.4F, -1.0F, -2.7F, -0.5672F, 0.0436F, 0.0873F));
		PartDefinition cube_r14 = Helmet.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(60, 12).addBox(0.0F, -1.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.0F, -0.7F, -0.7F, 0.0F, 0.0F, -1.2654F));
		PartDefinition cube_r15 = Helmet.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(44, 67).addBox(0.0F, -1.0F, 0.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.3F, -0.5F, 1.6F, 0.6981F, 0.0F, -0.3491F));
		PartDefinition cube_r16 = Helmet.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(10, 7).addBox(0.0F, -1.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.6F, -2.6F, 0.6F, 0.2618F, 0.0F, -0.3491F));
		PartDefinition cube_r17 = Helmet.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(0, 63).addBox(0.0F, -1.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.6F, -3.3F, -0.8F, 0.0F, 0.0F, -0.3491F));
		PartDefinition cube_r18 = Helmet.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(55, 5).addBox(0.0F, -1.0F, 0.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.6F, -4.0F, -2.0F, -0.2182F, 0.0F, -0.3491F));
		PartDefinition cube_r19 = Helmet.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(68, 40).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.4F, -5.0F, -1.0F, 0.1745F, 0.0F, 0.2182F));
		PartDefinition cube_r20 = Helmet.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(40, 67).addBox(-1.0F, -2.0F, 0.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.7F, -3.0F, -2.0F, -0.3491F, 0.0F, 0.2182F));
		PartDefinition cube_r21 = Helmet.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(48, 37).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.9F, -3.0F, -4.0F, 0.0F, 0.0F, 0.7418F));
		PartDefinition cube_r22 = Helmet.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(68, 54).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.6F, 4.1F, -3.9F, 0.0F, 0.0F, -0.7418F));
		PartDefinition cube_r23 = Helmet.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(67, 17).addBox(0.0F, 0.0F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 3.0F, -3.5F, -0.2618F, -0.0873F, -0.2618F));
		PartDefinition cube_r24 = Helmet.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(18, 52).addBox(0.0F, 0.0F, 0.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -2.0F, -3.7F, 0.0F, 0.0F, -0.0436F));
		PartDefinition cube_r25 = Helmet.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(20, 63).addBox(0.0F, 0.0F, -1.0F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.9F, -1.3F, -2.1F, 0.0436F, -0.0436F, -0.1571F));
		PartDefinition cube_r26 = Helmet.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(60, 17).addBox(0.0F, 0.0F, -1.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.3F, -2.0F, -2.5F, -0.0436F, -0.0436F, 0.0611F));
		PartDefinition cube_r27 = Helmet.addOrReplaceChild("cube_r27",
				CubeListBuilder.create().texOffs(60, 12).addBox(1.0F, -1.0F, 3.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(17, 7).addBox(0.0F, -1.0F, 1.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(57, 17)
						.addBox(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(39, 41).addBox(-2.0F, -1.0F, -4.0F, 1.0F, 1.0F, 11.0F, new CubeDeformation(0.0F)).texOffs(64, 35)
						.addBox(-10.0F, -1.0F, 3.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(17, 14).addBox(-9.0F, -1.0F, 1.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(57, 26)
						.addBox(-8.0F, -1.0F, -1.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(44, 25).addBox(-7.0F, -1.0F, -4.0F, 1.0F, 1.0F, 11.0F, new CubeDeformation(0.0F)).texOffs(24, 28)
						.addBox(-6.0F, -1.0F, -5.0F, 4.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.9F, -6.1F, -0.7F, -0.5236F, 0.0F, 0.0F));
		PartDefinition cube_r28 = Helmet.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(24, 41).addBox(-3.0F, -4.0F, -2.0F, 6.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.1F, -7.1F, -0.7F, -0.5236F, 0.0F, 0.0F));
		PartDefinition cube_r29 = Helmet.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(25, 7).addBox(-1.0F, -1.0F, -5.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.1F, -7.1F, -2.4F, -0.5236F, 0.0F, 0.0F));
		PartDefinition cube_r30 = Helmet.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(38, 0).addBox(-8.0F, -2.0F, -1.0F, 16.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -4.7F, 6.4F, -0.4363F, 0.0F, 0.0F));
		PartDefinition cube_r31 = Helmet.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(17, 10).addBox(-1.0F, -7.0F, -13.0F, 1.0F, 3.0F, 15.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.4F, -1.1F, 2.8F, -0.2182F, 0.6109F, 0.3927F));
		PartDefinition cube_r32 = Helmet.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(0, 7).addBox(-1.0F, -7.0F, -13.0F, 1.0F, 3.0F, 15.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.0F, -1.5F, 3.4F, -0.2182F, -0.6109F, -0.3927F));
		PartDefinition Chestplate = partdefinition.addOrReplaceChild("Chestplate",
				CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 3.0F, 0.0F, 2.0F, 0.0F, 0.0F, new CubeDeformation(0.25F)).texOffs(34, 7).addBox(-4.0F, -1.0F, -2.0F, 8.0F, 13.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition cube_r33 = Chestplate.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 2.0F, -3.0F, 16.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(11.9F, -0.2F, 0.4F, 0.0F, -0.1309F, 1.4835F));
		PartDefinition cube_r34 = Chestplate.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(55, 5).addBox(-1.0F, 2.0F, -3.0F, 5.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(5.0F, -3.2F, 0.4F, 0.0436F, 0.0F, 0.0F));
		PartDefinition cube_r35 = Chestplate.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(24, 52).addBox(-5.0F, -2.0F, 0.0F, 5.0F, 18.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(10.0F, 1.0F, 2.6F, 0.1309F, 0.0F, 0.0F));
		PartDefinition cube_r36 = Chestplate.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(21, 14).addBox(5.0F, -3.0F, 1.0F, 1.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(7.3F, 14.1F, 5.2F, 0.5672F, 0.0436F, -2.5744F));
		PartDefinition cube_r37 = Chestplate.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(10, 25).addBox(5.0F, -3.0F, 1.0F, 2.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.6F, 9.3F, 3.0F, -0.2618F, 0.0436F, -0.7854F));
		PartDefinition cube_r38 = Chestplate.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(0, 0).addBox(4.0F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-7.8F, 8.2F, 2.6F, -0.3927F, -0.0436F, 0.3054F));
		PartDefinition cube_r39 = Chestplate.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(4, 0).addBox(4.0F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.6F, 3.5F, 3.5F, -0.3054F, 0.1745F, 0.0F));
		PartDefinition cube_r40 = Chestplate.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(0, 20).addBox(3.0F, -2.0F, 1.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-7.4F, 1.0F, 1.4F, -0.3927F, -0.3054F, 0.0F));
		PartDefinition cube_r41 = Chestplate.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(0, 28).addBox(-6.0F, -2.0F, 0.0F, 11.0F, 23.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 1.0F, 2.6F, 0.0436F, 0.0F, 0.0F));
		PartDefinition RightPlate = partdefinition.addOrReplaceChild("RightPlate", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 2.0F, 1.0F, 0.0F, 1.0F, 0.0F, new CubeDeformation(0.25F)).texOffs(36, 53)
				.addBox(-3.0F, -3.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(58, 66).addBox(-2.0F, 7.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));
		PartDefinition cube_r42 = RightPlate.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(25, 10).addBox(0.0F, 0.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.7F, 12.4F, 0.4F, 0.2182F, 0.2182F, -0.2182F));
		PartDefinition cube_r43 = RightPlate.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(0, 7).addBox(0.0F, 0.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.9F, 10.7F, -1.6F, 0.9599F, 0.3491F, 0.2618F));
		PartDefinition cube_r44 = RightPlate.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(68, 58).addBox(0.0F, 0.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, 9.0F, -0.3F, -0.3054F, 0.3491F, 0.2618F));
		PartDefinition LeftPlate = partdefinition.addOrReplaceChild("LeftPlate", CubeListBuilder.create().texOffs(49, 25).addBox(0.0F, 0.0F, 1.0F, 2.0F, 3.0F, 0.0F, new CubeDeformation(0.25F)).texOffs(52, 37)
				.addBox(-1.0F, -2.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(44, 28).addBox(-0.5F, 7.8F, 0.3F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 2.0F, 0.0F));
		PartDefinition cube_r45 = LeftPlate.addOrReplaceChild("cube_r45", CubeListBuilder.create().texOffs(57, 26).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.0F, 10.6F, -0.2F, 0.1309F, 0.0F, 0.0F));
		PartDefinition cube_r46 = LeftPlate.addOrReplaceChild("cube_r46", CubeListBuilder.create().texOffs(66, 12).addBox(-2.0F, 0.0F, -2.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.4F, 10.7F, 2.3F, -0.1309F, 0.0F, 0.0F));
		PartDefinition cube_r47 = LeftPlate.addOrReplaceChild("cube_r47", CubeListBuilder.create().texOffs(24, 28).addBox(-2.0F, 0.0F, -2.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.5F, 7.8F, 0.2F, -0.1309F, 0.0F, 0.0F));
		PartDefinition RightLegging = partdefinition.addOrReplaceChild("RightLegging",
				CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 4.0F, 0.0F, 0.0F, 1.0F, 0.0F, new CubeDeformation(0.25F)).texOffs(0, 7).addBox(-1.3F, 0.0F, -1.6F, 3.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, 12.0F, 0.0F));
		PartDefinition LeftLegging = partdefinition.addOrReplaceChild("LeftLegging",
				CubeListBuilder.create().texOffs(48, 53).addBox(-1.0F, 2.0F, 0.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.25F)).texOffs(52, 53).addBox(-1.9F, 0.0F, -1.6F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(1.9F, 12.0F, 0.0F));
		PartDefinition LeftBoot = partdefinition.addOrReplaceChild("LeftBoot",
				CubeListBuilder.create().texOffs(64, 47).addBox(-1.9F, 9.0F, -1.6F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(0, 0).addBox(-3.9F, 4.0F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(1.9F, 12.0F, 0.0F));
		PartDefinition RightBoot = partdefinition.addOrReplaceChild("RightBoot",
				CubeListBuilder.create().texOffs(50, 66).addBox(-0.8F, 9.0F, -0.5F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(17, 7).addBox(-0.1F, 4.0F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-1.9F, 12.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
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
