package net.zylll.fabric_mod.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class PoopBlock extends Block{

    public PoopBlock(Settings settings) {
        super(settings);
    }

    public static boolean canSpawn(BlockState blockState, BlockView blockView, BlockPos pos, EntityType<?> entityType) {
        return false;
    }

    private static final VoxelShape SHAPE = Block.createCuboidShape(0.0,0.0,0.0,16.0,15.95,16.0);

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        //world.createExplosion(entity,pos.getX(),pos.getY(),pos.getZ(),1F, Explosion.DestructionType.NONE);
        if (entity instanceof PlayerEntity playerEntity && playerEntity.isInSneakingPose()) {
            playerEntity.sendMessage(new LiteralText("11111"), false);
        }
        super.onEntityCollision(state, world, pos, entity);
    }
}
