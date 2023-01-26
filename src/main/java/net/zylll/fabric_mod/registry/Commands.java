package net.zylll.fabric_mod.registry;

import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.zylll.fabric_mod.FabricMod;
import net.zylll.fabric_mod.command.NBTCommand;
import net.zylll.fabric_mod.command.HomeCommand;

public class Commands {

    public static void register() {
        FabricMod.log("Registering Commands for + " + FabricMod.MOD_ID);
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> NBTCommand.register(dispatcher));
        //CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> FakePlayerCommand.register(dispatcher));
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> HomeCommand.register(dispatcher));
    }

}
