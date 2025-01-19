// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package com.thestone.magicsword.client.entity.model;

import com.thestone.magicsword.entity.projectile.AcidSprayEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public class AcidSprayEntityModel extends EntityModel<AcidSprayEntity> {
	private final ModelPart bb_main;
	public AcidSprayEntityModel(ModelPart root) {
		this.bb_main = root.getChild("bb_main");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(36, 0).cuboid(-3.0F, -10.0F, -3.0F, 6.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData cube_r1 = bb_main.addChild("cube_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -7.0F, 0.0F, 0.0F, -1.5708F, 0.7854F));

		ModelPartData cube_r2 = bb_main.addChild("cube_r2", ModelPartBuilder.create().uv(0, 12).cuboid(-3.0F, -4.0F, -3.0F, 6.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -6.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		ModelPartData cube_r3 = bb_main.addChild("cube_r3", ModelPartBuilder.create().uv(18, 6).cuboid(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -7.0F, 0.0F, 0.0F, -1.5708F, -0.4363F));

		ModelPartData cube_r4 = bb_main.addChild("cube_r4", ModelPartBuilder.create().uv(18, 18).cuboid(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -7.0F, 0.0F, 0.0F, -1.5708F, -1.309F));

		ModelPartData cube_r5 = bb_main.addChild("cube_r5", ModelPartBuilder.create().uv(0, 24).cuboid(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -7.0F, 0.0F, -1.309F, 0.0F, 0.0F));

		ModelPartData cube_r6 = bb_main.addChild("cube_r6", ModelPartBuilder.create().uv(18, 30).cuboid(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -7.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

		ModelPartData cube_r7 = bb_main.addChild("cube_r7", ModelPartBuilder.create().uv(0, 36).cuboid(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -7.0F, 0.0F, -0.3927F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		bb_main.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public void setAngles(AcidSprayEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

	}
}