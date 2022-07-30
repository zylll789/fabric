package net.zylll.fabric_mod.tool;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.zylll.fabric_mod.ItemGroups;
import net.zylll.fabric_mod.tool.axe.PoopAxe;
import net.zylll.fabric_mod.tool.hoe.PoopHoe;
import net.zylll.fabric_mod.tool.pickaxe.PoopPickaxe;
import net.zylll.fabric_mod.tool.shovel.PoopShovel;
import net.zylll.fabric_mod.tool.weapon.PoopSword;

public class AllTools {
    //all tools are in this
    //
    public static final PoopSword POOP_SWORD = new PoopSword(new PoopMaterial(), 4,-2.0F, new FabricItemSettings().group(ItemGroups.TOOLS));
    public static final PoopAxe POOP_AXE = new PoopAxe(new PoopMaterial(), 5, -3.0F, new FabricItemSettings().group(ItemGroups.TOOLS));
    public static final PoopPickaxe POOP_PICKAXE = new PoopPickaxe(new PoopMaterial(), 3,-3.0F,new FabricItemSettings().group(ItemGroups.TOOLS));
    public static final PoopShovel POOP_SHOVEL = new PoopShovel(new PoopMaterial(), 3, -2.5F, new FabricItemSettings().group(ItemGroups.TOOLS));
    public static final PoopHoe POOP_HOE = new PoopHoe(new PoopMaterial(), 2, -1.0F, new FabricItemSettings().group(ItemGroups.TOOLS));

}
