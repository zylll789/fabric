package net.zylll.fabric_mod.block;

import net.minecraft.block.CropBlock;
import net.minecraft.item.ItemConvertible;
import net.zylll.fabric_mod.registry.Items;

public class PoopCrop extends CropBlock {
    public PoopCrop(Settings settings) {
        super(settings);
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return Items.POOP_CROP_SEED;
    }
}
