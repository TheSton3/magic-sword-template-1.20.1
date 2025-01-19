package com.thestone.magicsword.entity.projectile;

import com.thestone.magicsword.main.ModEntities;
import com.thestone.magicsword.util.Helper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class IceArrorEntity extends PersistentProjectileEntity {

    public IceArrorEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public IceArrorEntity(LivingEntity livingEntity, World world) {
        super(ModEntities.ICE_ARROW_ENTITY, livingEntity, world);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        entityHitResult.getEntity().setFrozenTicks(100);
        DamageSource source = this.getDamageSources().arrow(this, this);
        if (entityHitResult.getEntity() instanceof LivingEntity le) {
            World world = le.getEntityWorld();
            if (!world.isClient()) {
                Helper.spawnExlosionParticles(ParticleTypes.ITEM_SNOWBALL, entityHitResult.getEntity(), (ServerWorld) world);
                entityHitResult.getEntity().damage(source, 5.0F);
            }
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
            if (this.inGround) {
                if (this.inGroundTime % 5 == 0) {
                    this.discard();
                }
            }
        }
    }
}
