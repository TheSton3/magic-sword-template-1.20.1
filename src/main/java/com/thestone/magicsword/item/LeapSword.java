package com.thestone.magicsword.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class LeapSword extends SwordItem {
    public LeapSword(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        var width = user.getWidth() / 2;
        var height = user.getHeight() * 0.3F;
        if(user.getWorld() instanceof ServerWorld sw) {
            sw.spawnParticles(ParticleTypes.POOF, user.getX(), user.getY() + height, user.getZ(), 30, width, height, width, 0.08F);
        }
        Vec3d motion = user.getRotationVector();
        Vec3d dash = new Vec3d(motion.x * 2, 0.6f, motion.z * 2);
        user.setVelocity(dash);
        user.getItemCooldownManager().set(this, 35);
        return TypedActionResult.success(user.getStackInHand(hand));
    }
}
