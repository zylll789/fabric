package net.zylll.fabric_mod.registry;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.zylll.fabric_mod.FabricMod;

import static net.zylll.fabric_mod.FabricMod.makeID;

public class Sounds {
    public static SoundEvent POOP_SOUND = register(makeID("poop_sound"));
    public static SoundEvent POOP_EAT_SOUND = register(makeID("poop_eat_sound"));

    public static void register(){
        FabricMod.log("Register Sounds for + " + FabricMod.MOD_ID);
    }

    private static SoundEvent register(Identifier id){
        return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
    }
}
