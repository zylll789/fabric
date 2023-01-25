package net.zylll.fabric_mod.command;

import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.UUID;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class FakePlayerCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("player").requires(c -> c.hasPermissionLevel(4))
                .then(literal("spawn").requires(c -> c.hasPermissionLevel(4))
                        .then(argument("id", StringArgumentType.word()).requires(c -> c.hasPermissionLevel(4))
                                .executes((context) -> spawnPlayer(context,StringArgumentType.getString(context, "id"), context.getSource().getPlayer())))));
    }

    public static int spawnPlayer(CommandContext<ServerCommandSource> context, String id, PlayerEntity playerEntity) {
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
        //world.spawnEntity(playerEntity1);
        try {
            execute(context.getSource(), id, new Vec3d(pos.getX(),pos.getY(),pos.getZ()), new NbtCompound(), true);
        } catch (CommandSyntaxException e) {
            e.printStackTrace();
        }
        playerEntity1.sendMessage(new LiteralText(uuid.toString()), false);
        playerEntity1.sendMessage(new LiteralText("I am spawned"), false);
        return 0;
    }

    private static final SimpleCommandExceptionType FAILED_EXCEPTION = new SimpleCommandExceptionType(new TranslatableText("commands.summon.failed"));
    private static final SimpleCommandExceptionType FAILED_UUID_EXCEPTION = new SimpleCommandExceptionType(new TranslatableText("commands.summon.failed.uuid"));
    private static final SimpleCommandExceptionType INVALID_POSITION_EXCEPTION = new SimpleCommandExceptionType(new TranslatableText("commands.summon.invalidPosition"));


    private static void execute(ServerCommandSource source, String id, Vec3d pos, NbtCompound nbt, boolean initialize) throws CommandSyntaxException {
        BlockPos blockPos = new BlockPos(pos);
        if (!World.isValid(blockPos)) {
            throw INVALID_POSITION_EXCEPTION.create();
        } else {
            NbtCompound nbtCompound = nbt.copy();
            nbtCompound.putString("id", id);
            ServerWorld serverWorld = source.getWorld();
            Entity entity2 = EntityType.createInstanceFromId(111, serverWorld);
            if (entity2 == null) {
                throw FAILED_EXCEPTION.create();
            } else {
                if (initialize && entity2 instanceof MobEntity) {
                    ((MobEntity) entity2).initialize(source.getWorld(), source.getWorld().getLocalDifficulty(entity2.getBlockPos()), SpawnReason.COMMAND, (EntityData) null, (NbtCompound) null);
                }

                if (!serverWorld.spawnNewEntityAndPassengers(entity2)) {
                    throw FAILED_UUID_EXCEPTION.create();
                } else {
                    source.sendFeedback(new TranslatableText("commands.summon.success", entity2.getDisplayName()), true);
                }
            }
        }
    }
}
