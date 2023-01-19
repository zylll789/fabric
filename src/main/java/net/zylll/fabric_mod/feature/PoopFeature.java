package net.zylll.fabric_mod.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.zylll.fabric_mod.feature.featureConfig.PoopFeatureConfig;

import java.util.Random;

public class PoopFeature extends Feature<PoopFeatureConfig> {
    public PoopFeature(Codec<PoopFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<PoopFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos pos = context.getOrigin();
        Random random = context.getRandom();
        PoopFeatureConfig config = context.getConfig();
        int number = random.nextInt(5,15);
        Identifier blockID = config.blockID();
        BlockState state = Registry.BLOCK.get(blockID).getDefaultState();
        if (state == null)
            throw new IllegalStateException(blockID + "could not be parsed to a valid block identifier!");
        BlockPos testPos = new BlockPos(pos);
        for (int i = 0; i < world.getHeight(); i++) {
            testPos = testPos.up();
            if (world.getBlockState(testPos).isIn(BlockTags.DIRT)) {
                if (world.getBlockState(testPos.up()).isOf(Blocks.AIR)) {
                    for (int j = 0; j < number; j++) {
                        //unknown flags
                        world.setBlockState(testPos, state, 0x10);
                        testPos = testPos.up();
                        if (testPos.getY() >= world.getTopY()) break;
                    }
                    return true;
                }
            }
        }
        return false;
    }
}
