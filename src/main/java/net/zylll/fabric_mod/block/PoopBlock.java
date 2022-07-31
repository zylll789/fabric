package net.zylll.fabric_mod.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class PoopBlock extends Block{

    public PoopBlock(Settings settings) {
        super(settings);
    }

    public static boolean canSpawn(BlockState blockState, BlockView blockView, BlockPos pos, EntityType<?> entityType) {
        return false;
    }
}
