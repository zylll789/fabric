package net.zylll.fabric_mod.world.gen.feature;

import net.minecraft.block.Blocks;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

import java.util.List;

import static net.minecraft.world.gen.feature.OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES;
import static net.minecraft.world.gen.feature.OreConfiguredFeatures.STONE_ORE_REPLACEABLES;

public class OreConfiguredFeatures {

    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_CLOSESTOOL;

    static {
        ORE_CLOSESTOOL = ConfiguredFeatures.register("ore_closestool", Feature.ORE, new OreFeatureConfig(List.of(OreFeatureConfig.createTarget(STONE_ORE_REPLACEABLES, Blocks.REDSTONE_ORE.getDefaultState()), OreFeatureConfig.createTarget(DEEPSLATE_ORE_REPLACEABLES, Blocks.DEEPSLATE_REDSTONE_ORE.getDefaultState())), 8));
    }

}
