package io.github.humanhickory.GardenMod.init;

import io.github.humanhickory.GardenMod.GardenMod;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, GardenMod.MODID);

    public static final RegistryObject<Item> BLUEBERRY =registerFoodItem("blueberry", 1, 0.2f);
    public static final RegistryObject<Item> TOMATO = registerFoodItem("tomato", 2, 0.2f);
    public static final RegistryObject<Item> CHERRY = registerFoodItem("cherry", 2, 0.2f);
    public static final RegistryObject<Item> SPAGHETTI = registerFoodItem("spaghetti", 6, .6f);
    public static final RegistryObject<Item> CHERRY_PIE = registerFoodItem("cherry_pie", 3, 0.3f);
    public static final RegistryObject<Item> BLUEBERRY_PIE = registerFoodItem("blueberry_pie", 3, 0.3f);

    public static final RegistryObject<Item> COPPER_POT = registerItem("copper_pot");

    public static final RegistryObject<Item> BLUEBERRY_SEEDS = registerFoodBlockItem("blueberry_seeds", BlockInit.BLUEBERRY_CROP);
    public static final RegistryObject<Item> TOMATO_SEEDS = registerFoodBlockItem("tomato_seeds", BlockInit.TOMATO_CROP);
    public static final RegistryObject<Item> CABBAGE = registerFoodBlockItem("cabbage", BlockInit.CABBAGE_CROP);
    public static final RegistryObject<Item> BLACKBERRY = registerFoodBlockItem("blackberry", BlockInit.BLACKBERRY_BUSH);

    //PRIVATE METHODS

    private static RegistryObject<Item> registerItem(String name) {
        return CreativeTabInit.addToTab(ITEMS.register(name,
                () -> new Item(new Item.Properties())));
    }

    private static RegistryObject<Item> registerFoodItem(String name, int nutrition, float saturation) {
        return CreativeTabInit.addToFoodTab(ITEMS.register(name,
                () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(nutrition).saturationMod(saturation).build()))));
    }

    private static <T extends Block> RegistryObject<Item> registerFoodBlockItem(String name, RegistryObject<T> block){
        return CreativeTabInit.addToFoodTab(ITEMS.register(name, () -> new ItemNameBlockItem(block.get(), new Item.Properties())));
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block){
        return CreativeTabInit.addToTab(ITEMS.register(name, () -> new ItemNameBlockItem(block.get(), new Item.Properties())));
    }


}