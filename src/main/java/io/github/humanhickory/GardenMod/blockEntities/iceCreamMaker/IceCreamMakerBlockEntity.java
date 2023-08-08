package io.github.humanhickory.GardenMod.blockEntities.iceCreamMaker;

import io.github.humanhickory.GardenMod.init.BlockEntityInit;
import io.github.humanhickory.GardenMod.init.ItemInit;
import io.github.humanhickory.GardenMod.tools.AdaptedItemHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.FurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class IceCreamMakerBlockEntity extends BlockEntity {
    public static final String ITEMS_INPUT_TAG = "Input";
    public static final String ITEMS_OUTPUT_TAG = "Output";
    public static final String OPTIONAL_ITEMS_INPUT_TAG = "OptionalInput";
    public static final int SLOT_INPUT = 0;
    public static final int SLOT_INPUT_COUNT = 2;
    public static final int OPTIONAL_SLOT_INPUT = 0;
    public static final int OPTIONAL_SLOT_INPUT_COUNT = 1;
    public static final int SLOT_OUTPUT = 0;
    public static final int SLOT_OUTPUT_COUNT = 1;
    public static final int SLOT_COUNT = SLOT_INPUT_COUNT + SLOT_OUTPUT_COUNT + OPTIONAL_SLOT_INPUT_COUNT;
    public static final List<Item> acceptedOptionalItems = Arrays.asList(Items.COCOA_BEANS, ItemInit.BLACKBERRY.get(), ItemInit.BLUEBERRY.get(), ItemInit.CHERRY.get());
    public static final List<Item> iceCreamFlavors = Arrays.asList(ItemInit.CHOCOLATE_ICE_CREAM.get(), ItemInit.BLACKBERRY_ICE_CREAM.get(), ItemInit.BLUEBERRY_ICE_CREAM.get(), ItemInit.CHERRY_ICE_CREAM.get());


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
                if (inputTypeID == 1 && (stack.getItem() == Items.MILK_BUCKET || stack.getItem() == Items.POWDER_SNOW_BUCKET)) { //General Input
                   MakesIceCream(slot, stack, false);
                   return ItemStack.EMPTY;
                } else if (inputTypeID == 2 && acceptedOptionalItems.contains(stack.getItem())) { //optional Input
                    MakesIceCream(slot, stack, true);
                    return ItemStack.EMPTY;
                }
                return stack;
            }

            @Override
            @NotNull
            public ItemStack extractItem(int slot, int amount, boolean simulate)
            {
                if (amount == 0)
                    return ItemStack.EMPTY;

                validateSlotIndex(slot);

                ItemStack existing = this.stacks.get(slot);

                if (existing.isEmpty())
                    return ItemStack.EMPTY;

                int toExtract = Math.min(amount, existing.getMaxStackSize());

                if (existing.getCount() <= toExtract)
                {
                    if (!simulate)
                    {
                        this.stacks.set(slot, ItemStack.EMPTY);

                            RemovesInput(inputTypeID);

                        onContentsChanged(slot);
                        return existing;
                    }
                    else
                    {
                        return existing.copy();
                    }
                }
                else
                {
                    if (!simulate)
                    {
                        this.stacks.set(slot, ItemHandlerHelper.copyStackWithSize(existing, existing.getCount() - toExtract));
                        RemovesInput(inputTypeID);
                        onContentsChanged(slot);
                    }

                    return ItemHandlerHelper.copyStackWithSize(existing, toExtract);
                }
            }
        };
    }


    private void MakesIceCream(int slot, ItemStack stack, boolean isOptional){
        Item inputItem1;
        Item inputItem2;
        ItemStack optionalItem;

        if(!isOptional){
            int slotToCheck = slot == 0 ? 1 : 0;
            if(getInputItems().getStackInSlot(slotToCheck).isEmpty()) return;

            inputItem1 = stack.getItem();
            inputItem2 = getInputItems().getStackInSlot(slotToCheck).getItem();

            optionalItem = getOptionalInputItems().getStackInSlot(0);
        } else {
            if(getInputItems().getStackInSlot(0).isEmpty() || getInputItems().getStackInSlot(1).isEmpty()) return;

            inputItem1 = getInputItems().getStackInSlot(0).getItem();
            inputItem2 = getInputItems().getStackInSlot(1).getItem();
            optionalItem = stack;
        }

        boolean gotMilk = inputItem1 == Items.MILK_BUCKET || inputItem2 == Items.MILK_BUCKET;
        boolean gotSnow = inputItem1 == Items.POWDER_SNOW_BUCKET || inputItem2 ==Items.POWDER_SNOW_BUCKET;

        if(gotSnow && gotMilk && optionalItem.isEmpty()){
            getOutputItems().setStackInSlot(0, new ItemStack(ItemInit.ICE_CREAM.get()));
        } else if(gotSnow && gotMilk && !optionalItem.isEmpty()) {
            if(acceptedOptionalItems.contains(optionalItem.getItem())){
                int index = acceptedOptionalItems.indexOf(optionalItem.getItem());
                getOutputItems().setStackInSlot(0, new ItemStack( iceCreamFlavors.get(index)));
            }
        }
    }



    private void RemovesInput(int inputTypeID){
        ItemStack inputItem1 = getInputItems().getStackInSlot(0);
        ItemStack inputItem2 = getInputItems().getStackInSlot(1);


        if(inputTypeID == 3 && !inputItem2.isEmpty() && !inputItem1.isEmpty()) {
            getInputItems().extractItem(0, 1, false);
            getInputItems().extractItem(1, 1, false);
            //if we put more than 1 optional item in, we want to leave the reamining items there. Milk and snow don't stack so we don't worry about those.

            var optionalItem = getOptionalInputItems().getStackInSlot(0);
            getOptionalInputItems().extractItem(0, 1, false);
            if (optionalItem.getCount() - 1 > 0) {
                optionalItem.setCount(optionalItem.getCount() - 1);
                getOptionalInputItems().setStackInSlot(0, optionalItem);

            }
        } else if(inputTypeID == 1){
            getOutputItems().extractItem(0, 1, false);
        } else if(inputTypeID == 2){
            getOutputItems().extractItem(0, 1, false);
            //this will then go through and delete the first two

            getInputItems().setStackInSlot(0, inputItem1);
            getInputItems().setStackInSlot(1, inputItem2);

            boolean gotMilk = inputItem1.getItem() == Items.MILK_BUCKET || inputItem2.getItem() == Items.MILK_BUCKET;
            boolean gotSnow = inputItem1.getItem() == Items.POWDER_SNOW_BUCKET || inputItem2.getItem() ==Items.POWDER_SNOW_BUCKET;
            if(gotSnow && gotMilk){
                getOutputItems().setStackInSlot(0, new ItemStack(ItemInit.ICE_CREAM.get()));
            }
        }
    }


    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put(ITEMS_INPUT_TAG, inputItems.serializeNBT());
        tag.put(ITEMS_OUTPUT_TAG, outputItems.serializeNBT());
        tag.put(OPTIONAL_ITEMS_INPUT_TAG, optionalInputItems.serializeNBT());
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
        if (tag.contains(OPTIONAL_ITEMS_INPUT_TAG)) {
            optionalInputItems.deserializeNBT(tag.getCompound(OPTIONAL_ITEMS_INPUT_TAG));
        }
    }
}
