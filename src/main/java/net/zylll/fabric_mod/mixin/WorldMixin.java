package net.zylll.fabric_mod.mixin;

import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(World.class)
public class WorldMixin {

    /**
     * @author
     * @reason
     */
    @Overwrite
    private static boolean isInvalidVertically(int y){
        return false;
    }

}
