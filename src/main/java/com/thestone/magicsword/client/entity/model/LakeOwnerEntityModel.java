package com.thestone.magicsword.client.entity.model;

import com.thestone.magicsword.entity.LakeOwnerEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public class LakeOwnerEntityModel<T extends LakeOwnerEntity> extends SinglePartEntityModel<T> {
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart leftArm;
	private final ModelPart rightArm;
	private final ModelPart leftLeg;
	private final ModelPart rightLeg;
	public LakeOwnerEntityModel(ModelPart root) {
		this.head = root.getChild("head");
		this.body = root.getChild("body");
		this.leftArm = root.getChild("leftArm");
		this.rightArm = root.getChild("rightArm");
		this.leftLeg = root.getChild("leftLeg");
		this.rightLeg = root.getChild("rightLeg");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(17, 22).cuboid(-2.0F, -3.75F, -2.0F, 4.0F, 5.0F, 5.0F, new Dilation(0.0F))
		.uv(20, 0).cuboid(-3.0F, -4.0F, -3.0F, 6.0F, 11.0F, 7.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(32, 18).cuboid(-3.0F, 1.25F, -2.0F, 6.0F, 4.0F, 4.0F, new Dilation(0.0F))
		.uv(2, 0).cuboid(-2.0F, 1.25F, -2.0F, 4.0F, 23.0F, 4.0F, new Dilation(0.0F))
		.uv(0, 31).cuboid(-2.5F, 9.25F, -1.0F, 5.0F, 3.0F, 3.0F, new Dilation(0.0F))
		.uv(13, 34).cuboid(-2.0F, 5.25F, -1.0F, 4.0F, 4.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData leftArm = modelPartData.addChild("leftArm", ModelPartBuilder.create().uv(20, 41).cuboid(0.0F, -0.75F, -1.0F, 2.0F, 6.0F, 3.0F, new Dilation(0.0F))
		.uv(37, 40).cuboid(0.0F, 5.25F, -1.0F, 2.0F, 6.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.0F, 2.0F, 0.0F));

		ModelPartData rightArm = modelPartData.addChild("rightArm", ModelPartBuilder.create().uv(10, 41).cuboid(-2.0F, -0.75F, -1.0F, 2.0F, 6.0F, 3.0F, new Dilation(0.0F))
		.uv(37, 31).cuboid(-2.0F, 5.25F, -1.0F, 2.0F, 6.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(4.75F, 2.0F, 0.0F));

		ModelPartData leftLeg = modelPartData.addChild("leftLeg", ModelPartBuilder.create().uv(0, 37).cuboid(-0.5F, 0.25F, -1.0F, 2.0F, 7.0F, 3.0F, new Dilation(0.0F))
		.uv(46, 0).cuboid(-0.5F, 7.25F, -1.0F, 2.0F, 5.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.15F, 12.0F, 0.0F));

		ModelPartData rightLeg = modelPartData.addChild("rightLeg", ModelPartBuilder.create().uv(27, 34).cuboid(-1.5F, 0.25F, -1.0F, 2.0F, 7.0F, 3.0F, new Dilation(0.0F))
		.uv(44, 26).cuboid(-1.5F, 7.25F, -1.0F, 2.0F, 5.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(2.15F, 12.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		body.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		leftArm.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		rightArm.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return body;
	}

	@Override
	public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

	}
}