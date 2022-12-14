package net.zylll.fabric_mod.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.util.registry.Registry;
import net.zylll.fabric_mod.block.AllBlocks;
import net.zylll.fabric_mod.block.entity.AllBlockEntities;
import net.zylll.fabric_mod.block.entity.BreakEntity;
import net.zylll.fabric_mod.block.entity.PoopChestEntity;
import net.zylll.fabric_mod.block.entity.PoopContainerEntity;
import net.zylll.fabric_mod.entity.AllEntities;
import net.zylll.fabric_mod.entity.PoopCube;

import static net.zylll.fabric_mod.FabricMod.makeID;

public class Entities {

    public static void register(){
        //block entity
        AllBlockEntities.BREAK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, makeID("break_entity"),
                FabricBlockEntityTypeBuilder.create(BreakEntity::new, AllBlocks.BREAK_BLOCK).build(null));
        AllBlockEntities.POOP_CONTAINER_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, makeID("poop_container_entity"),
                FabricBlockEntityTypeBuilder.create(PoopContainerEntity::new, AllBlocks.POOP_CONTAINER).build(null));
        AllBlockEntities.POOP_CHEST_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, makeID("poop_chest"),
                FabricBlockEntityTypeBuilder.create(PoopChestEntity::new, AllBlocks.POOP_CHEST).build(null));
        //entity
        FabricDefaultAttributeRegistry.register(AllEntities.POOP_CUBE, PoopCube.createMobAttributes());
    }
}
