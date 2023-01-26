package net.zylll.fabric_mod.world.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Random;

public class SpiralFeature extends Feature<SpiralFeature.SpiralFeatureConfig> {
    public SpiralFeature(Codec<SpiralFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<SpiralFeatureConfig> context) {
        BlockPos pos = context.getOrigin();
        SpiralFeatureConfig config = context.getConfig();
        Direction offset = Direction.NORTH;
        BlockState state = Registry.BLOCK.get(config.blockID()).getDefaultState();
        StructureWorldAccess world = context.getWorld();
        if (state == null)
            throw new IllegalStateException(config.blockID() + "could not be parsed to a valid block identifier!");
        BlockPos testPos = new BlockPos(pos);
        for (int i = 0; i < world.getHeight(); i++) {
            testPos = testPos.up();
            if (world.getBlockState(testPos).isIn(BlockTags.SAND)) {
                if (world.getBlockState(testPos.up()).isOf(Blocks.AIR)) {
                    Random random = new Random();
                    for (int y = 0; y < random.nextInt(5, 20); y++) {
                        offset = offset.rotateYClockwise();
                        BlockPos blockPos = testPos.up(y).offset(offset);
                        context.getWorld().setBlockState(blockPos, state, 0x11);
                        if (testPos.up(y + 1).getY() >= world.getTopY()) break;
                    }
                    return true;
                }
            }
        }

        return false;
    }

    public record SpiralFeatureConfig(Identifier blockID) implements FeatureConfig {
        public static final Codec<SpiralFeatureConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Identifier.CODEC.fieldOf("blockID").forGetter(SpiralFeatureConfig::blockID)
        ).apply(instance, SpiralFeatureConfig::new));
    }
}
