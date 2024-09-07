package com.thestone.magicsword.main;

import com.thestone.magicsword.MagicSword;
import com.thestone.magicsword.entity.LakeOwnerEntity;
import com.thestone.magicsword.entity.projectile.FrozenAxeEntity;
import com.thestone.magicsword.entity.projectile.HookSwordEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.projectile.TridentEntity;
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

    public static final EntityType<HookSwordEntity> HOOK_SWORD_ENTITY = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(MagicSword.MOD_ID, "hook_sword_entity"),
            FabricEntityTypeBuilder.<HookSwordEntity>create(SpawnGroup.MISC, ((type, world) -> new HookSwordEntity(world)))
                    .dimensions(EntityDimensions.fixed(0.5f, 0.5f)).build());


}
