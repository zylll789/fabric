package net.zylll.fabric_mod.registry;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;

import java.util.Random;

public class Trades {

    public static void register() {
        sellSilkBag();
    }

    private static void sellSilkBag() {
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.CLERIC, 1, factories -> {
            ItemStack itemStack = new ItemStack(Items.SILK_BAG);
            NbtCompound nbtCompound = new NbtCompound();
            NbtList list = new NbtList();
            NbtCompound element = new NbtCompound();
            Random random0 = new Random();
            element.putString("id", "fabric_mod:poop");
            element.putInt("count", random0.nextInt(4, 9));
            list.add(element);
            nbtCompound.put("elements", list);
            itemStack.setNbt(nbtCompound);
            Random random1 = new Random();
            Random random2 = new Random();
            factories.add((entity, random) -> new TradeOffer(new ItemStack(net.minecraft.item.Items.EMERALD, random1.nextInt(3, 6)), itemStack, 1, random2.nextInt(1, 4),1));
        });
    }
}
