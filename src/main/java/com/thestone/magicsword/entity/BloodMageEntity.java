package com.thestone.magicsword.entity;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class BloodMageEntity extends HostileEntity {
    private static final TrackedData<Integer> BEAM_TARGET_ID = DataTracker.registerData(BloodMageEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Boolean> ATTACKING = DataTracker.registerData(BloodMageEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState attackAnimationState = new AnimationState();

    private int idleAnimationTimeout = 0;
    public int attackAnimationTimeout = 0;
    @Nullable
    private LivingEntity cachedBeamTarget;
    private int beamTicks;
    protected WanderAroundGoal wanderGoal;


    public BloodMageEntity(EntityType<? extends BloodMageEntity> entityType, World world) {
        super(entityType, world);
    }

    private void updateAnimations() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.age);
        } else {
            --this.idleAnimationTimeout;
        }
        if (this.hasBeamTarget() && attackAnimationTimeout <= 0) {
            attackAnimationTimeout = 80;
            attackAnimationState.start(this.age);
        } else {
            --this.attackAnimationTimeout;
        }
        if (!this.hasBeamTarget()) {
            attackAnimationState.stop();
        }
    }


    @Override
    protected void initGoals() {
        super.initGoals();
        GoToWalkTargetGoal goToWalkTargetGoal = new GoToWalkTargetGoal(this, 1.0);
        this.wanderGoal = new WanderAroundGoal(this, 1.0, 80);
        this.targetSelector.add(2, new ActiveTargetGoal(this, PlayerEntity.class, true));
        this.goalSelector.add(4, new BloodBeamGoal(this));
        this.goalSelector.add(5, goToWalkTargetGoal);
        this.goalSelector.add(7, this.wanderGoal);
        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(9, new LookAroundGoal(this));
        this.wanderGoal.setControls(EnumSet.of(Goal.Control.MOVE, Goal.Control.LOOK));
        goToWalkTargetGoal.setControls(EnumSet.of(Goal.Control.MOVE, Goal.Control.LOOK));
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 6.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.23F)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 35.0)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 30.0);
    }

    public void setAttacking(boolean attacking) {
        this.dataTracker.set(ATTACKING, attacking);
    }

    @Override
    public boolean isAttacking() {
        return this.dataTracker.get(ATTACKING);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(BEAM_TARGET_ID, 0);
        this.dataTracker.startTracking(ATTACKING, false);
    }

    public int getWarmupTime() {
        return 80;
    }

    void setBeamTarget(int entityId) {
        this.dataTracker.set(BEAM_TARGET_ID, entityId);
    }

    public boolean hasBeamTarget() {
        return this.dataTracker.get(BEAM_TARGET_ID) != 0;
    }

    @Override
    public boolean canSpawn(WorldView world) {
        return super.canSpawn(world);
    }

    @Nullable
    public LivingEntity getBeamTarget() {
        if (!this.hasBeamTarget()) {
            return null;
        } else if (this.getWorld().isClient) {
            if (this.cachedBeamTarget != null) {
                return this.cachedBeamTarget;
            } else {
                Entity entity = this.getWorld().getEntityById(this.dataTracker.get(BEAM_TARGET_ID));
                if (entity instanceof LivingEntity) {
                    this.cachedBeamTarget = (LivingEntity) entity;
                    return this.cachedBeamTarget;
                } else {
                    return null;
                }
            }
        } else {
            return this.getTarget();
        }
    }

    @Override
    public void onTrackedDataSet(TrackedData<?> data) {
        super.onTrackedDataSet(data);
        if (BEAM_TARGET_ID.equals(data)) {
            this.beamTicks = 0;
            this.cachedBeamTarget = null;
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getWorld().isClient()) {
            this.updateAnimations();
        }
    }

    @Override
    public void tickMovement() {
        if (this.isAlive()) {
            if (this.getWorld().isClient) {
                if (this.hasBeamTarget()) {
                    if (this.beamTicks < this.getWarmupTime()) {
                        this.beamTicks++;
                    }

                    LivingEntity livingEntity = this.getBeamTarget();
                    if (livingEntity != null) {
                        double d = (double) this.getBeamProgress(0.0F);
                        double e = livingEntity.getX() - this.getX();
                        double f = livingEntity.getBodyY(0.5) - this.getEyeY();
                        double g = livingEntity.getZ() - this.getZ();
                        double h = Math.sqrt(e * e + f * f + g * g);
                        e /= h;
                        f /= h;
                        g /= h;
                        double j = this.random.nextDouble();

                        while (j < h) {
                            j += 1.8 - d + this.random.nextDouble() * (1.7 - d);
                            this.getWorld().addParticle(ParticleTypes.BUBBLE, this.getX() + e * j, this.getEyeY() + f * j, this.getZ() + g * j, 0.0, 0.0, 0.0);
                        }
                    }
                }
            }
            if (this.hasBeamTarget()) {
                this.setYaw(this.headYaw);
            }
        }

        super.tickMovement();
    }

    public float getBeamProgress(float tickDelta) {
        return ((float) this.beamTicks + tickDelta) / (float) this.getWarmupTime();
    }

    public float getBeamTicks() {
        return (float) this.beamTicks;
    }

    static class BloodBeamGoal extends Goal {
        private final BloodMageEntity bloodMageEntity;
        private int beamTicks;


        public BloodBeamGoal(BloodMageEntity bloodMageEntity) {
            this.bloodMageEntity = bloodMageEntity;
            this.setControls(EnumSet.of(Goal.Control.MOVE, Goal.Control.LOOK));
        }

        @Override
        public boolean canStart() {
            LivingEntity livingEntity = this.bloodMageEntity.getTarget();
            return livingEntity != null && livingEntity.isAlive();
        }

        @Override
        public boolean shouldContinue() {
            return super.shouldContinue() && this.bloodMageEntity.getTarget() != null && this.bloodMageEntity.squaredDistanceTo(this.bloodMageEntity.getTarget()) > 9.0;
        }

        @Override
        public void start() {
            this.beamTicks = -10;
            this.bloodMageEntity.getNavigation().stop();
            LivingEntity livingEntity = this.bloodMageEntity.getTarget();
            if (livingEntity != null) {
                this.bloodMageEntity.getLookControl().lookAt(livingEntity, 90.0F, 90.0F);
            }

            this.bloodMageEntity.velocityDirty = true;
        }

        @Override
        public void stop() {
            this.bloodMageEntity.setBeamTarget(0);
            this.bloodMageEntity.setTarget(null);
            this.bloodMageEntity.wanderGoal.ignoreChanceOnce();
        }

        @Override
        public boolean shouldRunEveryTick() {
            return true;
        }

        @Override
        public void tick() {
            LivingEntity livingEntity = this.bloodMageEntity.getTarget();
            if (livingEntity != null) {
                this.bloodMageEntity.getNavigation().stop();
                this.bloodMageEntity.getLookControl().lookAt(livingEntity, 90.0F, 90.0F);
                if (!this.bloodMageEntity.canSee(livingEntity)) {
                    this.bloodMageEntity.setTarget(null);
                } else {
                    this.beamTicks++;
                    if (this.beamTicks == 0) {
                        this.bloodMageEntity.setBeamTarget(livingEntity.getId());
                        if (!this.bloodMageEntity.isSilent()) {
                            // this.bloodMageEntity.getWorld().sendEntityStatus(this.bloodMageEntity, EntityStatuses.PLAY_GUARDIAN_ATTACK_SOUND);
                        }
                    } else if (this.beamTicks >= this.bloodMageEntity.getWarmupTime()) {
                        float f = 1.0F;
                        if (this.bloodMageEntity.getWorld().getDifficulty() == Difficulty.HARD) {
                            f += 2.0F;
                        }

                        livingEntity.damage(this.bloodMageEntity.getDamageSources().indirectMagic(this.bloodMageEntity, this.bloodMageEntity), f);
                        livingEntity.damage(
                                this.bloodMageEntity.getDamageSources().mobAttack(this.bloodMageEntity), (float) this.bloodMageEntity.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE)
                        );
                        this.bloodMageEntity.setTarget(null);
                    }

                    super.tick();
                }
            }
        }
    }
}
