package net.zylll.fabric_mod.registry;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.Item;

public class Fuels {
    public static void register(){
        register(Items.POOP, 20);
    }

    private static void register(Item item, int time){
        FuelRegistry.INSTANCE.add(item, time);
    }
}
