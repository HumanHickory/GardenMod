package io.github.humanhickory.GardenMod.blockEntities.iceCreamMaker;

import io.github.humanhickory.GardenMod.init.BlockInit;
import io.github.humanhickory.GardenMod.init.MenuInit;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.inventory.ContainerLevelAccess;

import java.util.Objects;

public class IceCreamMakerMenu extends AbstractContainerMenu {
    private final ContainerLevelAccess canInteractWithCallable;
    private IceCreamMakerBlockEntity te;

    public IceCreamMakerMenu(final int windowId, final Inventory playerInventory, final IceCreamMakerBlockEntity tileEntity)
    {
        super(MenuInit.ICE_CREAM_MAKER.get(), windowId);
        this.canInteractWithCallable = ContainerLevelAccess.create(tileEntity.getLevel(), tileEntity.getBlockPos());
        this.te = tileEntity;

        // Main Inventory
        final int startX = 62;
        final int startY = 17;
        final int slotSizePlus2 = 18;
        for (int row = 0 ; row < 3 ; ++row)
        {
            for (int column = 0 ; column < 3 ; ++column)
            {
                int finalRow = row;
                int finalColumn = column;
               // tileEntity.cmItemStackHandler.ifPresent(h -> this.addSlot(new MaxStackSize1Slot(h, finalRow * 3 + finalColumn, startX + (finalColumn * slotSizePlus2), startY + (finalRow * slotSizePlus2))));
            }
        }

        // HotBar
        final int hotBarStartY = 142;
        final int playerInvStartX = 8;
        for (int column = 0; column < 9; ++column)
        {
            this.addSlot(new Slot(playerInventory, column, playerInvStartX + (column * slotSizePlus2), hotBarStartY));
        }

        // Main Player Inventory
        final int playerInvStartY = 84;
        for (int row = 0; row < 3; row++)
        {
            for (int column = 0; column < 9; column++)
            {
                this.addSlot(new Slot(playerInventory, 9 + (row * 9) + column, playerInvStartX + (column * slotSizePlus2), playerInvStartY + (row * slotSizePlus2)));
            }
        }
    }

    private static IceCreamMakerBlockEntity getTileEntity(final Inventory playerInventory, final FriendlyByteBuf data)
    {
        Objects.requireNonNull(playerInventory, "playerInventory cannot be null");
        Objects.requireNonNull(data, "data cannot be null");
        final BlockEntity tileAtPos = playerInventory.player.level().getBlockEntity(data.readBlockPos());
        if (tileAtPos instanceof IceCreamMakerBlockEntity)
        {
            return (IceCreamMakerBlockEntity) tileAtPos;
        }
        throw new IllegalStateException("Tile entity is not correct! " + tileAtPos);
    }

    public IceCreamMakerMenu(final int windowId, final Inventory playerInventory, final FriendlyByteBuf data)
    {
        this(windowId, playerInventory, getTileEntity(playerInventory, data));
    }

    @Override
    public boolean stillValid(Player playerIn)
    {
        return stillValid(canInteractWithCallable, playerIn, BlockInit.ICE_CREAM_MAKER.get());
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index)
    {
        ItemStack itemStack = ItemStack.EMPTY;
        final Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem())
        {
            final ItemStack itemStack1 = slot.getItem();
            itemStack = itemStack1.copy();
            if (index < 9)
            {
                if (!this.moveItemStackTo(itemStack1, 9, this.slots.size(), true))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.moveItemStackTo(itemStack1, 0, 9, false))
            {
                return ItemStack.EMPTY;
            }

            if (itemStack1.isEmpty())
            {
                slot.set(ItemStack.EMPTY);
            }
            else
            {
                slot.setChanged();
            }
        }
        return itemStack;
    }
}
