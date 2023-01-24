package net.zylll.fabric_mod.registry;

import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.zylll.fabric_mod.world.biome.PoopLand;

import static net.zylll.fabric_mod.FabricMod.makeID;

public class Biomes {

    public static final RegistryKey<Biome> POOP_LAND = register("poop_land");

    public static void register() {

    }

    private static void register(RegistryKey<Biome> key, Biome biome) {
        BuiltinRegistries.add(BuiltinRegistries.BIOME, key, biome);
    }

    private static RegistryKey<Biome> register(String name) {
        return RegistryKey.of(Registry.BIOME_KEY, makeID(name));
    }

    static {
        register(POOP_LAND, PoopLand.createPoopLand());
    }

}
