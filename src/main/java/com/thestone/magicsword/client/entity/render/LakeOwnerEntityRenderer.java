package com.thestone.magicsword.client.entity.render;

import com.thestone.magicsword.MagicSword;
import com.thestone.magicsword.client.entity.ModModelLayers;
import com.thestone.magicsword.client.entity.model.LakeOwnerEntityModel;
import com.thestone.magicsword.entity.LakeOwnerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class LakeOwnerEntityRenderer extends MobEntityRenderer<LakeOwnerEntity, LakeOwnerEntityModel<LakeOwnerEntity>> {
    private static final Identifier TEXTURE = new Identifier(MagicSword.MOD_ID, "textures/entity/lake_owner.png");

    public LakeOwnerEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new LakeOwnerEntityModel<>(context.getPart(ModModelLayers.LAKE_OWNER_ENTITY)), 1f);
    }

    @Override
    public Identifier getTexture(LakeOwnerEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(LakeOwnerEntity mobEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.scale(1f, 1f, 1f);
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}

