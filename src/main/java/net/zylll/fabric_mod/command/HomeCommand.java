package net.zylll.fabric_mod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import net.minecraft.util.math.BlockPos;
import net.zylll.fabric_mod.util.IEntityDataSaver;

public class HomeCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("home")
                .then(CommandManager.literal("set").executes(HomeCommand::executeHomeSet))
                .then(CommandManager.literal("return").executes(HomeCommand::executeHomeReturn))
                .then(CommandManager.literal("delete").executes(HomeCommand::executeHomeDelete)));
    }

    private static int executeHomeSet(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        IEntityDataSaver player = (IEntityDataSaver) context.getSource().getPlayer();
        BlockPos playerPos = context.getSource().getPlayer().getBlockPos();
        String pos = "(" + playerPos.getX() + "." + playerPos.getY() + "." + playerPos.getZ() + ")";
        player.getPersistentData().putIntArray("homePos",
                new int[]{playerPos.getX(), playerPos.getY(), playerPos.getZ()});
        context.getSource().sendFeedback(new LiteralText("Set home at" + pos), true);
        return 1;
    }

    private static int executeHomeReturn(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        IEntityDataSaver player = (IEntityDataSaver) context.getSource().getPlayer();
        int[] homePos = player.getPersistentData().getIntArray("homePos");
        if (homePos.length != 0) {
            int[] playerPos = player.getPersistentData().getIntArray("homePos");
            context.getSource().getPlayer().requestTeleport(playerPos[0], playerPos[1], playerPos[2]);
            context.getSource().sendFeedback(new LiteralText("Player return home!"), true);
            return 1;
        }else {
            context.getSource().sendFeedback(new LiteralText("No home has been set"), true);
            return -1;
        }
    }

    private static int executeHomeDelete(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        IEntityDataSaver player = (IEntityDataSaver) context.getSource().getPlayer();
        player.getPersistentData().putIntArray("homePos",
                new int[]{});
        context.getSource().sendFeedback(new LiteralText("Your Home is deleted!"), true);
        return 1;
    }

}
