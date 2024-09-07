package com.thestone.magicsword.main;

import com.thestone.magicsword.MagicSword;
import com.thestone.magicsword.item.*;
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
    public static final Item FROZEN_AXE = registerItem("frozen_axe",
            new FrozenAxe(ToolMaterials.DIAMOND,
                    3,
                    5.0f,
                    new FabricItemSettings().rarity(Rarity.EPIC)));
    public static final Item ARONDITE = registerItem("arondite",
            new SwordItem(ToolMaterials.DIAMOND,
                    3,
                    5.0f,
                    new FabricItemSettings().rarity(Rarity.EPIC)));
    public static final Item LEAP_SWORD = registerItem("leap_sword",
            new LeapSword(ToolMaterials.DIAMOND,
                    3,
                    5.0f,
                    new FabricItemSettings().rarity(Rarity.EPIC)));
    public static final Item MIDAS_SWORD = registerItem("midas_sword",
            new MidasSword(ToolMaterials.DIAMOND,
                    3,
                    5.0f,
                    new FabricItemSettings().rarity(Rarity.EPIC)));
    public static final Item ACID_SWORD = registerItem("acid_sword",
            new AcidSword(ToolMaterials.DIAMOND,
                    3,
                    5.0f,
                    new FabricItemSettings().rarity(Rarity.EPIC)));
    public static final Item ZOMBIE_SWORD = registerItem("zombie_sword",
            new ZombieSword(ToolMaterials.DIAMOND,
                    3,
                    5.0f,
                    new FabricItemSettings().rarity(Rarity.EPIC)));
    public static final Item VAMPIRE_RAPIER = registerItem("vampire_rapier",
            new SwordItem(ToolMaterials.DIAMOND,
                    3,
                    5.0f,
                    new FabricItemSettings().rarity(Rarity.EPIC)));
    public static final Item HOOK_SWORD = registerItem("hook_sword",
            new HookSword(ToolMaterials.DIAMOND,
                    3,
                    5.0f,
                    new FabricItemSettings().rarity(Rarity.EPIC)));
    public static final Item REAPER_SCYTLE = registerItem("reaper_scytle",
            new ReaperScytle(ToolMaterials.DIAMOND,
                    0,
                    5.0f,
                    new FabricItemSettings().rarity(Rarity.EPIC)));
    public static final Item PRISMARINE_DAGGER = registerItem("prismarine_dagger",
            new SwordItem(ToolMaterials.DIAMOND,
                    3,
                    5.0f,
                    new FabricItemSettings().rarity(Rarity.EPIC)));
    public static final Item ENDSTONE_SWORD = registerItem("endstone_sword",
            new SwordItem(ToolMaterials.DIAMOND,
                    3,
                    5.0f,
                    new FabricItemSettings().rarity(Rarity.EPIC)));


    private static void addItemsToCombatItemGroup(FabricItemGroupEntries entries) {
        entries.add(FIRE_FAN);
        entries.add(BLOODY_SCYTLE);
        entries.add(LOST_FIRE);
        entries.add(FROZEN_AXE);
        entries.add(ARONDITE);
        entries.add(LEAP_SWORD);
        entries.add(MIDAS_SWORD);
        entries.add(ACID_SWORD);
        entries.add(ZOMBIE_SWORD);
        entries.add(VAMPIRE_RAPIER);
        entries.add(HOOK_SWORD);
        entries.add(REAPER_SCYTLE);
        entries.add(PRISMARINE_DAGGER);
        entries.add(ENDSTONE_SWORD);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(MagicSword.MOD_ID, name), item);
    }


    public static void registerModItems() {
        MagicSword.LOGGER.info("Registering Mod Items for " + MagicSword.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(ModItems::addItemsToCombatItemGroup);

    }


}
