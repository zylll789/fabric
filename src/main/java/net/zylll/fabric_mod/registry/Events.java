package net.zylll.fabric_mod.registry;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.zylll.fabric_mod.FabricMod;
import net.zylll.fabric_mod.event.PlayerEventCopyFrom;

public class Events {

    public static void register(){
        FabricMod.log("Register Events for + " + FabricMod.MOD_ID);
        ServerPlayerEvents.COPY_FROM.register(new PlayerEventCopyFrom());
    }

}
