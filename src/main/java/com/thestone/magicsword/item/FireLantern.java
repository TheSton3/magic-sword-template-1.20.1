package com.thestone.magicsword.item;

import com.github.theredbrain.manaattributes.entity.ManaUsingEntity;
import com.thestone.magicsword.util.Helper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

import java.util.Optional;

public class FireLantern extends Item {
    public FireLantern(Settings settings) {
        super(settings);
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 40;
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
        if (remainingUseTicks % 5 == 0 && remainingUseTicks < getMaxUseTime(stack) - 5) {
            if (remainingUseTicks < 20) {
                onStoppedUsing(stack, world, user, remainingUseTicks);
            }

        }

    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (!world.isClient()) {
            if (user instanceof PlayerEntity player) {
                DamageSource source = user.getDamageSources().playerAttack(player);
                Optional<LivingEntity> targetEntityReturn = Helper.findClosestTarget(user, 18, 7);
                if (targetEntityReturn.isPresent()) {
                    LivingEntity targetEntity = targetEntityReturn.get();
                    if (((ManaUsingEntity) player).manaattributes$getMana() > 0) {
                        if (world instanceof ServerWorld serverWorld) {
                            int particleCount = 20;
                            Helper.spawnWaistHeightParticles(serverWorld, ParticleTypes.FLAME, user, targetEntity, particleCount);
                            Helper.spawnExlosionParticles(ParticleTypes.FLAME, targetEntity, serverWorld);
                            ((ManaUsingEntity) player).manaattributes$addMana(-1.5f);
                        }
                        stack.damage(2, user, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
                        targetEntity.damage(source, 5f);
                    }
                }

            }
        }
    }


    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.SPEAR;
    }

}