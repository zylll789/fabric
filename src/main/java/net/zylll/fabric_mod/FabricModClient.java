package net.zylll.fabric_mod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.zylll.fabric_mod.entity.AllEntities;
import net.zylll.fabric_mod.entity.model.PoopCubeEntityModel;
import net.zylll.fabric_mod.entity.randerer.PoopCubeRanderer;

import static net.zylll.fabric_mod.FabricMod.makeID;

@Environment(EnvType.CLIENT)
public class FabricModClient implements ClientModInitializer{
    public static final EntityModelLayer MODEL_POOP_CUBE_LAYER = new EntityModelLayer(makeID("poop_cube"), "main");

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(AllEntities.POOP_CUBE, PoopCubeRanderer::new);

        EntityModelLayerRegistry.registerModelLayer(MODEL_POOP_CUBE_LAYER, PoopCubeEntityModel::getTexturedModelData);
    }
}
