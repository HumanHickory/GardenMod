package io.github.humanhickory.GardenMod.init;

import io.github.humanhickory.GardenMod.GardenMod;
import io.github.humanhickory.GardenMod.blockEntities.iceChest.IceChestBlock;
import io.github.humanhickory.GardenMod.blockEntities.iceChest.IceChestBlockEntity;
import io.github.humanhickory.GardenMod.blocks.bushes.BlackberryBushBlock;
import io.github.humanhickory.GardenMod.blocks.crops.BlueberryCropBlock;
import io.github.humanhickory.GardenMod.blocks.crops.CabbageCropBlock;
import io.github.humanhickory.GardenMod.blocks.crops.TomatoCropBlock;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


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

    public static final RegistryObject<IceChestBlock> ICE_CHEST_BLOCK = BLOCKS.register("ice_chest",
            () -> new IceChestBlock(BlockBehaviour.Properties.copy(Blocks.BARREL)
                    .strength(5.0f, 15f)));



}
