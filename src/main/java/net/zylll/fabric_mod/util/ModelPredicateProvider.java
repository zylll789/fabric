package net.zylll.fabric_mod.util;

import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class ModelPredicateProvider {

    public static void registerBow(Item bow) {
        FabricModelPredicateProviderRegistry.register(bow, new Identifier("pull"),
                ((stack, world, entity, seed) -> {
                    if (entity == null) {
                        return 0;
                    }
                    if (entity.getActiveItem() != stack) {
                        return 0;
                    }
                    return (float) (stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / 20.0f;
                }));
        FabricModelPredicateProviderRegistry.register(bow, new Identifier("pulling"), ((stack, world, entity, seed) ->
                entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0f : 0.0f));
    }

}
