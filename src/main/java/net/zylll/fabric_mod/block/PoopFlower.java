package net.zylll.fabric_mod.block;

import net.minecraft.block.FlowerBlock;
import net.minecraft.entity.effect.StatusEffect;

public class PoopFlower extends FlowerBlock {
    public PoopFlower(StatusEffect suspiciousStewEffect, int effectDuration, Settings settings) {
        super(suspiciousStewEffect, effectDuration, settings);
    }
}
