package net.zylll.fabric_mod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import net.minecraft.util.math.BlockPos;
import net.zylll.fabric_mod.util.IEntityDataSaver;

public class SetHomeCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("home")
                .then(CommandManager.literal("set").executes(SetHomeCommand::execute)));
    }

    private static int execute(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        IEntityDataSaver player = (IEntityDataSaver) context.getSource().getPlayer();
        BlockPos playerPos = context.getSource().getPlayer().getBlockPos();
        String pos = "(" + playerPos.getX() + "." + playerPos.getY() + "." + playerPos.getZ() + ")";
        player.getPersistentData().putIntArray("homePos",
                new int[]{playerPos.getX(), playerPos.getY(), playerPos.getZ()});
        context.getSource().sendFeedback(new LiteralText("Set home at" + pos), true);
        return 1;
    }

}
