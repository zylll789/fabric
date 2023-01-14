package net.zylll.fabric_mod.tool.weapon;

import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.zylll.fabric_mod.registry.Items;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

public class PoopSword extends SwordItem {
    public PoopSword(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        return miner.isCreative();//创造执剑可破坏方块
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new TranslatableText("item.fabric_mod.poop_sword.tooltip"));
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        Random random = new Random();
        if(random.nextFloat() > 0.5F){
            target.dropStack(Items.POOP.getDefaultStack());
        }//50%概率掉poop
        World world = attacker.getWorld();
        world.createExplosion(attacker, attacker.getX(), attacker.getY(), attacker.getZ(), 1.0F, false, Explosion.DestructionType.NONE);
        return super.postHit(stack, target, attacker);//攻击爆炸
    }
}
