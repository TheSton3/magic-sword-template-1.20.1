package com.thestone.magicsword.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class ElementalSword extends SwordItem {
    public ElementalSword(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!attacker.getWorld().isClient()) {
            int hitChance = 25;
            int duration = 100;
            if (attacker.getRandom().nextInt(100) <= hitChance) {
                target.setFrozenTicks(100);
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, duration, 1), attacker);

            }
            if (attacker.getRandom().nextInt(100) <= hitChance) {
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, duration, 3), attacker);

            }

            if (attacker.getRandom().nextInt(100) <= hitChance) {
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, duration, 0), attacker);
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, duration, 1), attacker);

            }
            if (attacker.getRandom().nextInt(100) <= hitChance) {
               target.setFireTicks(100);

            }

        }
        return super.postHit(stack, target, attacker);
    }
}
