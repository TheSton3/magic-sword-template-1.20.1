// Made with Blockbench 4.11.1
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package com.thestone.magicsword.client.entity.model;

import com.thestone.magicsword.entity.AlyeEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class AlyeEntityModel<T extends AlyeEntity> extends SinglePartEntityModel<T> {
    private final ModelPart bone;
   

    public AlyeEntityModel(ModelPart root) {
        this.bone = root.getChild("bone");

    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData bone = modelPartData.addChild("bone", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData body = bone.addChild("body", ModelPartBuilder.create().uv(4, 29).cuboid(-3.0F, -0.5F, -4.0F, 6.0F, 7.0F, 4.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-3.0F, -0.5F, -4.5F, 6.0F, 15.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -27.5F, 2.0F));

        ModelPartData leftArm = body.addChild("leftArm", ModelPartBuilder.create().uv(34, 40).mirrored().cuboid(-1.5F, -0.25F, -1.7F, 3.0F, 9.0F, 3.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(-4.5F, -0.25F, -2.5F));

        ModelPartData leftArm_r1 = leftArm.addChild("leftArm_r1", ModelPartBuilder.create().uv(46, 46).mirrored().cuboid(-1.0F, -2.0F, -1.5F, 2.0F, 6.0F, 3.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-2.25F, 1.75F, -0.225F, 0.0F, 0.0F, 0.7854F));

        ModelPartData leftArmElbow = leftArm.addChild("leftArmElbow", ModelPartBuilder.create().uv(28, 52).mirrored().cuboid(-1.0F, 0.25F, 0.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.5F, 5.5F, -1.5F));

        ModelPartData leftArmWrist = leftArmElbow.addChild("leftArmWrist", ModelPartBuilder.create().uv(32, 23).mirrored().cuboid(-2.0F, 0.0F, -1.5F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, 6.25F, 1.5F));

        ModelPartData bell = leftArmWrist.addChild("bell", ModelPartBuilder.create().uv(24, 54).cuboid(-0.48F, 1.52F, -0.52F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(48, 19).cuboid(-0.98F, 2.52F, -1.02F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(44, 33).cuboid(-2.03F, 3.72F, -2.02F, 4.0F, 1.0F, 4.0F, new Dilation(0.0F))
                .uv(16, 47).cuboid(-1.53F, 0.72F, -1.42F, 3.0F, 4.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-0.3418F, 3.2502F, 0.13F, 0.0F, -1.5708F, 0.0F));

        ModelPartData cube_r1 = bell.addChild("cube_r1", ModelPartBuilder.create().uv(28, 47).cuboid(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.02F, -0.48F, -0.02F, 0.0F, 0.0F, 0.7854F));

        ModelPartData rightArm = body.addChild("rightArm", ModelPartBuilder.create().uv(34, 40).cuboid(-1.5F, -0.25F, -1.7F, 3.0F, 9.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(4.5F, -0.25F, -2.5F));

        ModelPartData rightArm_r1 = rightArm.addChild("rightArm_r1", ModelPartBuilder.create().uv(46, 46).cuboid(-1.0F, -2.0F, -1.5F, 2.0F, 6.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(2.25F, 1.75F, -0.225F, 0.0F, 0.0F, -0.7854F));

        ModelPartData rightArmElbow = rightArm.addChild("rightArmElbow", ModelPartBuilder.create().uv(28, 52).cuboid(-1.0F, 0.25F, 0.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-0.5F, 5.5F, -1.5F));

        ModelPartData rightArmWrist = rightArmElbow.addChild("rightArmWrist", ModelPartBuilder.create().uv(32, 23).cuboid(-1.0F, 0.0F, -1.5F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 6.25F, 1.5F));

        ModelPartData helberd = rightArmWrist.addChild("helberd", ModelPartBuilder.create().uv(0, 20).cuboid(-0.4944F, 1.75F, -0.6667F, 1.0F, 36.0F, 1.0F, new Dilation(0.0F))
                .uv(36, 52).cuboid(-0.4944F, -5.25F, -0.6667F, 1.0F, 7.0F, 1.0F, new Dilation(0.0F))
                .uv(40, 55).cuboid(-0.4944F, -4.0F, -1.6667F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(48, 55).cuboid(-0.4944F, -3.25F, 0.3333F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(48, 13).cuboid(-0.4944F, -3.25F, -5.6667F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
                .uv(46, 38).cuboid(0.0056F, -4.25F, -7.6667F, 0.0F, 2.0F, 6.0F, new Dilation(0.0F))
                .uv(44, 55).cuboid(-0.4944F, -0.75F, 0.3333F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(46, 0).cuboid(-0.4944F, -6.75F, 1.3333F, 1.0F, 9.0F, 4.0F, new Dilation(0.0F))
                .uv(4, 40).cuboid(-0.0444F, -10.75F, 1.3333F, 0.0F, 15.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.4944F, 0.25F, 20.9167F, -1.4835F, 0.0F, 0.0F));

        ModelPartData lock = body.addChild("lock", ModelPartBuilder.create().uv(16, 54).cuboid(-0.775F, -1.3F, -1.05F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(28, 50).cuboid(-0.775F, -3.3F, -0.925F, 3.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(-0.625F, 2.8F, 1.0F));

        ModelPartData cube_r2 = lock.addChild("cube_r2", ModelPartBuilder.create().uv(40, 52).cuboid(0.0F, -1.0F, -1.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

        ModelPartData body2 = body.addChild("body2", ModelPartBuilder.create().uv(16, 40).cuboid(-3.0F, 0.0F, -3.0F, 6.0F, 4.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 6.5F, -1.0F));

        ModelPartData Leg = body2.addChild("Leg", ModelPartBuilder.create().uv(32, 13).cuboid(-2.5F, 0.0F, -1.5F, 5.0F, 7.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-0.1F, 4.0F, -1.5F));

        ModelPartData Leg2 = Leg.addChild("Leg2", ModelPartBuilder.create().uv(22, 13).cuboid(-2.0F, 7.25F, -0.75F, 4.0F, 7.0F, 0.0F, new Dilation(0.0F))
                .uv(44, 23).cuboid(-2.0F, 0.25F, -2.75F, 4.0F, 7.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 6.75F, 1.25F));

        ModelPartData head = bone.addChild("head", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -27.875F, -0.5F));

        ModelPartData head2 = head.addChild("head2", ModelPartBuilder.create().uv(22, 0).cuboid(-3.0F, -4.5F, -3.0F, 6.0F, 7.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -2.125F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }


    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        bone.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart getPart() {
        return bone;
    }

    @Override
    public void setAngles(AlyeEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

    }
}