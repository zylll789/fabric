package net.zylll.fabric_mod;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.zylll.fabric_mod.registry.Blocks;
import net.zylll.fabric_mod.registry.Items;
import net.zylll.fabric_mod.tool.AllTools;

import static net.zylll.fabric_mod.FabricMod.makeID;

public class ItemGroups {
    public static final ItemGroup ITEMS = FabricItemGroupBuilder.build(makeID("items"), () -> new ItemStack(Items.POOP));
    public static final ItemGroup BLOCKS = FabricItemGroupBuilder.build(makeID("blocks"), () -> new ItemStack(Blocks.POOP_BLOCK));
    public static final ItemGroup TOOLS = FabricItemGroupBuilder.build(makeID("tools"), () -> new ItemStack(AllTools.POOP_SWORD));


}
