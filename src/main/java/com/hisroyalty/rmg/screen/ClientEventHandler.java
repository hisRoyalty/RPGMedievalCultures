package com.hisroyalty.rmg.screen;

import com.hisroyalty.rmg.RMGMod;
import com.hisroyalty.rmg.Util;
import com.hisroyalty.rmg.client.RMGClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkHooks;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = RMGMod.MODID)
public class ClientEventHandler {

    @SubscribeEvent
    public static void renderTick(TickEvent.RenderTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            Minecraft instance = Minecraft.getInstance();
            if (instance.player != null) {
                    if (instance.screen == null && RMGClient.AWAITING_DISPLAY.get()) {
                        Util.displayCultureSelectionScreen(instance);
                    }

                    if (instance.screen == null && RMGClient.NEXT_PAGE.get() && !(RMGClient.AWAITING_DISPLAY.get())) {
                        Util.displayConfirmationScreen(instance);
                    }

            }
        }
    }




}
