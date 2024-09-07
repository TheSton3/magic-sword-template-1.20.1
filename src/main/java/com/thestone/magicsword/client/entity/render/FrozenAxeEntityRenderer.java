package com.thestone.magicsword.client.entity.render;

import com.thestone.magicsword.MagicSword;
import com.thestone.magicsword.client.entity.ModModelLayers;
import com.thestone.magicsword.client.entity.model.FrozenAxeEntityModel;
import com.thestone.magicsword.entity.projectile.FrozenAxeEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.TridentEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class FrozenAxeEntityRenderer extends EntityRenderer<FrozenAxeEntity> {
    public static final Identifier TEXTURE = new Identifier(MagicSword.MOD_ID, "textures/entity/frozen_axe_entity.png");
    private final FrozenAxeEntityModel model;

    public FrozenAxeEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.model = new FrozenAxeEntityModel(context.getPart(ModModelLayers.FROZEN_AXE_ENTITY));
    }
    @Override
    public void render(FrozenAxeEntity frozenAxeEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();
        matrixStack.scale(0.3f,0.3f,0.3f);
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.lerp(g, frozenAxeEntity.prevYaw, frozenAxeEntity.getYaw()) - 90.0F));
        matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(MathHelper.lerp(g, frozenAxeEntity.prevPitch, frozenAxeEntity.getPitch()) + 90.0F));
        VertexConsumer vertexConsumer = ItemRenderer.getDirectItemGlintConsumer(
                vertexConsumerProvider, this.model.getLayer(this.getTexture(frozenAxeEntity)), false, frozenAxeEntity.isEnchanted()
        );
        this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 0.0F);
        matrixStack.pop();
        super.render(frozenAxeEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
    @Override
    public Identifier getTexture(FrozenAxeEntity entity) {
        return TEXTURE;
    }




}
