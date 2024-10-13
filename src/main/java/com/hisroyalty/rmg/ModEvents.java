package com.hisroyalty.rmg;

import com.hisroyalty.rmg.chat.TextFormatter;
import com.hisroyalty.rmg.command.CultureCommand;
import com.hisroyalty.rmg.lp.LuckpermsIntegration;
import com.hisroyalty.rmg.network.ModMessages;
import com.hisroyalty.rmg.network.NextScreenS2C;
import com.hisroyalty.rmg.network.SelectCultureS2CScreen;
import com.hisroyalty.rmg.util.ClientUtil;
import com.mojang.authlib.GameProfile;
import com.mojang.datafixers.kinds.IdF;
import net.luckperms.api.LuckPerms;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

import java.awt.*;
import java.util.Objects;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = RMGMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {


    @SubscribeEvent
    public static void onLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (ModList.get().isLoaded("luckperms")) {
            if (event.getEntity() instanceof ServerPlayer s && !s.level().isClientSide() && !LuckpermsIntegration.playerHasGroup(s)) {
                ModMessages.sendToPlayer(new SelectCultureS2CScreen(), s);
            }

        }


    }
    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
        new CultureCommand(event.getDispatcher());
        ConfigCommand.register(event.getDispatcher());
    }

    /*
    @SubscribeEvent
    public static void TabEvent(PlayerEvent.TabListNameFormat e) {
        if(e.getEntity() != null && e.getEntity() instanceof ServerPlayer s) {
            if (ModList.get().isLoaded("luckperms"))
            e.setDisplayName(LuckpermsIntegration.getFormattedText(s));
        }
    }

    @SubscribeEvent
    public static void onNameFormatEvent(PlayerEvent.NameFormat e) {
        if(e.getEntity() != null && e.getEntity() instanceof ServerPlayer s) {
            if (ModList.get().isLoaded("luckperms")) {
                e.setDisplayname(LuckpermsIntegration.getFormattedText(s));
            }
        }
    }


    @SubscribeEvent
    public static void onServerChat(ServerChatEvent e) {

        ServerPlayer player = e.getPlayer();

        String msg = e.getRawText();

        if(msg == null || msg.length() <= 0) return;
        String name = LuckpermsIntegration.getRawText(player);
        String chatMessageFormat = "$name: $msg";
        String fmat = chatMessageFormat.replace("$name", name);
        MutableComponent beforeMsg = TextFormatter.stringToFormattedText(fmat.substring(0, fmat.indexOf("$msg")));
        MutableComponent afterMsg = TextFormatter.stringToFormattedText(fmat.substring(fmat.indexOf("$msg") + 4, fmat.length()));
        MutableComponent msgComp = TextFormatter.stringToFormattedText(msg, true, true);
        e.setMessage(beforeMsg.append(msgComp.append(afterMsg)));


    }

     */












}
