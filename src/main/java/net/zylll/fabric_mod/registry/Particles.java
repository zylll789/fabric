package net.zylll.fabric_mod.registry;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleType;
import net.minecraft.util.registry.Registry;
import net.zylll.fabric_mod.FabricMod;

import static net.zylll.fabric_mod.FabricMod.makeID;

public class Particles {

    public static void register() {
        FabricMod.log("Register Particles for + " + FabricMod.MOD_ID);
    }

    public static final DefaultParticleType RED_STAR = register("red_star", FabricParticleTypes.simple());

    private static DefaultParticleType register(String id, ParticleType<?> type) {
        return (DefaultParticleType) Registry.register(Registry.PARTICLE_TYPE, makeID(id), type);
    }

}
