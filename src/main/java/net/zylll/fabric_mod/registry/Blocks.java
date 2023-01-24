package net.zylll.fabric_mod.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.registry.Registry;
import net.zylll.fabric_mod.block.*;

import static net.zylll.fabric_mod.FabricMod.makeID;

public class Blocks {

    //all blocks are in this
    //
    public static final PoopBlock POOP_BLOCK = new PoopBlock(FabricBlockSettings.of(Material.SNOW_BLOCK).strength
            (0.2F, 0.1F).sounds(BlockSoundGroup.SNOW).jumpVelocityMultiplier
            (0.8F).velocityMultiplier(0.8F).mapColor(MapColor.YELLOW).allowsSpawning(PoopBlock::canSpawn).requiresTool());
    public static final PoopSlab POOP_SLAB = new PoopSlab(FabricBlockSettings.of(Material.SNOW_BLOCK).strength
            (0.2F, 0.1F).sounds(BlockSoundGroup.SNOW).jumpVelocityMultiplier
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
    public static final UIBlock UI_BLOCK = new UIBlock(FabricBlockSettings.of(Material.SNOW_BLOCK).strength
            (1.0F, 1.0F).sounds(BlockSoundGroup.SNOW).mapColor(MapColor.GRAY).requiresTool());

    //flower
    public static final PoopFlower POOP_FLOWER = new PoopFlower(StatusEffects.FIRE_RESISTANCE, 12,
            FabricBlockSettings.of(Material.PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS));
    //fluid block
    public static Block POOP_FLUID;

    public static void register() {
        //block
        register("poop_block", POOP_BLOCK);
        register("poop_slab", POOP_SLAB);
        register("trick_block", TRICK_BLOCK);
        register("ore_changed_block", ORE_CHANGED_BLOCK);
        register("break_block", BREAK_BLOCK);
        register("poop_container", POOP_CONTAINER);
        register("closestool", CLOSESTOOL);
        register("poop_chest", POOP_CHEST);
        register("ui_block", UI_BLOCK);
        //flower
        register("poop_flower",POOP_FLOWER);
        //fluid block
        POOP_FLUID = register("poop_fluid", Fluids.STILL_POOP_FLUID);
    }

    private static void register(String id, Block block) {
        Registry.register(Registry.BLOCK, makeID(id), block);
    }

    private static Block register(String id, FlowableFluid flowableFluid) {
        return Registry.register(Registry.BLOCK, makeID(id), new FluidBlock(flowableFluid, FabricBlockSettings.copy(net.minecraft.block.Blocks.WATER)));
    }

}
