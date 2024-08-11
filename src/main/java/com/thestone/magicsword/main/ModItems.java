package com.thestone.magicsword.main;

import com.thestone.magicsword.MagicSword;
import com.thestone.magicsword.item.BloodyScytle;
import com.thestone.magicsword.item.FireFan;
import com.thestone.magicsword.item.LostFireSword;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModItems {


    public static final Item LOST_FIRE = registerItem("lost_fire_sword",
            new LostFireSword(ToolMaterials.NETHERITE,
                    3,
                    5.0f,
                    new FabricItemSettings().rarity(Rarity.EPIC).fireproof()));

    public static final Item FIRE_FAN = registerItem("fire_fan",
            new FireFan(ToolMaterials.DIAMOND,
                    3,
                    5.0f,
                    new FabricItemSettings().rarity(Rarity.EPIC)));
    public static final Item BLOODY_SCYTLE = registerItem("bloody_scytle",
            new BloodyScytle(ToolMaterials.DIAMOND,
                    3,
                    5.0f,
                    new FabricItemSettings().rarity(Rarity.EPIC)));


    private static void addItemsToCombatItemGroup(FabricItemGroupEntries entries) {
        entries.add(FIRE_FAN);
        entries.add(BLOODY_SCYTLE);
        entries.add(LOST_FIRE);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(MagicSword.MOD_ID, name), item);
    }


    public static void registerModItems() {
        MagicSword.LOGGER.info("Registering Mod Items for " + MagicSword.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(ModItems::addItemsToCombatItemGroup);

    }


}
