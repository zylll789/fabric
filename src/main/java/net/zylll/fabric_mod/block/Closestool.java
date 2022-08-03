package net.zylll.fabric_mod.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class Closestool extends Block {

    private static final VoxelShape SHAPE = Block.createCuboidShape(0.0,0.0,3.0,11.0,7.0,13.0);
    private static final VoxelShape SHAPE2 = Block.createCuboidShape(12.0, 4.0, 4.0, 16.0, 14.0, 12.0);
    private static final VoxelShape BASE = VoxelShapes.combineAndSimplify(SHAPE, SHAPE2, BooleanBiFunction.OR);

    public Closestool(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return BASE;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return BASE;
    }
}
