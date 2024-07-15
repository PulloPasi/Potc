// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

public class Modelblackbeard<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation("modid", "blackbeard"), "main");
	private final ModelPart Helmet;
	private final ModelPart Chestplate;
	private final ModelPart RightPlate;
	private final ModelPart LeftPlate;
	private final ModelPart RightLegging;
	private final ModelPart LeftLegging;
	private final ModelPart RightBoot;
	private final ModelPart LeftBoot;

	public Modelblackbeard(ModelPart root) {
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
				CubeListBuilder.create().texOffs(0, 0)
						.addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(61, 7)
						.addBox(-4.3F, -5.0F, -3.9F, 1.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(55, 59)
						.addBox(-4.4F, -6.0F, -1.9F, 1.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(6, 57)
						.addBox(3.1F, -6.0F, -1.9F, 1.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(0, 0)
						.addBox(3.1F, -5.0F, -3.9F, 1.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(64, 27)
						.addBox(-4.0F, -3.0F, -4.2F, 2.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(29, 64)
						.addBox(2.0F, -3.0F, -4.2F, 2.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 5)
						.addBox(-2.0F, -2.0F, -4.2F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(26, 30)
						.addBox(-2.0F, -4.0F, -4.2F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = Helmet.addOrReplaceChild("cube_r1",
				CubeListBuilder.create().texOffs(16, 35).addBox(-4.0F, -2.0F, 0.0F, 1.0F, 6.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(6.0F, 0.6F, -4.3F, -0.0873F, 0.0F, 0.0F));

		PartDefinition cube_r2 = Helmet.addOrReplaceChild("cube_r2",
				CubeListBuilder.create().texOffs(35, 64).addBox(-4.0F, -2.0F, 0.0F, 1.0F, 5.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.8F, 0.8F, -4.6F, -0.3054F, 0.0F, 0.0F));

		PartDefinition cube_r3 = Helmet.addOrReplaceChild("cube_r3",
				CubeListBuilder.create().texOffs(40, 0).addBox(-3.0F, -3.0F, 1.0F, 1.0F, 4.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.6F, -0.5F, -3.9F, 0.0F, 0.0F, 0.4363F));

		PartDefinition cube_r4 = Helmet.addOrReplaceChild("cube_r4",
				CubeListBuilder.create().texOffs(48, 0).addBox(-3.0F, -3.0F, 1.0F, 1.0F, 4.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(7.1F, -1.0F, -3.9F, 0.0F, 0.0F, -0.3927F));

		PartDefinition cube_r5 = Helmet.addOrReplaceChild("cube_r5",
				CubeListBuilder.create().texOffs(34, 11).addBox(-2.0F, -1.0F, 2.0F, 2.0F, 1.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.9F, -7.1F, -0.9F, -0.1309F, 0.48F, 0.0F));

		PartDefinition cube_r6 = Helmet.addOrReplaceChild("cube_r6",
				CubeListBuilder.create().texOffs(11, 54).addBox(-2.0F, -1.0F, 0.0F, 2.0F, 1.0F, 5.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.9F, -7.1F, -0.9F, -0.1309F, 0.48F, 0.0F));

		PartDefinition cube_r7 = Helmet.addOrReplaceChild("cube_r7",
				CubeListBuilder.create().texOffs(48, 0).addBox(-3.0F, -2.0F, -1.0F, 4.0F, 2.0F, 5.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.0F, -8.1F, -0.9F, -0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r8 = Helmet.addOrReplaceChild("cube_r8",
				CubeListBuilder.create().texOffs(37, 11).addBox(-2.0F, -1.0F, -3.0F, 2.0F, 1.0F, 8.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.3F, -7.1F, -0.9F, -0.1309F, 0.48F, 0.0F));

		PartDefinition cube_r9 = Helmet.addOrReplaceChild("cube_r9",
				CubeListBuilder.create().texOffs(34, 0).addBox(-2.0F, -1.0F, -5.0F, 2.0F, 1.0F, 10.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.6F, -7.0F, -0.9F, -0.1309F, 0.48F, 0.0F));

		PartDefinition cube_r10 = Helmet.addOrReplaceChild("cube_r10",
				CubeListBuilder.create().texOffs(13, 21).addBox(-1.0F, -3.0F, -6.0F, 1.0F, 3.0F, 11.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.6F, -7.0F, -0.9F, -0.1309F, 0.48F, 0.0F));

		PartDefinition cube_r11 = Helmet.addOrReplaceChild("cube_r11",
				CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, -2.0F, 4.0F, 1.0F, 2.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.5F, -8.2F, -10.2F, -0.0873F, 0.0F, 0.0F));

		PartDefinition cube_r12 = Helmet.addOrReplaceChild("cube_r12",
				CubeListBuilder.create().texOffs(21, 5).addBox(-1.0F, -3.0F, -6.0F, 1.0F, 3.0F, 11.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.1F, -6.3F, 3.8F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r13 = Helmet.addOrReplaceChild("cube_r13",
				CubeListBuilder.create().texOffs(26, 24).addBox(-1.0F, -3.0F, -6.0F, 1.0F, 3.0F, 11.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.8F, -7.0F, -0.4F, -0.1309F, -0.48F, 0.0F));

		PartDefinition Chestplate = partdefinition.addOrReplaceChild("Chestplate",
				CubeListBuilder.create().texOffs(0, 16)
						.addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(28, 51)
						.addBox(2.0F, -1.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(56, 19)
						.addBox(-4.0F, -0.9F, -2.6F, 2.0F, 13.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 56)
						.addBox(2.0F, -0.9F, -2.6F, 2.0F, 13.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(32, 38)
						.addBox(-4.0F, 0.1F, 1.4F, 8.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(25, 64)
						.addBox(1.0F, -0.9F, -2.6F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(63, 62)
						.addBox(-2.0F, -0.9F, -2.6F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(24, 0)
						.addBox(-6.0F, -1.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition RightPlate = partdefinition.addOrReplaceChild("RightPlate",
				CubeListBuilder.create().texOffs(16, 38)
						.addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(25, 56)
						.addBox(-3.0F, -1.9F, 1.4F, 4.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(61, 0)
						.addBox(-3.0F, 5.1F, 1.9F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(26, 19)
						.addBox(-3.2F, -1.9F, -2.0F, 1.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(60, 55)
						.addBox(-3.7F, 5.1F, -2.0F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(35, 56)
						.addBox(-3.0F, -1.9F, -2.3F, 4.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(62, 19)
						.addBox(-3.0F, 5.1F, -2.8F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(14, 60)
						.addBox(-3.0F, -2.5F, -2.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition LeftPlate = partdefinition.addOrReplaceChild("LeftPlate",
				CubeListBuilder.create().texOffs(0, 32)
						.addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(45, 59)
						.addBox(-1.0F, -2.0F, -2.4F, 4.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(62, 51)
						.addBox(-1.0F, 5.0F, -2.7F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(58, 43)
						.addBox(-1.0F, -1.9F, 1.6F, 4.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(62, 23)
						.addBox(-1.0F, 5.1F, 2.0F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(50, 32)
						.addBox(2.5F, -1.9F, -2.0F, 1.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(58, 29)
						.addBox(2.9F, 5.1F, -2.0F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(60, 36)
						.addBox(1.0F, -2.4F, -2.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition RightLegging = partdefinition.addOrReplaceChild("RightLegging", CubeListBuilder.create()
				.texOffs(46, 47).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition LeftLegging = partdefinition.addOrReplaceChild("LeftLegging", CubeListBuilder.create()
				.texOffs(39, 20).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition RightBoot = partdefinition.addOrReplaceChild("RightBoot", CubeListBuilder.create().texOffs(0, 48)
				.addBox(-2.2F, 8.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition LeftBoot = partdefinition.addOrReplaceChild("LeftBoot", CubeListBuilder.create().texOffs(49, 11)
				.addBox(-1.9F, 8.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(1.9F, 12.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
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