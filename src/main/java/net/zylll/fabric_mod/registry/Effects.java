package net.zylll.fabric_mod.registry;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.registry.Registry;
import net.zylll.fabric_mod.FabricMod;
import net.zylll.fabric_mod.effect.EpinephrineEffect;
import net.zylll.fabric_mod.effect.ExplosionEffect;
import net.zylll.fabric_mod.effect.FreezeEffect;
import net.zylll.fabric_mod.effect.WeightlessEffect;

import static net.zylll.fabric_mod.FabricMod.makeID;

public class Effects {

    public static final EpinephrineEffect EPINEPHRINE_EFFECT = (EpinephrineEffect) new EpinephrineEffect()
            .addAttributeModifier(EntityAttributes.GENERIC_MAX_HEALTH, "EAFAC3CD-0E7A-6632-B78C-2692F31D767C", 20, EntityAttributeModifier.Operation.ADDITION);
    public static final ExplosionEffect EXPLOSION_EFFECT = new ExplosionEffect();
    public static final FreezeEffect FREEZE_EFFECT = new FreezeEffect(StatusEffectCategory.HARMFUL, 0x4371FF);
    public static final WeightlessEffect WEIGHTLESS_EFFECT = new WeightlessEffect(StatusEffectCategory.BENEFICIAL, 0xFFAFC2);

    public static void register() {
        FabricMod.log("Register Effects for + " + FabricMod.MOD_ID);
        register("epinephrine_effect", EPINEPHRINE_EFFECT);
        register("explosion_effect", EXPLOSION_EFFECT);
        register("freeze_effect", FREEZE_EFFECT);
        register("weightless_effect", WEIGHTLESS_EFFECT);
    }

    private static void register(String id, StatusEffect entry) {
        Registry.register(Registry.STATUS_EFFECT, makeID(id), entry);
    }
}
