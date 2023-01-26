package net.zylll.fabric_mod.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.zylll.fabric_mod.world.feature.ModPlacedFeatures;

public class TreeGeneration {

    public static void generateTrees() {
        BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.PLAINS), GenerationStep.Feature.VEGETAL_DECORATION,
                ModPlacedFeatures.POOP_TREE_PLACED.getKey().get());
    }

}
