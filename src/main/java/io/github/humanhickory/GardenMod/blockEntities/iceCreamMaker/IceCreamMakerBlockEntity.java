package io.github.humanhickory.GardenMod.blockEntities.iceCreamMaker;

import io.github.humanhickory.GardenMod.init.BlockEntityInit;
import io.github.humanhickory.GardenMod.init.BlockInit;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.FurnaceMenu;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class IceCreamMakerBlockEntity extends AbstractFurnaceBlockEntity {
    public IceCreamMakerBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(BlockEntityInit.ICE_CREAM_MAKER_BLOCK_ENTITY.get(), pPos, pBlockState, RecipeType.SMELTING);
    }

    @Override
    protected Component getDefaultName() {
        return Component.literal("Ice Cream Maker");
    }

    @Override
    protected AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory) {
        return new FurnaceMenu(pContainerId, pInventory, this, this.dataAccess);
    }
}
