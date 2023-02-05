package net.zylll.fabric_mod.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.registry.Registry;
import net.zylll.fabric_mod.FabricMod;
import net.zylll.fabric_mod.block.entity.*;
import net.zylll.fabric_mod.entity.PoopCube;
import net.zylll.fabric_mod.entity.YSlimeEntity;

import static net.zylll.fabric_mod.FabricMod.makeID;

public class Entities {

    public static final EntityType<PoopCube> POOP_CUBE = Registry.register(Registry.ENTITY_TYPE, makeID("poop_cube"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, PoopCube::new).dimensions(EntityDimensions.fixed(0.75F, 0.75F)).build());
    public static final EntityType<YSlimeEntity> Y_SLIME = Registry.register(Registry.ENTITY_TYPE,makeID("y_slime"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER,YSlimeEntity::new).dimensions(EntityDimensions.fixed(1.6f,1f)).build());

    public static void register(){
        FabricMod.log("Register Entities for + " + FabricMod.MOD_ID);
        //block entity
        AllBlockEntities.BREAK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, makeID("break_entity"),
                FabricBlockEntityTypeBuilder.create(BreakEntity::new, Blocks.BREAK_BLOCK).build(null));
        AllBlockEntities.POOP_CONTAINER_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, makeID("poop_container_entity"),
                FabricBlockEntityTypeBuilder.create(PoopContainerEntity::new, Blocks.POOP_CONTAINER).build(null));
        AllBlockEntities.POOP_CHEST_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, makeID("poop_chest"),
                FabricBlockEntityTypeBuilder.create(PoopChestEntity::new, Blocks.POOP_CHEST).build(null));
        AllBlockEntities.UI_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, makeID("ui_block_entity"),
                FabricBlockEntityTypeBuilder.create(UIBlockEntity::new, Blocks.UI_BLOCK).build(null));
        //entity
        FabricDefaultAttributeRegistry.register(POOP_CUBE, PoopCube.createMobAttributes());
        FabricDefaultAttributeRegistry.register(Y_SLIME, YSlimeEntity.setAttributes());

    }
}
