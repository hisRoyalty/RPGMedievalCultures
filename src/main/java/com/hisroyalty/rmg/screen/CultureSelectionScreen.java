package com.hisroyalty.rmg.screen;

import com.hisroyalty.rmg.RMGMod;
import com.hisroyalty.rmg.client.RMGClient;
import com.mojang.authlib.minecraft.client.MinecraftClient;
import com.mojang.blaze3d.vertex.Tesselator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.components.Button;

import java.awt.*;
import java.util.List;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.gui.widget.ScrollPanel;
import org.jetbrains.annotations.NotNull;

import java.util.List;


@OnlyIn(Dist.CLIENT)
public class CultureSelectionScreen extends Screen {
    private final ResourceLocation EMP = new ResourceLocation(RMGMod.MODID, "textures/gui/emp.png");
    private final ResourceLocation KNG = new ResourceLocation(RMGMod.MODID, "textures/gui/king.png");
    private final ResourceLocation MTH = new ResourceLocation(RMGMod.MODID, "textures/gui/mth.png");
    private final ResourceLocation CMW = new ResourceLocation(RMGMod.MODID, "textures/gui/common.png");
    private final ResourceLocation HGL = new ResourceLocation(RMGMod.MODID, "textures/gui/highland.png");
    private final ResourceLocation NMD = new ResourceLocation(RMGMod.MODID, "textures/gui/nomad.png");
    private final ResourceLocation EMP_DESC = new ResourceLocation(RMGMod.MODID, "textures/gui/empire_desc.png");
    private final ResourceLocation KNG_DESC = new ResourceLocation(RMGMod.MODID, "textures/gui/kingdom_desc.png");
    private final ResourceLocation MTH_DESC = new ResourceLocation(RMGMod.MODID, "textures/gui/mth_desc.png");
    private final ResourceLocation CMW_DESC = new ResourceLocation(RMGMod.MODID, "textures/gui/commonwealth.png");
    private final ResourceLocation HGL_DESC = new ResourceLocation(RMGMod.MODID, "textures/gui/highland_desc.png");
    private final ResourceLocation NMD_DESC = new ResourceLocation(RMGMod.MODID, "textures/gui/nomad_desc.png");
    private final ResourceLocation BG = new ResourceLocation(RMGMod.MODID, "textures/gui/bg.png");

    private final ResourceLocation RS = new ResourceLocation(RMGMod.MODID, "textures/gui/rs.png");
    private final ResourceLocation NEXT = new ResourceLocation(RMGMod.MODID, "textures/gui/next.png");
    private final ResourceLocation HOVERED_NEXT = new ResourceLocation(RMGMod.MODID, "textures/gui/hovered_next.png");


    List<ResourceLocation> images = List.of(EMP, KNG, MTH, CMW, HGL, NMD);
    List<ResourceLocation> dimages = List.of(EMP_DESC, KNG_DESC, MTH_DESC, CMW_DESC, HGL_DESC, NMD_DESC);


    private int leftPos, topPos;

    public static ResourceLocation selectedImage = null;

    public CultureSelectionScreen() {
        super(Component.translatable("screen.cultures.choose_culture"));
    }

    @Override
    protected void init() {
        super.init();
        Minecraft mc = Minecraft.getInstance();
        int spacing = 6;
        int imageWidth = 50;
        int imageHeight = 95;

        leftPos =  (width - imageWidth) /2;;
        topPos = (height - imageHeight) /2;
        System.out.println(mc.getWindow().getGuiScaledHeight());


        for (int i = 0; i < images.size(); i++) {

            int debugX = 75;
            int x = leftPos + debugX + (i % 3) * (imageWidth + spacing);
            int y = (i / 3) * (imageHeight + spacing) - (imageHeight / 2) + topPos  ;

            ResourceLocation image = images.get(i);
            ImageButton button = new ImageButton(x, y, imageWidth, imageHeight, 0, 0, 0, image, imageWidth, imageHeight, b -> {
                selectedImage = image;
            });

            this.addRenderableWidget(button);
        }








        TestButton nextButton = new TestButton(this.width - 120, this.height - 20, 120, 22, 0, 0, 0, NEXT, HOVERED_NEXT, 120, 22, k -> {
            if (selectedImage != null) {
                Minecraft.getInstance().setScreen(null);
                RMGClient.AWAITING_DISPLAY.set(false);
                RMGClient.NEXT_PAGE.set(true);
            } else {
                RMGClient.NEXT_PAGE.set(false);
            }
        });
        this.addRenderableWidget(nextButton);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int x, int y, float k) {
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, x, y, k);

        Minecraft mc = Minecraft.getInstance();

        int imageHeight = 95;
        int imageWidth = 50;
        int spacing = 6;

        String[] labels = {"Empire", "Kingdom", "Motherland", "Commonwealth", "Highland", "Nomad"};
        int maxLabelWidth = imageWidth;


