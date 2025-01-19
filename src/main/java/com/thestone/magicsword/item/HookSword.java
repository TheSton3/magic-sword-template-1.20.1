package com.thestone.magicsword.item;

import com.thestone.magicsword.entity.projectile.HookSwordEntity;
import com.thestone.magicsword.main.ModEntities;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class HookSword extends SwordItem {
    public HookSword(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (!world.isClient) {
            double maxRange = 24D * 2D;
            double maxSpeed = 10D * 1.5D;

            HookSwordEntity hookshot = new HookSwordEntity(ModEntities.HOOK_SWORD_ENTITY, user, world);
            hookshot.setProperties(stack, maxRange, maxSpeed, user.getPitch(), user.headYaw, 0f, 1.5f * (float) (maxSpeed / 10));
            world.spawnEntity(hookshot);
            user.getItemCooldownManager().set(this, 40);
        }
        world.playSound(user, user.getBlockPos(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1F, 1F);

        return super.use(world, user, hand);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        return super.finishUsing(stack, world, user);
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 72000;
    }

}
