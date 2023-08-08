package io.github.humanhickory.GardenMod.init;

import io.github.humanhickory.GardenMod.GardenMod;
import io.github.humanhickory.GardenMod.blockEntities.iceCreamMaker.IceCreamMakerBlockEntity;
import io.github.humanhickory.GardenMod.blockEntities.iceCreamMaker.IceCreamMakerContainer;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MenuTypeInit {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, GardenMod.MODID);

    public static final RegistryObject<MenuType<IceCreamMakerContainer>> ICE_CREAM_MAKER_CONTAINER = MENU_TYPES.register("ice_cream_maker",
            () -> IForgeMenuType.create((windowId, inv, data) -> new IceCreamMakerContainer(windowId, inv.player, data.readBlockPos())));

}
