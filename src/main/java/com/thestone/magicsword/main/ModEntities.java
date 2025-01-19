package com.thestone.magicsword.main;

import com.thestone.magicsword.MagicSword;
import com.thestone.magicsword.entity.AlyeEntity;
import com.thestone.magicsword.entity.LakeOwnerEntity;
import com.thestone.magicsword.entity.projectile.*;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {


    public static final EntityType<FrozenAxeEntity> FROZEN_AXE_ENTITY = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(MagicSword.MOD_ID, "frozen_axe_entity"),
            FabricEntityTypeBuilder.<FrozenAxeEntity>create(SpawnGroup.MISC, FrozenAxeEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5F, 0.5F)).trackedUpdateRate(20).build());


    public static final EntityType<LakeOwnerEntity> LAKE_OWNER_ENTITY = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(MagicSword.MOD_ID, "lake_owner_entity"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, LakeOwnerEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 1f)).build());

    public static final EntityType<AlyeEntity> ALYE_ENTITY = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(MagicSword.MOD_ID, "alye_entity"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, AlyeEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 2f)).build());

    public static final EntityType<WindEntity> WIND_ENTITY = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(MagicSword.MOD_ID, "wind_entity"),
            FabricEntityTypeBuilder.<WindEntity>create(SpawnGroup.MISC, WindEntity::new)
                    .dimensions(EntityDimensions.fixed(2f, 2f)).build());

    public static final EntityType<HookSwordEntity> HOOK_SWORD_ENTITY = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(MagicSword.MOD_ID, "hook_sword_entity"),
            FabricEntityTypeBuilder.<HookSwordEntity>create(SpawnGroup.MISC, ((type, world) -> new HookSwordEntity(world)))
                    .dimensions(EntityDimensions.fixed(0.5f, 0.5f)).build());

    public static final EntityType<AcidSprayEntity> ACID_SPRAY_ENTITY = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(MagicSword.MOD_ID, "acid_spray_entity"),
            FabricEntityTypeBuilder.<AcidSprayEntity>create(SpawnGroup.MISC, AcidSprayEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5F, 0.5F)).trackedUpdateRate(20).build());

    public static final EntityType<LightingEntity> LIGHTING_ENTITY = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(MagicSword.MOD_ID, "lighting_entity"),
            FabricEntityTypeBuilder.<LightingEntity>create(SpawnGroup.MISC, LightingEntity::new)
                    .dimensions(EntityDimensions.fixed(2f, 2f)).build());
    public static final EntityType<IceArrorEntity> ICE_ARROW_ENTITY = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(MagicSword.MOD_ID, "ice_arrow_entity"),
            FabricEntityTypeBuilder.<IceArrorEntity>create(SpawnGroup.MISC, IceArrorEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5F, 0.5F)).trackedUpdateRate(20).build());

}
