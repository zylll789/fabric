package net.zylll.fabric_mod.world.biome;

import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.MusicSound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.zylll.fabric_mod.world.gen.feature.OrePlacedFeatures;
import org.jetbrains.annotations.Nullable;

public class PoopLand {

    protected static int getSkyColor(float temperature) {
        float f = temperature / 3.0F;
        f = MathHelper.clamp(f, -1.0F, 1.0F);
        return MathHelper.hsvToRgb(0.62222224F - f * 0.05F, 0.5F + f * 0.1F, 1.0F);
    }

    private static Biome createBiome(Biome.Precipitation precipitation, Biome.Category category, float temperature, float downfall, int waterColor, int waterFogColor, SpawnSettings.Builder spawnSettings, net.minecraft.world.biome.GenerationSettings.Builder generationSettings, @Nullable MusicSound music) {
        return (new net.minecraft.world.biome.Biome.Builder()).precipitation(precipitation)
                .category(category).temperature(temperature).downfall(downfall)
                .effects((new net.minecraft.world.biome.BiomeEffects.Builder()).waterColor(waterColor).waterFogColor(waterFogColor).fogColor(12638463).skyColor(getSkyColor(temperature)).moodSound(BiomeMoodSound.CAVE).music(music).build()).spawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    public static Biome createPoopLand() {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addFarmAnimals(spawnSettings);//生成动物
        DefaultBiomeFeatures.addMonsters(spawnSettings, 95, 5, 100, false);//生成怪物

        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();
        addBasicFeatures(generationSettings);
        generationSettings.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_CLOSESTOOL);
        generationSettings.feature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, OrePlacedFeatures.ORE_CLOSESTOOL);
        return createBiome(Biome.Precipitation.NONE, Biome.Category.NONE, 1, 0, 0xC49C33, 329011, spawnSettings, generationSettings, null);
    }

    private static void addBasicFeatures(net.minecraft.world.biome.GenerationSettings.Builder generationSettings) {
        DefaultBiomeFeatures.addLandCarvers(generationSettings);
        DefaultBiomeFeatures.addAmethystGeodes(generationSettings);
        DefaultBiomeFeatures.addDungeons(generationSettings);
        DefaultBiomeFeatures.addMineables(generationSettings);
        DefaultBiomeFeatures.addSprings(generationSettings);
        DefaultBiomeFeatures.addFrozenTopLayer(generationSettings);
    }

}
