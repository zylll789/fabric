package net.zylll.fabric_mod.fluid;

import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.zylll.fabric_mod.block.AllBlocks;
import net.zylll.fabric_mod.registry.Fluids;
import net.zylll.fabric_mod.registry.Items;

public abstract class PoopFluid extends FabricModFluid{

    @Override
    public Fluid getFlowing() {
        return Fluids.FLOWING_POOP_FLUID;
    }

    @Override
    public Fluid getStill() {
        return Fluids.STILL_POOP_FLUID;
    }

    @Override
    public Item getBucketItem() {
        return Items.POOP_FLUID_BUCKET;
    }

    @Override
    protected BlockState toBlockState(FluidState state) {
        return AllBlocks.POOP_FLUID.getDefaultState().with(Properties.LEVEL_15, getBlockStateLevel(state));
    }

    public static class Flowing extends PoopFluid{

        @Override
        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
            super.appendProperties(builder);
            builder.add(LEVEL);
        }

        @Override
        public boolean isStill(FluidState state) {
            return false;
        }

        @Override
        public int getLevel(FluidState state) {
            return state.get(LEVEL);
        }
    }

    public static class Still extends PoopFluid{

        @Override
        public int getLevel(FluidState state) {
            return 8;
        }

        @Override
        public boolean isStill(FluidState state) {
            return true;
        }
    }
}
