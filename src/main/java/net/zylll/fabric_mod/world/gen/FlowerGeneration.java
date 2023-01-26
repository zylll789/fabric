package net.zylll.fabric_mod.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;
import net.zylll.fabric_mod.registry.Biomes;
import net.zylll.fabric_mod.world.feature.ModPlacedFeatures;

public class FlowerGeneration {

    public static void generateFlowers() {
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.POOP_LAND), GenerationStep.Feature.VEGETAL_DECORATION,
                ModPlacedFeatures.POOP_FLOWER_PLACED.getKey().get());
    }

}
