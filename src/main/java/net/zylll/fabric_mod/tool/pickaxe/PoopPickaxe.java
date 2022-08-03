package net.zylll.fabric_mod.tool.pickaxe;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.zylll.fabric_mod.registry.Items;

import java.util.Random;

public class PoopPickaxe extends PickaxeItem {
    public PoopPickaxe(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        Random random1 = new Random();
        if(random1.nextFloat() - 0.9F > 0.0F){
            ((PlayerEntity)miner).giveItemStack(Items.POOP.getDefaultStack());}//10%获得poop
        Random random2 = new Random();
        if (random2.nextFloat() - 0.95F > 0.0F){
            world.breakBlock(getPos(pos, 1, (PlayerEntity)miner), true);
            world.breakBlock(getPos(pos, -1, (PlayerEntity)miner), true);
        }//5%破坏左右方块
        return super.postMine(stack, world, state, pos, miner);
    }

    private BlockPos getPos(BlockPos pos, int y ,PlayerEntity miner){
        if (miner.getHorizontalFacing()== Direction.EAST||miner.getHorizontalFacing()== Direction.WEST){
            pos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() + y);
        }else if(miner.getHorizontalFacing()== Direction.SOUTH||miner.getHorizontalFacing()== Direction.NORTH){
            pos = new BlockPos(pos.getX() + y, pos.getY(), pos.getZ());
        }
        return pos;
    }
}
