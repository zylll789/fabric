package net.zylll.fabric_mod.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;

public class CopyItem extends Item {
    public CopyItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        if (stack.hasNbt()) {
            assert stack.getNbt() != null;
            return stack.getNbt().getInt("amount") > 0;
        }
        return false;
    }

    public static NbtCompound writeNBT(int amount) {
        NbtCompound nbt = new NbtCompound();
        nbt.putInt("amount", amount);
        NbtString string = NbtString.of(NbtString.escape("Number of copies " + amount));
        NbtList nbtList = new NbtList();
        nbtList.add(string);
        NbtCompound nbt1 = new NbtCompound();
        nbt1.put("Lore", nbtList);
        nbt.put("display", nbt1);
        return nbt;
    }
}
