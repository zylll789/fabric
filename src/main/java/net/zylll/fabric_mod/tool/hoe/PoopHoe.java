package net.zylll.fabric_mod.tool.hoe;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.zylll.fabric_mod.registry.Items;

import java.util.Random;

public class PoopHoe extends HoeItem {
    public PoopHoe(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }
    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        Random random = new Random();
        if(random.nextFloat() - 0.9F > 0.0F){
            ((PlayerEntity)miner).giveItemStack(Items.POOP.getDefaultStack());}
        return super.postMine(stack, world, state, pos, miner);//10%获得poop
    }
}
