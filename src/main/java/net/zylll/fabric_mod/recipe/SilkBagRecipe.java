package net.zylll.fabric_mod.recipe;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.zylll.fabric_mod.registry.Items;
import net.zylll.fabric_mod.registry.Recipes;

import java.util.ArrayList;

public class SilkBagRecipe extends SpecialCraftingRecipe {
    public SilkBagRecipe(Identifier id) {
        super(id);
    }

    @Override
    public boolean matches(CraftingInventory inventory, World world) {
        int count = 0;
        boolean hasSilkBag = false;
        for (int i = 0; i < inventory.size(); ++i) {
            ItemStack itemStack = inventory.getStack(i);
            if (!itemStack.isEmpty()) {
                count++;
                if (itemStack.getItem() == Items.SILK_BAG) {
                    hasSilkBag = true;
                }
                if (itemStack.hasNbt()) {
                    return false;
                }
            }
        }
        return count >= 2 && hasSilkBag;
    }

    @Override
    public ItemStack craft(CraftingInventory inventory) {
        int count = 0;
        boolean hasSilkBag = false;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < inventory.size(); ++i) {
            ItemStack itemStack = inventory.getStack(i);
            if (!itemStack.isEmpty()) {
                count++;
                if (itemStack.hasNbt()) {
                    return ItemStack.EMPTY;
                }
                if (itemStack.getItem() == Items.SILK_BAG) {
                    hasSilkBag = true;
                }
                list.add(i);
            }
        }
        if (count >= 2 && hasSilkBag) {
            ItemStack itemStack = new ItemStack(Items.SILK_BAG);
            NbtCompound nbt = new NbtCompound();
            NbtList nbtList = new NbtList();
            for (int slot : list) {
                int count1 = inventory.getStack(slot).getCount();
                String id = Registry.ITEM.getId(inventory.getStack(slot).getItem()).toString();
                NbtCompound nbt1 = new NbtCompound();
                nbt1.putString("id", id);
                nbt1.putInt("count", count1);
                nbtList.add(nbt1);
            }
            nbt.put("elements", nbtList);
            itemStack.setNbt(nbt);
            return itemStack;
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean fits(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Recipes.SILK_BAG_RECIPE;
    }

    @Override
    public DefaultedList<ItemStack> getRemainder(CraftingInventory inventory) {
        for (int i = 0; i < inventory.size(); ++i) {
            inventory.setStack(i, ItemStack.EMPTY);
        }
        return super.getRemainder(inventory);
    }
}
