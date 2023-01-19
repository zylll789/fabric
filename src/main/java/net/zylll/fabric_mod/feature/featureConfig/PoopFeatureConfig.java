package net.zylll.fabric_mod.feature.featureConfig;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.FeatureConfig;

public record PoopFeatureConfig(Identifier blockID) implements FeatureConfig {

    public static Codec<PoopFeatureConfig> POOP_FEATURE_CONFIG_CODEC = RecordCodecBuilder.create(
            instance -> instance.group(Identifier.CODEC.fieldOf("blockID").forGetter(PoopFeatureConfig::getBlockID))
                    .apply(instance, PoopFeatureConfig::new));

    private Identifier getBlockID() {
        return this.blockID;
    }

}
