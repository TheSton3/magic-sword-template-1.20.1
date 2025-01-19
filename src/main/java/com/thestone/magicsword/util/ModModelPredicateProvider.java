package com.thestone.magicsword.util;

import com.thestone.magicsword.item.IceBow;
import com.thestone.magicsword.item.LostFireSword;
import com.thestone.magicsword.main.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

import static net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry.*;

public class ModModelPredicateProvider {
    public static void registerModModels() {
        registerLostFireSwordTag();
        registerCustomBow(ModItems.ICE_BOW);
        registerCustomBow(ModItems.MULTI_BOW);

    }

    private static void registerLostFireSwordTag() {
        register(ModItems.LOST_FIRE,
                new Identifier("flamed"),
                (stack, world, entity, seed) -> LostFireSword.isFlamed(stack) ? 1.0F : 0.0F);


    }

    private static void registerCustomBow(Item item) {
        register(item, new Identifier("pull"), (stack, world, entity, seed) -> {
            if (entity == null) {
                return 0.0F;
            } else {
                return entity.getActiveItem() != stack ? 0.0F : (float) (stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / 20.0F;
            }
        });
        register(
                item,
                new Identifier("pulling"),
                (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F
        );
        register(
                item,
                new Identifier("charge"),
                (stack, world, entity, seed) -> entity != null  && IceBow.isCharged(stack) && entity.isUsingItem() && entity.getActiveItem()  == stack ? 1.0F : 0.0F
        );

    }

}
