package com.thestone.magicsword;

import com.thestone.magicsword.entity.AlyeEntity;
import com.thestone.magicsword.entity.LakeOwnerEntity;
import com.thestone.magicsword.main.ModEffects;
import com.thestone.magicsword.main.ModEntities;
import com.thestone.magicsword.main.ModItems;
import com.thestone.magicsword.main.ModParticles;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MagicSword implements ModInitializer {
    public static String MOD_ID = "magic";
    public static final Logger LOGGER = LoggerFactory.getLogger("magic");
    public static final ClampedEntityAttribute MANA = new ClampedEntityAttribute("attribute.name.magic.mana", 0, 0, 999999);
    public static final ClampedEntityAttribute MANAREGEN = new ClampedEntityAttribute("attribute.name.magic.manaregen", 4, -999999, 999999);
    public static final ClampedEntityAttribute MANACOST = new ClampedEntityAttribute("attribute.name.magic.manacost", 100, 0, 999999);

    @Override
    public void onInitialize() {
        ModItems.registerModItems();
        ModParticles.registerParticles();
        ModEffects.registerEffects();
        ModEffects.registerPierceEffect();
        ModEffects.registerRainingEffects();

        FabricDefaultAttributeRegistry.register(ModEntities.LAKE_OWNER_ENTITY, LakeOwnerEntity.createLakeOwnerAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.ALYE_ENTITY, AlyeEntity.createAttributes());
        MANA.setTracked(true);
        MANAREGEN.setTracked(true);
        MANACOST.setTracked(true);
    }
}