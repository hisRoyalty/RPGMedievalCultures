package com.hisroyalty.rmg.screen.widget;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.resources.ResourceLocation;
public class HoverableImageButton extends ImageButton {
    private final ResourceLocation hoveredResourceLocation;





    public HoverableImageButton(int pX, int pY, int pWidth, int pHeight, int pXTexStart, int pYTexStart, ResourceLocation pResourceLocation, ResourceLocation hoveredResourceLocation, OnPress pOnPress) {
        super(pX, pY, pWidth, pHeight, pXTexStart, pYTexStart, pResourceLocation, pOnPress);
        this.hoveredResourceLocation = hoveredResourceLocation;

    }


    @Override
    public void renderWidget(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        pGuiGraphics.blit(this.isHovered() && isActive() ? this.hoveredResourceLocation : this.resourceLocation, getX(), getX(), 0, 0, this.width, this.height, this.width, this.height);

    }
}

