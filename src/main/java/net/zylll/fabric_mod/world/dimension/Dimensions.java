package net.zylll.fabric_mod.world.dimension;

import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.zylll.fabric_mod.FabricMod;

import static net.zylll.fabric_mod.FabricMod.makeID;

public class Dimensions {

    public static final RegistryKey<World> POOP_DIMENSION_KEY = RegistryKey.of(Registry.WORLD_KEY, makeID("poop_world"));
    public static final RegistryKey<DimensionType> POOP_DIMENSION_TYPE_KEY = RegistryKey.of(Registry.DIMENSION_TYPE_KEY, POOP_DIMENSION_KEY.getValue());

    public static void register() {
        FabricMod.log("Registering Dimensions for " + FabricMod.MOD_ID);
    }

}
