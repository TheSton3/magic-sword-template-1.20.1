package com.thestone.magicsword.client.entity;

import com.thestone.magicsword.MagicSword;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayers {
    public static final EntityModelLayer FROZEN_AXE_ENTITY =
            new EntityModelLayer(new Identifier(MagicSword.MOD_ID, "frozen_axe_entity"), "main");
    public static final EntityModelLayer LAKE_OWNER_ENTITY =
            new EntityModelLayer(new Identifier(MagicSword.MOD_ID, "lake_owner_entity"), "main");
    public static final EntityModelLayer HOOK_SWORD_ENTITY =
            new EntityModelLayer(new Identifier(MagicSword.MOD_ID, "hook_sword_entity"), "main");
    public static final EntityModelLayer ACID_SPRAY_ENTITY =
            new EntityModelLayer(new Identifier(MagicSword.MOD_ID, "acid_spray_entity"), "main");
    public static final EntityModelLayer ALYE_ENTITY =
            new EntityModelLayer(new Identifier(MagicSword.MOD_ID, "alye_entity"), "main");
    public static final EntityModelLayer WIND_ENTITY =
            new EntityModelLayer(new Identifier(MagicSword.MOD_ID, "wind_entity"), "main");
    public static final EntityModelLayer LIGHTING_ENTITY =
            new EntityModelLayer(new Identifier(MagicSword.MOD_ID, "lighting_entity"), "main");

}
