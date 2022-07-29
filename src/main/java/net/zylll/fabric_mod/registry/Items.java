package net.zylll.fabric_mod.registry;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import net.zylll.fabric_mod.item.AllItems;
import net.zylll.fabric_mod.tool.AllTools;

import static net.zylll.fabric_mod.FabricMod.makeID;

public class Items {

    public Items(){}

    public static void register(){
        register("poop", AllItems.POOP);
        register("poop_block", AllItems.POOP_BLOCK_ITEM);
        register("poop_sword", AllTools.POOP_SWORD);
    }

    private static void register(String id, Item item){
        Registry.register(Registry.ITEM, makeID(id), item);
    }

    public static void register(String id,BlockItem blockItem){
        Registry.register(Registry.ITEM, makeID(id), blockItem);
    }

}
