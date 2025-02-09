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
                    2,
                    -2.4F,
                    new FabricItemSettings().rarity(Rarity.EPIC).fireproof()));

    public static final Item FIRE_FAN = registerItem("fire_fan",
            new FireFan(ToolMaterials.DIAMOND,
                    0,
                    -2.0F,
                    new FabricItemSettings().rarity(Rarity.EPIC)));
    public static final Item BLOODY_SCYTLE = registerItem("bloody_scytle",
            new BloodyScytle(ToolMaterials.DIAMOND,
                    2,
                    -3.2F,
                    new FabricItemSettings().rarity(Rarity.EPIC)));
    public static final Item FROZEN_AXE = registerItem("frozen_axe",
            new FrozenAxe(ToolMaterials.DIAMOND,
                    2,
                    -3.0F,
                    new FabricItemSettings().rarity(Rarity.EPIC)));
    public static final Item ARONDITE = registerItem("arondite",
            new SwordItem(ToolMaterials.DIAMOND,
                    1,
                    -2.4F,
                    new FabricItemSettings().rarity(Rarity.EPIC)));
    public static final Item LEAP_SWORD = registerItem("leap_sword",
            new LeapSword(ToolMaterials.DIAMOND,
                    1,
                    -2.4F,
                    new FabricItemSettings().rarity(Rarity.EPIC)));
    public static final Item MIDAS_SWORD = registerItem("midas_sword",
            new MidasSword(ToolMaterials.DIAMOND,
                    1,
                    -2.4F,
                    new FabricItemSettings().rarity(Rarity.EPIC)));
    public static final Item ACID_SWORD = registerItem("acid_sword",
            new AcidSword(ToolMaterials.DIAMOND,
                    1,
                    -2.4F,
                    new FabricItemSettings().rarity(Rarity.EPIC)));
    public static final Item ZOMBIE_SWORD = registerItem("zombie_sword",
            new ZombieSword(ToolMaterials.DIAMOND,
                    1,
                    -2.4F,
                    new FabricItemSettings().rarity(Rarity.EPIC)));
    public static final Item VAMPIRE_RAPIER = registerItem("vampire_rapier",
            new VampireSword(ToolMaterials.DIAMOND,
                    1,
                    -2.4F,
                    new FabricItemSettings().rarity(Rarity.EPIC)));
    public static final Item HOOK_SWORD = registerItem("hook_sword",
            new HookSword(ToolMaterials.DIAMOND,
                    1,
                    -2.4F,
                    new FabricItemSettings().rarity(Rarity.EPIC)));
    public static final Item REAPER_SCYTLE = registerItem("reaper_scytle",
            new ReaperScytle(ToolMaterials.DIAMOND,
                    1,
                    -3.2F,
                    new FabricItemSettings().rarity(Rarity.EPIC)));
    public static final Item PRISMARINE_DAGGER = registerItem("prismarine_dagger",
            new SwordItem(ToolMaterials.DIAMOND,
                    0,
                    -1.4f,
                    new FabricItemSettings().rarity(Rarity.EPIC)));
    public static final Item ENDSTONE_SWORD = registerItem("endstone_sword",
            new SwordItem(ToolMaterials.DIAMOND,
                    0,
                    -2.4F,
                    new FabricItemSettings().rarity(Rarity.EPIC)));
    public static final Item BLOODY_AXE = registerItem("bloody_axe",
            new BloodyAxe(ToolMaterials.DIAMOND,
                    1,
                    -3.0F,
                    new FabricItemSettings().rarity(Rarity.EPIC)));
    public static final Item VOID_AXE = registerItem("void_axe",
            new AxeItem(ToolMaterials.DIAMOND,
                    1,
                    -3.0F,
                    new FabricItemSettings().rarity(Rarity.EPIC)));
    public static final Item PORTAL_SWORD = registerItem("portal_sword",
            new PortralSword(ToolMaterials.DIAMOND,
                    1,
                    -2.4F,
                    new FabricItemSettings().rarity(Rarity.EPIC)));
    public static final Item LIGHTING = registerItem("lighting",
            new Lighting(ToolMaterials.DIAMOND,
                    0,
                    -2.0f,
                    new FabricItemSettings().rarity(Rarity.EPIC)));
    public static final Item FLAME_SWORD = registerItem("flame_sword",
            new FlamedSword(ToolMaterials.DIAMOND,
                    0,
                    -2.4F,
                    new FabricItemSettings().rarity(Rarity.EPIC)));
    public static final Item ELEMENTAL_SWORD = registerItem("elemental_sword",
            new ElementalSword(ToolMaterials.DIAMOND,
                    3,
                    -2.4F,
                    new FabricItemSettings().rarity(Rarity.EPIC)));
    public static final Item WATER_DUAL_AXE = registerItem("water_dual_axe",
            new WaterDualAxe(ToolMaterials.DIAMOND,
                    1,
                    -3.0F,
                    new FabricItemSettings().rarity(Rarity.EPIC)));
    public static final Item WIND_STAFF = registerItem("wind_staff",
            new WindStaff(new FabricItemSettings()
                    .maxCount(1)
                    .rarity(Rarity.EPIC)));
    public static final Item ICE_BOW = registerItem("ice_bow",
            new IceBow(new FabricItemSettings()
                    .maxCount(1)
                    .rarity(Rarity.EPIC)));
    public static final Item MULTI_BOW = registerItem("multi_bow",
            new MultiBow(new FabricItemSettings()
                    .maxCount(1)
                    .rarity(Rarity.EPIC)));
    public static final Item EXCALIBUR_SWORD = registerItem("excalibur_sword",
            new ExcaliburSword(ToolMaterials.DIAMOND,
                    3,
                    -2.4F,
                    new FabricItemSettings().rarity(Rarity.EPIC)));
    public static final Item MIDNIGHT_DAGGER = registerItem("midnight_dagger",
            new MidnightDagger(ToolMaterials.DIAMOND,
                    0,
                    -1.4f,
                    new FabricItemSettings().rarity(Rarity.EPIC)));
    public static final Item FIRE_LANTERN = registerItem("fire_lantern",
            new FireLantern(new FabricItemSettings().rarity(Rarity.EPIC).maxCount(1).maxDamage(300)));
    public static final Item BLOOD_PIECE = registerItem("blood_piece",
            new Item(new FabricItemSettings()));
    public static final Item IRON_HANDLE = registerItem("iron_handle",
            new Item(new FabricItemSettings()));
    public static final Item BLOOD_CORE = registerItem("blood_core",
            new Item(new FabricItemSettings()));
    public static final Item BLOOD_SCYTLE_EDGE = registerItem("blood_scytle_edge",
            new Item(new FabricItemSettings()));


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
        entries.add(BLOODY_AXE);
        entries.add(VOID_AXE);
        entries.add(PORTAL_SWORD);
        entries.add(LIGHTING);
        entries.add(FLAME_SWORD);
        entries.add(WIND_STAFF);
        entries.add(ELEMENTAL_SWORD);
        entries.add(WATER_DUAL_AXE);
        entries.add(ICE_BOW);
        entries.add(EXCALIBUR_SWORD);
        entries.add(MULTI_BOW);
        entries.add(MIDNIGHT_DAGGER);
        entries.add(FIRE_LANTERN);


    }

    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
        entries.add(BLOOD_PIECE);
        entries.add(IRON_HANDLE);
        entries.add(BLOOD_CORE);
        entries.add(BLOOD_SCYTLE_EDGE);

    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(MagicSword.MOD_ID, name), item);
    }


    public static void registerModItems() {
        MagicSword.LOGGER.info("Registering Mod Items for " + MagicSword.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(ModItems::addItemsToCombatItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);

    }


}
