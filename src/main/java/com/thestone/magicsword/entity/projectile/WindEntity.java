package com.thestone.magicsword.entity.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ExplosiveProjectileEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class WindEntity extends PersistentProjectileEntity {


    public WindEntity(EntityType<? extends PersistentProjectileEntity> entityType, PlayerEntity owner, World world) {
        super(entityType, owner, world);
    }

    public WindEntity(EntityType<WindEntity> windEntityEntityType, World world) {
        super(windEntityEntityType, world);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        if (entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) entity;
            Vec3d motion = livingEntity.getRotationVector();
            Vec3d dash = new Vec3d(motion.x * 2, 0.6f, motion.z * 2);
            livingEntity.setVelocity(dash);
        }

    }

    @Override
    protected ItemStack asItemStack() {
        return ItemStack.EMPTY;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getWorld().isClient) {
            if ((this.inGround && this.inGroundTime != 0 && this.inGroundTime >= 200)) {
                this.discard();
            }
        }
    }
}
