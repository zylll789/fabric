package net.zylll.fabric_mod.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreeperEntity.class)
public abstract class CreeperEntityMixin extends Entity {
    @Shadow protected abstract void spawnEffectsCloud();

    public CreeperEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(at = @At("HEAD"),method = "explode", cancellable = true)
    protected void explodeMixin(CallbackInfo info){
        if(!this.world.isClient){
            info.cancel();
            this.world.createExplosion(this, this.getX(), this.getY(), this.getZ(), 6f, Explosion.DestructionType.NONE);
            this.discard();
            this.spawnEffectsCloud();
        }
    }
}
