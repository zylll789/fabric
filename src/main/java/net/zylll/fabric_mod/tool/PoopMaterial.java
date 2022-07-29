package net.zylll.fabric_mod.tool;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.zylll.fabric_mod.item.AllItems;

public class PoopMaterial implements ToolMaterial {
    @Override
    public int getDurability() {
        return 10;//耐久
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 0;
    }

    @Override
    public float getAttackDamage() {
        return -1;
    }

    @Override
    public int getMiningLevel() {
        return 0;
    }

    @Override
    public int getEnchantability() {
        return 9;//附魔
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(AllItems.POOP);//修复物品
    }
}
