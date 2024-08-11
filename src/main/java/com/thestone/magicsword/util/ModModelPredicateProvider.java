package com.thestone.magicsword.util;

import com.thestone.magicsword.item.LostFireSword;
import com.thestone.magicsword.main.ModItems;
import net.minecraft.util.Identifier;

import static net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry.*;

public class ModModelPredicateProvider {
    public static void registerModModels() {
        registerLostFireSwordTag();

    }

    private static void registerLostFireSwordTag() {
        register(ModItems.LOST_FIRE,
                new Identifier("flamed"),
                (stack, world, entity, seed) -> LostFireSword.isFlamed(stack) ? 1.0F : 0.0F);


    }

}
