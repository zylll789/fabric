package net.zylll.fabric_mod.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.zylll.fabric_mod.registry.Blocks;

public class DowsingRodItem extends Item {
    public DowsingRodItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (context.getWorld().isClient()) {
            BlockPos pos = context.getBlockPos();
            PlayerEntity playerEntity = context.getPlayer();
            boolean found = false;
            for (int i = 0; i <= pos.getY() + 64; i++) {
                Block block = context.getWorld().getBlockState(pos.down(i)).getBlock();
                if (isValuableBlock(block)) {
                    outputValuableCoordinates(pos.down(i), playerEntity, block);
                    found = true;
                }
            }
            if (!found) {
                playerEntity.sendMessage(new TranslatableText("item.fabric_mod.dowsing_rod.no_found"), false);
            }
        }
        context.getStack().damage(1, context.getPlayer(), (playerEntity -> playerEntity.sendToolBreakStatus(playerEntity.getActiveHand())));
        return super.useOnBlock(context);
    }

    private void outputValuableCoordinates(BlockPos pos, PlayerEntity playerEntity, Block block) {
        playerEntity.sendMessage(new LiteralText("Found" + block.asItem().getName().getString() + "at"
                + "(" + pos.getX() + "," + pos.getY() + "," + pos.getZ() + ")"), false);
    }

    private boolean isValuableBlock(Block block) {
        return block == Blocks.POOP_BLOCK || block == Blocks.TRICK_BLOCK || block == Blocks.ORE_CHANGED_BLOCK;
    }
}
