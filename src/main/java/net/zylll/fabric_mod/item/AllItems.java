package net.zylll.fabric_mod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Rarity;
import net.zylll.fabric_mod.ItemGroups;
import net.zylll.fabric_mod.block.AllBlocks;
import net.zylll.fabric_mod.entity.AllEntities;
import net.zylll.fabric_mod.entity.PoopCube;

public class AllItems {
    //all items are in this
    //public static final Class NAME = new Class(new FabricItemSettings().group(ItemGroups.ITEMS));
    public static final Poop POOP =
            new Poop(new FabricItemSettings().group(ItemGroups.ITEMS).food(
                    new FoodComponent.Builder().hunger(1).meat().snack().saturationModifier(1f).alwaysEdible().statusEffect(
                            new StatusEffectInstance(StatusEffects.POISON, 20*15, 2),0.9F).build()).rarity(Rarity.UNCOMMON));
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
}
