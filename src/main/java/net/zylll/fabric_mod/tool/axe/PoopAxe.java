package net.zylll.fabric_mod.tool.axe;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.zylll.fabric_mod.item.AllItems;

public class PoopAxe extends AxeItem {
    public PoopAxe(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        state.getBlock().afterBreak(world,(PlayerEntity)miner,pos,state,null, AllItems.POOP_BLOCK_ITEM.getDefaultStack());
        return super.postMine(stack, world, state, pos, miner);
    }
}
