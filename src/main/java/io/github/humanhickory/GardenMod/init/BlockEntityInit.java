package io.github.humanhickory.GardenMod.init;

import io.github.humanhickory.GardenMod.GardenMod;
import io.github.humanhickory.GardenMod.blockEntities.iceChest.IceChestBlockEntity;
import io.github.humanhickory.GardenMod.blockEntities.iceCreamMaker.IceCreamMakerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityInit {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, GardenMod.MODID);

    public static final RegistryObject<BlockEntityType<IceChestBlockEntity>> ICE_CHEST_BLOCK_ENTITY = BLOCK_ENTITIES
            .register("ice_chest",
                    () -> BlockEntityType.Builder.of(IceChestBlockEntity::new, BlockInit.ICE_CHEST_BLOCK.get())
                            .build(null));

    public static final RegistryObject<BlockEntityType<IceCreamMakerBlockEntity>> ICE_CREAM_MAKER_BLOCK_ENTITY = BLOCK_ENTITIES
            .register("ice_cream_maker",
                    () -> BlockEntityType.Builder.of(IceCreamMakerBlockEntity::new, BlockInit.ICE_CREAM_MAKER.get())
                            .build(null));
}
