package net.zylll.fabric_mod.recipe;

import com.google.common.collect.Lists;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import net.zylll.fabric_mod.registry.Recipes;

import java.util.ArrayList;
import java.util.Objects;

public class CleanUIBlockRecipe extends SpecialCraftingRecipe {
    private int waterBucketSlot = 0;

    public CleanUIBlockRecipe(Identifier id) {
        super(id);
    }

    @Override
    public boolean matches(CraftingInventory inventory, World world) {
        ArrayList<ItemStack> list = Lists.newArrayList();
        for (int i = 0; i < inventory.size(); ++i) {
            ItemStack stack = inventory.getStack(i);
            if (!stack.isEmpty()) {
                if (stack.getItem() == Items.WATER_BUCKET) {
                    list.add(stack);
                } else if (stack.getItem() == net.zylll.fabric_mod.registry.Items.UI_BLOCK_ITEM) {
                    list.add(stack);
                } else return false;
            }
        }
        return list.size() == 2;
    }

    @Override
    public ItemStack craft(CraftingInventory inventory) {
        boolean hasUIBlock = false;
        boolean hasWaterBucket = false;
        int count = 0;
        for (int i = 0; i < inventory.size(); ++i) {
            ItemStack stack = inventory.getStack(i);
            if (!stack.isEmpty()) {
                if (stack.getItem() == Items.WATER_BUCKET) {
                    hasWaterBucket = true;
                    waterBucketSlot = i;
                } else if (Objects.equals(stack.getNbt(), net.zylll.fabric_mod.registry.Items.UI_BLOCK_ITEM.getDefaultStack().getNbt())) {
                    hasUIBlock = true;
                }
                count++;
            }
        }
        if (hasUIBlock && hasWaterBucket && count == 2) {
            return new ItemStack(net.zylll.fabric_mod.registry.Items.POOP_BLOCK_ITEM, 6);
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean fits(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public DefaultedList<ItemStack> getRemainder(CraftingInventory inventory) {
        DefaultedList<ItemStack> list = DefaultedList.ofSize(9, ItemStack.EMPTY);
        list.set(waterBucketSlot, new ItemStack(Items.BUCKET, 1));
        return list;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Recipes.CLEAN_UI_BLOCK_RECIPE;
    }
}
