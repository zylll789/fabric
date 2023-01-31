package net.zylll.fabric_mod.registry;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.registry.Registry;
import net.zylll.fabric_mod.FabricMod;
import net.zylll.fabric_mod.enchantment.ThunderSwordEnchantment;

import static net.zylll.fabric_mod.FabricMod.makeID;

public class Enchantments {

    public static final Enchantment THUNDER_SWORD_ENCHANTMENT = register("thunder_sword_enchantment",
            new ThunderSwordEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));

    public static void register(){
        FabricMod.log("Registering Enchantments for + " + FabricMod.MOD_ID);
    }

    private static Enchantment register(String id,Enchantment enchantment){
        return Registry.register(Registry.ENCHANTMENT, makeID(id), enchantment);
    }

}
