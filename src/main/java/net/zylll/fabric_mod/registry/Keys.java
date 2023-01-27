package net.zylll.fabric_mod.registry;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.zylll.fabric_mod.util.IEntityDataSaver;
import org.lwjgl.glfw.GLFW;

import static net.zylll.fabric_mod.FabricMod.MOD_ID;

public class Keys {

    private static KeyBinding key_isWeightless;

    public static void register(){
        key_isWeightless = KeyBindingHelper.registerKeyBinding(new KeyBinding("key." + MOD_ID + ".weightless", GLFW.GLFW_KEY_SPACE,"key.categories." + MOD_ID + ".all"));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (key_isWeightless.wasPressed()){
                if(client.player!=null) {
                    if (client.player.hasStatusEffect(Effects.WEIGHTLESS_EFFECT)) {
                        IEntityDataSaver entityDataSaver = (IEntityDataSaver) client.player;
                        entityDataSaver.getWeightless().putBoolean("isWeightless",!entityDataSaver.getWeightless().getBoolean("isWeightless"));
                    }
                }
            }
        });
    }
}
