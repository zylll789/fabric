package net.zylll.fabric_mod.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.zylll.fabric_mod.util.IEntityDataSaver;

public class WeightlessEffect extends StatusEffect {
    public WeightlessEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        super.onApplied(entity, attributes, amplifier);
        if (entity instanceof PlayerEntity playerEntity) {
            //playerEntity.setNoGravity(false);
            IEntityDataSaver player = (IEntityDataSaver) playerEntity;
            player.getWeightless().putBoolean("isWeightless", false);
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity instanceof PlayerEntity playerEntity) {
            IEntityDataSaver player = (IEntityDataSaver) playerEntity;
            boolean isWeightless = player.getWeightless().getBoolean("isWeightless");
            if (isWeightless) {
                //playerEntity.setNoGravity(true);
                Vec3d velocity = playerEntity.getVelocity();
                playerEntity.setVelocity(velocity.x, 0.4f, velocity.z);
            } else {
                //playerEntity.setNoGravity(false);
            }
        }
        super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        super.onRemoved(entity, attributes, amplifier);
        if (entity instanceof PlayerEntity playerEntity) {
            IEntityDataSaver player = (IEntityDataSaver) playerEntity;
            player.getWeightless().putBoolean("isWeightless", false);
        }
        //entity.setNoGravity(false);
    }

}
