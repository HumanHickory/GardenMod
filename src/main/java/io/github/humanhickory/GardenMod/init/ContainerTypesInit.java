package io.github.humanhickory.GardenMod.init;

import io.github.humanhickory.GardenMod.GardenMod;
import io.github.humanhickory.GardenMod.blocks.ice_cream_maker.IceCreamMakerContainer;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ContainerTypesInit {
    public static final DeferredRegister<MenuType<?>> CONTAINER_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, GardenMod.MODID);


    public static final RegistryObject<MenuType<IceCreamMakerContainer>> ICE_CREAM_MAKER = CONTAINER_TYPES
            .register("ice_cream_maker", () -> IForgeMenuType.create(IceCreamMakerContainer::new));
}
