package net.zylll.fabric_mod.tool.axe;

import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.zylll.fabric_mod.registry.Blocks;
import net.zylll.fabric_mod.registry.Items;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

public class PoopAxe extends AxeItem {
    public PoopAxe(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new TranslatableText("item.fabric_mod.poop_axe.tooltip1"));
        tooltip.add(new TranslatableText("item.fabric_mod.poop_axe.tooltip2"));
        tooltip.add(new TranslatableText("item.fabric_mod.poop_axe.tooltip3"));
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        Random random1 = new Random();
        if(random1.nextFloat() - 0.9F > 0.0F){
            ((PlayerEntity)miner).giveItemStack(Items.POOP.getDefaultStack());}//10%获得poop
        Random random2 = new Random();
        if (random2.nextFloat() - 0.95F > 0.0F){
            world.breakBlock(getPos(pos, 1), true);
            world.breakBlock(getPos(pos, -1), true);
        }//5%破坏上下方块
        Random random3 = new Random();
        if (random3.nextFloat()<0.1F){
            world.setBlockState(pos, Blocks.POOP_BLOCK.getDefaultState());
        }//10%将挖掘方块变成poop
        return super.postMine(stack, world, state, pos, miner);
    }

    private BlockPos getPos(BlockPos pos, int y){
        pos = new BlockPos(pos.getX(), pos.getY() + y, pos.getZ());
        return pos;
    }
}
