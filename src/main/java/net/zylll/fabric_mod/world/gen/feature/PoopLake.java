package net.zylll.fabric_mod.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.LakeFeature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Random;

public class PoopLake extends Feature<LakeFeature.Config> {
    private static final BlockState CAVE_AIR;


    public PoopLake(Codec<LakeFeature.Config> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<LakeFeature.Config> context) {
        BlockPos blockPos = context.getOrigin();
        StructureWorldAccess structureWorldAccess = context.getWorld();
        Random random = context.getRandom();
        LakeFeature.Config config = context.getConfig();
        if (blockPos.getY() <= structureWorldAccess.getBottomY() + 4) {
            return false;
        } else {
            blockPos = blockPos.down(4);
            boolean[] bls = new boolean[2048];
            int i = random.nextInt(4) + 4;

            for(int j = 0; j < i; ++j) {
                double d = random.nextDouble() * 6.0D + 3.0D;
                double e = random.nextDouble() * 4.0D + 2.0D;
                double f = random.nextDouble() * 6.0D + 3.0D;
                double g = random.nextDouble() * (16.0D - d - 2.0D) + 1.0D + d / 2.0D;
                double h = random.nextDouble() * (8.0D - e - 4.0D) + 2.0D + e / 2.0D;
                double k = random.nextDouble() * (16.0D - f - 2.0D) + 1.0D + f / 2.0D;

                for(int l = 1; l < 15; ++l) {
                    for(int m = 1; m < 15; ++m) {
                        for(int n = 1; n < 7; ++n) {
                            double o = ((double)l - g) / (d / 2.0D);
                            double p = ((double)n - h) / (e / 2.0D);
                            double q = ((double)m - k) / (f / 2.0D);
                            double r = o * o + p * p + q * q;
                            if (r < 1.0D) {
                                bls[(l * 16 + m) * 8 + n] = true;
                            }
                        }
                    }
                }
            }

            BlockState blockState = config.fluid().getBlockState(random, blockPos);

            int t;
            boolean v1;
            int s;
            int u;
            for(s = 0; s < 16; ++s) {
                for(t = 0; t < 16; ++t) {
                    for(u = 0; u < 8; ++u) {
                        v1 = !bls[(s * 16 + t) * 8 + u] && (s < 15 && bls[((s + 1) * 16 + t) * 8 + u] || s > 0 && bls[((s - 1) * 16 + t) * 8 + u] || t < 15 && bls[(s * 16 + t + 1) * 8 + u] || t > 0 && bls[(s * 16 + (t - 1)) * 8 + u] || u < 7 && bls[(s * 16 + t) * 8 + u + 1] || u > 0 && bls[(s * 16 + t) * 8 + (u - 1)]);
                        if (v1) {
                            Material material = structureWorldAccess.getBlockState(blockPos.add(s, u, t)).getMaterial();
                            if (u >= 4 && material.isLiquid()) {
                                return false;
                            }

                            if (u < 4 && !material.isSolid() && structureWorldAccess.getBlockState(blockPos.add(s, u, t)) != blockState) {
                                return false;
                            }
                        }
                    }
                }
            }

            boolean bl2;
            for(s = 0; s < 16; ++s) {
                for(t = 0; t < 16; ++t) {
                    for(u = 0; u < 8; ++u) {
                        if (bls[(s * 16 + t) * 8 + u]) {
                            BlockPos blockPos2 = blockPos.add(s, u, t);
                            if (this.canReplace(structureWorldAccess.getBlockState(blockPos2))) {
                                bl2 = u >= 4;
                                structureWorldAccess.setBlockState(blockPos2, bl2 ? CAVE_AIR : blockState, 2);
                                if (bl2) {
                                    structureWorldAccess.createAndScheduleBlockTick(blockPos2, CAVE_AIR.getBlock(), 0);
                                    this.markBlocksAboveForPostProcessing(structureWorldAccess, blockPos2);
                                }
                            }
                        }
                    }
                }
            }

            BlockState blockState2 = config.barrier().getBlockState(random, blockPos);
            if (!blockState2.isAir()) {
                for(t = 0; t < 16; ++t) {
                    for(u = 0; u < 16; ++u) {
                        for(int v = 0; v < 8; ++v) {
                            bl2 = !bls[(t * 16 + u) * 8 + v] && (t < 15 && bls[((t + 1) * 16 + u) * 8 + v] || t > 0 && bls[((t - 1) * 16 + u) * 8 + v] || u < 15 && bls[(t * 16 + u + 1) * 8 + v] || u > 0 && bls[(t * 16 + (u - 1)) * 8 + v] || v < 7 && bls[(t * 16 + u) * 8 + v + 1] || v > 0 && bls[(t * 16 + u) * 8 + (v - 1)]);
                            if (bl2 && (v < 4 || random.nextInt(2) != 0)) {
                                BlockState blockState3 = structureWorldAccess.getBlockState(blockPos.add(t, v, u));
                                if (blockState3.getMaterial().isSolid() && !blockState3.isIn(BlockTags.LAVA_POOL_STONE_CANNOT_REPLACE)) {
                                    BlockPos blockPos3 = blockPos.add(t, v, u);
                                    structureWorldAccess.setBlockState(blockPos3, blockState2, 2);
                                    this.markBlocksAboveForPostProcessing(structureWorldAccess, blockPos3);
                                }
                            }
                        }
                    }
                }
            }

            if (blockState.getFluidState().isIn(FluidTags.WATER)) {
                for(t = 0; t < 16; ++t) {
                    for(u = 0; u < 16; ++u) {
                        v1 = true;
                        BlockPos blockPos4 = blockPos.add(t, 4, u);
                        if (structureWorldAccess.getBiome(blockPos4).value().canSetIce(structureWorldAccess, blockPos4, false) && this.canReplace(structureWorldAccess.getBlockState(blockPos4))) {
                            structureWorldAccess.setBlockState(blockPos4, Blocks.ICE.getDefaultState(), 2);
                        }
                    }
                }
            }

            return true;
        }
    }
    private boolean canReplace(BlockState state) {
        return !state.isIn(BlockTags.FEATURES_CANNOT_REPLACE);
    }

    static {
        CAVE_AIR = Blocks.CAVE_AIR.getDefaultState();
    }

}
