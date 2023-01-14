package net.zylll.fabric_mod.block.entity;


import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.screen.ScreenHandlerType;
import net.zylll.fabric_mod.screen.handler.UIBlockScreenHandler;

import static net.zylll.fabric_mod.FabricMod.makeID;

public class AllBlockEntities {
    public static BlockEntityType<BreakEntity> BREAK_ENTITY;
    public static BlockEntityType<PoopContainerEntity> POOP_CONTAINER_ENTITY;
    public static BlockEntityType<PoopChestEntity> POOP_CHEST_ENTITY;
    public static BlockEntityType<UIBlockEntity> UI_BLOCK_ENTITY;
    public static final ScreenHandlerType<UIBlockScreenHandler> UI_BLOCK_SCREEN_HANDLER;

    static {
        UI_BLOCK_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(makeID("ui_block"), UIBlockScreenHandler::new);
    }
}
