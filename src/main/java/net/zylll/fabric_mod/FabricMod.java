package net.zylll.fabric_mod;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.zylll.fabric_mod.registry.Blocks;
import net.zylll.fabric_mod.registry.Fuels;
import net.zylll.fabric_mod.registry.Items;
import net.zylll.fabric_mod.registry.Sounds;

public class FabricMod implements ModInitializer {

	public static final String MOD_ID = "fabric_mod";

	@Override
	public void onInitialize() {
		Sounds.register();
		Blocks.register();
		Items.register();
		Fuels.register();
	}

	public static Identifier makeID(String id){
		return new Identifier(MOD_ID, id);
	}

}
