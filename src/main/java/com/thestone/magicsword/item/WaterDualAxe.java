package com.thestone.magicsword.item;

import com.thestone.magicsword.main.ModEffects;
import com.thestone.magicsword.util.Helper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class WaterDualAxe extends SwordItem {
    public WaterDualAxe(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        World world = attacker.getWorld();
        if (!world.isClient()) {
            Helper.spawnExlosionParticles(ParticleTypes.RAIN, target, (ServerWorld) world);
        }
        return super.postHit(stack, target, attacker);

    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        user.addStatusEffect(new StatusEffectInstance(ModEffects.RAINING,200));

        return super.use(world, user, hand);
    }


}
