package net.zylll.fabric_mod.armor.material;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.zylll.fabric_mod.item.AllItems;

public class PoopArmorMaterial implements ArmorMaterial {

    private static final int[] PROTECTION = {1,2,2,1};

    @Override
    public int getDurability(EquipmentSlot slot) {
        return 30;//耐久
    }

    @Override
    public int getProtectionAmount(EquipmentSlot slot) {
        return PROTECTION[slot.getEntitySlotId()];//护甲
    }

    @Override
    public int getEnchantability() {
        return 9;//附魔能力
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_LEATHER;//穿戴音效
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(AllItems.POOP);//修理物品
    }

    @Override
    public String getName() {
        return "poop";//前缀名
    }

    @Override
    public float getToughness() {
        return 0.0F;//韧性
    }

    @Override
    public float getKnockbackResistance() {
        return 0.0F;//抗击退
    }
}
