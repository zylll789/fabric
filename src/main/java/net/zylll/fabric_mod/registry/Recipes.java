package net.zylll.fabric_mod.registry;

import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.zylll.fabric_mod.recipe.CleanUIBlockRecipe;

public class Recipes {

    public static final RecipeSerializer<CleanUIBlockRecipe> CLEAN_UI_BLOCK_RECIPE
            = RecipeSerializer.register("crafting_clean_ui_block", new SpecialRecipeSerializer<>(CleanUIBlockRecipe::new));
    public static void register() {

    }
}
