package net.zylll.fabric_mod.registry;

import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.zylll.fabric_mod.FabricMod;
import net.zylll.fabric_mod.recipe.*;

public class Recipes {

    public static final RecipeSerializer<CleanUIBlockRecipe> CLEAN_UI_BLOCK_RECIPE
            = RecipeSerializer.register("crafting_clean_ui_block", new SpecialRecipeSerializer<>(CleanUIBlockRecipe::new));
    public static final RecipeSerializer<CopyItemRecipe> COPY_ITEM_RECIPE
            = RecipeSerializer.register("crafting_copy_item", new SpecialRecipeSerializer<>(CopyItemRecipe::new));
    public static final RecipeSerializer<CopyRecipe> COPY_RECIPE
            = RecipeSerializer.register("crafting_copy", new SpecialRecipeSerializer<>(CopyRecipe::new));
    public static final RecipeSerializer<CopyRepairRecipe> COPY_REPAIR_RECIPE
            = RecipeSerializer.register("crafting_copy_item_repair", new SpecialRecipeSerializer<>(CopyRepairRecipe::new));
    public static final RecipeSerializer<SilkBagRecipe> SILK_BAG_RECIPE
            = RecipeSerializer.register("crafting_silk_bag", new SpecialRecipeSerializer<>(SilkBagRecipe::new));

    public static void register() {
        FabricMod.log("Register Recipes for + " + FabricMod.MOD_ID);
    }
}
