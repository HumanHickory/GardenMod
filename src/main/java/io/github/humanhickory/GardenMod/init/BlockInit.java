package io.github.humanhickory.GardenMod.init;

import io.github.humanhickory.GardenMod.GardenMod;
import io.github.humanhickory.GardenMod.blocks.bushes.BlackberryBushBlock;
import io.github.humanhickory.GardenMod.blocks.crops.BlueberryCropBlock;
import io.github.humanhickory.GardenMod.blocks.crops.CabbageCropBlock;
import io.github.humanhickory.GardenMod.blocks.crops.TomatoCropBlock;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, GardenMod.MODID);

    public static final RegistryObject<Block> BLUEBERRY_CROP = BLOCKS.register("blueberry_crop",
            ()-> new BlueberryCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)));

    public static final RegistryObject<Block> TOMATO_CROP = BLOCKS.register("tomato_crop",
            ()-> new TomatoCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)));

    public static final RegistryObject<Block> CABBAGE_CROP = BLOCKS.register("cabbage_crop",
            ()-> new CabbageCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)));

    public static final RegistryObject<Block> BLACKBERRY_BUSH = BLOCKS.register("blackberry_bush",
            ()-> new BlackberryBushBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT).randomTicks().noCollission().sound(SoundType.SWEET_BERRY_BUSH).pushReaction(PushReaction.DESTROY)));



}
