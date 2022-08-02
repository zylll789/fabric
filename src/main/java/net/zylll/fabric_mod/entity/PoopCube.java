package net.zylll.fabric_mod.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.world.World;

public class PoopCube extends PathAwareEntity {
    public PoopCube(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }
}
