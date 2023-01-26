package net.zylll.fabric_mod.registry;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;
import net.zylll.fabric_mod.FabricMod;
import net.zylll.fabric_mod.world.feature.ModConfiguredFeatures;
import net.zylll.fabric_mod.world.feature.PoopFeature;
import net.zylll.fabric_mod.world.feature.SpiralFeature;
import net.zylll.fabric_mod.world.feature.featureConfig.PoopFeatureConfig;
import net.zylll.fabric_mod.world.gen.feature.PoopLake;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static net.zylll.fabric_mod.FabricMod.makeID;

public class Features {

    public static PoopLake POOP_LAKE;

    public static final Feature<SpiralFeature.SpiralFeatureConfig> SPIRAL = new SpiralFeature(SpiralFeature.SpiralFeatureConfig.CODEC);
    public static final ConfiguredFeature<SpiralFeature.SpiralFeatureConfig, SpiralFeature> SPIRAL_FEATURE_CONFIG = new ConfiguredFeature<>(
            (SpiralFeature) SPIRAL, new SpiralFeature.SpiralFeatureConfig(makeID("poop_block"))
    );
    public static PlacedFeature SPIRAL_FEATURE_PLACED = new PlacedFeature(RegistryEntry.of(SPIRAL_FEATURE_CONFIG), List.of(SquarePlacementModifier.of()));

    public static Feature<PoopFeatureConfig> POOP_FEATURE = new PoopFeature(PoopFeatureConfig.POOP_FEATURE_CONFIG_CODEC);
    public static ConfiguredFeature<PoopFeatureConfig, PoopFeature> POOP_FEATURE_CONFIG = new ConfiguredFeature<>(
            (PoopFeature) POOP_FEATURE, new PoopFeatureConfig(makeID("closestool"))
    );
    public static PlacedFeature POOP_FEATURE_PLACED = new PlacedFeature(RegistryEntry.of(POOP_FEATURE_CONFIG), List.of(SquarePlacementModifier.of()));


    //ore
    private static ConfiguredFeature<?, ?> OVERWORLD_POOP_BLOCK_CONFIGURED_FEATURE = new ConfiguredFeature<>(Feature.ORE, new OreFeatureConfig
            (OreConfiguredFeatures.STONE_ORE_REPLACEABLES, Blocks.POOP_BLOCK.getDefaultState(), 9));
    public static PlacedFeature OVERWORLD_POOP_BLOCK_PLACED_FEATURE = new PlacedFeature(RegistryEntry.of
            (OVERWORLD_POOP_BLOCK_CONFIGURED_FEATURE), Arrays.asList(
            CountPlacementModifier.of(20), SquarePlacementModifier.of(), HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(70))
    ));

    private static ConfiguredFeature<?, ?> NETHER_TRICK_BLOCK_CONFIGURED_FEATURE = new ConfiguredFeature<>(Feature.ORE, new OreFeatureConfig
            (OreConfiguredFeatures.NETHERRACK, Blocks.TRICK_BLOCK.getDefaultState(), 18));
    public static PlacedFeature NETHER_TRICK_BLOCK_PLACED_FEATURE = new PlacedFeature(RegistryEntry.of
            (NETHER_TRICK_BLOCK_CONFIGURED_FEATURE), Arrays.asList(
            CountPlacementModifier.of(25), SquarePlacementModifier.of(), HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(120))
    ));

    private static ConfiguredFeature<?, ?> END_ORE_CHANGED_BLOCK_CONFIGURED_FEATURE = new ConfiguredFeature<>(Feature.ORE, new OreFeatureConfig
            (new BlockMatchRuleTest(net.minecraft.block.Blocks.END_STONE), Blocks.ORE_CHANGED_BLOCK.getDefaultState(), 9));
    public static PlacedFeature END_ORE_CHANGED_BLOCK_PLACED_FEATURE = new PlacedFeature(RegistryEntry.of
            (END_ORE_CHANGED_BLOCK_CONFIGURED_FEATURE), Arrays.asList(
            CountPlacementModifier.of(25), SquarePlacementModifier.of(), HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64))
    ));

    public static void register() {
        FabricMod.log("Register Features for + " + FabricMod.MOD_ID);
        ModConfiguredFeatures.registerConfiguredFeatures();
        POOP_LAKE = Registry.register(Registry.FEATURE, makeID("poop_lake"), new PoopLake(LakeFeature.Config.CODEC));

        register("overworld_poop_block", OVERWORLD_POOP_BLOCK_CONFIGURED_FEATURE, OVERWORLD_POOP_BLOCK_PLACED_FEATURE, BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES);
        register("nether_trick_block", NETHER_TRICK_BLOCK_CONFIGURED_FEATURE, NETHER_TRICK_BLOCK_PLACED_FEATURE, BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES);
        register("end_ore_changed_block", END_ORE_CHANGED_BLOCK_CONFIGURED_FEATURE, END_ORE_CHANGED_BLOCK_PLACED_FEATURE, BiomeSelectors.foundInTheEnd(), GenerationStep.Feature.UNDERGROUND_ORES);

        register("spiral_feature", SPIRAL, SPIRAL_FEATURE_CONFIG, SPIRAL_FEATURE_PLACED, BiomeSelectors.categories(Biome.Category.DESERT), GenerationStep.Feature.VEGETAL_DECORATION);

        register("poop_feature", POOP_FEATURE, POOP_FEATURE_CONFIG, POOP_FEATURE_PLACED, BiomeSelectors.categories(Biome.Category.PLAINS), GenerationStep.Feature.VEGETAL_DECORATION);

    }

    private static void register(String id, Feature<?> f, ConfiguredFeature<?, ?> cf, PlacedFeature p, Predicate<BiomeSelectionContext> selector, GenerationStep.Feature step) {
        Registry.register(Registry.FEATURE, makeID(id), f);
        register(id, cf, p, selector, step);
    }

    private static void register(String id, ConfiguredFeature<?, ?> cf, PlacedFeature p, Predicate<BiomeSelectionContext> selector, GenerationStep.Feature step) {
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, makeID(id), cf);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, makeID(id), p);
        BiomeModifications.addFeature(selector, step, RegistryKey.of(Registry.PLACED_FEATURE_KEY, makeID(id)));

    }

}
