package com.thestone.magicsword;

import com.thestone.magicsword.client.entity.ModModelLayers;
import com.thestone.magicsword.client.entity.model.FrozenAxeEntityModel;
import com.thestone.magicsword.client.entity.model.HookSwordEntityModel;
import com.thestone.magicsword.client.entity.model.LakeOwnerEntityModel;
import com.thestone.magicsword.client.entity.render.FrozenAxeEntityRenderer;
import com.thestone.magicsword.client.entity.render.HookSwordEntityRenderer;
import com.thestone.magicsword.client.entity.render.LakeOwnerEntityRenderer;
import com.thestone.magicsword.main.ModEntities;
import com.thestone.magicsword.main.ModParticles;
import com.thestone.magicsword.particle.BloodyPieceParticle;
import com.thestone.magicsword.particle.BloodySweepParticle;
import com.thestone.magicsword.particle.FireBoomParticle;
import com.thestone.magicsword.util.ModModelPredicateProvider;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class MagicSwordClient implements ClientModInitializer {


    @Override
    public void onInitializeClient() {
        ParticleFactoryRegistry.getInstance().register(ModParticles.FIRE_BOOM_PARTICLE, FireBoomParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.BLOODY_PIECE, BloodyPieceParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.BLOODY_SWEEP, BloodySweepParticle.Factory::new);

        EntityRendererRegistry.register(ModEntities.FROZEN_AXE_ENTITY, FrozenAxeEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.HOOK_SWORD_ENTITY, HookSwordEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.LAKE_OWNER_ENTITY, LakeOwnerEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.FROZEN_AXE_ENTITY, FrozenAxeEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.LAKE_OWNER_ENTITY, LakeOwnerEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.HOOK_SWORD_ENTITY, HookSwordEntityModel::getTexturedModelData);

        ModModelPredicateProvider.registerModModels();
    }
}
