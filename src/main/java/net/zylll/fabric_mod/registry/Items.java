package net.zylll.fabric_mod.registry;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.item.*;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import net.zylll.fabric_mod.FabricMod;
import net.zylll.fabric_mod.ItemGroups;
import net.zylll.fabric_mod.armor.PoopArmorItem;
import net.zylll.fabric_mod.item.*;
import net.zylll.fabric_mod.tool.AllTools;
import net.zylll.fabric_mod.util.ModelPredicateProvider;

import static net.zylll.fabric_mod.FabricMod.makeID;

public class Items {
    //all items are in this
    //public static final Class NAME = new Class(new FabricItemSettings().group(ItemGroups.ITEMS));
    public static final Poop POOP =
            new Poop(new FabricItemSettings().group(ItemGroups.ITEMS).food(
                    new FoodComponent.Builder().hunger(1).meat().snack().saturationModifier(1f).alwaysEdible().statusEffect(
                            new StatusEffectInstance(StatusEffects.POISON, 20 * 15, 2), 0.9F).build()).rarity(Rarity.UNCOMMON));
    public static final DowsingRodItem DOWSING_ROD = new DowsingRodItem(new FabricItemSettings().group(ItemGroups.TOOLS).rarity(Rarity.EPIC).maxCount(1).maxDamage(10));
    public static Item POOP_FLUID_BUCKET;
    public static CopyItem COPY_ITEM = new CopyItem(new FabricItemSettings().group(ItemGroups.ITEMS).rarity(Rarity.RARE).maxCount(1));
    public static FinishedBookItem FINISHED_BOOK_ITEM = new FinishedBookItem(new FabricItemSettings().group(ItemGroups.ITEMS).maxCount(1));
    public static SilkBag SILK_BAG = new SilkBag(new FabricItemSettings().group(ItemGroups.ITEMS).maxCount(1));
    public static final MusicDisc DISC_POOP_SOUND = new MusicDisc(3, Sounds.POOP_SOUND, new FabricItemSettings().group(ItemGroups.ITEMS));
    //block item
    public static final PoopBlockItem POOP_BLOCK_ITEM = new PoopBlockItem(Blocks.POOP_BLOCK, new FabricItemSettings().group(ItemGroups.BLOCKS));
    public static final PoopSlabItem POOP_SLAB_ITEM = new PoopSlabItem(Blocks.POOP_SLAB, new FabricItemSettings().group(ItemGroups.BLOCKS));
    public static final TrickBlockItem TRICK_BLOCK_ITEM = new TrickBlockItem(Blocks.TRICK_BLOCK, new FabricItemSettings().group(ItemGroups.BLOCKS));
    public static final OreChangedBlockItem ORE_CHANGED_BLOCK_ITEM = new OreChangedBlockItem(Blocks.ORE_CHANGED_BLOCK, new FabricItemSettings().group(ItemGroups.BLOCKS));
    public static final BreakBlockItem BREAK_BLOCK_ITEM = new BreakBlockItem(Blocks.BREAK_BLOCK, new FabricItemSettings().group(ItemGroups.BLOCKS));
    public static final PoopContainerItem POOP_CONTAINER_ITEM = new PoopContainerItem(Blocks.POOP_CONTAINER, new FabricItemSettings().group(ItemGroups.BLOCKS));
    public static final ClosestoolItem CLOSESTOOL_ITEM = new ClosestoolItem(Blocks.CLOSESTOOL, new FabricItemSettings().group(ItemGroups.BLOCKS));
    public static final PoopChestItem POOP_CHEST_ITEM = new PoopChestItem(Blocks.POOP_CHEST, new FabricItemSettings().group(ItemGroups.BLOCKS));
    public static final UIBlockItem UI_BLOCK_ITEM = new UIBlockItem(Blocks.UI_BLOCK, new FabricItemSettings().group(ItemGroups.BLOCKS));
    public static final PoopGlassItem POOP_GLASS_ITEM = new PoopGlassItem(Blocks.POOP_GLASS, new FabricItemSettings().group(ItemGroups.BLOCKS));

