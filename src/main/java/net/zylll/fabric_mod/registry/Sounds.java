package net.zylll.fabric_mod.registry;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import static net.zylll.fabric_mod.FabricMod.makeID;

public class Sounds {
    public static SoundEvent POOP_SOUND = register(makeID("poop_sound"));

    public Sounds(){
    }

    public static void register(){
    }

    private static SoundEvent register(Identifier id){
        return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
    }
}
