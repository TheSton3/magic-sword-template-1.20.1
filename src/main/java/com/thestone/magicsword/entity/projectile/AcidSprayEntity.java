package com.thestone.magicsword.entity.projectile;

import com.thestone.magicsword.main.ModEffects;
import com.thestone.magicsword.main.ModEntities;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;

public class AcidSprayEntity extends PersistentProjectileEntity {


    public AcidSprayEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public AcidSprayEntity(LivingEntity livingEntity, World world) {
        super(ModEntities.ACID_SPRAY_ENTITY, livingEntity, world);
    }


    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        if (!this.getWorld().isClient()) {
            AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(this.getWorld(), this.getX(), this.getY(), this.getZ());
            Entity entity = this.getOwner();
            if (entity instanceof LivingEntity) {
                areaEffectCloudEntity.setOwner((LivingEntity) entity);
            }
            areaEffectCloudEntity.setRadius(3.0F);
            areaEffectCloudEntity.setRadiusOnUse(-0.5F);
            areaEffectCloudEntity.setWaitTime(10);
            areaEffectCloudEntity.setRadiusGrowth(-areaEffectCloudEntity.getRadius() / (float) areaEffectCloudEntity.getDuration());
            areaEffectCloudEntity.addEffect(new StatusEffectInstance(ModEffects.ACID, 100, 1));
            this.getWorld().spawnEntity(areaEffectCloudEntity);
        }
        super.onBlockHit(blockHitResult);
    }

    @Override
    protected ItemStack asItemStack() {
        return ItemStack.EMPTY;
    }

    @Override
    public void tick() {
        super.tick();
        super.tick();
        if (this.getWorld().isClient) {
            if ((this.inGround && this.inGroundTime != 0 && this.inGroundTime >= 200)) {
                this.discard();
            }
        }
    }
}


