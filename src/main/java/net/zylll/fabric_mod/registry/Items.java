package net.zylll.fabric_mod.registry;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import net.zylll.fabric_mod.armor.PoopArmorItem;
import net.zylll.fabric_mod.item.AllItems;
import net.zylll.fabric_mod.tool.AllTools;

import static net.zylll.fabric_mod.FabricMod.makeID;

public class Items {

    public static void register(){
        //item
        register("poop", AllItems.POOP);
        //block item
        register("poop_block", AllItems.POOP_BLOCK_ITEM);
        register("poop_slab", AllItems.POOP_SLAB_ITEM);
        register("trick_block", AllItems.TRICK_BLOCK_ITEM);
        register("ore_changed_block", AllItems.ORE_CHANGED_BLOCK_ITEM);
        register("break_block", AllItems.BREAK_BLOCK_ITEM);
        //tool
        register("poop_sword", AllTools.POOP_SWORD);
        register("poop_axe", AllTools.POOP_AXE);
        register("poop_pickaxe", AllTools.POOP_PICKAXE);
        register("poop_shovel", AllTools.POOP_SHOVEL);
        register("poop_hoe", AllTools.POOP_HOE);
        //armor
        register("poop_helmet", PoopArmorItem.POOP_HELMET);
        register("poop_chestplate", PoopArmorItem.POOP_CHESTPLATE);
        register("poop_leggings", PoopArmorItem.POOP_LEGGINGS);
        register("poop_boots", PoopArmorItem.POOP_BOOTS);
    }

    private static void register(String id, Item item){
        Registry.register(Registry.ITEM, makeID(id), item);
    }

    public static void register(String id,BlockItem blockItem){
        Registry.register(Registry.ITEM, makeID(id), blockItem);
    }

}
