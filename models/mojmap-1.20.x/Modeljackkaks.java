// Made with Blockbench 4.11.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

public class Modeljackkaks<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation("modid", "jackkaks"), "main");
	private final ModelPart Helmet;
	private final ModelPart Chestplate;
	private final ModelPart RightPlate;
	private final ModelPart LeftPlate;

	public Modeljackkaks(ModelPart root) {
		this.Helmet = root.getChild("Helmet");
		this.Chestplate = root.getChild("Chestplate");
		this.RightPlate = root.getChild("RightPlate");
		this.LeftPlate = root.getChild("LeftPlate");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Helmet = partdefinition.addOrReplaceChild("Helmet",
				CubeListBuilder.create().texOffs(24, 9)
						.addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(12, 69)
						.addBox(4.0F, -8.0F, 2.0F, 1.0F, 11.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(10, 0)
						.addBox(-0.6F, -8.0F, 3.6F, 1.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(48, 69)
						.addBox(-2.9F, -8.0F, 3.6F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(32, 17)
						.addBox(-4.0F, -8.0F, -4.1F, 8.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(24, 8)
						.addBox(-2.0F, 0.0F, -4.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(17, 8)
						.addBox(1.0F, 0.0F, -4.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = Helmet.addOrReplaceChild("cube_r1",
				CubeListBuilder.create().texOffs(36, 73).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 7.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(5.4F, -6.0F, 1.4F, 0.0F, 0.0F, -0.1309F));

		PartDefinition cube_r2 = Helmet
				.addOrReplaceChild("cube_r2",
						CubeListBuilder.create().texOffs(72, 41).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 8.0F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(5.0F, -6.0F, 0.1F, 0.0436F, 0.0F, 0.0F));

		PartDefinition cube_r3 = Helmet.addOrReplaceChild("cube_r3",
				CubeListBuilder.create().texOffs(32, 73).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 7.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.6F, -6.0F, -0.9F, 0.0F, 0.0F, -0.1309F));

		PartDefinition cube_r4 = Helmet.addOrReplaceChild("cube_r4",
				CubeListBuilder.create().texOffs(52, 69).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 9.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.5F, -6.0F, 4.2F, 0.0436F, 0.0F, 0.0436F));

		PartDefinition cube_r5 = Helmet.addOrReplaceChild("cube_r5",
				CubeListBuilder.create().texOffs(72, 11).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 9.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.7F, -6.0F, 5.1F, 0.0873F, -0.0873F, 0.0436F));

		PartDefinition cube_r6 = Helmet
				.addOrReplaceChild("cube_r6",
						CubeListBuilder.create().texOffs(72, 0).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 9.0F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(1.7F, -6.0F, 5.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition cube_r7 = Helmet.addOrReplaceChild("cube_r7",
				CubeListBuilder.create().texOffs(16, 72).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 8.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.8F, -6.0F, 4.7F, 0.0436F, 0.0F, -0.0436F));

		PartDefinition cube_r8 = Helmet.addOrReplaceChild("cube_r8",
				CubeListBuilder.create().texOffs(4, 69).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 11.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.0F, -6.0F, 5.0F, 0.0436F, 0.0F, -0.0436F));

		PartDefinition cube_r9 = Helmet.addOrReplaceChild("cube_r9",
				CubeListBuilder.create().texOffs(68, 41).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 8.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.6F, -6.0F, 1.2F, 0.0F, 0.0F, -0.0436F));

		PartDefinition cube_r10 = Helmet.addOrReplaceChild("cube_r10",
				CubeListBuilder.create().texOffs(24, 72).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 8.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.9F, -6.0F, 0.0F, 0.0F, 0.0F, 0.0873F));

		PartDefinition cube_r11 = Helmet.addOrReplaceChild("cube_r11",
				CubeListBuilder.create().texOffs(28, 73).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 7.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.7F, -6.0F, -1.0F, 0.0F, -0.0436F, 0.1745F));

		PartDefinition cube_r12 = Helmet.addOrReplaceChild("cube_r12",
				CubeListBuilder.create().texOffs(44, 68).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 11.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.2F, -6.0F, 4.2F, 0.0F, 0.2182F, 0.0436F));

		PartDefinition cube_r13 = Helmet.addOrReplaceChild("cube_r13",
				CubeListBuilder.create().texOffs(20, 72).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 9.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.0F, -6.0F, 3.0F, 0.0F, 0.0F, 0.0436F));

		PartDefinition cube_r14 = Helmet.addOrReplaceChild("cube_r14",
				CubeListBuilder.create().texOffs(8, 69).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 11.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.0F, -6.0F, -2.0F, -0.0436F, 0.0F, 0.0436F));

		PartDefinition cube_r15 = Helmet.addOrReplaceChild("cube_r15",
				CubeListBuilder.create().texOffs(56, 69).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 10.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(5.0F, -6.0F, -2.0F, -0.0436F, 0.0F, -0.0436F));

		PartDefinition Chestplate = partdefinition.addOrReplaceChild("Chestplate",
				CubeListBuilder.create().texOffs(30, 41)
						.addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)).texOffs(34, 36)
						.addBox(-4.0F, 10.0F, -2.4F, 8.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(8, 33)
						.addBox(1.0F, 0.0F, -2.2F, 3.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(0, 0)
						.addBox(-2.4F, 2.0F, -2.1F, 5.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(0, 29)
						.addBox(4.1F, 0.0F, -2.1F, 0.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(0, 69)
						.addBox(-4.0F, 0.0F, -2.2F, 2.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(0, 13)
						.addBox(-4.1F, 10.0F, -2.1F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(0, 6)
						.addBox(4.1F, 10.0F, -2.1F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(0, 23)
						.addBox(0.0F, 8.0F, -2.4F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r16 = Chestplate.addOrReplaceChild("cube_r16",
				CubeListBuilder.create().texOffs(44, 57).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 9.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(5.3F, 11.0F, -1.8F, 0.0873F, 0.1745F, -0.0436F));

		PartDefinition cube_r17 = Chestplate.addOrReplaceChild("cube_r17",
				CubeListBuilder.create().texOffs(17, 4).addBox(-4.0F, -2.0F, -1.0F, 12.0F, 2.0F, 0.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.9F, 4.5F, -1.3F, 0.0F, 0.0F, 1.0036F));

		PartDefinition RightPlate = partdefinition.addOrReplaceChild("RightPlate", CubeListBuilder.create()
				.texOffs(28, 57).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition LeftPlate = partdefinition.addOrReplaceChild("LeftPlate", CubeListBuilder.create()
				.texOffs(50, 53).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(5.0F, 2.0F, 0.0F));

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
	}
}