package com.thestone.magicsword.item;

import com.thestone.magicsword.util.Helper;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LostFireSword extends SwordItem {


    public LostFireSword(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (stack.getNbt().getBoolean("Flamed")) {
            target.setFireTicks(60);
            World world = target.getWorld();
            if (!world.isClient()) {
                Helper.spawnExlosionParticles(ParticleTypes.LAVA, target, (ServerWorld) world);
            }
        }
        return true;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient()) {
            if (entity instanceof PlayerEntity playerEntity) {
                if (!isFlamed(stack) && (playerEntity.getFireTicks() > 0 || playerEntity.isOnFire())) {
                    setFlamed(stack, true);
                } else if (isFlamed(stack) && (playerEntity.getFireTicks() <= 0) || !playerEntity.isOnFire()) {
                    setFlamed(stack, false);
                }

            }
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    public static boolean isFlamed(ItemStack stack) {
        NbtCompound nbtCompound = stack.getNbt();
        return nbtCompound != null && nbtCompound.getBoolean("Flamed");
    }

    public static void setFlamed(ItemStack stack, boolean heated) {
        NbtCompound nbtCompound = stack.getOrCreateNbt();
        nbtCompound.putBoolean("Flamed", heated);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        if (stack.getNbt().getBoolean("Flamed")) {
            tooltip.add(Text.translatable("tooltip.magic.flamed").formatted(Formatting.GOLD));
        }
    }
}
