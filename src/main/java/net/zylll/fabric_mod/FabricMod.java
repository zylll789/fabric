package net.zylll.fabric_mod;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.zylll.fabric_mod.registry.*;

public class FabricMod implements ModInitializer {

	public static final String MOD_ID = "fabric_mod";

	@Override
	public void onInitialize() {
		Fluids.register();
		Sounds.register();
		Blocks.register();
		Items.register();
		Fuels.register();
		Entities.register();
		Features.register();
		Commands.register();
		Recipes.register();
	}

	public static Identifier makeID(String id){
		return new Identifier(MOD_ID, id);
	}

}
