// Made with Blockbench 4.12.2
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package com.thestone.magicsword.client.entity.model;

import com.thestone.magicsword.client.entity.animations.BloodMageAnimations;
import com.thestone.magicsword.entity.BloodMageEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public class BloodMageModel<T extends BloodMageEntity> extends SinglePartEntityModel<T> {
    private final ModelPart baron;

    public BloodMageModel(ModelPart root) {
        this.baron = root.getChild("baron");


    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData baron = modelPartData.addChild("baron", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData head = baron.addChild("head", ModelPartBuilder.create().uv(0, 30).cuboid(-3.25F, -7.7118F, -5.2316F, 6.5F, 6.5F, 5.75F, new Dilation(0.0F))
                .uv(30, 18).cuboid(-2.8F, -9.4618F, -4.5816F, 5.6F, 1.75F, 6.6F, new Dilation(0.0F))
                .uv(30, 27).cuboid(-1.45F, -1.2118F, -2.2816F, 2.9F, 1.25F, 2.3F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -37.7882F, 2.2316F));

        ModelPartData cube_r1 = head.addChild("cube_r1", ModelPartBuilder.create().uv(26, 30).cuboid(-2.0F, -0.475F, -2.75F, 4.0F, 1.35F, 5.5F, new Dilation(0.0F)), ModelTransform.of(0.0F, -9.3368F, 1.7684F, -0.3054F, 0.0F, 0.0F));

        ModelPartData cube_r2 = head.addChild("cube_r2", ModelPartBuilder.create().uv(32, 12).cuboid(-1.3F, -0.375F, -2.75F, 2.6F, 1.25F, 4.7F, new Dilation(0.0F)), ModelTransform.of(0.0F, -9.8368F, 4.0184F, -0.3054F, 0.0F, 0.0F));

        ModelPartData torso = baron.addChild("torso", ModelPartBuilder.create().uv(0, 0).cuboid(-4.75F, -1.375F, -3.375F, 9.5F, 12.0F, 6.0F, new Dilation(0.0F))
                .uv(0, 18).cuboid(-4.75F, -7.875F, -1.875F, 9.5F, 6.5F, 4.5F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -29.875F, -0.375F));

        ModelPartData left_leg = torso.addChild("left_leg", ModelPartBuilder.create().uv(32, 0).cuboid(-1.75F, 0.25F, -1.75F, 3.5F, 7.5F, 3.5F, new Dilation(0.0F)), ModelTransform.pivot(3.0F, 10.375F, 0.875F));

        ModelPartData left_knee = left_leg.addChild("left_knee", ModelPartBuilder.create().uv(14, 43).cuboid(-1.6F, 0.1875F, 0.3625F, 3.2F, 7.5F, 3.1F, new Dilation(0.0F))
                .uv(46, 27).cuboid(-1.5F, 7.6875F, -1.2875F, 3.0F, 1.75F, 4.75F, new Dilation(0.0F)), ModelTransform.pivot(0.05F, 7.5625F, -1.7125F));

        ModelPartData right_arm = torso.addChild("right_arm", ModelPartBuilder.create().uv(42, 37).cuboid(-3.0F, -0.875F, -1.75F, 3.0F, 8.25F, 3.5F, new Dilation(0.0F)), ModelTransform.pivot(-4.75F, -7.0F, 0.875F));

        ModelPartData right_elbow = right_arm.addChild("right_elbow", ModelPartBuilder.create().uv(26, 49).cuboid(-1.35F, 0.0F, -2.8625F, 2.75F, 8.25F, 2.85F, new Dilation(0.0F))
                .uv(50, 49).cuboid(-1.45F, 8.25F, -3.1125F, 2.85F, 2.25F, 3.1F, new Dilation(0.0F)), ModelTransform.pivot(-1.4F, 7.375F, 1.5125F));

        ModelPartData right_leg = torso.addChild("right_leg", ModelPartBuilder.create().uv(26, 37).cuboid(-1.75F, 0.25F, -1.75F, 3.5F, 7.5F, 3.5F, new Dilation(0.0F)), ModelTransform.pivot(-3.0F, 10.375F, 0.875F));

        ModelPartData right_knee = right_leg.addChild("right_knee", ModelPartBuilder.create().uv(48, 0).cuboid(-1.6F, 0.1875F, 0.3625F, 3.2F, 7.5F, 3.1F, new Dilation(0.0F))
                .uv(48, 11).cuboid(-1.5F, 7.6875F, -1.2875F, 3.0F, 1.75F, 4.75F, new Dilation(0.0F)), ModelTransform.pivot(-0.05F, 7.5625F, -1.7125F));

        ModelPartData left_arm = torso.addChild("left_arm", ModelPartBuilder.create().uv(0, 43).cuboid(0.0F, -0.875F, -1.75F, 3.0F, 8.25F, 3.5F, new Dilation(0.0F)), ModelTransform.pivot(4.75F, -7.0F, 0.875F));

        ModelPartData left_elbow = left_arm.addChild("left_elbow", ModelPartBuilder.create().uv(38, 49).cuboid(-1.4F, 0.0F, -2.8625F, 2.75F, 8.25F, 2.85F, new Dilation(0.0F))
                .uv(14, 54).cuboid(-1.4F, 8.25F, -3.1125F, 2.85F, 2.25F, 3.1F, new Dilation(0.0F)), ModelTransform.pivot(1.4F, 7.375F, 1.5125F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        baron.render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }

    @Override
    public void setAngles(BloodMageEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.animateMovement(BloodMageAnimations.ANIM_BARON_WALK, limbSwing, limbSwingAmount, 1f, 1f);
        this.updateAnimation(entity.idleAnimationState, BloodMageAnimations.ANIM_BARON_IDLE, ageInTicks, 1f);
        this.updateAnimation(entity.attackAnimationState, BloodMageAnimations.ANIM_BARON_ATTACK, ageInTicks, 1f);

    }

    @Override
    public ModelPart getPart() {
        return baron;
    }
}