package net.zylll.fabric_mod.recipe;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.zylll.fabric_mod.registry.Recipes;

public class CopyItemRecipe extends SpecialCraftingRecipe {
    public CopyItemRecipe(Identifier id) {
        super(id);
    }

    @Override
    public boolean matches(CraftingInventory inventory, World world) {
        boolean hasPaper = false;
        boolean hasDiamond = false;
        for (int i = 0; i < inventory.size(); ++i) {
            ItemStack itemStack = inventory.getStack(i);
            if (!itemStack.isEmpty()) {
                if (itemStack.getItem() == Items.PAPER) {
                    hasPaper = true;
                } else if (itemStack.getItem() == Items.DIAMOND) {
                    hasDiamond = true;
                } else return false;
            }
        }
        return hasDiamond && hasPaper;
    }

    @Override
    public ItemStack craft(CraftingInventory inventory) {
        boolean hasPaper = false;
        boolean hasDiamond = false;
        int count = 0;
        for (int i = 0; i < inventory.size(); ++i) {
            ItemStack itemStack = inventory.getStack(i);
            if (!itemStack.isEmpty()) {
                if (itemStack.getItem() != Items.PAPER && itemStack.getItem() != Items.DIAMOND) {
                    return ItemStack.EMPTY;
                }
                if (itemStack.getItem() == Items.PAPER && hasPaper) {
                    return ItemStack.EMPTY;
                }
                if (itemStack.getItem() == Items.PAPER) {
                    hasPaper = true;
                }
                if (itemStack.getItem() == Items.DIAMOND) {
                    hasDiamond = true;
                    count++;
                }
            }
        }
        if (hasDiamond && hasPaper) {
            ItemStack stack = new ItemStack(net.zylll.fabric_mod.registry.Items.COPY_ITEM);
            NbtCompound nbt = new NbtCompound();
            nbt.putInt("amount", count);
            NbtString string = NbtString.of(NbtString.escape("Number of copies " + count));
            NbtList nbtList = new NbtList();
            nbtList.add(string);
            NbtCompound nbt1 = new NbtCompound();
            nbt1.put("Lore", nbtList);
            nbt.put("display", nbt1);
            stack.setNbt(nbt);
            return stack;
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean fits(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Recipes.COPY_ITEM_RECIPE;
    }
}
