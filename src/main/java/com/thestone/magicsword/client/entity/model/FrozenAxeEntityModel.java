package com.thestone.magicsword.client.entity.model;// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;

public class FrozenAxeEntityModel extends Model {
	private final ModelPart bone;


	public FrozenAxeEntityModel(ModelPart root) {
		super(RenderLayer::getEntitySolid);
		this.bone = root;
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData bone = modelPartData.addChild("bone", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData handle = bone.addChild("handle", ModelPartBuilder.create().uv(80, 0).cuboid(-6.4F, -54.4F, 9.0F, 5.0F, 8.0F, 6.0F, new Dilation(0.0F))
				.uv(62, 0).cuboid(-6.4F, -46.4F, 10.0F, 5.0F, 32.0F, 4.0F, new Dilation(0.0F))
				.uv(28, 44).cuboid(-6.4F, -8.0F, 2.0F, 5.0F, 42.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -6.6F, -13.0F));

		ModelPartData cube_r1 = handle.addChild("cube_r1", ModelPartBuilder.create().uv(80, 14).cuboid(-2.4F, -1.5F, -2.0F, 5.0F, 13.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-4.0F, -14.8F, 11.5F, -0.7854F, 0.0F, 0.0F));

		ModelPartData blade = bone.addChild("blade", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -41.6F, -22.8F, 4.0F, 17.0F, 27.0F, new Dilation(0.0F))
				.uv(46, 44).cuboid(-0.8F, -45.8F, -29.3F, 2.0F, 24.0F, 12.0F, new Dilation(0.0F))
				.uv(0, 30).cuboid(0.2F, -53.8F, -36.3F, 0.0F, 40.0F, 14.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, -34.4F, 9.8F));

		ModelPartData cube_r2 = blade.addChild("cube_r2", ModelPartBuilder.create().uv(46, 80).cuboid(-2.0F, -2.0F, -2.0F, 4.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -26.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		ModelPartData cube_r3 = blade.addChild("cube_r3", ModelPartBuilder.create().uv(82, 31).cuboid(-2.0F, -2.0F, -2.0F, 4.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -43.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		ModelPartData cube_r4 = blade.addChild("cube_r4", ModelPartBuilder.create().uv(62, 26).cuboid(0.2F, -5.2F, -4.8F, 0.0F, 10.0F, 10.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -53.6F, -29.48F, -0.7854F, 0.0F, 0.0F));

		ModelPartData cube_r5 = blade.addChild("cube_r5", ModelPartBuilder.create().uv(62, 36).cuboid(0.2F, -5.2F, -4.8F, 0.0F, 10.0F, 10.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -13.8F, -29.48F, -0.7854F, 0.0F, 0.0F));

		ModelPartData cube_r6 = blade.addChild("cube_r6", ModelPartBuilder.create().uv(0, 0).cuboid(-0.8F, -5.2F, -4.8F, 2.0F, 10.0F, 10.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -21.8F, -22.48F, -0.7854F, 0.0F, 0.0F));

		ModelPartData cube_r7 = blade.addChild("cube_r7", ModelPartBuilder.create().uv(35, 0).cuboid(-0.8F, -5.2F, -4.8F, 2.0F, 10.0F, 10.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -45.6F, -22.48F, -0.7854F, 0.0F, 0.0F));

		ModelPartData cube_r8 = blade.addChild("cube_r8", ModelPartBuilder.create().uv(66, 72).cuboid(-2.0F, -4.0F, -4.0F, 4.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -41.6F, -17.2F, -0.7854F, 0.0F, 0.0F));

		ModelPartData cube_r9 = blade.addChild("cube_r9", ModelPartBuilder.create().uv(74, 48).cuboid(-2.0F, -4.0F, -4.0F, 4.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -24.6F, -17.2F, -0.7854F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 128, 128);
	}


	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		bone.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}


