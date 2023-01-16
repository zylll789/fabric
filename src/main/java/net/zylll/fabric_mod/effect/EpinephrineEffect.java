package net.zylll.fabric_mod.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;

public class EpinephrineEffect extends StatusEffect {
    public EpinephrineEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0xFF40FC);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return false;
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        if (entity instanceof PlayerEntity playerEntity) {
            playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 1, 2));
            playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 1 * 20, 3));
            playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 30 * 20, 2, false, false));
            playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 20 * 20, 2, false, false));
        }
        super.onApplied(entity, attributes, amplifier);
    }

    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        if (entity instanceof PlayerEntity playerEntity) {
            playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 60 * 20, 1, false, false));
            playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 10 * 20, 1, false, false));
            playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 60 * 20, 3, false, false));
            playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 10 * 20, 2, false, false));
            if (playerEntity.getHealth() > playerEntity.getMaxHealth()) {
                playerEntity.setHealth(playerEntity.getMaxHealth());
            }
        }
        super.onRemoved(entity, attributes, amplifier);
    }
}
