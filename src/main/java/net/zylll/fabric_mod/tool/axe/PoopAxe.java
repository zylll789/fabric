package net.zylll.fabric_mod.tool.axe;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.zylll.fabric_mod.block.AllBlocks;
import net.zylll.fabric_mod.item.AllItems;

import java.util.Random;

public class PoopAxe extends AxeItem {
    public PoopAxe(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        Random random1 = new Random();
        if(random1.nextFloat() - 0.9F > 0.0F){
            ((PlayerEntity)miner).giveItemStack(AllItems.POOP.getDefaultStack());}//10%获得poop
        Random random2 = new Random();
        if (random2.nextFloat() - 0.95F > 0.0F){
            world.breakBlock(getPos(pos, 1), true);
            world.breakBlock(getPos(pos, -1), true);
        }//5%破坏上下方块
        Random random3 = new Random();
        if (random3.nextFloat()<0.1F){
            world.setBlockState(pos, AllBlocks.POOP_BLOCK.getDefaultState());
        }//10%将挖掘方块变成poop
        return super.postMine(stack, world, state, pos, miner);
    }

    private BlockPos getPos(BlockPos pos, int y){
        pos = new BlockPos(pos.getX(), pos.getY() + y, pos.getZ());
        return pos;
    }
}
