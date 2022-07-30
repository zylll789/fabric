package net.zylll.fabric_mod.armor;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.zylll.fabric_mod.ItemGroups;
import net.zylll.fabric_mod.armor.material.PoopArmorMaterial;

public class PoopArmorItem extends ArmorItem {
    public PoopArmorItem(ArmorMaterial material, EquipmentSlot slot, Settings settings) {
        super(material, slot, settings);
    }
    public static final ArmorMaterial POOP_MATERIAL = new PoopArmorMaterial();
    public static final PoopArmorItem POOP_HELMET = new PoopArmorItem(POOP_MATERIAL, EquipmentSlot.HEAD, new FabricItemSettings().group(ItemGroups.TOOLS));
    public static final PoopArmorItem POOP_CHEST = new PoopArmorItem(POOP_MATERIAL, EquipmentSlot.CHEST, new FabricItemSettings().group(ItemGroups.TOOLS));
    public static final PoopArmorItem POOP_LEGGINGS = new PoopArmorItem(POOP_MATERIAL, EquipmentSlot.LEGS, new FabricItemSettings().group(ItemGroups.TOOLS));
    public static final PoopArmorItem POOP_BOOTS = new PoopArmorItem(POOP_MATERIAL, EquipmentSlot.FEET, new FabricItemSettings().group(ItemGroups.TOOLS));
}
