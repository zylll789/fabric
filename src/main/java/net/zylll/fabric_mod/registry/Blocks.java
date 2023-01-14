package net.zylll.fabric_mod.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.util.registry.Registry;
import net.zylll.fabric_mod.block.AllBlocks;

import static net.zylll.fabric_mod.FabricMod.makeID;

public class Blocks {

    public static void register(){
        //block
        register("poop_block", AllBlocks.POOP_BLOCK);
        register("poop_slab", AllBlocks.POOP_SLAB);
        register("trick_block", AllBlocks.TRICK_BLOCK);
        register("ore_changed_block", AllBlocks.ORE_CHANGED_BLOCK);
        register("break_block", AllBlocks.BREAK_BLOCK);
        register("poop_container", AllBlocks.POOP_CONTAINER);
        register("closestool", AllBlocks.CLOSESTOOL);
        register("poop_chest", AllBlocks.POOP_CHEST);
        register("ui_block", AllBlocks.UI_BLOCK);
        //fluid block
        AllBlocks.POOP_FLUID = register("poop_fluid", Fluids.STILL_POOP_FLUID);
    }

    private static void register(String id, Block block){
        Registry.register(Registry.BLOCK, makeID(id), block);
    }

    private static Block register(String id, FlowableFluid flowableFluid){
        return Registry.register(Registry.BLOCK, makeID(id), new FluidBlock(flowableFluid, FabricBlockSettings.copy(net.minecraft.block.Blocks.WATER)));
    }

}
