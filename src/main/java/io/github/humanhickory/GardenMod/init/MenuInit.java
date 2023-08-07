package io.github.humanhickory.GardenMod.init;

import io.github.humanhickory.GardenMod.GardenMod;
import io.github.humanhickory.GardenMod.blockEntities.iceCreamMaker.IceCreamMakerBlockEntity;
import io.github.humanhickory.GardenMod.blockEntities.iceCreamMaker.IceCreamMakerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MenuInit {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, GardenMod.MODID);

    public static final RegistryObject<MenuType<IceCreamMakerMenu>> ICE_CREAM_MAKER = MENU_TYPES
            .register("ice_cream_maker", () -> IForgeMenuType.create(IceCreamMakerMenu::new));

}
