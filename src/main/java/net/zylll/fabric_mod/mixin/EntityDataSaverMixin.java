package net.zylll.fabric_mod.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import net.zylll.fabric_mod.util.IEntityDataSaver;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityDataSaverMixin implements IEntityDataSaver {
    private NbtCompound persistentData;
    private NbtCompound isWeightless;

    @Override
    public NbtCompound getPersistentData() {
        if (this.persistentData == null) {
            this.persistentData = new NbtCompound();
        }
        return persistentData;
    }

    @Override
    public NbtCompound getWeightless() {
        if (this.isWeightless == null) {
            this.isWeightless = new NbtCompound();
        }
        return isWeightless;
    }

    @Inject(method = "writeNbt", at = @At("HEAD"))
    protected void injectWriteNBT(NbtCompound nbt, CallbackInfoReturnable info) {
        if (persistentData != null) {
            nbt.put("fabric_mod.extra_data", persistentData);
        }
        if (isWeightless != null) {
            nbt.put("fabric_mod.isWeightless", isWeightless);
        }
    }

    @Inject(method = "readNbt", at = @At("HEAD"))
    protected void injectReadNBT(NbtCompound nbt, CallbackInfo ci) {
        if (nbt.contains("fabric_mod.extra_data", 10)) {
            persistentData = nbt.getCompound("fabric_mod.extra_data");
        }
        if (nbt.contains("fabric_mod.isWeightless", 10)) {
            isWeightless = nbt.getCompound("fabric_mod.isWeightless");
        }
    }
}
