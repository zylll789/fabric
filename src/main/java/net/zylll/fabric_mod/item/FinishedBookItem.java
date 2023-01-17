package net.zylll.fabric_mod.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.ArrayList;

public class FinishedBookItem extends Item {
    public FinishedBookItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ArrayList<String> list = new ArrayList<>();
        list.add(bookChar("My Fabric Mod"));
        list.add(bookChar("Topic Poop or Not"));
        list.add(bookChar("very good"));
        user.giveItemStack(returnBook(user.getEntityName(), "My Book", list, true, "My Book"));
        ItemStack itemStack = user.getStackInHand(hand);
        if (itemStack.getItem() == net.zylll.fabric_mod.registry.Items.FINISHED_BOOK_ITEM) {
            itemStack.decrement(1);
        }
        return super.use(world, user, hand);
    }

    private static ItemStack returnBook(String author, String filtered_title, ArrayList<String> pages, boolean resolved, String title) {
        ItemStack stack = new ItemStack(Items.WRITTEN_BOOK);
        NbtCompound nbtCompound = new NbtCompound();
        nbtCompound.putString("author", author);
        nbtCompound.putString("filtered_title", filtered_title);
        NbtList list = new NbtList();
        for (String s : pages) {
            NbtString string = NbtString.of(s);
            list.add(string);
        }
        nbtCompound.put("pages", list);
        nbtCompound.putBoolean("resolved", resolved);
        nbtCompound.putString("title", title);
        stack.setNbt(nbtCompound);
        return stack;
    }

    private static String bookChar(String s) {
        return Text.Serializer.toJson(new LiteralText(s));
    }
}
