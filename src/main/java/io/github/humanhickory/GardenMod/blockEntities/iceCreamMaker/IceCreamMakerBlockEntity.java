package io.github.humanhickory.GardenMod.blockEntities.iceCreamMaker;

import io.github.humanhickory.GardenMod.init.BlockEntityInit;
import io.github.humanhickory.GardenMod.tools.AdaptedItemHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;
import org.checkerframework.checker.units.qual.A;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

public class IceCreamMakerBlockEntity extends BlockEntity {
    public static final String ITEMS_INPUT_TAG = "Input";
    public static final String ITEMS_OUTPUT_TAG = "Output";
    public static final int SLOT_INPUT = 0;
    public static final int SLOT_INPUT_COUNT = 2;

    public static final int OPTIONAL_SLOT_INPUT = 0;
    public static final int OPTIONAL_SLOT_INPUT_COUNT = 1;

    public static final int SLOT_OUTPUT = 0;
    public static final int SLOT_OUTPUT_COUNT = 1;

    public static final int SLOT_COUNT = SLOT_INPUT_COUNT + SLOT_OUTPUT_COUNT + OPTIONAL_SLOT_INPUT_COUNT;

    private final ItemStackHandler inputItems = createItemHandler(SLOT_INPUT_COUNT, 1);
    private final ItemStackHandler optionalInputItems = createItemHandler(OPTIONAL_SLOT_INPUT_COUNT, 2);
    private final ItemStackHandler outputItems = createItemHandler(SLOT_OUTPUT_COUNT, 3);
    private final LazyOptional<IItemHandler> itemHandler = LazyOptional.of(() -> new CombinedInvWrapper(inputItems, outputItems, optionalInputItems));


    private final LazyOptional<IItemHandler> inputItemHandler = LazyOptional.of(() -> new AdaptedItemHandler(inputItems));
    private final LazyOptional<IItemHandler> outputItemHandler = LazyOptional.of(() -> new AdaptedItemHandler(outputItems));
    private final LazyOptional<IItemHandler> optionalInputItemHandler = LazyOptional.of(() -> new AdaptedItemHandler(optionalInputItems));

    public IceCreamMakerBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityInit.ICE_CREAM_MAKER_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        itemHandler.invalidate();
        inputItemHandler.invalidate();
        outputItemHandler.invalidate();
        optionalInputItemHandler.invalidate();
    }

    public void tickServer() {
        return;
    }

    public ItemStackHandler getInputItems() {
        return inputItems;
    }

    public ItemStackHandler getOptionalInputItems() {
        return optionalInputItems;
    }

    public ItemStackHandler getOutputItems() {
        return outputItems;
    }

    @Nonnull
    private ItemStackHandler createItemHandler(int slots, int inputType) {
        final int inputTypeID = inputType;

        return new ItemStackHandler(slots) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }

            @Override
            public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {

                if(inputTypeID == 1){ //General Input
                    if (stack.getItem() == Items.MILK_BUCKET || stack.getItem() == Items.POWDER_SNOW_BUCKET) {
                        return ItemStack.EMPTY;
                    } else {
                        return stack;
                    }
                } else if (inputTypeID == 2){ //optional Input
                    if (stack.getItem() == Items.COCOA_BEANS) {
                        return ItemStack.EMPTY;
                    } else {
                        return stack;
                    }
                }
                else { //output
                    return stack;
                }
            }

        };
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put(ITEMS_INPUT_TAG, inputItems.serializeNBT());
        tag.put(ITEMS_OUTPUT_TAG, outputItems.serializeNBT());
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        if (tag.contains(ITEMS_INPUT_TAG)) {
            inputItems.deserializeNBT(tag.getCompound(ITEMS_INPUT_TAG));
        }
        if (tag.contains(ITEMS_OUTPUT_TAG)) {
            outputItems.deserializeNBT(tag.getCompound(ITEMS_OUTPUT_TAG));
        }
    }
}
