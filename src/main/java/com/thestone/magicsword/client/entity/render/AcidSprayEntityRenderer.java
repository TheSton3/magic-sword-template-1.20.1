package com.thestone.magicsword.client.entity.render;

import com.thestone.magicsword.MagicSword;
import com.thestone.magicsword.client.entity.ModModelLayers;
import com.thestone.magicsword.client.entity.model.AcidSprayEntityModel;
import com.thestone.magicsword.entity.projectile.AcidSprayEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;

public class AcidSprayEntityRenderer extends ProjectileEntityRenderer<AcidSprayEntity> {

    public static final Identifier TEXTURE = new Identifier(MagicSword.MOD_ID, "textures/entity/acid_spray.png");
    private final AcidSprayEntityModel model;

    public AcidSprayEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.model = new AcidSprayEntityModel(ctx.getPart(ModModelLayers.ACID_SPRAY_ENTITY));
    }



    @Override
    public Identifier getTexture(AcidSprayEntity entity) {
        return TEXTURE;
    }
}
