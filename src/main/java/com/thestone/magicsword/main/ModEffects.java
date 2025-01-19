package com.thestone.magicsword.main;

import com.thestone.magicsword.MagicSword;
import com.thestone.magicsword.effects.AcidEffect;
import com.thestone.magicsword.effects.PierceEffect;
import com.thestone.magicsword.effects.RainingEffect;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEffects {
    public static StatusEffect ACID;
    public static StatusEffect PIERCE;
    public static StatusEffect RAINING;

    public static StatusEffect registerStatusEffect(String name) {
        return Registry.register(Registries.STATUS_EFFECT, new Identifier(MagicSword.MOD_ID, name),
                new AcidEffect(StatusEffectCategory.HARMFUL, 8889187));


    }

    public static StatusEffect registerPierceEffect(String name) {
        return Registry.register(Registries.STATUS_EFFECT, new Identifier(MagicSword.MOD_ID, name),
                new PierceEffect(StatusEffectCategory.HARMFUL, 8889187).addAttributeModifier(EntityAttributes.GENERIC_ARMOR, "cd45be7c-c86f-4a7e-813b-42a44a054f44", -2, EntityAttributeModifier.Operation.ADDITION));


    }
    public static StatusEffect registerRainingEffect(String name) {
        return Registry.register(Registries.STATUS_EFFECT, new Identifier(MagicSword.MOD_ID, name),
                new RainingEffect(StatusEffectCategory.BENEFICIAL, 8889187));

    }

    public static void registerEffects() {
        ACID = registerStatusEffect("acid");
        PIERCE = registerPierceEffect("pierce");
        RAINING = registerRainingEffect("raining");
    }

    public static void registerPierceEffect() {

    }
    public static void registerRainingEffects() {

    }
}
