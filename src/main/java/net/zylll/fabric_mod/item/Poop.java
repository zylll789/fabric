package net.zylll.fabric_mod.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.EndermiteEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
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
        user.dropStack(AllItems.POOP.getDefaultStack());
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

    /*@Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            double d = user.getX();
            double e = user.getY();
            double f = user.getZ();

            for(int i = 0; i < 16; ++i) {
                double g = user.getX() + (user.getRandom().nextDouble() - 0.5D) * 16.0D;
                double h = MathHelper.clamp(user.getY() + (double)(user.getRandom().nextInt(16) - 8), (double)world.getBottomY(), (double)(world.getBottomY() + ((ServerWorld)world).getLogicalHeight() - 1));
                double j = user.getZ() + (user.getRandom().nextDouble() - 0.5D) * 16.0D;
                if (user.hasVehicle()) {
                    user.stopRiding();
                }

                if (user.teleport(g, h, j, true)) {
                    SoundEvent soundEvent = Sounds.POOP_SOUND;
                    world.playSound((PlayerEntity)null, d, e, f, soundEvent, SoundCategory.PLAYERS, 1.0F, 1.0F);
                    user.playSound(soundEvent, 1.0F, 1.0F);
                    break;
                }
            }
            if (user.getRandom().nextFloat() < 0.05F && user.world.getGameRules().getBoolean(GameRules.DO_MOB_SPAWNING)) {
                EndermiteEntity endermiteEntity = (EndermiteEntity) EntityType.ENDERMITE.create(user.world);
                endermiteEntity.refreshPositionAndAngles(d, e, f, user.getYaw(), user.getPitch());
                user.world.spawnEntity(endermiteEntity);
            }
        }
        return TypedActionResult.success(user.getStackInHand(hand));
    }*/
}
