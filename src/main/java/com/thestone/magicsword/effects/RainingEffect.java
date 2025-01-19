package com.thestone.magicsword.effects;

import com.thestone.magicsword.item.WaterDualAxe;
import com.thestone.magicsword.main.ModEffects;
import com.thestone.magicsword.util.Helper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.Box;

import java.util.List;
import java.util.Random;

public class RainingEffect extends HighOrbitingEffect {
    public RainingEffect(StatusEffectCategory category, int color) {
        super (category, color);
        setParticleType1(ParticleTypes.ENCHANT);
        setParticleType2(ParticleTypes.POOF);
        setParticleType3(ParticleTypes.CRIT);
        yOffset = 4f;
        width = 4;
    }

    @Override
    public void applyUpdateEffect(LivingEntity livingEntity, int amplifier) {
        if (!livingEntity.getWorld().isClient()) {
            ServerWorld world = (ServerWorld) livingEntity.getWorld();
            double x = livingEntity.getX();
            double y = livingEntity.getY();
            double z = livingEntity.getZ();
            float damage = 3f;
            double radius = 4f;
            float duration = 400f;
            int frequency = Math.max(3, 10 - amplifier);

            DamageSource damageSource =  livingEntity.getDamageSources().indirectMagic(livingEntity, livingEntity);
            if (livingEntity.age % frequency == 0 && livingEntity instanceof  PlayerEntity player) {
                Box box = new Box(x - radius, y - 1, z - radius, x + radius, y + 1, z + radius);
                List<Entity> nearbyEntities = world.getOtherEntities(livingEntity, box, EntityPredicates.VALID_LIVING_ENTITY);

                if (!nearbyEntities.isEmpty() && player.getMainHandStack().getItem() instanceof WaterDualAxe) {
                    Entity randomEntity = nearbyEntities.get(new Random().nextInt(nearbyEntities.size()));
                    if (randomEntity instanceof LivingEntity target && Helper.checkFriendlyFire(target, player)) {
                        if (target instanceof PlayerEntity)
                            damageSource = livingEntity.getDamageSources().playerAttack(player);
                        target.timeUntilRegen = 0;
                        Helper.applyDamageWithoutKnockback(target, damageSource, damage);
                        target.timeUntilRegen = 0;
                        Helper.spawnRainingParticles(world, ParticleTypes.RAIN, target, 20, yOffset);
                        Helper.spawnRainingParticles(world, ParticleTypes.DRIPPING_WATER, target, 4, yOffset);
                        Helper.spawnOrbitParticles(world, target.getPos(), ParticleTypes.DRIPPING_WATER, 0.5, 6);

                        if (new Random().nextInt(100) < 5)
                            Helper.incrementStatusEffect(livingEntity, ModEffects.RAINING, (int) duration, 1, 10);
                    }
                }
            }
        }

        super.applyUpdateEffect(livingEntity, amplifier);
    }
}
