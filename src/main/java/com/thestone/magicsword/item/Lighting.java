package com.thestone.magicsword.item;

import com.thestone.magicsword.entity.projectile.LightingEntity;
import com.thestone.magicsword.main.ModEntities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class Lighting extends SwordItem {
    public Lighting(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        LightingEntity lightingEntity = new LightingEntity(ModEntities.LIGHTING_ENTITY, user,world);
        lightingEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 2.5F + (float) 0 * 0.5F, 1.0F);
        world.spawnEntity(lightingEntity);
        user.getItemCooldownManager().set(this, 100);
        return TypedActionResult.success(itemStack, world.isClient());
    }

}
