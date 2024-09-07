package com.thestone.magicsword.item;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import net.minecraft.util.math.random.Random;

public class MidasSword extends SwordItem {
    public MidasSword(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        int i = 1 + target.getRandom().nextInt(3);
        ItemStack itemStack = new ItemStack(Items.GOLD_NUGGET,i);
        for (int j = 0; j < i; j++) {
            ItemEntity itemEntity = target.dropItem(itemStack.getItem(), 1);
            if (itemEntity != null) {
                itemEntity.setVelocity(
                        itemEntity.getVelocity()
                                .add(
                                        (target.getRandom().nextFloat() - target.getRandom().nextFloat()) * 0.3F,
                                        target.getRandom().nextFloat() * 0.10F,
                                        (target.getRandom().nextFloat() - target.getRandom().nextFloat()) * 0.3F
                                )
                );
            }
        }

        return super.postHit(stack, target, attacker);
    }
}
