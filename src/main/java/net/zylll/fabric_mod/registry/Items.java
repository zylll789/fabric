package net.zylll.fabric_mod.registry;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.item.*;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import net.zylll.fabric_mod.ItemGroups;
import net.zylll.fabric_mod.armor.PoopArmorItem;
import net.zylll.fabric_mod.block.AllBlocks;
import net.zylll.fabric_mod.entity.AllEntities;
import net.zylll.fabric_mod.item.*;
import net.zylll.fabric_mod.tool.AllTools;

import static net.zylll.fabric_mod.FabricMod.makeID;

public class Items {
    //all items are in this
    //public static final Class NAME = new Class(new FabricItemSettings().group(ItemGroups.ITEMS));
    public static final Poop POOP =
            new Poop(new FabricItemSettings().group(ItemGroups.ITEMS).food(
                    new FoodComponent.Builder().hunger(1).meat().snack().saturationModifier(1f).alwaysEdible().statusEffect(
                            new StatusEffectInstance(StatusEffects.POISON, 20*15, 2),0.9F).build()).rarity(Rarity.UNCOMMON));
    public static Item POOP_FLUID_BUCKET;
    //block item
    public static final PoopBlockItem POOP_BLOCK_ITEM = new PoopBlockItem(AllBlocks.POOP_BLOCK, new FabricItemSettings().group(ItemGroups.BLOCKS));
    public static final PoopSlabItem POOP_SLAB_ITEM = new PoopSlabItem(AllBlocks.POOP_SLAB, new FabricItemSettings().group(ItemGroups.BLOCKS));
    public static final TrickBlockItem TRICK_BLOCK_ITEM = new TrickBlockItem(AllBlocks.TRICK_BLOCK, new FabricItemSettings().group(ItemGroups.BLOCKS));
    public static final OreChangedBlockItem ORE_CHANGED_BLOCK_ITEM = new OreChangedBlockItem(AllBlocks.ORE_CHANGED_BLOCK, new FabricItemSettings().group(ItemGroups.BLOCKS));
    public static final BreakBlockItem BREAK_BLOCK_ITEM = new BreakBlockItem(AllBlocks.BREAK_BLOCK, new FabricItemSettings().group(ItemGroups.BLOCKS));
    public static final PoopContainerItem POOP_CONTAINER_ITEM = new PoopContainerItem(AllBlocks.POOP_CONTAINER, new FabricItemSettings().group(ItemGroups.BLOCKS));
    public static final ClosestoolItem CLOSESTOOL_ITEM = new ClosestoolItem(AllBlocks.CLOSESTOOL, new FabricItemSettings().group(ItemGroups.BLOCKS));
    public static final PoopChestItem POOP_CHEST_ITEM = new PoopChestItem(AllBlocks.POOP_CHEST, new FabricItemSettings().group(ItemGroups.BLOCKS));
    //entity egg item
    public static final Item POOP_CUBE_SPAWN_EGG = new SpawnEggItem(AllEntities.POOP_CUBE, 12885043, 12428647, new FabricItemSettings().group(ItemGroups.ITEMS));


    public static void register(){
        //item
        register("poop", POOP);
        POOP_FLUID_BUCKET =  register("poop_fluid_bucket", Fluids.STILL_POOP_FLUID);
        //entity egg
        register("poop_cube_spawn_egg", POOP_CUBE_SPAWN_EGG);
        //block item
        register("poop_block", POOP_BLOCK_ITEM);
        register("poop_slab", POOP_SLAB_ITEM);
        register("trick_block", TRICK_BLOCK_ITEM);
        register("ore_changed_block", ORE_CHANGED_BLOCK_ITEM);
        register("break_block", BREAK_BLOCK_ITEM);
        register("poop_container", POOP_CONTAINER_ITEM);
        register("closestool", CLOSESTOOL_ITEM);
        register("poop_chest", POOP_CHEST_ITEM);
        //tool
        register("poop_sword", AllTools.POOP_SWORD);
        register("poop_axe", AllTools.POOP_AXE);
        register("poop_pickaxe", AllTools.POOP_PICKAXE);
        register("poop_shovel", AllTools.POOP_SHOVEL);
        register("poop_hoe", AllTools.POOP_HOE);
        //armor
        register("poop_helmet", PoopArmorItem.POOP_HELMET);
        register("poop_chestplate", PoopArmorItem.POOP_CHESTPLATE);
        register("poop_leggings", PoopArmorItem.POOP_LEGGINGS);
        register("poop_boots", PoopArmorItem.POOP_BOOTS);
    }

    private static void register(String id, Item item){
        Registry.register(Registry.ITEM, makeID(id), item);
    }

    public static void register(String id,BlockItem blockItem){
        Registry.register(Registry.ITEM, makeID(id), blockItem);
    }

    public static Item register(String id, FlowableFluid flowableFluid){
        return Registry.register(Registry.ITEM, makeID(id), new BucketItem(flowableFluid, new FabricItemSettings().group
                (ItemGroups.ITEMS).maxCount(1).recipeRemainder(net.minecraft.item.Items.BUCKET)));
    }

}
