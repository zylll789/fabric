package net.zylll.fabric_mod.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;

public class AllBlocks {
    //all blocks are in this
    //
    public static final PoopBlock POOP_BLOCK = new PoopBlock(FabricBlockSettings.of(Material.SNOW_BLOCK).strength
            (0.2F,0.1F).sounds(BlockSoundGroup.SNOW).jumpVelocityMultiplier
            (0.8F).velocityMultiplier(0.8F).mapColor(MapColor.YELLOW).allowsSpawning(PoopBlock::canSpawn).requiresTool());
    public static final PoopSlab POOP_SLAB = new PoopSlab(FabricBlockSettings.of(Material.SNOW_BLOCK).strength
            (0.2F,0.1F).sounds(BlockSoundGroup.SNOW).jumpVelocityMultiplier
            (0.8F).velocityMultiplier(0.8F).mapColor(MapColor.YELLOW).allowsSpawning(PoopSlab::canSpawn).requiresTool().nonOpaque());
    public static final TrickBlock TRICK_BLOCK = new TrickBlock(FabricBlockSettings.of(Material.METAL).strength
            (5.0F, 6.0F).sounds(BlockSoundGroup.METAL).mapColor(MapColor.DIAMOND_BLUE).requiresTool());
    public static final OreChangedBlock ORE_CHANGED_BLOCK = new OreChangedBlock(FabricBlockSettings.of(Material.STONE).strength
            (1.0F, 1.0F).requiresTool());
    public static final BreakBlock BREAK_BLOCK = new BreakBlock(AbstractBlock.Settings.of(Material.STONE).strength
            (2.0F, 1.0F).requiresTool());
    public static final PoopContainer POOP_CONTAINER = new PoopContainer(AbstractBlock.Settings.of(Material.STONE).strength
            (2.5F, 0.5F).requiresTool().nonOpaque());
    public static final Closestool CLOSESTOOL = new Closestool(FabricBlockSettings.of(Material.SOLID_ORGANIC).strength
            (1.0F, 1.0F).sounds(BlockSoundGroup.STONE).mapColor(MapColor.OFF_WHITE).nonOpaque());
    public static final PoopChest POOP_CHEST = new PoopChest(FabricBlockSettings.of(Material.WOOD).strength
            (2.5F, 0.5F).sounds(BlockSoundGroup.STONE).mapColor(MapColor.GRAY).requiresTool().nonOpaque());
}
