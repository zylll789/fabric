package net.zylll.fabric_mod.mixin;

import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.zylll.fabric_mod.registry.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BrewingRecipeRegistry.class)
public class BrewingMixin {

    @Inject(at = @At("HEAD"), method = "registerDefaults")
    private static void registerDefaults(CallbackInfo callbackInfo) {
        BrewingMixin.registerPotionRecipe(Potions.AWKWARD, Items.POOP, Potions.POISON);
        BrewingMixin.registerPotionRecipe(Potions.STRENGTH, Items.POOP, net.zylll.fabric_mod.registry.Potions.EPINEPHRINE);
    }

    @Invoker("registerPotionRecipe")
    public static void registerPotionRecipe(Potion input, Item item, Potion output) {
    }
}
