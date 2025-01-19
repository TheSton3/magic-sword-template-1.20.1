package com.thestone.magicsword.client.entity.render;

import com.thestone.magicsword.MagicSword;
import com.thestone.magicsword.entity.projectile.AcidSprayEntity;
import com.thestone.magicsword.entity.projectile.IceArrorEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;
@Environment(EnvType.CLIENT)
public class IceArrowRenderer  extends ProjectileEntityRenderer<IceArrorEntity> {
    public static final Identifier TEXTURE = new Identifier(MagicSword.MOD_ID, "textures/entity/ice_arrow.png");;

    public IceArrowRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(IceArrorEntity entity) {
        return TEXTURE;
    }
}
