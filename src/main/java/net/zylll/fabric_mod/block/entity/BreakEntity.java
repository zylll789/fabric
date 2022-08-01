package net.zylll.fabric_mod.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BreakEntity extends BlockEntity {
    public BreakEntity(BlockPos pos, BlockState state) {
        super(AllBlockEntities.BREAK_ENTITY, pos, state);
    }

    public static void tick(World world, BlockPos pos){
        BlockPos up = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
        if (!world.getBlockState(up).isAir()){
            Item item = world.getBlockState(up).getBlock().asItem();
            world.setBlockState(up, Blocks.AIR.getDefaultState());
            ItemEntity itemEntity = new ItemEntity(world, up.getX(), up.getY(), up.getZ(), new ItemStack(item, 1));
            world.spawnEntity(itemEntity);
        }
    }
}
