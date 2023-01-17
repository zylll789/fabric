package net.zylll.fabric_mod.registry;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;
import net.zylll.fabric_mod.feature.SpiralFeature;
import net.zylll.fabric_mod.world.gen.feature.PoopLake;

import java.util.Arrays;

import static net.zylll.fabric_mod.FabricMod.makeID;

public class Features {
    public static PoopLake POOP_LAKE;
    private static final Feature<SpiralFeature.SpiralFeatureConfig> SPIRAL = new SpiralFeature(SpiralFeature.SpiralFeatureConfig.CODEC);
    //public static final ConfiguredFeature<?, ?> POOP_SPIRAL = SPIRAL.


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
        POOP_LAKE = Registry.register(Registry.FEATURE, makeID("poop_lake"), new PoopLake(LakeFeature.Config.CODEC));

        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, makeID("overworld_poop_block"), OVERWORLD_POOP_BLOCK_CONFIGURED_FEATURE);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, makeID("overworld_poop_block"), OVERWORLD_POOP_BLOCK_PLACED_FEATURE);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES,
                RegistryKey.of(Registry.PLACED_FEATURE_KEY, makeID("overworld_poop_block")));

        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, makeID("nether_trick_block"), NETHER_TRICK_BLOCK_CONFIGURED_FEATURE);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, makeID("nether_trick_block"), NETHER_TRICK_BLOCK_PLACED_FEATURE);
        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES,
                RegistryKey.of(Registry.PLACED_FEATURE_KEY, makeID("nether_trick_block")));

        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, makeID("end_ore_changed_block"), END_ORE_CHANGED_BLOCK_CONFIGURED_FEATURE);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, makeID("end_ore_changed_block"), END_ORE_CHANGED_BLOCK_PLACED_FEATURE);
        BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(), GenerationStep.Feature.UNDERGROUND_ORES,
                RegistryKey.of(Registry.PLACED_FEATURE_KEY, makeID("end_ore_changed_block")));

        register("spiral", SPIRAL);
    }

    private static void register(String id, Feature<?> c) {
        Registry.register(Registry.FEATURE, makeID(id), c);
    }
}
