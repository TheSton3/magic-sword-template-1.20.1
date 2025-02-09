package com.thestone.magicsword.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class MidnightDagger extends SwordItem {
    public MidnightDagger(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        StatusEffectInstance darkNess = new StatusEffectInstance(StatusEffects.DARKNESS, 260, 0, false, false);
        StatusEffectInstance blindNEss = new StatusEffectInstance(StatusEffects.BLINDNESS, 260, 0, false, false);
        target.addStatusEffect(darkNess);
        target.addStatusEffect(blindNEss);
        attacker.setInvisible(false);
        return super.postHit(stack, target, attacker);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            user.getItemCooldownManager().set(this, 20);
            user.setInvisible(true);
        }
        return TypedActionResult.success(user.getStackInHand(hand));
    }


}
