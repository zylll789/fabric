package net.zylll.fabric_mod.util;

import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;
import net.zylll.fabric_mod.registry.Items;

public class LootTableModifiers {

    private static final Identifier IGLOO_STRUCTURE_CHEST_ID = new Identifier("minecraft", "chests/igloo_chest");

    public static void modifyLootTable() {
        LootTableLoadingCallback.EVENT.register(((resourceManager, manager, id, supplier, setter) -> {
            if(IGLOO_STRUCTURE_CHEST_ID.equals(id)){
                LootPool.Builder builder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(1f))
                        .with(ItemEntry.builder(Items.DOWSING_ROD))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,1.0f)).build());
                supplier.withPool(builder.build());
            }
        }));
    }

}
