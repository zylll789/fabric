package net.zylll.fabric_mod.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

public class PoopContainerEntity extends BlockEntity {

    private DefaultedList<ItemStack> itemStacks = DefaultedList.ofSize(1, ItemStack.EMPTY);

    public PoopContainerEntity(BlockPos pos, BlockState state) {
        super(AllBlockEntities.POOP_CONTAINER_ENTITY, pos, state);
    }

    public void use(PlayerEntity playerEntity){
        if (itemStacks.get(0).isEmpty()){
            itemStacks.set(0, playerEntity.getMainHandStack().split(1));
        } else {
            ItemScatterer.spawn(this.getWorld(), this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), itemStacks.get(0));
        }
        markDirty();
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, itemStacks);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, itemStacks);
    }
}
