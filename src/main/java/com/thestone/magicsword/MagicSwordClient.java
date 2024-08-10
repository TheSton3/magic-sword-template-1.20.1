package com.thestone.magicsword;

import com.thestone.magicsword.main.ModParticles;
import com.thestone.magicsword.particle.BloodyPieceParticle;
import com.thestone.magicsword.particle.FireBoomParticle;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;

public class MagicSwordClient implements ClientModInitializer {



    @Override
    public void onInitializeClient() {
        ParticleFactoryRegistry.getInstance().register(ModParticles.FIRE_BOOM_PARTICLE, FireBoomParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.BLOODY_PIECE, BloodyPieceParticle.Factory::new);
    }
}
