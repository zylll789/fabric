package net.zylll.fabric_mod.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;
import net.zylll.fabric_mod.registry.Items;
import net.zylll.fabric_mod.registry.Sounds;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Poop extends Item {
    public Poop(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new TranslatableText("item.fabric_mod.poop.tooltip"));
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        user.dropStack(Items.POOP.getDefaultStack());
        user.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 20*10, 3));
        return super.finishUsing(stack, world, user);
    }

    @Override
    public void onCraft(ItemStack stack, World world, PlayerEntity player) {
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 20*5, 1));
        super.onCraft(stack, world, player);
    }

    @Override
    public SoundEvent getEatSound() {
        return Sounds.POOP_EAT_SOUND;
    }

}
