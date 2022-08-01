package net.zylll.fabric_mod.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;
import net.zylll.fabric_mod.block.AllBlocks;
import org.lwjgl.system.CallbackI;

import static net.zylll.fabric_mod.FabricMod.makeID;

public class Blocks {

    public static void register(){
        //block
        register("poop_block", AllBlocks.POOP_BLOCK);
        register("poop_slab", AllBlocks.POOP_SLAB);
        register("trick_block", AllBlocks.TRICK_BLOCK);
        register("ore_changed_block", AllBlocks.ORE_CHANGED_BLOCK);
        register("break_block", AllBlocks.BREAK_BLOCK);

    }

    private static void register(String id, Block block){
        Registry.register(Registry.BLOCK, makeID(id), block);
    }

}
