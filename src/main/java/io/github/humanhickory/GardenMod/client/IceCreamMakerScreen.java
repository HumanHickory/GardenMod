package io.github.humanhickory.GardenMod.client;

import io.github.humanhickory.GardenMod.GardenMod;
import io.github.humanhickory.GardenMod.blockEntities.iceCreamMaker.IceCreamMakerContainer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class IceCreamMakerScreen extends AbstractContainerScreen<IceCreamMakerContainer> {

    private final ResourceLocation GUI = new ResourceLocation(GardenMod.MODID, "textures/gui/container/ice_cream_maker_screen.png");

    public IceCreamMakerScreen(IceCreamMakerContainer container, Inventory inventory, Component title) {
        super(container, inventory, title);
        this.inventoryLabelY = this.imageHeight - 110;
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTicks, int mouseX, int mouseY) {
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        graphics.blit(GUI, relX, relY, 0, 0, this.imageWidth, this.imageHeight);
    }
}
