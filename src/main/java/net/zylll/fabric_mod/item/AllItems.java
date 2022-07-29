package net.zylll.fabric_mod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.zylll.fabric_mod.ItemGroups;
import net.zylll.fabric_mod.block.AllBlocks;

public class AllItems {
    //all items are in this
    //public static final Class NAME = new Class(new FabricItemSettings().group(ItemGroups.ITEMS));
    public static final Poop POOP = new Poop(new FabricItemSettings().group(ItemGroups.ITEMS));
    public static final PoopBlockItem POOP_BLOCK_ITEM = new PoopBlockItem(AllBlocks.POOP_BLOCK, new FabricItemSettings().group(ItemGroups.BLOCKS));
}
