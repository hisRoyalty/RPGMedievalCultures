package com.hisroyalty.rmg.network;

import com.hisroyalty.rmg.RMGMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {
    private static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(RMGMod.MODID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;



        net.messageBuilder(SelectCultureS2CScreen.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(SelectCultureS2CScreen::decode)
                .encoder(SelectCultureS2CScreen::encode)
                .consumerMainThread(SelectCultureS2CScreen::handle)
                .add();

        net.messageBuilder(NextScreenS2C.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(NextScreenS2C::decode)
                .encoder(NextScreenS2C::encode)
                .consumerMainThread(NextScreenS2C::handle)
                .add();

        net.messageBuilder(SelectCultureC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(SelectCultureC2SPacket::decode)
                .encoder(SelectCultureC2SPacket::encode)
                .consumerMainThread(SelectCultureC2SPacket::handle)
                .add();






    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
}
