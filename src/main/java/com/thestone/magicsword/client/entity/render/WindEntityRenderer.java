package com.thestone.magicsword.client.entity.render;

import com.thestone.magicsword.MagicSword;
import com.thestone.magicsword.client.entity.ModModelLayers;
import com.thestone.magicsword.client.entity.model.AlyeEntityModel;
import com.thestone.magicsword.client.entity.model.FrozenAxeEntityModel;
import com.thestone.magicsword.client.entity.model.WindEntityModel;
import com.thestone.magicsword.entity.AlyeEntity;
import com.thestone.magicsword.entity.projectile.FrozenAxeEntity;
import com.thestone.magicsword.entity.projectile.WindEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class WindEntityRenderer extends EntityRenderer<WindEntity> {
    public static final Identifier TEXTURE = new Identifier(MagicSword.MOD_ID, "textures/entity/wind.png");
    private final WindEntityModel model;

    public WindEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.model = new WindEntityModel(context.getPart(ModModelLayers.WIND_ENTITY));
    }

    @Override
    public void render(WindEntity windEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();
        matrixStack.scale(1f, 1f, 1f);
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.lerp(g, windEntity.prevYaw, windEntity.getYaw()) - 90.0F));
        matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(MathHelper.lerp(g, windEntity.prevPitch, windEntity.getPitch()) + 90.0F));
        VertexConsumer vertexConsumer = ItemRenderer.getDirectItemGlintConsumer(
                vertexConsumerProvider, this.model.getLayer(this.getTexture(windEntity)), false, false
        );
        this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 0.0F);
        matrixStack.pop();
        super.render(windEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(WindEntity entity) {
        return TEXTURE;
    }
    }