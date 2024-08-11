package com.thestone.magicsword.item;

import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.nbt.NbtCompound;
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
        if(stack.getNbt().getBoolean("Flamed")) {
            target.setFireTicks(60);
        }
        return true;
    }


    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (!isFlamed(context.getStack()) && context.getWorld().getBlockState(context.getBlockPos()).getBlock() == Blocks.LAVA_CAULDRON) {
            setFlamed(context.getStack(), true);
            context.getWorld().setBlockState(context.getBlockPos(), Blocks.CAULDRON.getDefaultState());
            return ActionResult.SUCCESS;
        } else if (isFlamed(context.getStack()) && (context.getWorld().getBlockState(context.getBlockPos()).getBlock() == Blocks.WATER_CAULDRON ||
                context.getWorld().getBlockState(context.getBlockPos()).getBlock() == Blocks.POWDER_SNOW_CAULDRON)) {
            setUnCharged(context.getStack());
            context.getWorld().setBlockState(context.getBlockPos(), Blocks.CAULDRON.getDefaultState());
            return ActionResult.SUCCESS;
        }
        return super.useOnBlock(context);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient()) {
            if (entity instanceof PlayerEntity playerEntity) {
                if (isFlamed(stack)) {
                    playerEntity.setFireTicks(20);
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

    public static void setUnCharged(ItemStack stack) {
        NbtCompound nbtCompound = stack.getNbt();
        if (nbtCompound != null) {
            nbtCompound.remove("Flamed");
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        if(stack.getNbt().getBoolean("Flamed")){
            tooltip.add(Text.translatable("tooltip.magic.flamed").formatted(Formatting.GOLD));
        }
    }
}
