package net.zylll.fabric_mod.command;

import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.UUID;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class FakePlayerCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("player").requires(c -> c.hasPermissionLevel(4))
                .then(literal("spawn").requires(c -> c.hasPermissionLevel(4))
                .then(argument("id", StringArgumentType.word()).requires(c -> c.hasPermissionLevel(4))
                        .executes((context)-> spawnPlayer(StringArgumentType.getString(context,"id"),context.getSource().getPlayer())))));
    }

    public static int spawnPlayer(String id, PlayerEntity playerEntity) {
        BlockPos pos = playerEntity.getBlockPos();
        World world = playerEntity.getWorld();
        float yaw = playerEntity.getYaw();
        UUID uuid = UUID.randomUUID();
        while (uuid.toString().equals(playerEntity.getUuid().toString())) {
            uuid = UUID.randomUUID();
        }
        GameProfile gameProfile = new GameProfile(uuid, id);
        PlayerEntity playerEntity1 = new PlayerEntity(world, pos, yaw, gameProfile) {
            @Override
            public boolean isSpectator() {
                return false;
            }

            @Override
            public boolean isCreative() {
                return false;
            }
        };
        playerEntity.sendMessage(new LiteralText(uuid.toString()), false);
        playerEntity.sendMessage(new LiteralText(playerEntity.getUuid().toString()), false);
        playerEntity.sendMessage(new LiteralText(id), false);
        //unfinished
        playerEntity1.sendMessage(new LiteralText(uuid.toString()), false);
        playerEntity1.sendMessage(new LiteralText("I am spawned"), false);
        return 0;
    }


}
