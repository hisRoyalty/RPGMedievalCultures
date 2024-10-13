package com.hisroyalty.rmg.lp.event;

import com.hisroyalty.rmg.Config;
import com.hisroyalty.rmg.RMGMod;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.event.EventBus;
import net.luckperms.api.event.node.NodeAddEvent;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.server.ServerLifecycleHooks;

public class MyListener {
    private final RMGMod mod;
    private final LuckPerms luckPerms;

    public MyListener(RMGMod mod, LuckPerms luckPerms) {
        this.mod = mod;
        this.luckPerms = luckPerms;
    }

    public void register() {
        EventBus eventBus = luckPerms.getEventBus();
        eventBus.subscribe(this.mod, NodeAddEvent.class, this::onNodeAdd);
    }

    private void onNodeAdd(NodeAddEvent e) {
        if (!e.isUser()) {
            return;
        }
        User target = (User) e.getTarget();
        Node node = e.getNode();
        ServerPlayer player = ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayer(target.getUniqueId());

        if (player==null) {
            return;
        }
        BlockPos empSpawn = new BlockPos(Config.empSpawnX, Config.empSpawnY, Config.empSpawnZ);
        BlockPos kngSpawn = new BlockPos(Config.kngSpawnX, Config.kngSpawnY, Config.kngSpawnZ);
        BlockPos mthSpawn = new BlockPos(Config.mthSpawnX, Config.mthSpawnY, Config.mthSpawnZ);
        BlockPos cmwSpawn = new BlockPos(Config.cmwSpawnX, Config.cmwSpawnY, Config.cmwSpawnZ);
        BlockPos hglSpawn = new BlockPos(Config.hghSpawnX, Config.hghSpawnY, Config.hghSpawnZ);
        BlockPos nmdSpawn = new BlockPos(Config.nmdSpawnX, Config.nmdSpawnY, Config.nmdSpawnZ);
        BlockPos korSpawn = new BlockPos(Config.korSpawnX, Config.korSpawnY, Config.korSpawnZ);
            if (node.getKey().equals("group.empire")) {
                player.setRespawnPosition(ServerLevel.OVERWORLD, empSpawn, 0.0F, true, false);
            } else if (node.getKey().equals("group.kingdom")) {
                player.setRespawnPosition(ServerLevel.OVERWORLD, kngSpawn, 0.0F, true, false);
            } else if (node.getKey().equals("group.motherland")) {
                player.setRespawnPosition(ServerLevel.OVERWORLD, mthSpawn, 0.0F, true, false);
            } else if (node.getKey().equals("group.commonwealth")) {
                player.setRespawnPosition(ServerLevel.OVERWORLD, cmwSpawn, 0.0F, true, false);
            } else if (node.getKey().equals("group.highland")) {
                player.setRespawnPosition(ServerLevel.OVERWORLD, hglSpawn, 0.0F, true, false);
            } else if (node.getKey().equals("group.nomad")) {
                player.setRespawnPosition(ServerLevel.OVERWORLD, nmdSpawn, 0.0F, true, false);
            } else if (node.getKey().equals("group.kor")) {
                player.setRespawnPosition(ServerLevel.OVERWORLD, korSpawn, 0.0F, true, false);
            }


    }
}
