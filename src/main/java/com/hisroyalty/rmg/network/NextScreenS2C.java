package com.hisroyalty.rmg.network;



import com.hisroyalty.rmg.client.RMGClient;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public record NextScreenS2C() {
    public static NextScreenS2C decode(FriendlyByteBuf buf) {
        return new NextScreenS2C();
    }

    public void encode(FriendlyByteBuf buf) {
    }

    public void handle(Supplier<NetworkEvent.Context> contextSupplier) {
        contextSupplier.get().enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () ->{
            RMGClient.AWAITING_DISPLAY.set(false);
            RMGClient.NEXT_PAGE.set(true);
        }));
        contextSupplier.get().setPacketHandled(true);
    }
}
