package com.hisroyalty.rmg.network;


import com.hisroyalty.rmg.client.RMGClient;
import com.hisroyalty.rmg.lp.LuckpermsIntegration;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public record SelectCultureS2CScreen() {
    public static SelectCultureS2CScreen decode(FriendlyByteBuf buf) {
        return new SelectCultureS2CScreen();
    }

    public void encode(FriendlyByteBuf buf) {
    }

    public void handle(Supplier<NetworkEvent.Context> contextSupplier) {
        contextSupplier.get().enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
                RMGClient.AWAITING_DISPLAY.set(true);
        }));
        contextSupplier.get().setPacketHandled(true);
    }
}

