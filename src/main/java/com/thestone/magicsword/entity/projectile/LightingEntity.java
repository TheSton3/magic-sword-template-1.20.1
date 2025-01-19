package com.thestone.magicsword.entity.projectile;

import com.thestone.magicsword.main.ModEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class LightingEntity extends PersistentProjectileEntity {

    public LightingEntity(EntityType<? extends PersistentProjectileEntity> entityType, PlayerEntity owner, World world) {
        super(entityType, owner, world);
    }

    public LightingEntity(EntityType<LightingEntity> lightingEntityEntityType, World world) {
        super(lightingEntityEntityType, world);
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        if (!this.getWorld().isClient()) {
            Entity entity2 = this.getOwner();
            BlockPos blockPos = blockHitResult.getBlockPos();

            LightningEntity lightningEntity = EntityType.LIGHTNING_BOLT.create(this.getWorld());
            if (lightningEntity != null) {
                lightningEntity.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(blockPos));
                lightningEntity.setChanneler(entity2 instanceof ServerPlayerEntity ? (ServerPlayerEntity) entity2 : null);
                this.getWorld().spawnEntity(lightningEntity);
                this.discard();

            }

        }
        super.onBlockHit(blockHitResult);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        if (!this.getWorld().isClient()) {
            Entity entity2 = this.getOwner();
            BlockPos blockPos = entityHitResult.getEntity().getBlockPos();

            LightningEntity lightningEntity = EntityType.LIGHTNING_BOLT.create(this.getWorld());
            if (lightningEntity != null) {
                lightningEntity.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(blockPos));
                lightningEntity.setChanneler(entity2 instanceof ServerPlayerEntity ? (ServerPlayerEntity) entity2 : null);
                this.getWorld().spawnEntity(lightningEntity);


            }
        }
    }

    @Override
    protected ItemStack asItemStack() {
        return ItemStack.EMPTY;
    }
}
