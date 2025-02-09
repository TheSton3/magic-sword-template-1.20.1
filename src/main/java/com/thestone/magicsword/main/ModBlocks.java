package com.thestone.magicsword.main;

import com.thestone.magicsword.MagicSword;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block BLOODY_BLOCK = registerBlock("bloody_block",
            new Block(FabricBlockSettings.copyOf(Blocks.STONE).strength(2f)));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(MagicSword.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(MagicSword.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        MagicSword.LOGGER.info("Registering ModBlocks for " + MagicSword.MOD_ID);
    }

    private static void addBlocksToCombatItemGroup(FabricItemGroupEntries entries) {
        entries.add(BLOODY_BLOCK);
    }


}




