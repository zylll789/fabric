package net.zylll.fabric_mod.world.structures;

import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.StructureFeature;
import net.zylll.fabric_mod.FabricMod;
import net.zylll.fabric_mod.mixin.StructureFeatureAccessor;

public class Structures {

    /**
     /**
     * Registers the structure itself and sets what its path is. In this case, the
     * structure will have the Identifier of structure_tutorial:sky_structures.
     *
     * It is always a good idea to register your Structures so that other mods and datapacks can
     * use them too directly from the registries. It great for mod/datapacks compatibility.
     */
    public static StructureFeature<?> SKY_STRUCTURES = new SkyStructures();
    public static StructureFeature<?> ADVANCED_DUNGEON = new AdvancedDungeonStructures();

    public static void registerStructureFeatures() {
        // This surface structure stage places the structure before plants and ores are generated.
        StructureFeatureAccessor.callRegister(FabricMod.MOD_ID + ":sky_structures", SKY_STRUCTURES, GenerationStep.Feature.SURFACE_STRUCTURES);
        StructureFeatureAccessor.callRegister(FabricMod.MOD_ID + ":advanced_dungeon", ADVANCED_DUNGEON, GenerationStep.Feature.SURFACE_STRUCTURES);
    }
}