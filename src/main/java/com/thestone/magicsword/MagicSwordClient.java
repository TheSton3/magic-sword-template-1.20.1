package com.thestone.magicsword;

import com.thestone.magicsword.client.entity.ModModelLayers;
import com.thestone.magicsword.client.entity.model.*;
import com.thestone.magicsword.client.entity.render.*;
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
        ParticleFactoryRegistry.getInstance().register(ModParticles.BLOODY_PARTICLE, BloodyPieceParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.BLOODY_SWEEP, BloodySweepParticle.Factory::new);

        EntityRendererRegistry.register(ModEntities.FROZEN_AXE_ENTITY, FrozenAxeEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.HOOK_SWORD_ENTITY, HookSwordEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.LAKE_OWNER_ENTITY, LakeOwnerEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.ACID_SPRAY_ENTITY, AcidSprayEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.ICE_ARROW_ENTITY, IceArrowRenderer::new);
        EntityRendererRegistry.register(ModEntities.ALYE_ENTITY, AlyeEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.WIND_ENTITY, WindEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.LIGHTING_ENTITY, LightingEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.BLOOD_MAGE, BloodMageRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.FROZEN_AXE_ENTITY, FrozenAxeEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.LAKE_OWNER_ENTITY, LakeOwnerEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.HOOK_SWORD_ENTITY, HookSwordEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.ACID_SPRAY_ENTITY, AcidSprayEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.ALYE_ENTITY, AlyeEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.WIND_ENTITY, WindEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.LIGHTING_ENTITY, LightingEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.BLOOD_MAGE_ENTITY, BloodMageModel::getTexturedModelData);


        ModModelPredicateProvider.registerModModels();

    }
}
