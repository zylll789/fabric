package net.zylll.fabric_mod.entity.randerer;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.zylll.fabric_mod.FabricModClient;
import net.zylll.fabric_mod.entity.PoopCube;
import net.zylll.fabric_mod.entity.model.PoopCubeEntityModel;

import static net.zylll.fabric_mod.FabricMod.makeID;

public class PoopCubeRanderer extends MobEntityRenderer<PoopCube, PoopCubeEntityModel> {
    public PoopCubeRanderer(EntityRendererFactory.Context context) {
        super(context, new PoopCubeEntityModel(context.getPart(FabricModClient.MODEL_POOP_CUBE_LAYER)), 0.5F);
    }

    @Override
    public Identifier getTexture(PoopCube entity) {
        return makeID("textures/entity/poop_cube/poop_cube.png");
    }
}
