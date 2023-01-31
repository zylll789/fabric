package net.zylll.fabric_mod.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public class ThunderSwordEnchantment extends Enchantment {
    public ThunderSwordEnchantment(Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
    }

    @Override
    public int getMinPower(int level) {
        return 1;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if (user instanceof PlayerEntity playerEntity && target instanceof LivingEntity livingEntity) {
            if (!playerEntity.getWorld().isClient) {
                ServerWorld world = (ServerWorld) playerEntity.getWorld();
                BlockPos pos = livingEntity.getBlockPos();
                EntityType.LIGHTNING_BOLT.spawn(world, null, null, null, pos, null, false, false);
            }
        }
        super.onTargetDamaged(user, target, level);
    }
}
