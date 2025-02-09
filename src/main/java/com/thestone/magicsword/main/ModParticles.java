package com.thestone.magicsword.main;

import com.thestone.magicsword.MagicSword;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModParticles {
    public static final DefaultParticleType FIRE_BOOM_PARTICLE = FabricParticleTypes.simple();
    public static final DefaultParticleType BLOODY_PARTICLE = FabricParticleTypes.simple();
    public static final DefaultParticleType BLOODY_SWEEP = FabricParticleTypes.simple();

    public static void registerParticles(){
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(MagicSword.MOD_ID,"fire_boom_particle"),
                FIRE_BOOM_PARTICLE);
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(MagicSword.MOD_ID,"bloody_particle"),
                BLOODY_PARTICLE);
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(MagicSword.MOD_ID,"bloody_sweep"),
                BLOODY_SWEEP);
    }
}
