package net.zylll.fabric_mod.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.zylll.fabric_mod.screen.handler.UIBlockScreenHandler;

public class UIBlockEntity extends LootableContainerBlockEntity {

    public UIBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(AllBlockEntities.UI_BLOCK_ENTITY, blockPos, blockState);
    }

    private DefaultedList<ItemStack> inv = DefaultedList.ofSize(2, ItemStack.EMPTY);

    @Override
    protected DefaultedList<ItemStack> getInvStackList() {
        return this.inv;
    }

    @Override
    protected void setInvStackList(DefaultedList<ItemStack> list) {
        this.inv = list;
    }

    @Override
    protected Text getContainerName() {
        return new TranslatableText("UI Block");
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new UIBlockScreenHandler(syncId, playerInventory, this);
    }

    @Override
    public int size() {
        return 2;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inv);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inv);
    }
}
