package net.zylll.fabric_mod.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.zylll.fabric_mod.item.AllItems;

public class TrickBlock extends Block {

    private static final BooleanProperty COLOR = BooleanProperty.of("color");

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(COLOR);
    }

    public TrickBlock(Settings settings) {
        super(settings);
        setDefaultState(this.getStateManager().getDefaultState().with(COLOR, true));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        Item item = player.getMainHandStack().getItem();
        if (!state.get(COLOR) && item == Items.DIAMOND){
            if (!player.isCreative()){
                player.getStackInHand(Hand.MAIN_HAND).decrement(1);
            }
            world.setBlockState(pos, state.get(COLOR) ? state.with(COLOR, false) : state.with(COLOR, true));
            return ActionResult.SUCCESS;
        }
        if (state.get(COLOR) && item == AllItems.POOP){
            if (!player.isCreative()){
                player.getStackInHand(Hand.MAIN_HAND).decrement(1);
            }
            world.setBlockState(pos, state.get(COLOR) ? state.with(COLOR, false) : state.with(COLOR, true));
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }
}