    public static final BlockItem POOP_LOG_ITEM = new BlockItem(Blocks.POOP_LOG, new FabricItemSettings().group(ItemGroups.BLOCKS));
    public static final BlockItem POOP_WOOD_ITEM = new BlockItem(Blocks.POOP_WOOD, new FabricItemSettings().group(ItemGroups.BLOCKS));
    public static final BlockItem STRIPPED_POOP_LOG_ITEM = new BlockItem(Blocks.STRIPPED_POOP_LOG, new FabricItemSettings().group(ItemGroups.BLOCKS));
    public static final BlockItem STRIPPED_POOP_WOOD_ITEM = new BlockItem(Blocks.STRIPPED_POOP_WOOD, new FabricItemSettings().group(ItemGroups.BLOCKS));
    public static final BlockItem POOP_LEAVES_ITEM = new BlockItem(Blocks.POOP_LEAVES, new FabricItemSettings().group(ItemGroups.BLOCKS));
    public static final BlockItem POOP_SAPLING_ITEM = new BlockItem(Blocks.POOP_SAPLING, new FabricItemSettings().group(ItemGroups.BLOCKS));
    public static final BlockItem POOP_PLANKS_ITEM = new BlockItem(Blocks.POOP_PLANKS, new FabricItemSettings().group(ItemGroups.BLOCKS));
    //crop
    public static final Item POOP_CROP_SEED = new AliasedBlockItem(Blocks.POOP_CROP, new FabricItemSettings().group(ItemGroups.ITEMS));
    //flower
    public static final PoopFlowerItem POOP_FLOWER_ITEM = new PoopFlowerItem(Blocks.POOP_FLOWER, new FabricItemSettings().group(ItemGroups.BLOCKS));
    //entity egg item
    public static final Item POOP_CUBE_SPAWN_EGG = new SpawnEggItem(Entities.POOP_CUBE, 12885043, 12428647, new FabricItemSettings().group(ItemGroups.ITEMS));
    public static final Item Y_SLIME_SPAWN_EGG = new SpawnEggItem(Entities.Y_SLIME, 0xFF0009, 0x56FF1D, new FabricItemSettings().group(ItemGroups.ITEMS));


    public static void register() {
        FabricMod.log("Register Items for + " + FabricMod.MOD_ID);
        //item
        register("poop", POOP);
        register("dowsing_rod", DOWSING_ROD);
        register("copy_item", COPY_ITEM);
        register("finished_book", FINISHED_BOOK_ITEM);
        register("silk_bag", SILK_BAG);
        register("poop_sound_disc", DISC_POOP_SOUND);
        POOP_FLUID_BUCKET = register("poop_fluid_bucket", Fluids.STILL_POOP_FLUID);
        //entity egg
        register("poop_cube_spawn_egg", POOP_CUBE_SPAWN_EGG);
        register("y_slime_spawn_egg", Y_SLIME_SPAWN_EGG);
        //block item
        register("poop_block", POOP_BLOCK_ITEM);
        register("poop_slab", POOP_SLAB_ITEM);
        register("trick_block", TRICK_BLOCK_ITEM);
        register("ore_changed_block", ORE_CHANGED_BLOCK_ITEM);
        register("break_block", BREAK_BLOCK_ITEM);
        register("poop_container", POOP_CONTAINER_ITEM);
        register("closestool", CLOSESTOOL_ITEM);
        register("poop_chest", POOP_CHEST_ITEM);
        register("ui_block", UI_BLOCK_ITEM);
        register("poop_glass", POOP_GLASS_ITEM);

        register("poop_log", POOP_LOG_ITEM);
        register("poop_wood", POOP_WOOD_ITEM);
        register("stripped_poop_log", STRIPPED_POOP_LOG_ITEM);
        register("stripped_poop_wood", STRIPPED_POOP_WOOD_ITEM);
        register("poop_leaves", POOP_LEAVES_ITEM);
        register("poop_sapling", POOP_SAPLING_ITEM);
        register("poop_planks", POOP_PLANKS_ITEM);
        //crop
        register("poop_crop_seed", POOP_CROP_SEED);
        //flower
        register("poop_flower", POOP_FLOWER_ITEM);
        //tool
        register("poop_sword", AllTools.POOP_SWORD);
        register("poop_axe", AllTools.POOP_AXE);
        register("poop_pickaxe", AllTools.POOP_PICKAXE);
        register("poop_shovel", AllTools.POOP_SHOVEL);
        register("poop_hoe", AllTools.POOP_HOE);
        register("plunger", AllTools.PLUNGER);
        register("poop_bow", AllTools.POOP_BOW);
        //armor
        register("poop_helmet", PoopArmorItem.POOP_HELMET);
        register("poop_chestplate", PoopArmorItem.POOP_CHESTPLATE);
        register("poop_leggings", PoopArmorItem.POOP_LEGGINGS);
        register("poop_boots", PoopArmorItem.POOP_BOOTS);
    }

    public static void registerClient() {
        ModelPredicateProvider.registerBow(AllTools.POOP_BOW);
    }

    private static void register(String id, Item item) {
        Registry.register(Registry.ITEM, makeID(id), item);
    }

    public static void register(String id, BlockItem blockItem) {
        Registry.register(Registry.ITEM, makeID(id), blockItem);
    }

    public static Item register(String id, FlowableFluid flowableFluid) {
        return Registry.register(Registry.ITEM, makeID(id), new BucketItem(flowableFluid, new FabricItemSettings().group
                (ItemGroups.ITEMS).maxCount(1).recipeRemainder(net.minecraft.item.Items.BUCKET)));
    }

}
