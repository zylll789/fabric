package net.zylll.fabric_mod.world.feature;

import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import net.zylll.fabric_mod.FabricMod;
import net.zylll.fabric_mod.registry.Blocks;

import java.util.List;

public class ModConfiguredFeatures {

    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> POOP_TREE =
            ConfiguredFeatures.register("poop_tree", Feature.TREE, new TreeFeatureConfig.Builder(
                    BlockStateProvider.of(Blocks.POOP_LOG), new StraightTrunkPlacer(5, 6, 3),
                    BlockStateProvider.of(Blocks.POOP_LEAVES),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 4),
                    new TwoLayersFeatureSize(1, 0, 2)).build());

    public static final RegistryEntry<PlacedFeature> POOP_TREE_CHECKED =
            PlacedFeatures.register("poop_tree_checked", POOP_TREE,
                    PlacedFeatures.wouldSurvive(Blocks.POOP_SAPLING));

    public static final RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> POOP_TREE_SPAWN =
            ConfiguredFeatures.register("poop_tree_spawn", Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfig(List.of(new RandomFeatureEntry(POOP_TREE_CHECKED, 0.5f)), POOP_TREE_CHECKED));

    public static void registerConfiguredFeatures() {
        FabricMod.log("Register Configured Features for " + FabricMod.MOD_ID);
    }

}
