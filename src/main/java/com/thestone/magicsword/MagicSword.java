package com.thestone.magicsword;

import com.thestone.magicsword.main.ModItems;
import com.thestone.magicsword.main.ModParticles;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MagicSword implements ModInitializer {
    public static String MOD_ID = "magic";
    public static final Logger LOGGER = LoggerFactory.getLogger("magic");

    @Override
    public void onInitialize() {
        ModItems.registerModItems();
        ModParticles.registerParticles();


    }
}