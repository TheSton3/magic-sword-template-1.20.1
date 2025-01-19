// Made with Blockbench 4.11.1
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package com.thestone.magicsword.client.entity.model;

import com.thestone.magicsword.entity.projectile.WindEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class WindEntityModel<T extends WindEntity> extends SinglePartEntityModel<T> {
    private final ModelPart wind;

    public WindEntityModel(ModelPart root) {
        this.wind = root.getChild("wind");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData wind = modelPartData.addChild("wind", ModelPartBuilder.create().uv(0, 35).cuboid(-3.0F, 4.75F, -3.0F, 6.0F, 5.0F, 6.0F, new Dilation(0.0F))
                .uv(0, 20).cuboid(-5.0F, 0.0F, -5.0F, 10.0F, 5.0F, 10.0F, new Dilation(0.0F))
                .uv(0, 1).cuboid(-7.0F, -5.0F, -7.0F, 14.0F, 5.0F, 14.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 14.25F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        wind.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart getPart() {
        return wind;
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

    }
}