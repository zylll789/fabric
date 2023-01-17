package net.zylll.fabric_mod.recipe;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import net.zylll.fabric_mod.registry.Items;
import net.zylll.fabric_mod.registry.Recipes;

import static net.zylll.fabric_mod.item.CopyItem.writeNBT;

public class CopyRecipe extends SpecialCraftingRecipe {
    public CopyRecipe(Identifier id) {
        super(id);
    }

    private int copyItemSlot;
    private int itemSlot;
    private static ItemStack copyItem = ItemStack.EMPTY;
    private static ItemStack leftItem = ItemStack.EMPTY;
    private NbtCompound nbtCompound;
    private int leftCount;

    @Override
    public boolean matches(CraftingInventory inventory, World world) {
        int count = 0;
        int copyItemCount = 0;
        for (int i = 0; i < inventory.size(); ++i) {
            ItemStack itemStack = inventory.getStack(i);
            if (!itemStack.isEmpty()) {
                if (itemStack.getItem() == Items.COPY_ITEM) {
                    if (itemStack.hasNbt()) {
                        assert itemStack.getNbt() != null;
                        if (itemStack.getNbt().get("amount") != null) {
                            count++;
                            copyItemCount++;
                        }
                    }
                } else {
                    count++;
                }
            }
        }
        return count == 2 && copyItemCount == 1;
    }

    @Override
    public ItemStack craft(CraftingInventory inventory) {
        int count = 0;
        int copyItemCount = 0;
        for (int i = 0; i < inventory.size(); ++i) {
            ItemStack itemStack = inventory.getStack(i);
            if (!itemStack.isEmpty()) {
                if (itemStack.getItem() == net.minecraft.item.Items.DIAMOND || itemStack.getItem() == net.minecraft.item.Items.DIAMOND_BLOCK) {
                    return ItemStack.EMPTY;
                }
                if (itemStack.getItem() == Items.COPY_ITEM) {
                    if (itemStack.hasNbt()) {
                        assert itemStack.getNbt() != null;
                        if (itemStack.getNbt().getInt("amount") > 0) {
                            count++;
                            copyItemCount++;
                            copyItemSlot = i;
                        }
                    }
                } else {
                    count++;
                    itemSlot = i;
                }
            }
        }
        if (count == 2 && copyItemCount == 1) {
            ItemStack stack = inventory.getStack(copyItemSlot);
            assert stack.getNbt() != null;
            int amount = stack.getNbt().getInt("amount");
            leftCount = amount - 1;
            ItemStack stack1 = inventory.getStack(itemSlot);
            Item item = inventory.getStack(itemSlot).getItem();
            leftItem = stack1;
            nbtCompound = stack1.getNbt();
            copyItem = stack;
            if (stack1.hasNbt()) {
                NbtCompound nbt = stack1.getNbt();
                ItemStack stack2 = new ItemStack(item);
                stack2.setNbt(nbt);
                return stack2;
            }
            return new ItemStack(item);
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean fits(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Recipes.COPY_RECIPE;
    }

    @Override
    public DefaultedList<ItemStack> getRemainder(CraftingInventory inventory) {
        DefaultedList<ItemStack> list = DefaultedList.ofSize(9, ItemStack.EMPTY);
        ItemStack stack = new ItemStack(copyItem.getItem());
        stack.setNbt(writeNBT(leftCount));
        list.set(copyItemSlot, stack);
        ItemStack itemStack = new ItemStack(leftItem.getItem());
        itemStack.setNbt(nbtCompound);
        list.set(itemSlot, itemStack);
        return list;
    }

}
