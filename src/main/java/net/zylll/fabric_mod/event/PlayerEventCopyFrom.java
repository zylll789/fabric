package net.zylll.fabric_mod.event;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import net.zylll.fabric_mod.util.IEntityDataSaver;

public class PlayerEventCopyFrom implements ServerPlayerEvents.CopyFrom {
    @Override
    public void copyFromPlayer(ServerPlayerEntity oldPlayer, ServerPlayerEntity newPlayer, boolean alive) {
        IEntityDataSaver old = (IEntityDataSaver) oldPlayer;
        IEntityDataSaver newP = (IEntityDataSaver) newPlayer;
        newP.getPersistentData().putIntArray("homePos", old.getPersistentData().getIntArray("homePos"));
    }
}
