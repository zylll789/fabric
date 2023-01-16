package net.zylll.fabric_mod.registry;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.registry.Registry;
import net.zylll.fabric_mod.effect.EpinephrineEffect;

import static net.zylll.fabric_mod.FabricMod.makeID;

public class Effects {

    public static final EpinephrineEffect EPINEPHRINE_EFFECT = (EpinephrineEffect) new EpinephrineEffect()
            .addAttributeModifier(EntityAttributes.GENERIC_MAX_HEALTH, "EAFAC3CD-0E7A-6632-B78C-2692F31D767C", 20, EntityAttributeModifier.Operation.ADDITION);

    public static void register() {
        register("epinephrine_effect", EPINEPHRINE_EFFECT);
    }

    private static void register(String id, StatusEffect entry) {
        Registry.register(Registry.STATUS_EFFECT, makeID(id), entry);
    }
}
