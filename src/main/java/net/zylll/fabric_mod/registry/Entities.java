package net.zylll.fabric_mod.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.util.registry.Registry;
import net.zylll.fabric_mod.block.entity.*;
import net.zylll.fabric_mod.entity.AllEntities;
import net.zylll.fabric_mod.entity.PoopCube;

import static net.zylll.fabric_mod.FabricMod.makeID;

public class Entities {

    public static void register(){
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
        FabricDefaultAttributeRegistry.register(AllEntities.POOP_CUBE, PoopCube.createMobAttributes());
    }
}
