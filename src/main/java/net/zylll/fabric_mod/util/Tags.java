package net.zylll.fabric_mod.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static net.zylll.fabric_mod.FabricMod.makeID;

public class Tags {

    public static class Blocks {

        public static final TagKey<Block> DOWSING_ROD_TARGET = createTag("dowsing_rod_target");
        public static final TagKey<Block> POOP_ORE = createCommonTag("poop_ore");
        public static final TagKey<Block> POOP_LOGS = createTag("poop_logs");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(Registry.BLOCK_KEY, makeID(name));
        }

        private static TagKey<Block> createCommonTag(String name) {
            return TagKey.of(Registry.BLOCK_KEY, new Identifier("c", name));
        }

    }

    public static class Items {
        public static final TagKey<Item> POOP_ORE = createCommonTag("poop_ore");
        public static final TagKey<Item> POOP_LOGS = createTag("poop_logs");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(Registry.ITEM_KEY, makeID(name));
        }

        private static TagKey<Item> createCommonTag(String name) {
            return TagKey.of(Registry.ITEM_KEY, new Identifier("c", name));
        }

    }

}
