package net.zylll.fabric_mod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.util.Identifier;
import net.zylll.fabric_mod.effect.WeightlessEffect;
import net.zylll.fabric_mod.registry.*;
import net.zylll.fabric_mod.util.LootTableModifiers;
import net.zylll.fabric_mod.world.dimension.Dimensions;
import net.zylll.fabric_mod.world.gen.WorldGen;
import net.zylll.fabric_mod.world.structures.Structures;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;

import java.util.Objects;


public class FabricMod implements ModInitializer {

    public static final String MOD_ID = "fabric_mod";
    public static final Logger LOGGER = LogManager.getLogger("fabric_mod");

    private static KeyBinding key;

    @Override
    public void onInitialize() {
        log("Start for Registering Fabric Mod!");
        Particles.register();
        Fluids.register();
        Sounds.register();
        Events.register();
        Blocks.register();
        Paintings.register();
        Items.register();
        Recipes.register();
        Fuels.register();
        Entities.register();
        Effects.register();
        Potions.register();
        Features.register();
        Biomes.register();
        Trades.register();
        Commands.register();

        WorldGen.generate();
        Structures.registerStructureFeatures();
        LootTableModifiers.modifyLootTable();

        Dimensions.register();

        Keys.register();
    }

    public static Identifier makeID(String id) {
        return new Identifier(MOD_ID, id);
    }

    public static void log(String s) {
        LOGGER.info(s);
    }

}
