package net.zylll.fabric_mod.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public class SpiralFeature extends Feature<SpiralFeature.SpiralFeatureConfig> {
    public SpiralFeature(Codec<SpiralFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<SpiralFeatureConfig> context) {
        BlockPos pos = context.getOrigin();
        SpiralFeatureConfig config = context.getConfig();
        Direction offset = Direction.NORTH;
        int height = config.height().get(context.getRandom());
        for (int y = 0;y < height; y++){
            offset = offset.rotateYClockwise();
            BlockPos blockPos = pos.up(y).offset(offset);
            context.getWorld().setBlockState(blockPos, config.block().getBlockState(context.getRandom(), blockPos), 3);

        }

        return true;
    }

    public record SpiralFeatureConfig(IntProvider height, BlockStateProvider block) implements FeatureConfig {
        public static final Codec<SpiralFeatureConfig> CODEC = RecordCodecBuilder.create(instance ->instance.group(
                IntProvider.VALUE_CODEC.fieldOf("height").forGetter(SpiralFeatureConfig::height),
                BlockStateProvider.TYPE_CODEC.fieldOf("block").forGetter(SpiralFeatureConfig::block)
        ).apply(instance, instance.stable(SpiralFeatureConfig::new)));
    }
}
