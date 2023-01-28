package net.zylll.fabric_mod.entity.randerer;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import net.zylll.fabric_mod.entity.YSlimeEntity;
import net.zylll.fabric_mod.entity.model.YSlimeModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import static net.zylll.fabric_mod.FabricMod.makeID;

public class YSlimeRanderer extends GeoEntityRenderer<YSlimeEntity> {
    public YSlimeRanderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new YSlimeModel());
    }

    @Override
    public Identifier getTextureLocation(YSlimeEntity animatable) {
        return makeID("textures/entity/y_slime/y_slime.png");
    }
}
