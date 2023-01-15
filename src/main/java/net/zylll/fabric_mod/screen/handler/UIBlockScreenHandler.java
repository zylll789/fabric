package net.zylll.fabric_mod.screen.handler;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.zylll.fabric_mod.block.entity.AllBlockEntities;
import net.zylll.fabric_mod.registry.Items;

public class UIBlockScreenHandler extends ScreenHandler {

    private Inventory inventory;
    private final PropertyDelegate propertyDelegate;

    public UIBlockScreenHandler(int syncId, PlayerInventory inventory){
        this(syncId, inventory, new SimpleInventory(2), new ArrayPropertyDelegate(1));
    }

    public UIBlockScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate){
        super(AllBlockEntities.UI_BLOCK_SCREEN_HANDLER, syncId);
        this.inventory = inventory;
        this.propertyDelegate = propertyDelegate;
        this.addProperties(propertyDelegate);
        checkSize(inventory, 2);
        checkDataCount(propertyDelegate, 1);
        this.addSlot(new Slot(this.inventory, 0, 56, 17){
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.getItem() == Items.POOP;
            }
        });
        this.addSlot(new Slot(this.inventory, 1, 99, 17){
            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }
        });
        int i;
        for(i = 0; i < 3; ++i){
            for(int j = 0; j < 9; ++j){
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for(i = 0; i < 9; ++i){
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    public int getTick(){
        return propertyDelegate.get(0);
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

    @Override
    public ItemStack transferSlot(PlayerEntity player, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if(slot.hasStack()){
            ItemStack itemStack2 = slot.getStack();
            itemStack = itemStack2.copy();
            if (index < this.inventory.size()) {
                if (!this.insertItem(itemStack2, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(itemStack2, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }
            if(itemStack2.isEmpty()){
                slot.setStack(ItemStack.EMPTY);
            }else {
                slot.markDirty();
            }
            if(itemStack2.getCount() == itemStack.getCount()){
                return ItemStack.EMPTY;
            }
            slot.onTakeItem(player, itemStack2);
        }
        return itemStack;
    }
}
