package net.zylll.fabric_mod;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.zylll.fabric_mod.registry.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class FabricMod implements ModInitializer {

    public static final String MOD_ID = "fabric_mod";
    public static final Logger LOGGER = LogManager.getLogger("modid");

    @Override
    public void onInitialize() {
        Fluids.register();
        Sounds.register();
        Blocks.register();
        Items.register();
        Recipes.register();
        Fuels.register();
        Entities.register();
        Features.register();
        Effects.register();
        Potions.register();
        Commands.register();
        Trades.register();
    }

    public static Identifier makeID(String id) {
        return new Identifier(MOD_ID, id);
    }

    public static void log(String s){
        LOGGER.info(s);
    }

}
