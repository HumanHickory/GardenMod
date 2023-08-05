package io.github.humanhickory.GardenMod.init;

import io.github.humanhickory.GardenMod.GardenMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = GardenMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CreativeTabInit {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, GardenMod.MODID);

    public static final List<Supplier<? extends ItemLike>> GARDEN_MOD_TAB_ITEMS = new ArrayList<>();
    public static final List<Supplier<? extends ItemLike>> FOOD_TAB_ITEMS = new ArrayList<>();

    public static final RegistryObject<CreativeModeTab> GARDEN_MOD_TAB = TABS.register("garden_mod_tab",
            ()-> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.garden_mod_tab"))
                    .icon(ItemInit.BLUEBERRY.get()::getDefaultInstance)
                    .displayItems((displayParams, output) ->
                            GARDEN_MOD_TAB_ITEMS.forEach(itemLike -> output.accept(itemLike.get())))
                    .build()
    );

    public static <T extends Item> RegistryObject<T> addToTab(RegistryObject<T> itemLike){
        GARDEN_MOD_TAB_ITEMS.add(itemLike);
        return itemLike;
    }

    public static <T extends Item> RegistryObject<T> addToFoodTab(RegistryObject<T> itemLike){
        GARDEN_MOD_TAB_ITEMS.add(itemLike);
        return itemLike;
    }

    @SubscribeEvent
    public static void buildContents(BuildCreativeModeTabContentsEvent event){
        if(event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS){
            FOOD_TAB_ITEMS.forEach(itemLike -> event.accept(itemLike.get()));
        }
    }

}
