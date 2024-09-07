package com.thestone.magicsword.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.PotionEntity;
import net.minecraft.entity.raid.RaiderEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class AcidSword extends SwordItem {
    public AcidSword(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);


    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        Vec3d vec3d = user.getVelocity();
        double d = user.getX() - vec3d.x - user.getX();
        double e = user.getEyeY() - 1.1F - user.getY();
        double f = user.getZ() - vec3d.z - user.getZ();
        double g = Math.sqrt(d * d + f * f);
        Potion potion = Potions.HARMING;

          if (g >= 8.0 && !user.hasStatusEffect(StatusEffects.SLOWNESS)) {
            potion = Potions.SLOWNESS;
        } else if (user.getHealth() >= 8.0F && !user.hasStatusEffect(StatusEffects.POISON)) {
            potion = Potions.POISON;
        } else if (g <= 3.0 && !user.hasStatusEffect(StatusEffects.WEAKNESS) && user.getRandom().nextFloat() < 0.25F) {
            potion = Potions.WEAKNESS;
        }

        PotionEntity potionEntity = new PotionEntity(user.getWorld(), user);
        potionEntity.setItem(PotionUtil.setPotion(new ItemStack(Items.SPLASH_POTION), potion));
        potionEntity.setPitch(potionEntity.getPitch() - -20.0F);
        potionEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 2.5F + (float) 0 * 0.5F, 1.0F);

        world.spawnEntity(potionEntity);

        return TypedActionResult.success(user.getStackInHand(hand), world.isClient());
    }
}
