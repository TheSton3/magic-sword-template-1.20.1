package com.thestone.magicsword.mixin;

import com.mojang.datafixers.types.templates.Tag;
import com.thestone.magicsword.main.ModItems;
import com.thestone.magicsword.main.ModParticles;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "attack", at = @At(value = "TAIL"))
    private void spawnBloodySweep(CallbackInfo ci) {
        double d = -MathHelper.sin(this.getYaw() * (float) (Math.PI / 180.0));
        double e = MathHelper.cos(this.getYaw() * (float) (Math.PI / 180.0));
        if (this.getWorld() instanceof ServerWorld && this.getMainHandStack().getItem() == ModItems.BLOODY_SCYTLE) {
            ((ServerWorld) this.getWorld()).spawnParticles(ModParticles.BLOODY_SWEEP, this.getX() + d, this.getBodyY(0.5), this.getZ() + e, 0, d, 0.0, e, 0.0);
        }
    }

    @ModifyVariable(method = "attack", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/enchantment/EnchantmentHelper;getAttackDamage(Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/EntityGroup;)F"), ordinal = 1)
    private float getAttackDamage(float baseDamage, Entity target) {
        if (this.getMainHandStack().getItem() == ModItems.ARONDITE) {
            if (target instanceof Monster) {
                return baseDamage + 6;
            }

        }
        if (this.getMainHandStack().getItem() == ModItems.BLOODY_AXE) {
            if (this.getHealth() <= (this.getMaxHealth() / 2)) {
                return baseDamage + 4;
            }
        }
        return baseDamage;
    }


}
