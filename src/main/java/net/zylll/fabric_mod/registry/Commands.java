package net.zylll.fabric_mod.registry;

import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.zylll.fabric_mod.command.FakePlayerCommand;
import net.zylll.fabric_mod.command.NBTCommand;

public class Commands {

    public static void register(){
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> NBTCommand.register(dispatcher));
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> FakePlayerCommand.register(dispatcher));
    }

}
