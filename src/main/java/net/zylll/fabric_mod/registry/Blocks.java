package net.zylll.fabric_mod.registry;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.registry.Registry;
import net.zylll.fabric_mod.FabricMod;
import net.zylll.fabric_mod.block.*;
import net.zylll.fabric_mod.world.feature.tree.PoopSaplingGenerator;

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
    public static final PoopGlass POOP_GLASS = new PoopGlass(FabricBlockSettings.copy(net.minecraft.block.Blocks.GLASS)
            .strength(3.0f).nonOpaque());

    public static final Block POOP_LOG = new PillarBlock(FabricBlockSettings.copy(net.minecraft.block.Blocks.OAK_LOG).strength(4.0f));
    public static final Block POOP_WOOD = new PillarBlock(FabricBlockSettings.copy(net.minecraft.block.Blocks.OAK_WOOD).strength(4.0f));
    public static final Block STRIPPED_POOP_LOG = new PillarBlock(FabricBlockSettings.copy(net.minecraft.block.Blocks.STRIPPED_OAK_LOG).strength(4.0f));
    public static final Block STRIPPED_POOP_WOOD = new PillarBlock(FabricBlockSettings.copy(net.minecraft.block.Blocks.STRIPPED_OAK_WOOD).strength(4.0f));
    public static final Block POOP_LEAVES = new LeavesBlock(FabricBlockSettings.copy(net.minecraft.block.Blocks.OAK_LEAVES));
    public static final PoopSapling POOP_SAPLING = new PoopSapling(new PoopSaplingGenerator(),
            FabricBlockSettings.copy(net.minecraft.block.Blocks.OAK_SAPLING).nonOpaque().breakInstantly());

    public static final Block POOP_PLANKS = new Block(FabricBlockSettings.copy(net.minecraft.block.Blocks.OAK_PLANKS).strength(4.0f));
    //crop
    public static final PoopCrop POOP_CROP = new PoopCrop(FabricBlockSettings.copy(net.minecraft.block.Blocks.WHEAT).nonOpaque());
    //flower
    public static final PoopFlower POOP_FLOWER = new PoopFlower(StatusEffects.NAUSEA, 12,
            FabricBlockSettings.copy(net.minecraft.block.Blocks.DANDELION).nonOpaque().strength(4.0f).breakInstantly());
    public static final Block POTTED_POOP_FLOWER = new FlowerPotBlock(POOP_FLOWER,
            FabricBlockSettings.copy(net.minecraft.block.Blocks.POTTED_ALLIUM).nonOpaque());

    //fluid block
    public static Block POOP_FLUID;

    public static void register() {
        FabricMod.log("Registering Blocks for + " + FabricMod.MOD_ID);
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
        register("poop_glass", POOP_GLASS);

        register("poop_log", POOP_LOG);
        register("poop_wood", POOP_WOOD);
        register("stripped_poop_log", STRIPPED_POOP_LOG);
        register("stripped_poop_wood", STRIPPED_POOP_WOOD);
        register("poop_leaves", POOP_LEAVES);
        register("poop_sapling", POOP_SAPLING);
        register("poop_planks", POOP_PLANKS);
        //crop
        register("poop_crop", POOP_CROP);
        //flower
        register("poop_flower", POOP_FLOWER);
        register("potted_poop_flower", POTTED_POOP_FLOWER);
        //fluid block
        POOP_FLUID = register("poop_fluid", Fluids.STILL_POOP_FLUID);

        registerFlammableBlock();
        registerStrippableBlock();
    }

    public static void registerClient() {
        FabricMod.log("Register Blocks Client for + " + FabricMod.MOD_ID);
        BlockRenderLayerMap.INSTANCE.putBlock(POOP_FLOWER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(POTTED_POOP_FLOWER, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(POOP_CROP, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(POOP_GLASS, RenderLayer.getTranslucent());

        BlockRenderLayerMap.INSTANCE.putBlock(POOP_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(POOP_LEAVES, RenderLayer.getCutout());
    }

    private static void registerFlammableBlock() {
        FlammableBlockRegistry instance = FlammableBlockRegistry.getDefaultInstance();
        instance.add(POOP_LOG, 5, 5);
        instance.add(POOP_WOOD, 5, 5);
        instance.add(STRIPPED_POOP_LOG, 5, 5);
        instance.add(STRIPPED_POOP_WOOD, 5, 5);
        instance.add(POOP_PLANKS, 5, 20);
        instance.add(POOP_LEAVES, 30, 60);
    }

    private static void registerStrippableBlock() {
        StrippableBlockRegistry.register(POOP_LOG, STRIPPED_POOP_LOG);
        StrippableBlockRegistry.register(POOP_WOOD, STRIPPED_POOP_WOOD);
    }

    private static void register(String id, Block block) {
        Registry.register(Registry.BLOCK, makeID(id), block);
    }

    private static Block register(String id, FlowableFluid flowableFluid) {
        return Registry.register(Registry.BLOCK, makeID(id), new FluidBlock(flowableFluid, FabricBlockSettings.copy(net.minecraft.block.Blocks.WATER)));
    }

}
