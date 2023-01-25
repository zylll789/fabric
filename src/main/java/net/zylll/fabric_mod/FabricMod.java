package net.zylll.fabric_mod;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.zylll.fabric_mod.registry.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class FabricMod implements ModInitializer {

    public static final String MOD_ID = "fabric_mod";
    private static final Logger LOGGER = LogManager.getLogger("fabric_mod");

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
    }

    public static Identifier makeID(String id) {
        return new Identifier(MOD_ID, id);
    }

    public static void log(String s){
        LOGGER.info(s);
    }

}
