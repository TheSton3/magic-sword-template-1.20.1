package com.thestone.magicsword.client.entity;

import com.thestone.magicsword.MagicSword;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;

public class ModModelLayers {
    public static final EntityModelLayer FROZEN_AXE_ENTITY =
            new EntityModelLayer(new Identifier(MagicSword.MOD_ID, "frozen_axe_entity"), "main");
    public static final EntityModelLayer LAKE_OWNER_ENTITY =
            new EntityModelLayer(new Identifier(MagicSword.MOD_ID, "lake_owner_entity"), "main");
    public static final EntityModelLayer HOOK_SWORD_ENTITY =
            new EntityModelLayer(new Identifier(MagicSword.MOD_ID, "hook_sword_entity"), "main");
}
