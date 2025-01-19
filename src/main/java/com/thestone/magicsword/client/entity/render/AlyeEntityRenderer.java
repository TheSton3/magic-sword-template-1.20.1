package com.thestone.magicsword.client.entity.render;

import com.thestone.magicsword.MagicSword;
import com.thestone.magicsword.client.entity.ModModelLayers;
import com.thestone.magicsword.client.entity.model.AlyeEntityModel;
import com.thestone.magicsword.entity.AlyeEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class AlyeEntityRenderer extends MobEntityRenderer<AlyeEntity, AlyeEntityModel<AlyeEntity>> {

    private static final Identifier TEXTURE = new Identifier(MagicSword.MOD_ID, "textures/entity/alye.png");

    public AlyeEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new AlyeEntityModel<>(context.getPart(ModModelLayers.ALYE_ENTITY)), 0.6f);
    }

    @Override
    public Identifier getTexture(AlyeEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(AlyeEntity mobEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.scale(1f, 1f, 1f);
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
