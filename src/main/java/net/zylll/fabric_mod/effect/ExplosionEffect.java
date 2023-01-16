package net.zylll.fabric_mod.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.InstantStatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class ExplosionEffect extends InstantStatusEffect {
    public ExplosionEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0xFF0009);
    }

    @Override
    public boolean isInstant() {
        return true;
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        World world = entity.getWorld();
        world.createExplosion(entity, entity.getX(), entity.getY(), entity.getZ(), 2.0F * amplifier, false, Explosion.DestructionType.NONE);
        super.onApplied(entity, attributes, amplifier);
    }
}
