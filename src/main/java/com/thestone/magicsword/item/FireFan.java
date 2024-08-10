package com.thestone.magicsword.item;

import com.thestone.magicsword.MagicSword;
import com.thestone.magicsword.main.ModParticles;
import com.thestone.magicsword.util.Helper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class FireFan extends SwordItem {
    public FireFan(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        return super.postHit(stack, target, attacker);
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (itemStack.getDamage() >= itemStack.getMaxDamage() - 1) {
            return TypedActionResult.fail(itemStack);
        }
        user.setCurrentHand(hand);
        return TypedActionResult.consume(itemStack);
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (!world.isClient) {
            if (remainingUseTicks % 5 == 0 && remainingUseTicks < getMaxUseTime(stack) - 5) {
                if (remainingUseTicks < 10) {
                    onStoppedUsing(stack, world, user, remainingUseTicks);
                }
            }
            if (remainingUseTicks == getMaxUseTime(stack) - 1) {
                world.playSoundFromEntity(null, user, SoundEvents.ENTITY_VILLAGER_HURT, user.getSoundCategory(), 0.5f, 1.4f);
            }
        }
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 40;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (!user.getEntityWorld().isClient() && user instanceof PlayerEntity player) {
            int coolDown = 35;
            float damageModifier = 0.7f;
            float damage = (float) (Helper.getAttackDamage(stack) * damageModifier);
            float distance = 16f;
            DamageSource source = player.getDamageSources().playerAttack(player);

            if (remainingUseTicks < 11) {
                world.playSound(null, user.getBlockPos(), SoundEvents.ENTITY_VILLAGER_HURT,
                        user.getSoundCategory(), 0.8f, 0.4f);
                Helper.spawnDirectionalParticles((ServerWorld) world, ModParticles.FIRE_BOOM_PARTICLE, player, 10, distance);
                Helper.setInFireAndDamageEntitiesInTrajectory((ServerWorld) world, player, distance, damage, source, 10);
                user.setVelocity(user.getRotationVector().negate().multiply(+1.1));
                user.setVelocity(user.getVelocity().x, 0, user.getVelocity().z);
            }
            player.getItemCooldownManager().set(this, coolDown);

        }

    }

}
