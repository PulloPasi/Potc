// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

public class Modelangelica<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation("modid", "angelica"), "main");
	private final ModelPart Head;
	private final ModelPart Body;
	private final ModelPart RightArm;
	private final ModelPart LeftArm;
	private final ModelPart RightLeg;
	private final ModelPart LeftLeg;
	private final ModelPart LeftBoot;
	private final ModelPart RightBoot;

	public Modelangelica(ModelPart root) {
		this.Head = root.getChild("Head");
		this.Body = root.getChild("Body");
		this.RightArm = root.getChild("RightArm");
		this.LeftArm = root.getChild("LeftArm");
		this.RightLeg = root.getChild("RightLeg");
		this.LeftLeg = root.getChild("LeftLeg");
		this.LeftBoot = root.getChild("LeftBoot");
		this.RightBoot = root.getChild("RightBoot");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Head = partdefinition.addOrReplaceChild("Head",
				CubeListBuilder.create().texOffs(0, 0)
						.addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(34, 56)
						.addBox(-4.0F, -8.0F, -4.0F, 0.0F, 14.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(0, 0)
						.addBox(-4.0F, 0.0F, -4.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(54, 67)
						.addBox(-5.2F, -8.0F, -2.0F, 1.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(0, 68)
						.addBox(-4.8F, -8.0F, 0.0F, 1.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(60, 59)
						.addBox(-4.9F, -8.0F, 2.0F, 1.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(50, 67)
						.addBox(-3.9F, -8.0F, 4.0F, 1.0F, 11.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(44, 58)
						.addBox(-2.9F, -8.0F, 3.7F, 2.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(10, 60)
						.addBox(-0.9F, -8.0F, 3.9F, 2.0F, 11.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(28, 58)
						.addBox(1.1F, -8.0F, 3.9F, 2.0F, 13.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(66, 64)
						.addBox(3.1F, -8.0F, 3.6F, 1.0F, 11.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(22, 52)
						.addBox(-5.0F, -8.0F, -4.0F, 1.0F, 13.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(67, 35)
						.addBox(3.8F, -8.0F, -2.0F, 1.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(20, 67)
						.addBox(4.0F, -8.0F, 0.0F, 1.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(38, 58)
						.addBox(3.5F, -7.9F, 2.0F, 1.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(16, 52)
						.addBox(4.0F, -8.0F, -4.0F, 1.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(4, 0)
						.addBox(3.0F, 0.0F, -4.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(24, 8)
						.addBox(-2.9F, -9.0F, -4.0F, 6.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(24, 0)
						.addBox(-2.9F, -11.0F, -3.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(52, 16)
						.addBox(-1.9F, -12.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(12, 33)
						.addBox(-2.9F, -9.0F, 4.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(20, 17)
						.addBox(-2.9F, -9.0F, -6.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(28, 22)
						.addBox(-1.9F, -9.0F, 6.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(24, 20)
						.addBox(-1.9F, -9.0F, -7.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = Head.addOrReplaceChild("cube_r1",
				CubeListBuilder.create().texOffs(40, 28).addBox(0.0F, -1.0F, -4.0F, 1.0F, 1.0F, 8.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-5.8F, -7.8F, 0.0F, 0.0F, 0.0F, -0.0873F));

		PartDefinition cube_r2 = Head.addOrReplaceChild("cube_r2",
				CubeListBuilder.create().texOffs(56, 0).addBox(0.0F, -1.0F, -3.0F, 1.0F, 1.0F, 6.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-6.8F, -7.7F, 0.0F, 0.0F, 0.0F, -0.0873F));

		PartDefinition cube_r3 = Head.addOrReplaceChild("cube_r3",
				CubeListBuilder.create().texOffs(14, 22).addBox(-1.0F, -1.0F, -5.0F, 2.0F, 1.0F, 10.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.8F, -7.9F, 0.0F, 0.0F, 0.0F, -0.0873F));

		PartDefinition cube_r4 = Head.addOrReplaceChild("cube_r4",
				CubeListBuilder.create().texOffs(42, 17).addBox(0.0F, -1.0F, -4.0F, 1.0F, 1.0F, 8.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(5.1F, -8.6F, 0.0F, 0.0F, 0.0F, -0.3054F));

		PartDefinition cube_r5 = Head.addOrReplaceChild("cube_r5",
				CubeListBuilder.create().texOffs(28, 17).addBox(0.0F, -1.0F, -5.0F, 2.0F, 1.0F, 10.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.2F, -8.0F, 0.0F, 0.0F, 0.0F, -0.3054F));

		PartDefinition cube_r6 = Head.addOrReplaceChild("cube_r6",
				CubeListBuilder.create().texOffs(50, 52).addBox(0.0F, -1.0F, -3.0F, 1.0F, 1.0F, 6.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(6.0F, -8.9F, 0.0F, 0.0F, 0.0F, -0.3054F));

		PartDefinition cube_r7 = Head.addOrReplaceChild("cube_r7",
				CubeListBuilder.create().texOffs(28, 28).addBox(-1.0F, -2.0F, 0.0F, 1.0F, 2.0F, 10.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.5F, -9.1F, -1.0F, -0.1309F, -0.1309F, 0.0F));

		PartDefinition cube_r8 = Head.addOrReplaceChild("cube_r8",
				CubeListBuilder.create().texOffs(56, 45).addBox(-1.0F, -2.0F, 0.0F, 1.0F, 1.0F, 5.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.8F, -7.3F, 8.8F, -0.1309F, -0.1309F, 0.0F));

		PartDefinition Body = partdefinition.addOrReplaceChild("Body",
				CubeListBuilder.create().texOffs(0, 16)
						.addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(66, 51)
						.addBox(-4.0F, 0.0F, -3.0F, 1.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(64, 7)
						.addBox(3.0F, 0.0F, -3.0F, 1.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(32, 40)
						.addBox(-5.0F, 0.0F, -3.0F, 1.0F, 13.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(0, 60)
						.addBox(-5.0F, 13.0F, -2.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(50, 59)
						.addBox(4.0F, 13.0F, -2.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(44, 40)
						.addBox(4.0F, 0.0F, -3.0F, 1.0F, 13.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Body_r1 = Body.addOrReplaceChild("Body_r1",
				CubeListBuilder.create().texOffs(60, 7).addBox(-4.0F, -19.0F, 2.0F, 2.0F, 9.0F, 0.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(14.7F, 8.3F, -4.2F, 0.0F, 0.0F, -1.5708F));

		PartDefinition Body_r2 = Body.addOrReplaceChild("Body_r2",
				CubeListBuilder.create().texOffs(66, 21).addBox(-4.0F, -24.0F, 2.0F, 2.0F, 14.0F, 0.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(12.1F, 18.7F, -4.1F, 0.0F, 0.0F, -0.6109F));

		PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(16, 36)
				.addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(0, 32)
				.addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(0, 48)
				.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(44, 4)
				.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition LeftBoot = partdefinition.addOrReplaceChild("LeftBoot", CubeListBuilder.create().texOffs(51, 37)
				.addBox(-1.9F, 8.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition RightBoot = partdefinition.addOrReplaceChild("RightBoot", CubeListBuilder.create()
				.texOffs(50, 26).addBox(-2.1F, 8.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-1.9F, 12.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		Head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftBoot.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightBoot.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}