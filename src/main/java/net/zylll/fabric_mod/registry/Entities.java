package net.zylll.fabric_mod.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.util.registry.Registry;
import net.zylll.fabric_mod.block.AllBlocks;
import net.zylll.fabric_mod.block.entity.AllBlockEntities;
import net.zylll.fabric_mod.block.entity.BreakEntity;
import net.zylll.fabric_mod.block.entity.PoopContainerEntity;

import static net.zylll.fabric_mod.FabricMod.makeID;

public class Entities {

    public static void register(){
        //block entity
        AllBlockEntities.BREAK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, makeID("break_entity"),
                FabricBlockEntityTypeBuilder.create(BreakEntity::new, AllBlocks.BREAK_BLOCK).build(null));
        AllBlockEntities.POOP_CONTAINER_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, makeID("poop_container_entity"),
                FabricBlockEntityTypeBuilder.create(PoopContainerEntity::new, AllBlocks.POOP_CONTAINER).build(null));
    }
}
