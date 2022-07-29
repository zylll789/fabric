package net.zylll.fabric_mod;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.zylll.fabric_mod.block.AllBlocks;
import net.zylll.fabric_mod.item.AllItems;

import static net.zylll.fabric_mod.FabricMod.MOD_ID;

public class ItemGroups {
    public static final ItemGroup ITEMS = FabricItemGroupBuilder.build(new Identifier(MOD_ID, "items"),() -> new ItemStack(AllItems.POOP));
    public static final ItemGroup BLOCKS = FabricItemGroupBuilder.build(new Identifier(MOD_ID, "blocks"),() -> new ItemStack(AllBlocks.POOP_BLOCK));
}
