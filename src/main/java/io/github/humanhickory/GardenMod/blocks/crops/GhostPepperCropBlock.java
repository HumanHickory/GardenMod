package io.github.humanhickory.GardenMod.blocks.crops;

import io.github.humanhickory.GardenMod.init.ItemInit;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;


public class GhostPepperCropBlock extends CropBlock {
    public static final int MaxAge = 7;
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, MaxAge);

    public GhostPepperCropBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemLike getBaseSeedId(){
        return ItemInit.GHOST_PEPPER_SEEDS.get();
    }

    @Override
    protected IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return MaxAge;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
