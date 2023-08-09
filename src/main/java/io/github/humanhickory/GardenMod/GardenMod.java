package io.github.humanhickory.GardenMod;

import io.github.humanhickory.GardenMod.init.*;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(GardenMod.MODID)
public class GardenMod {
    public static final String MODID = "gardenmod";

    public GardenMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ItemInit.ITEMS.register(bus);
        BlockInit.BLOCKS.register(bus);
        CreativeTabInit.TABS.register(bus);
        BlockEntityInit.BLOCK_ENTITIES.register(bus);
        MenuTypeInit.MENU_TYPES.register(bus);

    }




}

//Ctrl Alt L to Auto Format
//Ctrl  B To find Implementation
//Shift F6 to refactor
// Ctrl +  / Comment and uncomment