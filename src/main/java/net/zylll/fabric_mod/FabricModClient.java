package net.zylll.fabric_mod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;
import net.zylll.fabric_mod.block.entity.AllBlockEntities;
import net.zylll.fabric_mod.entity.AllEntities;
import net.zylll.fabric_mod.entity.model.PoopCubeEntityModel;
import net.zylll.fabric_mod.entity.randerer.PoopCubeRanderer;
import net.zylll.fabric_mod.registry.Blocks;
import net.zylll.fabric_mod.registry.Fluids;
import net.zylll.fabric_mod.registry.Particles;
import net.zylll.fabric_mod.screen.UIBlockScreen;

import static net.zylll.fabric_mod.FabricMod.makeID;

@Environment(EnvType.CLIENT)
public class FabricModClient implements ClientModInitializer {
    public static final EntityModelLayer MODEL_POOP_CUBE_LAYER = new EntityModelLayer(makeID("poop_cube"), "main");

    @Override
    public void onInitializeClient() {

        FabricMod.log("Start for Registering Client of Fabric Mod!");

        //screen handler
        ScreenRegistry.register(AllBlockEntities.UI_BLOCK_SCREEN_HANDLER, UIBlockScreen::new);

        Blocks.registerClient();

        //entity
        EntityRendererRegistry.register(AllEntities.POOP_CUBE, PoopCubeRanderer::new);
        EntityModelLayerRegistry.registerModelLayer(MODEL_POOP_CUBE_LAYER, PoopCubeEntityModel::getTexturedModelData);

        //fluid
        FluidRenderHandlerRegistry.INSTANCE.register(Fluids.STILL_POOP_FLUID, Fluids.FLOWING_POOP_FLUID, new SimpleFluidRenderHandler(
                new Identifier("minecraft:block/water_still"),
                new Identifier("minecraft:block/water_flow"),
                0xC49C33));
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), Fluids.STILL_POOP_FLUID, Fluids.FLOWING_POOP_FLUID);

        //particles
        ClientLifecycleEvents.CLIENT_STARTED.register(client -> {
            ClientSpriteRegistryCallback.event(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE).register((atlasTexture, registry) -> registry.register(makeID("particle/red_star")));
            ParticleFactoryRegistry.getInstance().register(Particles.RED_STAR, FlameParticle.Factory::new);
        });
    }
}
