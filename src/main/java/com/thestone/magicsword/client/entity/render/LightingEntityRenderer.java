package com.thestone.magicsword.client.entity.render;

import com.thestone.magicsword.MagicSword;
import com.thestone.magicsword.client.entity.ModModelLayers;
import com.thestone.magicsword.client.entity.model.AcidSprayEntityModel;
import com.thestone.magicsword.client.entity.model.LightingEntityModel;
import com.thestone.magicsword.entity.projectile.AcidSprayEntity;
import com.thestone.magicsword.entity.projectile.LightingEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;

public class LightingEntityRenderer extends ProjectileEntityRenderer<LightingEntity> {
    public static final Identifier TEXTURE = new Identifier(MagicSword.MOD_ID, "textures/entity/lighting_entity_texture.png");
    private final LightingEntityModel model;

    public LightingEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.model = new LightingEntityModel(ctx.getPart(ModModelLayers.LIGHTING_ENTITY));
    }


    @Override
    public Identifier getTexture(LightingEntity entity) {
        return TEXTURE;
    }
}
