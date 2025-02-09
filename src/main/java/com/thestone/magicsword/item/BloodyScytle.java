package com.thestone.magicsword.item;

import com.thestone.magicsword.main.ModParticles;
import com.thestone.magicsword.util.Helper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class BloodyScytle extends SwordItem {
    float distance = 16f;

    public BloodyScytle(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {

        return super.postHit(stack, target, attacker);
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            int coolDown = 40;
            float damageModifier = 0.7f;
            DamageSource source = user.getDamageSources().playerAttack(user);
            float damage = (float) (Helper.getAttackDamage(user.getStackInHand(hand)) * damageModifier);
            world.playSound(null, user.getBlockPos(), SoundEvents.BLOCK_SCULK_BREAK,
                    user.getSoundCategory(), 0.8f, 0.4f);
            Helper.spawnDirectionalScytleParticles((ServerWorld) world, ModParticles.BLOODY_PARTICLE, user, 10, distance);
            Helper.setEffectAndDamageEntitiesInTrajectory((ServerWorld) world, user, distance, damage, source);
            user.damage(user.getDamageSources().magic(), (float) 6);
            user.getItemCooldownManager().set(this, coolDown);
        }

        return TypedActionResult.success(user.getStackInHand(hand));
    }
}