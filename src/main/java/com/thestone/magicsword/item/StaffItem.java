package com.thestone.magicsword.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class StaffItem extends Item {
    public StaffItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        NbtCompound nbtCompound = stack.getNbt();
        if(nbtCompound.contains("Charges",10)){

        }
        return TypedActionResult.pass(stack);
    }

    public static void setCharges(int charges) {
        NbtCompound tag = new NbtCompound();
        tag.putInt("Charges", charges);
    }

    public static int getCharges( NbtCompound tag) {
        return tag.getInt("Charges");
    }


}
