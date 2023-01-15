package net.zylll.fabric_mod.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.zylll.fabric_mod.block.UIBlock;
import net.zylll.fabric_mod.registry.Items;
import net.zylll.fabric_mod.screen.handler.UIBlockScreenHandler;
import org.jetbrains.annotations.Nullable;

public class UIBlockEntity extends LootableContainerBlockEntity implements SidedInventory {

    public UIBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(AllBlockEntities.UI_BLOCK_ENTITY, blockPos, blockState);
        this.uiBlock = (UIBlock) blockState.getBlock();
    }

    private DefaultedList<ItemStack> inv = DefaultedList.ofSize(2, ItemStack.EMPTY);
    private int timer = 0;
    private UIBlock uiBlock;
    protected final PropertyDelegate propertyDelegate = new PropertyDelegate() {
        @Override
        public int get(int index) {
            return index == 0 ? timer : 0;
        }

        @Override
        public void set(int index, int value) {
            if(index == 0){
                timer = value;
            }
        }

        @Override
        public int size() {
            return 1;
        }
    };

    @Override
    protected DefaultedList<ItemStack> getInvStackList() {
        return this.inv;
    }

    @Override
    protected void setInvStackList(DefaultedList<ItemStack> list) {
        this.inv = list;
    }

    public static void tick(World world, BlockState state, UIBlockEntity entity){
        if(entity.inv.get(0).getCount() >= 2 && entity.inv.get(1).getCount() < entity.inv.get(1).getMaxCount()){
            world.setBlockState(entity.getPos(), state.with(entity.uiBlock.LIT, 1));
            entity.timer++;
            if(entity.timer == 20 * 5){
                entity.timer = 0;
                entity.inv.get(0).decrement(2);
                if(entity.inv.get(1).isEmpty()){
                    entity.inv.set(1, new ItemStack(Items.POOP_BLOCK_ITEM));
                }else {
                    entity.inv.get(1).increment(1);
                }
            }
        }else {
            entity.timer = 0;
            world.setBlockState(entity.getPos(), state.with(entity.uiBlock.LIT, 0));
        }
    }

    @Override
    protected Text getContainerName() {
        return new TranslatableText("block.fabric_mod.ui_block");
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new UIBlockScreenHandler(syncId, playerInventory, this, propertyDelegate);
    }

    @Override
    public int size() {
        return 2;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putInt("tick", timer);
        Inventories.writeNbt(nbt, inv);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        timer = nbt.getInt("tick");
        Inventories.readNbt(nbt, inv);
    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        if(side == Direction.DOWN)return new int[]{1};
        return new int[]{0};
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
        return slot == 0;
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        return slot == 1 && dir == Direction.DOWN;
    }
}
