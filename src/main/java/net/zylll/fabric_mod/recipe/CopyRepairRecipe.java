package net.zylll.fabric_mod.recipe;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.zylll.fabric_mod.registry.Recipes;

import static net.zylll.fabric_mod.item.CopyItem.writeNBT;

public class CopyRepairRecipe extends SpecialCraftingRecipe {
    public CopyRepairRecipe(Identifier id) {
        super(id);
    }

    private int copyItemSlot;

    @Override
    public boolean matches(CraftingInventory inventory, World world) {
        boolean hasCopyItem = false;
        boolean hasDiamond = false;
        for (int i = 0; i < inventory.size(); ++i) {
            ItemStack itemStack = inventory.getStack(i);
            if (!itemStack.isEmpty()) {
                if (itemStack.getItem() == net.zylll.fabric_mod.registry.Items.COPY_ITEM) {
                    hasCopyItem = true;
                } else if (itemStack.getItem() == Items.DIAMOND) {
                    hasDiamond = true;
                } else return false;
            }
        }
        return hasDiamond && hasCopyItem;
    }

    @Override
    public ItemStack craft(CraftingInventory inventory) {
        boolean hasCopyItem = false;
        boolean hasDiamond = false;
        int count = 0;
        for (int i = 0; i < inventory.size(); ++i) {
            ItemStack itemStack = inventory.getStack(i);
            if (!itemStack.isEmpty()) {
                if (itemStack.getItem() == net.zylll.fabric_mod.registry.Items.COPY_ITEM && hasCopyItem) {
                    return ItemStack.EMPTY;
                }
                if (itemStack.getItem() == net.zylll.fabric_mod.registry.Items.COPY_ITEM) {
                    hasCopyItem = true;
                    copyItemSlot = i;
                }
                if (itemStack.getItem() == Items.DIAMOND) {
                    hasDiamond = true;
                    count++;
                }
            }
        }
        if (hasCopyItem && hasDiamond) {
            ItemStack itemStack = inventory.getStack(copyItemSlot);
            Item item = inventory.getStack(copyItemSlot).getItem();
            assert itemStack.getNbt() != null;
            int amount = itemStack.getNbt().getInt("amount");
            ItemStack stack = new ItemStack(item);
            stack.setNbt(writeNBT(amount + count));
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
        return Recipes.COPY_REPAIR_RECIPE;
    }
}
