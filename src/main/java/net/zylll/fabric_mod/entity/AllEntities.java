package net.zylll.fabric_mod.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.registry.Registry;

import static net.zylll.fabric_mod.FabricMod.makeID;

public class AllEntities {
    public static final EntityType<PoopCube> POOP_CUBE = Registry.register(Registry.ENTITY_TYPE, makeID("poop_cube"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, PoopCube::new).dimensions(EntityDimensions.fixed(0.75F, 0.75F)).build());
}
