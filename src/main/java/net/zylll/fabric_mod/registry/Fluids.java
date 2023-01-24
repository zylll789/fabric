package net.zylll.fabric_mod.registry;

import net.minecraft.fluid.FlowableFluid;
import net.minecraft.util.registry.Registry;
import net.zylll.fabric_mod.FabricMod;
import net.zylll.fabric_mod.fluid.PoopFluid;

import static net.zylll.fabric_mod.FabricMod.makeID;

public class Fluids {

    public static FlowableFluid STILL_POOP_FLUID ;
    public static FlowableFluid FLOWING_POOP_FLUID;

    public static void register(){
        FabricMod.log("Register Fluids for + " + FabricMod.MOD_ID);
        STILL_POOP_FLUID = Registry.register(Registry.FLUID, makeID("poop_fluid"), new PoopFluid.Still());
        FLOWING_POOP_FLUID = Registry.register(Registry.FLUID, makeID("flowing_poop_fluid"), new PoopFluid.Flowing());
    }
}
