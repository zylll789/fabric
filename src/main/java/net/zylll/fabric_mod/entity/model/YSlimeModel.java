package net.zylll.fabric_mod.entity.model;

import net.minecraft.util.Identifier;
import net.zylll.fabric_mod.entity.YSlimeEntity;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import static net.zylll.fabric_mod.FabricMod.makeID;

public class YSlimeModel extends AnimatedGeoModel<YSlimeEntity> {
    @Override
    public Identifier getModelLocation(YSlimeEntity object) {
        return makeID("geo/y_slime.geo.json");
    }

    @Override
    public Identifier getTextureLocation(YSlimeEntity object) {
        return makeID("textures/entity/y_slime/y_slime.png");
    }

    @Override
    public Identifier getAnimationFileLocation(YSlimeEntity animatable) {
        return makeID("animations/y_slime.animation.json");
    }

//    @SuppressWarnings({"unchecked"})
//    @Override
//    public void setCustomAnimations(YSlimeEntity animatable, int instanceId, AnimationEvent animationEvent) {
//        super.setCustomAnimations(animatable, instanceId, animationEvent);
//        IBone y_slime = this.getAnimationProcessor().getBone("y_slime");
//        EntityModelData entityModelData = (EntityModelData) animationEvent.getExtraDataOfType(EntityModelData.class).get(0);
//        if (y_slime != null) {
//            y_slime.setRotationX(entityModelData.headPitch * ((float) Math.PI / 180F));
//            y_slime.setRotationZ(entityModelData.netHeadYaw * ((float) Math.PI / 180F));
//        }
//    }
}
