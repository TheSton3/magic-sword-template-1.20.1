package com.thestone.magicsword.entity.projectile;

import com.thestone.magicsword.item.HookSword;
import com.thestone.magicsword.main.ModEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.dragon.EnderDragonPart;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;


public class HookSwordEntity extends PersistentProjectileEntity {
    private static final TrackedData<Integer> HOOKED_ENTITY_ID = DataTracker.registerData(HookSwordEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private double maxRange = 0D;
    private double maxSpeed = 0D;
    private boolean isPulling = false;
    private Entity hookedEntity;
    private ItemStack stack;

    public HookSwordEntity(EntityType<? extends PersistentProjectileEntity> entityType, PlayerEntity owner, World world) {
        super(entityType, owner, world);
        this.setNoGravity(true);
        this.setDamage(0);
    }

    public HookSwordEntity(World world, double x, double y, double z) {
        super(ModEntities.HOOK_SWORD_ENTITY, x, y, z, world);
        this.setNoGravity(true);
        this.setDamage(0);
    }

    public HookSwordEntity(World world) {
        super(ModEntities.HOOK_SWORD_ENTITY, world);
        this.setNoGravity(true);
        this.setDamage(0);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.getDataTracker().startTracking(HOOKED_ENTITY_ID, 0);
    }

    //TODO Реворкнуть исчезновение
    @Override
    public void tick() {
        super.tick();

        if (getOwner() instanceof PlayerEntity owner) {
            if (isPulling && age % 2 == 0)
                getWorld().playSound(null, getOwner().getBlockPos(), SoundEvents.ENTITY_ARROW_HIT, SoundCategory.PLAYERS, 1F, 1F);

            if (!getWorld().isClient) {
                if (owner.isDead() || owner.distanceTo(this) > maxRange || !(owner.getMainHandStack().getItem() instanceof HookSword || owner.getOffHandStack().getItem() instanceof HookSword))
                    this.kill();


                if (this.hookedEntity != null) {
                    if (this.hookedEntity.isRemoved()) {
                        this.hookedEntity = null;
                        onRemoved();
                    }
                    this.updatePosition(this.hookedEntity.getX(), this.hookedEntity.getBodyY(0.8D), this.hookedEntity.getZ());

                }

                if (owner.getMainHandStack() == stack || owner.getOffHandStack() == stack) {
                    if (isPulling) {
                        Entity target = owner;
                        Entity origin = this;

                        if (owner.isSneaking() && hookedEntity != null) {
                            target = hookedEntity;
                            origin = owner;
                        }

                        double brakeZone = (6D);
                        double pullSpeed = (10D) / 6D;
                        Vec3d distance = origin.getPos().subtract(target.getPos().add(0, target.getHeight() / 2, 0));
                        Vec3d motion = distance.normalize().multiply(distance.length() < brakeZone ? (pullSpeed * distance.length()) / brakeZone : pullSpeed);

                        if (Math.abs(distance.y) < 0.1D)
                            motion = new Vec3d(motion.x, 0, motion.z);
                        if (new Vec3d(distance.x, 0, distance.z).length() < new Vec3d(target.getWidth() / 2, 0, target.getWidth() / 2).length() / 1.4)
                            motion = new Vec3d(0, motion.y, 0);

                        target.setVelocity(motion);
                        target.velocityModified = true;

                        if (owner.distanceTo(this) <= 3D)
                            this.kill();


                        if (stack.getMaxDamage() > 0 && age % 20 == 0)
                            stack.damage(1, owner, (entity) -> entity.sendToolBreakStatus(owner.getActiveHand()));
                    }
                } else {
                    this.kill();

                }
            }
        } else {
            this.kill();

        }
    }

    @Override
    public void kill() {
        if (!getWorld().isClient && getOwner() instanceof PlayerEntity owner) {
            owner.setNoGravity(false);
        }
        super.kill();
    }

    @Override
    public boolean shouldRender(double distance) {
        return true;
    }

    @Override
    protected float getDragInWater() {
        return 0.99F;
    }

    @Override
    public boolean canUsePortals() {
        return false;
    }

    @Override
    protected ItemStack asItemStack() {
        return this.stack.copy();
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        isPulling = true;
        if (!getWorld().isClient && getOwner() instanceof PlayerEntity owner && hookedEntity == null) {
            owner.setNoGravity(true);
        }
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        if (!getWorld().isClient && getOwner() instanceof PlayerEntity owner && entityHitResult.getEntity() != owner) {
            if ((entityHitResult.getEntity() instanceof LivingEntity || entityHitResult.getEntity() instanceof EnderDragonPart) && hookedEntity == null) {
                hookedEntity = entityHitResult.getEntity();
                dataTracker.set(HOOKED_ENTITY_ID, hookedEntity.getId() + 1);
                isPulling = true;
            }
        }
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound tag) {
        super.readCustomDataFromNbt(tag);
        maxRange = tag.getDouble("maxRange");
        maxSpeed = tag.getDouble("maxSpeed");
        isPulling = tag.getBoolean("isPulling");
        stack = ItemStack.fromNbt(tag.getCompound("hookshotItem"));
        if (getWorld().getEntityById(tag.getInt("owner")) instanceof PlayerEntity owner)
            setOwner(owner);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound tag) {
        super.writeCustomDataToNbt(tag);
        tag.putDouble("maxRange", maxRange);
        tag.putDouble("maxSpeed", maxSpeed);
        tag.putBoolean("isPulling", isPulling);
        tag.put("hookshotItem", stack.writeNbt(new NbtCompound()));
        if (getOwner() instanceof PlayerEntity owner)
            tag.putInt("owner", owner.getId());
    }

    public void setProperties(ItemStack stack, double maxRange, double maxVelocity, float pitch, float yaw, float roll, float modifierZ) {
        float f = 0.017453292F;
        float x = -MathHelper.sin(yaw * f) * MathHelper.cos(pitch * f);
        float y = -MathHelper.sin((pitch + roll) * f);
        float z = MathHelper.cos(yaw * f) * MathHelper.cos(pitch * f);
        this.setVelocity(x, y, z, modifierZ, 0);

        this.stack = stack;
        this.maxRange = maxRange;
        this.maxSpeed = maxVelocity;
    }


}

