package com.thestone.magicsword.item;

import com.thestone.magicsword.main.ModParticles;
import com.thestone.magicsword.util.Helper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

public class ExcaliburSword extends SwordItem {
    public ExcaliburSword(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            boolean bl = world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING);
            ItemStack stack = user.getStackInHand(hand);
            int coolDown = 60;
            float distance = 16f;
            Helper.spawnDirectionalParticles((ServerWorld) world, ParticleTypes.EXPLOSION, user, 10, distance);
            Entity entity = Helper.getTargetedEntity(user, 15);
            world.createExplosion(user, entity.getX(), entity.getY(), entity.getZ(), (float) 5, bl, World.ExplosionSourceType.MOB);
            user.getItemCooldownManager().set(this, coolDown);
        }
        return TypedActionResult.success(user.getStackInHand(hand));
    }
}
