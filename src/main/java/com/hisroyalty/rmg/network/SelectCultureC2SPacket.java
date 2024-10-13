package com.hisroyalty.rmg.network;

import com.hisroyalty.rmg.Config;
import com.hisroyalty.rmg.Util;
import com.hisroyalty.rmg.Util2;
import com.hisroyalty.rmg.lp.LuckpermsIntegration;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import net.luckperms.api.node.NodeType;
import net.luckperms.api.node.types.InheritanceNode;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

import java.util.Objects;
import java.util.function.Supplier;

public record SelectCultureC2SPacket(ResourceLocation culture) {

    public static SelectCultureC2SPacket decode(FriendlyByteBuf buf) {
        return new SelectCultureC2SPacket(buf.readResourceLocation());
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeResourceLocation(this.culture());
    }

    public void handle(Supplier<NetworkEvent.Context> contextSupplier) {
        contextSupplier.get().enqueueWork(() -> {

                    ServerPlayer sender = contextSupplier.get().getSender();
                    String s = this.culture().toString();
                    if (sender == null) return;
                    LuckpermsIntegration.doLuckpermsStuff(s, sender);
                    Vec3 empSpawn = new Vec3(Config.empSpawnX, Config.empSpawnY, Config.empSpawnZ);
                    Vec3 kngSpawn = new Vec3(Config.kngSpawnX, Config.kngSpawnY, Config.kngSpawnZ);
                    Vec3 mthSpawn = new Vec3(Config.mthSpawnX, Config.mthSpawnY, Config.mthSpawnZ);
                    Vec3 cmwSpawn = new Vec3(Config.cmwSpawnX, Config.cmwSpawnY, Config.cmwSpawnZ);
                    Vec3 hghSpawn = new Vec3(Config.hghSpawnX, Config.hghSpawnY, Config.hghSpawnZ);
                    Vec3 nmdSpawn = new Vec3(Config.nmdSpawnX, Config.nmdSpawnY, Config.nmdSpawnZ);
            MinecraftServer server = sender.getServer();
            ServerLevel targetLevel = server.getLevel(ServerLevel.OVERWORLD);

            if (s.contains("emp")) {
                Util2.sendPlayerToDimension(sender, targetLevel, empSpawn);
            } else if (s.contains("king")) {
                Util2.sendPlayerToDimension(sender, targetLevel, kngSpawn);
            } else if (s.contains("mth")) {
                Util2.sendPlayerToDimension(sender, targetLevel, mthSpawn);
            } else if (s.contains("common")) {
                Util2.sendPlayerToDimension(sender, targetLevel, cmwSpawn);
            } else if (s.contains("highland")) {
                Util2.sendPlayerToDimension(sender, targetLevel, hghSpawn);
            } else if (s.contains("nomad")) {
                Util2.sendPlayerToDimension(sender, targetLevel, nmdSpawn);
            }

        });
        contextSupplier.get().setPacketHandled(true);
    }
}
