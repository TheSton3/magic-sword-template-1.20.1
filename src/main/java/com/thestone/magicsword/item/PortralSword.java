package com.thestone.magicsword.item;

import com.thestone.magicsword.MagicSword;

import com.thestone.magicsword.util.Helper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Comparator;

public class PortralSword extends SwordItem {
    public PortralSword(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!user.getWorld().isClient()) {
            if (!user.isSneaking()) {
                Vec3d vec3d = Helper.getPlayerLookingSpot(user, 50);
                if (vec3d != null) {
                    user.teleport(vec3d.x, vec3d.y, vec3d.z);
                    user.getItemCooldownManager().set(this, 160);
                    return TypedActionResult.success(user.getStackInHand(hand));

                }
            }
            if (user.isSneaking()) {
                Box box = Helper.createBox(user, 5);
                Entity closestEntity = world.getOtherEntities(user, box, EntityPredicates.VALID_LIVING_ENTITY).stream()
                        .min(Comparator.comparingDouble(entity -> entity.squaredDistanceTo(user)))
                        .orElse(null);
                if (closestEntity != null) {
                    user.teleport(closestEntity.getX(), closestEntity.getY(), closestEntity.getZ());
                    user.getItemCooldownManager().set(this, 60);
                    return TypedActionResult.success(user.getStackInHand(hand));
                }
            }
        }


        return TypedActionResult.consume(user.getStackInHand(hand));
    }
}
