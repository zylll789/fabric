package net.zylll.fabric_mod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.util.Rarity;
import net.zylll.fabric_mod.ItemGroups;
import net.zylll.fabric_mod.block.AllBlocks;

public class AllItems {
    //all items are in this
    //public static final Class NAME = new Class(new FabricItemSettings().group(ItemGroups.ITEMS));
    public static final Poop POOP =
            new Poop(new FabricItemSettings().group(ItemGroups.ITEMS).food(
                    new FoodComponent.Builder().hunger(1).meat().snack().saturationModifier(1f).alwaysEdible().statusEffect(
                            new StatusEffectInstance(StatusEffects.POISON, 20*15, 2),0.9F).build()).rarity(Rarity.UNCOMMON));
    public static final PoopBlockItem POOP_BLOCK_ITEM = new PoopBlockItem(AllBlocks.POOP_BLOCK, new FabricItemSettings().group(ItemGroups.BLOCKS));
    public static final PoopSlabItem POOP_SLAB_ITEM = new PoopSlabItem(AllBlocks.POOP_SLAB, new FabricItemSettings().group(ItemGroups.BLOCKS));
    public static final TrickBlockItem TRICK_BLOCK_ITEM = new TrickBlockItem(AllBlocks.TRICK_BLOCK, new FabricItemSettings().group(ItemGroups.BLOCKS));
}
