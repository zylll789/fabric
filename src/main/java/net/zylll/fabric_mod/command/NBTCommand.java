package net.zylll.fabric_mod.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;

import static net.minecraft.server.command.CommandManager.literal;
import static net.minecraft.server.command.CommandManager.argument;

public class NBTCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher){
        dispatcher.register(literal("nbt").requires(c -> c.hasPermissionLevel(4)).executes(c -> getNBT(c.getSource().getPlayer()))
                .then(argument("slot", IntegerArgumentType.integer()).requires(c -> c.hasPermissionLevel(4))
                .executes((c) -> getNBT(IntegerArgumentType.getInteger(c, "slot"), c.getSource().getPlayer()))));
    }

    private static int getNBT(int slot, PlayerEntity playerEntity){
        if(slot >= 0 && slot <= 40){
            ItemStack itemStack = playerEntity.getInventory().getStack(slot);
            if(itemStack != ItemStack.EMPTY){
                if (itemStack.hasNbt()) {
                    assert itemStack.getNbt() != null;
                    String s = itemStack.getNbt().toString();
                    playerEntity.sendMessage(new LiteralText(s), false);
                    return Command.SINGLE_SUCCESS;
                } else {
                    playerEntity.sendMessage(new LiteralText("This item has no NBT"), false);
                }
            }else {
                playerEntity.sendMessage(new LiteralText("There is no item in slot " + slot), false);
            }
        }else {
            playerEntity.sendMessage(new LiteralText("Have no slot with index = " + slot), false);
        }
        return 0;
    }

    private static int getNBT(PlayerEntity playerEntity){
        ItemStack itemStack = playerEntity.getMainHandStack();
        if(itemStack != ItemStack.EMPTY){
            if (itemStack.hasNbt()) {
                assert itemStack.getNbt() != null;
                String s = itemStack.getNbt().toString();
                playerEntity.sendMessage(new LiteralText(s), true);
                return Command.SINGLE_SUCCESS;
            } else {
                playerEntity.sendMessage(new LiteralText("This item has no NBT"), false);
            }
        }else {
            playerEntity.sendMessage(new LiteralText("There is no item in hand"), false);
        }
        return 0;
    }
}
