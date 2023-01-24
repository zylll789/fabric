package net.zylll.fabric_mod.registry;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.Item;
import net.zylll.fabric_mod.FabricMod;

public class Fuels {
    public static void register(){
        FabricMod.log("Register Fuels for + " + FabricMod.MOD_ID);
        register(Items.POOP, 20);
    }

    private static void register(Item item, int time){
        FuelRegistry.INSTANCE.add(item, time);
    }
}
