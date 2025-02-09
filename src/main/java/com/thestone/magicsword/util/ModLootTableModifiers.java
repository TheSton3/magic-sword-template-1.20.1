package com.thestone.magicsword.util;

import com.thestone.magicsword.main.ModItems;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.condition.RandomChanceWithLootingLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

public class ModLootTableModifiers {

    public static Identifier ZOMBIE_ID = new Identifier("minecraft", "entities/zombie");

    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (ZOMBIE_ID.equals(id)) {
                LootPool.Builder pool = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.3f))
                        .with(ItemEntry.builder(ModItems.BLOOD_PIECE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(pool.build());
            }


        });

    }
}
