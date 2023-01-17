package net.zylll.fabric_mod.tool.shovel;

import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.zylll.fabric_mod.registry.Blocks;
import net.zylll.fabric_mod.registry.Items;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

public class PoopShovel extends ShovelItem {
    public PoopShovel(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new TranslatableText("item.fabric_mod.poop_shovel.tooltip"));
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        Random random = new Random();
        if(random.nextFloat() - 0.9F > 0.0F){
            ((PlayerEntity)miner).giveItemStack(Items.POOP.getDefaultStack());}
        return super.postMine(stack, world, state, pos, miner);//10%获得poop
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        if (world.getBlockState(pos)== net.minecraft.block.Blocks.DIRT.getDefaultState()){
            world.setBlockState(pos, Blocks.POOP_BLOCK.getDefaultState());
        }
        return super.useOnBlock(context);
    }

    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        if(state.getBlock()== Blocks.POOP_BLOCK){
            return 10.0F;//特殊挖掘
        }else {
            return 0.9F;
        }
    }
}
