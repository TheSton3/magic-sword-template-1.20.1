package com.thestone.magicsword.effects;

import net.minecraft.entity.DamageUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class AcidEffect extends StatusEffect {
    public AcidEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        DamageSource source = entity.getDamageSources().magic();
        if (!entity.getWorld().isClient()) {
            int v = entity.getArmor() / 2;
            float amount = DamageUtil.getDamageLeft(v, (float) entity.getArmor(), (float) entity.getAttributeValue(EntityAttributes.GENERIC_ARMOR_TOUGHNESS));
            entity.damageArmor(source, amount);

        }

        System.out.println(entity.getArmor());
        super.applyUpdateEffect(entity, amplifier);
    }


    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
