//from command structures

package net.zylll.fabric_mod.util;

import net.minecraft.network.packet.s2c.play.ChunkDataS2CPacket;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.WorldChunk;
import net.zylll.fabric_mod.mixin.ChunkMapAccessor;

public final class Utilities {

    private Utilities() {
    }

    public static void refreshChunksOnClients(ServerWorld serverWorld) {
        int viewDistance = ((ChunkMapAccessor) serverWorld.getChunkManager().threadedAnvilChunkStorage).getWatchDistance();
        serverWorld.getPlayers().forEach(serverPlayerEntity -> {
            for (int x = -viewDistance; x <= viewDistance; x++) {
                for (int z = -viewDistance; z <= viewDistance; z++) {
                    if (x + z < viewDistance) {
                        Chunk chunk = serverWorld.getChunk(new ChunkPos
                                (serverPlayerEntity.getChunkPos().x + x, serverPlayerEntity.getChunkPos().z + z).getStartPos());
                        if (chunk instanceof WorldChunk worldChunk) {
                            ChunkDataS2CPacket chunkDataS2CPacket = new ChunkDataS2CPacket(worldChunk, serverWorld.getLightingProvider(), null, null, true);
                            serverPlayerEntity.sendUnloadChunkPacket(worldChunk.getPos());
                            serverPlayerEntity.sendInitialChunkPackets(worldChunk.getPos(), chunkDataS2CPacket);
                        }
                    }
                }
            }
        });
    }

}
