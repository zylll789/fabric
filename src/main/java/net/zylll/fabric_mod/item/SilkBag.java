package net.zylll.fabric_mod.item;

import com.google.gson.JsonSyntaxException;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.zylll.fabric_mod.registry.Items;

public class SilkBag extends Item {
    public SilkBag(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (itemStack.getItem() == Items.SILK_BAG) {
            if (itemStack.hasNbt()) {
                assert itemStack.getNbt() != null;
                NbtList list = (NbtList) itemStack.getNbt().get("elements");
                assert list != null;
                if (!list.isEmpty()) {
                    for (NbtElement nbt : list) {
                        NbtCompound nbtCompound = (NbtCompound) nbt;
                        String id = nbtCompound.getString("id");
                        int count = nbtCompound.getInt("count");
                        Item item = Registry.ITEM.getOrEmpty(new Identifier(id)).orElseThrow(() -> new JsonSyntaxException("No such item" + id));
                        user.giveItemStack(new ItemStack(item, count));
                    }
                    itemStack.decrement(1);
                }
            }
        }
        return super.use(world, user, hand);
    }
}