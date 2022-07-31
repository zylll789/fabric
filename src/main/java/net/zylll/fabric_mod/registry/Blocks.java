package net.zylll.fabric_mod.registry;

import net.minecraft.block.Block;
import net.minecraft.util.registry.Registry;
import net.zylll.fabric_mod.block.AllBlocks;

import static net.zylll.fabric_mod.FabricMod.makeID;

public class Blocks {

    public static void register(){
        register("poop_block", AllBlocks.POOP_BLOCK);
        register("poop_slab", AllBlocks.POOP_SLAB);
        register("trick_block", AllBlocks.TRICK_BLOCK);
    }

    private static void register(String id, Block block){
        Registry.register(Registry.BLOCK, makeID(id), block);
    }
}
