package io.github.humanhickory.GardenMod.blockEntities.iceCreamMaker;

import io.github.humanhickory.GardenMod.GardenMod;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.recipebook.SmeltingRecipeBookComponent;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.FurnaceMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.NotNull;

public class OldIceCreamMakerScreen extends Screen {
    private static final Component TITLE = Component.translatable("gui." + GardenMod.MODID + ".ice_cream_maker_screen");
    private static final Component EXAMPLE_BUTTON = Component.translatable("gui." + GardenMod.MODID + ".ice_cream_maker_screen.button.ice_cream_maker_button");

    private static final ResourceLocation TEXTURE = new ResourceLocation(GardenMod.MODID, "textures/gui/container/ice_cream_maker_screen.png");

    private final BlockPos position;
    private final int imageWidth, imageHeight;

    private IceCreamMakerBlockEntity blockEntity;
    private int leftPos, topPos;

    private Button button;

    public OldIceCreamMakerScreen(BlockPos position) {
        super(TITLE);

        this.position = position;
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    @Override
    protected void init() {
        super.init();

        this.leftPos = (this.width - this.imageWidth) / 2;
        this.topPos = (this.height - this.imageHeight) / 2;

        if(this.minecraft == null) return;
        Level level = this.minecraft.level;
        if(level == null) return;

        BlockEntity be = level.getBlockEntity(this.position);
        if(be instanceof IceCreamMakerBlockEntity blockEntity) {
            this.blockEntity = blockEntity;
        } else {
            System.err.printf("BlockEntity at %s is not of type ExampleScreenBlockEntity!\n", this.position);
            return;
        }

        this.button = addRenderableWidget(
                Button.builder(
                                EXAMPLE_BUTTON,
                                this::handleExampleButton)
                        .bounds(this.leftPos + 8, this.topPos + 20, 80, 20)
                        .tooltip(Tooltip.create(EXAMPLE_BUTTON))
                        .build());
    }

    @Override
    public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        renderBackground(graphics);
        graphics.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
        super.render(graphics, mouseX, mouseY, partialTicks);

        graphics.drawString(this.font,
                TITLE,
                this.leftPos + 8,
                this.topPos + 8,
                0x404040,
                false);

        graphics.drawString(this.font,
                "Seconds Existed: TEST",
                this.leftPos + 8,
                this.topPos + 44,
                0xFF0000,
                false);
    }

    private void handleExampleButton(Button button) {
        // logic here
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
