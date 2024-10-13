package com.hisroyalty.rmg.screen;

import com.hisroyalty.rmg.RMGMod;
import com.hisroyalty.rmg.client.RMGClient;
import com.hisroyalty.rmg.network.ModMessages;
import com.hisroyalty.rmg.network.SelectCultureC2SPacket;
import com.hisroyalty.rmg.screen.widget.HoverableImageButton;
import com.mojang.blaze3d.systems.RenderSystem;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import net.luckperms.api.node.NodeType;
import net.luckperms.api.node.types.InheritanceNode;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;

public class ConfirmScreen extends Screen {

    public ConfirmScreen() {
        super(Component.translatable("screen.cultures.choose_culture"));
    }

    private int middle;
    private final ResourceLocation EMP = new ResourceLocation(RMGMod.MODID, "textures/gui/emp.png");
    private final ResourceLocation KNG = new ResourceLocation(RMGMod.MODID, "textures/gui/king.png");
    private final ResourceLocation MTH = new ResourceLocation(RMGMod.MODID, "textures/gui/mth.png");
    private final ResourceLocation CMW = new ResourceLocation(RMGMod.MODID, "textures/gui/common.png");
    private final ResourceLocation HGL = new ResourceLocation(RMGMod.MODID, "textures/gui/highland.png");
    private final ResourceLocation NMD = new ResourceLocation(RMGMod.MODID, "textures/gui/nomad.png");
    private final ResourceLocation BG = new ResourceLocation(RMGMod.MODID, "textures/gui/plainbg.png");

    private final ResourceLocation CONFIRM = new ResourceLocation(RMGMod.MODID, "textures/gui/confirm.png");
    private final ResourceLocation HOVERED_CONFIRM = new ResourceLocation(RMGMod.MODID, "textures/gui/hovered_confirm.png");

    private ResourceLocation BACK = new ResourceLocation(RMGMod.MODID, "textures/gui/back.png");
    private ResourceLocation HOVERED_BACK = new ResourceLocation(RMGMod.MODID, "textures/gui/hovered_back.png");

    @Override
    protected void init() {
        super.init();
        this.middle = this.width / 2;

        TestButton next = new TestButton(this.width - 125, this.height - 40, 120, 20, 0, 0, 0, CONFIRM, HOVERED_CONFIRM, 120, 20, button -> {
            RMGClient.NEXT_PAGE.set(false);
            Minecraft.getInstance().setScreen(null);
            String culture = CultureSelectionScreen.selectedImage.toString();
            ModMessages.sendToServer(new SelectCultureC2SPacket(CultureSelectionScreen.selectedImage));
        });
        this.addRenderableWidget(next);
        TestButton back;
        back = new TestButton(this.width/10, this.height - 40, 70, 20, 0, 0, 0, BACK, HOVERED_BACK, 70, 20,  button -> {
            CultureSelectionScreen.selectedImage = null;
            Minecraft.getInstance().setScreen(null);
            RMGClient.AWAITING_DISPLAY.set(true);
            RMGClient.NEXT_PAGE.set(false);
        });
        this.addRenderableWidget(back);
    }


    @Override
    public void renderBackground(GuiGraphics pGuiGraphics) {
        pGuiGraphics.blit(BG, 0, 0, width, height, width, height, width, height);
    }

    @Override
    protected <T extends GuiEventListener & Renderable & NarratableEntry> T addRenderableWidget(T pWidget) {
        return super.addRenderableWidget(pWidget);
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        String culture = CultureSelectionScreen.selectedImage.toString();
        String cultureName = "";
        ResourceLocation l = null;
        if (culture.contains("emp")) {
            cultureName = "The Empire";
            l = EMP;
        } else if (culture.contains("king")) {
            cultureName = "The Kingdom";
            l = KNG;
        } else if (culture.contains("mth")) {
            cultureName = "The Motherland";
            l = MTH;
        } else if (culture.contains("common")) {
            cultureName = "The Commonwealth";
            l = CMW;
        } else if (culture.contains("highland")) {
            cultureName = "Highland";
            l = HGL;
        } else if (culture.contains("nomad")) {
            cultureName = "Nomads";
            l = NMD;
        }
        int textWidth = this.font.width("Are you sure you want to choose this culture?");
        int textWidth2 = this.font.width("Culture selected: " + cultureName);
        int x = (this.width - textWidth) / 2;
        int x2 = (this.width - textWidth2) / 2;
        int x3 = (this.width-60)/2;
        int y = 50;
        int y2 = y + 20;
        this.renderBackground(pGuiGraphics);
        pGuiGraphics.drawString(font, "Are you sure you want to choose this culture?", x, y, Color.WHITE.getRGB());
        pGuiGraphics.drawString(font, "Culture selected: " + cultureName, x2, y2, Color.WHITE.getRGB());
        if (l!=null) pGuiGraphics.blit(l, x3, 100, 0, 0, 50, 95, 50, 95);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
    }
}
