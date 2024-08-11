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
        itemModelGenerator.register(ModItems.BLOODY_SCYTLE, BIG_HANDHELD);


    }
    public static final Model BIG_HANDHELD = item("big_handheld", TextureKey.LAYER0);
    private static Model item(String parent, TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(new Identifier("magic", "item/" + parent)), Optional.empty(), requiredTextureKeys);
    }

}

