package net.zylll.fabric_mod.registry;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.util.registry.Registry;

import static net.zylll.fabric_mod.FabricMod.makeID;

public class Potions {

    public static final Potion EPINEPHRINE = new Potion(new StatusEffectInstance(Effects.EPINEPHRINE_EFFECT, 30 * 20));

    public static void register() {
        register("epinephrine", EPINEPHRINE);
    }

    private static void register(String id, Potion entry) {
        Registry.register(Registry.POTION, makeID(id), entry);
    }
}
