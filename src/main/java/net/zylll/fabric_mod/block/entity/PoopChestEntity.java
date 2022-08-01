package net.zylll.fabric_mod.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

public class PoopChestEntity extends LootableContainerBlockEntity {
    public PoopChestEntity(BlockPos blockPos, BlockState blockState) {
        super(AllBlockEntities.POOP_CHEST_ENTITY, blockPos, blockState);
    }

    private DefaultedList<ItemStack> itemStacks = DefaultedList.ofSize(27, ItemStack.EMPTY);

    @Override
    protected DefaultedList<ItemStack> getInvStackList() {
        return this.itemStacks;
    }

    @Override
    protected void setInvStackList(DefaultedList<ItemStack> list) {
        this.itemStacks = list;
    }

    @Override
    protected Text getContainerName() {
        return new TranslatableText("Box");
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return GenericContainerScreenHandler.createGeneric9x3(syncId, playerInventory, this);
    }

    @Override
    public int size() {
        return 27;
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