        for (int i = 0; i < images.size(); i++) {
            Minecraft instance = Minecraft.getInstance();
            int debugX = 75;
            int xx = leftPos + debugX + (i % 3) * (imageWidth + spacing);
            int yy = (i / 3) * (imageHeight + spacing) - (imageHeight / 2) + topPos;

            ResourceLocation image = images.get(i);
            ResourceLocation dimage = dimages.get(i);
            if (image == selectedImage) {
                guiGraphics.fill(xx, yy, xx + imageWidth, yy + 1, Color.YELLOW.getRGB());
                guiGraphics.fill(xx, yy + imageHeight - 1, xx + imageWidth, yy + imageHeight, Color.YELLOW.getRGB());
                guiGraphics.fill(xx, yy, xx + 1, yy + imageHeight, Color.YELLOW.getRGB());
                guiGraphics.fill(xx + imageWidth - 1, yy, xx + imageWidth, yy + imageHeight, Color.YELLOW.getRGB());
                guiGraphics.blit(dimage, 0, 0, 0, 0, width/2, height, width/2, height);
            }


            String label = labels[i % labels.length]; // Ensure the label index does not exceed the array length

            int labelWidth = mc.font.width(label);
            float scaleFactor = labelWidth > maxLabelWidth ? (float) maxLabelWidth / labelWidth-0.075f : 1.0f;
            int labelX = xx + (imageWidth - (int) (labelWidth * scaleFactor)) / 2;
            int labelY = yy + imageHeight - 10;

            guiGraphics.pose().pushPose();
            guiGraphics.pose().translate(labelX, labelY, 0);
            guiGraphics.pose().scale(scaleFactor, scaleFactor, 1.0f);
            guiGraphics.drawString(mc.font, label, 0, 0, Color.WHITE.getRGB());
            guiGraphics.pose().popPose();




        }
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }


    @Override
    public void renderBackground(GuiGraphics pGuiGraphics) {
        pGuiGraphics.blit(BG, 0, 0, width, height, width, height, width, height);
    }

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        int imageWidth = 50;
        int imageHeight = 100;
        int spacing = 6;
        for (int i = 0; i < 6; i++) {
            int x = leftPos + 75 + (i % 3) * (imageWidth + spacing);
            int y = (i / 3) * (imageHeight + spacing) - (imageHeight / 2) + topPos  ;
            if (pMouseX >= x && pMouseX <= x + imageWidth && pMouseY >= y && pMouseY <= y + imageHeight) {
                selectedImage = images.get(i);
            }
        }

        return super.mouseClicked(pMouseX, pMouseY, pButton);
    }

    /*
            @Override
        protected void drawPanel(GuiGraphics guiGraphics, int entryRight, int relativeY, Tesselator tess, int mouseX, int mouseY) {
            int imageHeight = 76;
            int spacing = 12;
            for (int index = 0; index < images.size(); index++) {
                this.index = index;
                int yPosition = (int) (this.top + index * (imageHeight + spacing) - scrollDistance) + 24 - 20;
                if (yPosition + imageHeight > this.top && yPosition < this.top + this.height) {
                    ResourceLocation image = images.get(index);
                    guiGraphics.blit(image, this.left, yPosition, 165, imageHeight, 165, imageHeight, 165, imageHeight);
                    if (selectedImage == image) {
                        guiGraphics.fill(this.left, yPosition, this.left + 165, yPosition + 1, Color.BLUE.getRGB());
                        guiGraphics.fill(this.left, yPosition + imageHeight - 1, this.left + 165, yPosition + imageHeight, Color.BLUE.getRGB());
                        guiGraphics.fill(this.left, yPosition, this.left + 1, yPosition + imageHeight, Color.BLUE.getRGB());
                        guiGraphics.fill(this.left + 165 - 1, yPosition, this.left + 165, yPosition + imageHeight, Color.BLUE.getRGB());
                    }
                }
            }
        }


        @Override
        public @NotNull NarrationPriority narrationPriority() {
            return NarrationPriority.FOCUSED;
        }

        @Override
        public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
            super.render(guiGraphics, mouseX, mouseY, partialTick);
        }

        @Override
        public void updateNarration(NarrationElementOutput narrationElementOutput) {
            this.images.forEach(image -> narrationElementOutput.add(NarratedElementType.USAGE, Component.literal(image.toString())));
        }

        @Override
        public boolean mouseReleased(double mouseX, double mouseY, int button) {
            return super.mouseReleased(mouseX, mouseY, button);
        }

        @Override
        public boolean mouseClicked(double mouseX, double mouseY, int button) {

            int imageHeight = 76;
            int spacing = 12;
            System.out.println(mouseX);
            System.out.println(this.left);
            System.out.println(CultureSelectionScreen.guiLeft);
            if (!(mouseX>350)) {
                for (int index = 0; index < images.size(); index++) {
                    int yPosition = (int) (this.top + index * (imageHeight + spacing) - scrollDistance) + 24 - 20;
                    if (mouseX >= this.left && mouseX <= this.left + 165 && mouseY >= yPosition && mouseY <= yPosition + imageHeight) {
                        selectedImage = images.get(index);
                    }
                }
            }
            return super.mouseClicked(mouseX, mouseY, button);
        }
    }
     */
}


