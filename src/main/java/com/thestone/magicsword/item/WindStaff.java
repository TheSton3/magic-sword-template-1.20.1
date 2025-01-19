package com.thestone.magicsword.item;

import com.thestone.magicsword.entity.projectile.WindEntity;
import com.thestone.magicsword.main.ModEntities;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class WindStaff extends StaffItem {
    public WindStaff(Settings settings) {
        super(settings);
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        WindEntity windEntity = new WindEntity(ModEntities.WIND_ENTITY, user, world);
        windEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 2.5F + (float) 0 * 0.5F, 1.0F);
        world.spawnEntity(windEntity);
        user.getItemCooldownManager().set(this, 80);
        return TypedActionResult.success(user.getStackInHand(hand));
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);

    }
}
