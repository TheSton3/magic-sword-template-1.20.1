package com.thestone.magicsword.world.gen;

import com.thestone.magicsword.entity.BloodMageEntity;
import com.thestone.magicsword.main.ModEntities;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;

public class ModEntitySpawns {
    public static void addSpawn() {
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.DARK_FOREST),
                SpawnGroup.MONSTER, ModEntities.BLOOD_MAGE, 30, 1, 2);

        SpawnRestriction.register(ModEntities.BLOOD_MAGE, SpawnRestriction.Location.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnIgnoreLightLevel);
    }
}
