package net.zylll.fabric_mod.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;

public class AllBlocks {
    //all blocks are in this
    //
    public static final PoopBlock POOP_BLOCK = new PoopBlock(FabricBlockSettings.of(Material.SNOW_BLOCK).strength
            (0.2F,0.1F).sounds(BlockSoundGroup.SNOW).jumpVelocityMultiplier
            (0.8F).velocityMultiplier(0.8F).mapColor(MapColor.YELLOW).allowsSpawning(PoopBlock::canSpawn));


}
