package com.thestone.magicsword.data;

import com.thestone.magicsword.main.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.FIRE_FAN, Models.HANDHELD);
        itemModelGenerator.register(ModItems.LEAP_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.MIDAS_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.ACID_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.ZOMBIE_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.VAMPIRE_RAPIER, Models.HANDHELD);
        itemModelGenerator.register(ModItems.HOOK_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.BLOODY_SCYTLE, LONG_HANDHELD);
        itemModelGenerator.register(ModItems.FROZEN_AXE, BIG_HANDHELD);
        itemModelGenerator.register(ModItems.ARONDITE, LONG_HANDHELD);
        itemModelGenerator.register(ModItems.REAPER_SCYTLE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.PRISMARINE_DAGGER, Models.HANDHELD);
        itemModelGenerator.register(ModItems.ENDSTONE_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.BLOODY_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.VOID_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.PORTAL_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.LIGHTING, Models.HANDHELD);
        itemModelGenerator.register(ModItems.FLAME_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.WIND_STAFF, Models.HANDHELD);
        itemModelGenerator.register(ModItems.ELEMENTAL_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.WATER_DUAL_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.EXCALIBUR_SWORD, Models.HANDHELD);





    }
    public static final Model BIG_HANDHELD = item_big("big_handheld", TextureKey.LAYER0);
    private static Model item_big(String parent, TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(new Identifier("magic", "item/" + parent)), Optional.empty(), requiredTextureKeys);
    }
    public static final Model LONG_HANDHELD = item_big("long_handheld", TextureKey.LAYER0);
    private static Model item_long(String parent, TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(new Identifier("magic", "item/" + parent)), Optional.empty(), requiredTextureKeys);
    }

}

