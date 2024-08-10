package com.thestone.magicsword.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Tameable;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.EntityStatusEffectS2CPacket;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.scoreboard.AbstractTeam;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Helper {
    public static Entity getTargetedEntity(Entity user, int range) {
        Vec3d rayCastOrigin = user.getEyePos();
        Vec3d userView = user.getRotationVec(1.0F).normalize().multiply(range);
        Vec3d rayCastEnd = rayCastOrigin.add(userView);
        Box searchBox = user.getBoundingBox().expand(range, range, range);
        EntityHitResult hitResult = ProjectileUtil.raycast(user, rayCastOrigin, rayCastEnd, searchBox,
                (target) -> !target.isSpectator() && target.canHit() && target instanceof LivingEntity, range * range);
        if (hitResult != null) {
            return hitResult.getEntity();
        }
        return null;
    }

    public static boolean isWalking(Entity entity) {
        return entity instanceof PlayerEntity player && (!player.isDead() && (player.isSwimming() || player.getVelocity().horizontalLength() > 0.1));
    }

    public static boolean checkFriendlyFire(LivingEntity target, LivingEntity attacker) {
        if (!checkEntityBlacklist(target, attacker)) {
            return false;
        }

        // Check if the player and the living entity are on the same team
        AbstractTeam playerTeam = attacker.getScoreboardTeam();
        AbstractTeam entityTeam = target.getScoreboardTeam();
        if (playerTeam != null && entityTeam != null && target.isTeammate(attacker)) {
            // They are on the same team, so friendly fire should not be allowed
            return false;
        }

        if (target instanceof PlayerEntity playerTarget) {
            if (playerTarget == attacker) {
                return false;
            }
            return !(attacker instanceof PlayerEntity playerAttacker) || playerAttacker.shouldDamagePlayer(playerTarget);
        }
        if (target instanceof Tameable tameable && attacker instanceof PlayerEntity player) {
            if (tameable.getOwner() != null) {
                if (tameable.getOwner() != player
                        && (tameable.getOwner() instanceof PlayerEntity ownerPlayer))
                    return player.shouldDamagePlayer(ownerPlayer);
                return tameable.getOwner() != player;
            }
            return true;
        }
        return true;
    }

    //Check if the target matches blacklisted entities (expand this to be configurable if there is demand)
    public static boolean checkEntityBlacklist(LivingEntity target, LivingEntity player) {
        if (target == null || player == null) {
            return false;
        }
        return !(target instanceof ArmorStandEntity)
                && !(target instanceof VillagerEntity);
    }

    //spawnParticle - spawns particles across both client & server
    public static void spawnParticle(World world, ParticleEffect particle, double xpos, double ypos, double zpos,
                                     double xvelocity, double yvelocity, double zvelocity) {
        if (world.isClient) {
            world.addParticle(particle, xpos, ypos, zpos, xvelocity, yvelocity, zvelocity);
        } else if (world instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(particle, xpos, ypos, zpos, 1, xvelocity, yvelocity, zvelocity, 0.1);
        }
    }

    public static Box createBox(Entity entity, int radius) {
        return new Box(entity.getX() + radius, entity.getY() + (float) radius / 3, entity.getZ() + radius,
                entity.getX() - radius, entity.getY() - (float) radius / 3, entity.getZ() - radius);
    }

    public static Vec3d getPositionLookingAt(PlayerEntity player, int range) {
        HitResult result = player.raycast(range, 0, false);
        //System.out.println(result.getType());
        if (!(result.getType() == HitResult.Type.BLOCK)) return null;

        BlockHitResult blockResult = (BlockHitResult) result;
        //System.out.println(blockResult.getBlockPos());
        return blockResult.getPos();
    }

    public static void incrementStatusEffect(
            LivingEntity livingEntity,
            StatusEffect statusEffect,
            int duration,
            int amplifier,
            int amplifierMax) {

        if (livingEntity.hasStatusEffect(statusEffect)) {
            int currentDuration = livingEntity.getStatusEffect(statusEffect).getDuration();
            int currentAmplifier = livingEntity.getStatusEffect(statusEffect).getAmplifier();

            if (currentAmplifier >= amplifierMax) {
                livingEntity.addStatusEffect(new StatusEffectInstance(
                        statusEffect, Math.max(currentDuration, duration), currentAmplifier, false, false, true));
                return;
            }

            livingEntity.addStatusEffect(new StatusEffectInstance(
                    statusEffect, Math.max(currentDuration, duration), Math.min(amplifierMax, currentAmplifier + amplifier), false, false, true));
        }
        livingEntity.addStatusEffect(new StatusEffectInstance(statusEffect, duration, 0, false, false, true));
    }

    // createFootfalls - creates weapon footfall particle effects (footsteps)
    public static void createFootfalls(Entity entity, ItemStack stack, World world, int stepMod, DefaultParticleType particle,
                                       DefaultParticleType sprintParticle, DefaultParticleType passiveParticle, boolean passiveParticles) {
        if ((entity instanceof PlayerEntity player) && true && player.getEquippedStack(EquipmentSlot.MAINHAND) == stack) {
            if (isWalking(player) && !player.isSwimming() && player.isOnGround()) {
                if (stepMod == 6) {
                    if (player.isSprinting()) {
                        world.addParticle(sprintParticle, player.getX() + player.getHandPosOffset(stack.getItem()).getX(),
                                player.getY() + player.getHandPosOffset(stack.getItem()).getY() + 0.2,
                                player.getZ() + player.getHandPosOffset(stack.getItem()).getZ(),
                                0, 0.0, 0);
                    } else {
                        world.addParticle(particle, player.getX() + player.getHandPosOffset(stack.getItem()).getX(),
                                player.getY() + player.getHandPosOffset(stack.getItem()).getY() + 0.2,
                                player.getZ() + player.getHandPosOffset(stack.getItem()).getZ(),
                                0, 0.0, 0);
                    }
                } else if (stepMod == 3) {
                    if (player.isSprinting()) {
                        world.addParticle(sprintParticle, player.getX() - player.getHandPosOffset(stack.getItem()).getX(),
                                player.getY() + player.getHandPosOffset(stack.getItem()).getY() + 0.2,
                                player.getZ() - player.getHandPosOffset(stack.getItem()).getZ(),
                                0, 0.0, 0);
                    } else {
                        world.addParticle(particle, player.getX() - player.getHandPosOffset(stack.getItem()).getX(),
                                player.getY() + player.getHandPosOffset(stack.getItem()).getY() + 0.2,
                                player.getZ() - player.getHandPosOffset(stack.getItem()).getZ(),
                                0, 0.0, 0);
                    }
                }
            }
            if (passiveParticles) {
                float randomy = (float) (Math.random());
                if (stepMod == 1) {
                    world.addParticle(passiveParticle, player.getX() - player.getHandPosOffset(stack.getItem()).getX(),
                            player.getY() + player.getHandPosOffset(stack.getItem()).getY() + 0.4 + randomy,
                            player.getZ() - player.getHandPosOffset(stack.getItem()).getZ(),
                            0, 0.0, 0);
                    world.addParticle(passiveParticle, player.getX() - player.getHandPosOffset(stack.getItem()).getX() + 0.1,
                            player.getY() + player.getHandPosOffset(stack.getItem()).getY() + randomy,
                            player.getZ() - player.getHandPosOffset(stack.getItem()).getZ() - 0.1,
                            0, 0.0, 0);
                } else if (stepMod == 4) {
                    world.addParticle(passiveParticle, player.getX() + player.getHandPosOffset(stack.getItem()).getX(),
                            player.getY() + player.getHandPosOffset(stack.getItem()).getY() + 0.4 + randomy,
                            player.getZ() + player.getHandPosOffset(stack.getItem()).getZ(),
                            0, 0.0, 0);
                    world.addParticle(passiveParticle, player.getX() + player.getHandPosOffset(stack.getItem()).getX() - 0.1,
                            player.getY() + player.getHandPosOffset(stack.getItem()).getY() + randomy,
                            player.getZ() + player.getHandPosOffset(stack.getItem()).getZ() + 0.1,
                            0, 0.0, 0);
                }
            }
        }
    }

    public static void spawnOrbitParticles(ServerWorld world, Vec3d center, ParticleEffect particleType, double radius, int particleCount) {
        for (int i = 0; i < particleCount; i++) {
            // Calculate the angle for this particle
            double angle = 2 * Math.PI * i / particleCount;

            // Calculate the x and z coordinates on the orbit
            double x = center.x + radius * Math.cos(angle);
            double z = center.z + radius * Math.sin(angle);
            double y = center.y;

            // Spawn the particle at the calculated position
            world.spawnParticles(particleType, x, y, z, 1, 0, 0, 0, 0);
        }
    }

    public static void spawnWaistHeightParticles(ServerWorld world, ParticleEffect particle, Entity entity1, Entity entity2, int count) {
        Vec3d startPos = entity1.getPos().add(0, entity1.getHeight() / 2.0, 0);
        Vec3d endPos = entity2.getPos().add(0, entity2.getHeight() / 2.0, 0);
        Vec3d direction = endPos.subtract(startPos);
        double distance = direction.length();
        Vec3d normalizedDirection = direction.normalize();

        for (int i = 0; i < count; i++) {
            double lerpFactor = (double) i / (count - 1);
            Vec3d currentPos = startPos.add(normalizedDirection.multiply(distance * lerpFactor));
            world.spawnParticles(particle,
                    currentPos.x, currentPos.y, currentPos.z,
                    1,
                    0, 0, 0,
                    0.0);
        }
    }

    public static void spawnRainingParticles(ServerWorld world, ParticleEffect particle, Entity entity2, int count, double blocksAbove) {
        Vec3d endPos = entity2.getPos().add(0, entity2.getHeight() / 2.0, 0);
        Vec3d startPos = endPos.add(0, blocksAbove, 0);
        Vec3d direction = endPos.subtract(startPos);
        double distance = direction.length();
        Vec3d normalizedDirection = direction.normalize();

        for (int i = 0; i < count; i++) {
            double lerpFactor = (double) i / (count - 1);
            Vec3d currentPos = startPos.add(normalizedDirection.multiply(distance * lerpFactor));
            world.spawnParticles(particle,
                    currentPos.x, currentPos.y, currentPos.z,
                    1,
                    0, 0, 0,
                    0.0);
        }
    }

    public static Optional<LivingEntity> findClosestTarget(LivingEntity livingEntity, double maxDistance, double width) {
        World world = livingEntity.getEntityWorld();
        Vec3d eyePosition = livingEntity.getEyePos();
        Vec3d lookVec = livingEntity.getRotationVec(1.0F);
        Vec3d targetVec = eyePosition.add(lookVec.x * maxDistance, lookVec.y * maxDistance, lookVec.z * maxDistance);

        // Calculate the perpendicular vector to the lookVec for the width
        Vec3d perpVec = new Vec3d(-lookVec.z, 0, lookVec.x).normalize().multiply(width / 2.0);

        // Create a search box that extends along the look vector with the specified width
        Box searchBox = new Box(
                eyePosition.subtract(perpVec.x, 1.0, perpVec.z),
                targetVec.add(perpVec.x, 1.0, perpVec.z)
        );

        // Find living entities within the search box, excluding the player
        List<LivingEntity> entities = world.getEntitiesByClass(LivingEntity.class, searchBox, e -> e != livingEntity);

        // Find the closest living entity to the player
        return entities.stream()
                .min(Comparator.comparingDouble(e -> e.squaredDistanceTo(livingEntity)));
    }

    public static List<LivingEntity> getNearbyLivingEntities(World world, Vec3d position, double radius) {
        Box searchBox = new Box(position.x - radius, position.y - radius, position.z - radius,
                position.x + radius, position.y + radius, position.z + radius);
        return world.getEntitiesByClass(LivingEntity.class, searchBox, entity -> true);
    }

    //Get Item attack damage
    public static double getAttackDamage(ItemStack stack) {
        return stack.getItem().getAttributeModifiers(EquipmentSlot.MAINHAND)
                .get(EntityAttributes.GENERIC_ATTACK_DAMAGE)
                .stream()
                .mapToDouble(EntityAttributeModifier::getValue)
                .sum();
    }

    public static void applyDamageWithoutKnockback(LivingEntity target, DamageSource source, float amount) {
        EntityAttributeInstance knockbackResistance = target.getAttributeInstance(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE);
        double originalKnockbackResistance = 0;
        if (knockbackResistance != null) {
            originalKnockbackResistance = knockbackResistance.getValue();
            knockbackResistance.setBaseValue(1.0);
        }
        try {
            target.damage(source, amount);
        } finally {
            if (knockbackResistance != null) {
                knockbackResistance.setBaseValue(originalKnockbackResistance);
            }
        }
    }

    public static void spawnDirectionalParticles(ServerWorld world, ParticleEffect particle, Entity entity, int count, double distance) {
        Vec3d startPos = entity.getPos().add(0, entity.getHeight() / 2.0, 0);

        float pitch = entity.getPitch(1.0F);
        float yaw = entity.getYaw(1.0F);

        double pitchRadians = Math.toRadians(pitch);
        double yawRadians = Math.toRadians(yaw);

        double xDirection = -Math.sin(yawRadians) * Math.cos(pitchRadians);
        double yDirection = -Math.sin(pitchRadians);
        double zDirection = Math.cos(yawRadians) * Math.cos(pitchRadians);
        Vec3d direction = new Vec3d(xDirection, yDirection, zDirection).normalize();

        for (int i = 0; i < count; i++) {
            double lerpFactor = (double) i / (count - 1);
            Vec3d currentPos = startPos.add(direction.multiply(distance * lerpFactor));
            world.spawnParticles(particle,
                    currentPos.x, currentPos.y, currentPos.z,
                    1,
                    0, 0, 0,
                    0.0);
        }
    }

    public static void spawnDirectionalScytleParticles(ServerWorld world, ParticleEffect particle, Entity entity, int count, double distance) {
        Vec3d startPos = entity.getPos().add(0, entity.getHeight() / 2.0, 0);

        float pitch = entity.getPitch(1.0F);
        float yaw = entity.getYaw(1.0F);

        double pitchRadians = Math.toRadians(pitch);
        double yawRadians = Math.toRadians(yaw);

        double xDirection = -Math.sin(yawRadians) * Math.cos(pitchRadians);
        double yDirection = -Math.sin(pitchRadians);
        double zDirection = Math.cos(yawRadians) * Math.cos(pitchRadians);
        Vec3d direction = new Vec3d(xDirection, yDirection, zDirection).normalize();

        for (int i = 0; i < count; i++) {
            double lerpFactor = (double) i / (count - 1);
            Vec3d currentPos = startPos.add(direction.multiply(distance * lerpFactor));
            world.spawnParticles(particle,
                    currentPos.x, currentPos.y, currentPos.z,
                    1,
                    0, 0, 0,
                    0.0);
            world.spawnParticles(particle,
                    currentPos.x + 2, currentPos.y, currentPos.z,
                    1,
                    0, 0, 0,
                    0.0);
            world.spawnParticles(particle,
                    currentPos.x - 2, currentPos.y, currentPos.z,
                    1,
                    0, 0, 0,
                    0.0);
        }
    }

    public static void setInFireAndDamageEntitiesInTrajectory(ServerWorld world, Entity sourceEntity, double distance, float damage, DamageSource damageSource, int fireTicks) {
        Vec3d startPos = sourceEntity.getPos().add(0, sourceEntity.getHeight() / 2.0, 0);
        float pitch = sourceEntity.getPitch(1.0F);
        float yaw = sourceEntity.getYaw(1.0F);

        double pitchRadians = Math.toRadians(pitch);
        double yawRadians = Math.toRadians(yaw);

        double xDirection = -Math.sin(yawRadians) * Math.cos(pitchRadians);
        double yDirection = -Math.sin(pitchRadians);
        double zDirection = Math.cos(yawRadians) * Math.cos(pitchRadians);
        Vec3d direction = new Vec3d(xDirection, yDirection, zDirection).normalize();

        Vec3d endPos = startPos.add(direction.multiply(distance));

        double boxSize = 0.5;
        Box searchBox = new Box(startPos, endPos).expand(boxSize);

        for (Entity entity : world.getOtherEntities(sourceEntity, searchBox)) {
            Box entityBox = entity.getBoundingBox().expand(entity.getTargetingMargin());
            if (entityBox.intersects(searchBox)) {
                if ((sourceEntity instanceof LivingEntity livingEntity)
                        && (entity instanceof LivingEntity livingTarget)
                        && Helper.checkFriendlyFire(livingTarget, livingEntity)) {
                    livingTarget.damage(damageSource, damage);
                    livingTarget.setOnFireFor(fireTicks);
                }
            }
        }
    }



    public static void damageEntitiesInTrajectory(ServerWorld world, Entity sourceEntity, double distance, float damage, DamageSource damageSource) {
        Vec3d startPos = sourceEntity.getPos().add(0, sourceEntity.getHeight() / 2.0, 0);
        float pitch = sourceEntity.getPitch(1.0F);
        float yaw = sourceEntity.getYaw(1.0F);

        double pitchRadians = Math.toRadians(pitch);
        double yawRadians = Math.toRadians(yaw);

        double xDirection = -Math.sin(yawRadians) * Math.cos(pitchRadians);
        double yDirection = -Math.sin(pitchRadians);
        double zDirection = Math.cos(yawRadians) * Math.cos(pitchRadians);
        Vec3d direction = new Vec3d(xDirection, yDirection, zDirection).normalize();

        Vec3d endPos = startPos.add(direction.multiply(distance));

        double boxSize = 0.5;
        Box searchBox = new Box(startPos, endPos).expand(boxSize);

        for (Entity entity : world.getOtherEntities(sourceEntity, searchBox)) {
            Box entityBox = entity.getBoundingBox().expand(entity.getTargetingMargin());
            if (entityBox.intersects(searchBox)) {
                if ((sourceEntity instanceof LivingEntity livingEntity)
                        && (entity instanceof LivingEntity livingTarget)
                        && Helper.checkFriendlyFire(livingTarget, livingEntity)) {
                    livingTarget.damage(damageSource, damage);
                }
            }
        }
    }

    public static void setEffectAndDamageEntitiesInTrajectory(ServerWorld world, Entity sourceEntity, double distance, float damage, DamageSource damageSource) {
        Vec3d startPos = sourceEntity.getPos().add(0, sourceEntity.getHeight() / 2.0, 0);
        float pitch = sourceEntity.getPitch(1.0F);
        float yaw = sourceEntity.getYaw(1.0F);

        double pitchRadians = Math.toRadians(pitch);
        double yawRadians = Math.toRadians(yaw);

        double xDirection = -Math.sin(yawRadians) * Math.cos(pitchRadians);
        double yDirection = -Math.sin(pitchRadians);
        double zDirection = Math.cos(yawRadians) * Math.cos(pitchRadians);
        Vec3d direction = new Vec3d(xDirection, yDirection, zDirection).normalize();

        Vec3d endPos = startPos.add(direction.multiply(distance));

        double boxSize = 0.5;
        Box searchBox = new Box(startPos, endPos).expand(boxSize);

        for (Entity entity : world.getOtherEntities(sourceEntity, searchBox)) {
            Box entityBox = entity.getBoundingBox().expand(entity.getTargetingMargin());
            if (entityBox.intersects(searchBox)) {
                if ((sourceEntity instanceof LivingEntity livingEntity)
                        && (entity instanceof LivingEntity livingTarget)
                        && Helper.checkFriendlyFire(livingTarget, livingEntity)) {
                    livingTarget.damage(damageSource, damage);

                    for (StatusEffectInstance statusEffectInstance : ((LivingEntity) sourceEntity).getStatusEffects()) {
                      livingTarget.setStatusEffect(statusEffectInstance,null);
                    }


                }
            }
        }
    }
}
