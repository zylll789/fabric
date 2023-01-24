package net.zylll.fabric_mod.registry;

import net.minecraft.entity.decoration.painting.PaintingMotive;
import net.minecraft.util.registry.Registry;
import net.zylll.fabric_mod.FabricMod;

import static net.zylll.fabric_mod.FabricMod.makeID;

public class Paintings {

    public static final PaintingMotive POOP_PAINTING = register("poop_painting", new PaintingMotive(32, 32));

    private static PaintingMotive register(String id, PaintingMotive paintingMotive) {
        return Registry.register(Registry.PAINTING_MOTIVE, makeID(id), paintingMotive);
    }

    public static void register() {
        FabricMod.log("Registering Paintings for + " + FabricMod.MOD_ID);
    }

}
