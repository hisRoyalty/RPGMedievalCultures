package com.hisroyalty.rmg.lp;

import com.hisroyalty.rmg.Config;
import com.hisroyalty.rmg.RMGMod;
import com.hisroyalty.rmg.Util2;
import com.hisroyalty.rmg.chat.TextFormatter;
import com.hisroyalty.rmg.lp.event.MyListener;
import com.mojang.authlib.GameProfile;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.cacheddata.CachedMetaData;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import net.luckperms.api.node.NodeType;
import net.luckperms.api.node.types.InheritanceNode;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;

public class LuckpermsIntegration {
    private static String playerNameFormat = Config.playerNameFormat;






    public static LuckPerms getLuckperms() {
        LuckPerms luckPerms;
        try {
            luckPerms = LuckPermsProvider.get();
        } catch (IllegalStateException e) {
            return null;
        }
        return luckPerms;
    }
    public static void doLuckpermsStuff(String s, ServerPlayer serverPlayer) {

        Vec3 empSpawn = new Vec3(Config.empSpawnX, Config.empSpawnY, Config.empSpawnZ);
        Vec3 kngSpawn = new Vec3(Config.kngSpawnX, Config.kngSpawnY, Config.kngSpawnZ);
        Vec3 mthSpawn = new Vec3(Config.mthSpawnX, Config.mthSpawnY, Config.mthSpawnZ);
        MinecraftServer server = serverPlayer.getServer();

        ServerLevel targetLevel = server.getLevel(ServerLevel.OVERWORLD);
        LuckPerms luckPerms = getLuckperms();

        if (s.contains("emp")) {

            Group g = luckPerms.getGroupManager().getGroup("empire");
            luckPerms.getUserManager().modifyUser(serverPlayer.getUUID(), (User user) -> {

                user.data().clear(NodeType.INHERITANCE::matches);

                Node node = InheritanceNode.builder(g).build();

                user.data().add(node);


                Util2.sendPlayerToDimension(serverPlayer, targetLevel, empSpawn);
            });


        } else if (s.contains("king")) {
            Group g = luckPerms.getGroupManager().getGroup("kingdom");
            luckPerms.getUserManager().modifyUser(serverPlayer.getUUID(), (User user) -> {

                user.data().clear(NodeType.INHERITANCE::matches);

                Node node = InheritanceNode.builder(g).build();

                user.data().add(node);

                Util2.sendPlayerToDimension(serverPlayer, targetLevel, kngSpawn);
            });
        } else if (s.contains("mth")) {
            Group g = luckPerms.getGroupManager().getGroup("motherland");
            luckPerms.getUserManager().modifyUser(serverPlayer.getUUID(), (User user) -> {

                user.data().clear(NodeType.INHERITANCE::matches);

                Node node = InheritanceNode.builder(g).build();

                user.data().add(node);



                Util2.sendPlayerToDimension(serverPlayer, targetLevel, mthSpawn);
            });
        } else if (s.contains("common")) {
            Group g = luckPerms.getGroupManager().getGroup("commonwealth");
            luckPerms.getUserManager().modifyUser(serverPlayer.getUUID(), (User user) -> {

                user.data().clear(NodeType.INHERITANCE::matches);

                Node node = InheritanceNode.builder(g).build();

                user.data().add(node);
            });
        } else if (s.contains("highland")) {
            Group g = luckPerms.getGroupManager().getGroup("highland");
            luckPerms.getUserManager().modifyUser(serverPlayer.getUUID(), (User user) -> {

                user.data().clear(NodeType.INHERITANCE::matches);

                Node node = InheritanceNode.builder(g).build();

                user.data().add(node);
            });
        } else if (s.contains("nomad")) {
            Group g = luckPerms.getGroupManager().getGroup("nomad");
            luckPerms.getUserManager().modifyUser(serverPlayer.getUUID(), (User user) -> {

                user.data().clear(NodeType.INHERITANCE::matches);

                Node node = InheritanceNode.builder(g).build();

                user.data().add(node);
            });
        }

    }

    public static boolean playerHasGroup(ServerPlayer player) {
        LuckPerms luckPerms = getLuckperms();
        User user = luckPerms.getUserManager().getUser(player.getUUID());
        if (user == null) {
            return false;
        }
        return user.getInheritedGroups(user.getQueryOptions()).stream().anyMatch(group -> group.getName().equals("empire") || group.getName().equals("kingdom") || group.getName().equals("motherland") || group.getName().equals("commonwealth") || group.getName().equals("highland") || group.getName().equals("nomad") || group.getName().equals("kor"));
    }

    public static void setCulture(ServerPlayer player, String culture) {
        LuckPerms luckPerms = getLuckperms();

        if (culture.equals("empire")) {
            Group g = luckPerms.getGroupManager().getGroup("empire");
            luckPerms.getUserManager().modifyUser(player.getUUID(), (User user) -> {

                user.data().clear(NodeType.INHERITANCE::matches);

                Node node = InheritanceNode.builder(g).build();

                user.data().add(node);
            });
        }
        if (culture.equals("kingdom")) {
            Group g = luckPerms.getGroupManager().getGroup("kingdom");
            luckPerms.getUserManager().modifyUser(player.getUUID(), (User user) -> {

                user.data().clear(NodeType.INHERITANCE::matches);

                Node node = InheritanceNode.builder(g).build();

                user.data().add(node);
            });
        }
        if (culture.equals("motherland")) {
            Group g = luckPerms.getGroupManager().getGroup("motherland");
            luckPerms.getUserManager().modifyUser(player.getUUID(), (User user) -> {

                user.data().clear(NodeType.INHERITANCE::matches);

                Node node = InheritanceNode.builder(g).build();

                user.data().add(node);
            });
        }
        if (culture.equals("commonwealth")) {
            Group g = luckPerms.getGroupManager().getGroup("commonwealth");
            luckPerms.getUserManager().modifyUser(player.getUUID(), (User user) -> {

                user.data().clear(NodeType.INHERITANCE::matches);

                Node node = InheritanceNode.builder(g).build();

                user.data().add(node);
            });
        }
        if (culture.equals("highland")) {
            Group g = luckPerms.getGroupManager().getGroup("highland");
            luckPerms.getUserManager().modifyUser(player.getUUID(), (User user) -> {

                user.data().clear(NodeType.INHERITANCE::matches);

                Node node = InheritanceNode.builder(g).build();

                user.data().add(node);
            });
        }
        if (culture.equals("nomad")) {
            Group g = luckPerms.getGroupManager().getGroup("nomad");
            luckPerms.getUserManager().modifyUser(player.getUUID(), (User user) -> {

                user.data().clear(NodeType.INHERITANCE::matches);

                Node node = InheritanceNode.builder(g).build();

                user.data().add(node);
            });
        }
        if (culture.equals("kor")) {
            Group g = luckPerms.getGroupManager().getGroup("kor");
            luckPerms.getUserManager().modifyUser(player.getUUID(), (User user) -> {

                user.data().clear(NodeType.INHERITANCE::matches);

                Node node = InheritanceNode.builder(g).build();

                user.data().add(node);
            });
        }


    }
    public static String getCulture(ServerPlayer player) {
        LuckPerms luckPerms;
        try {
            luckPerms = LuckPermsProvider.get();
        } catch (IllegalStateException e) {
            return null;
        }
        User user = luckPerms.getUserManager().getUser(player.getUUID());
        if (user == null) {
            return null;
        }
        return user.getInheritedGroups(user.getQueryOptions()).stream().filter(group -> group.getName().equals("empire") || group.getName().equals("kingdom") || group.getName().equals("motherland") || group.getName().equals("commonwealth") || group.getName().equals("highland") || group.getName().equals("nomad") || group.getName().equals("kor3")).findFirst().map(Group::getName).orElse(null);
    }


    public static String getPlayerPrefix(ServerPlayer player) {
        LuckPerms luckPerms;
        try {
            luckPerms = LuckPermsProvider.get();
        } catch (IllegalStateException e) {
            return null;
        }
        User user = luckPerms.getUserManager().getUser(player.getUUID());
        if (user == null) {
            return null;
        }
        return user.getCachedData().getMetaData().getPrefix();
    }

    public static String getPlayerSuffix(ServerPlayer player) {
        LuckPerms luckPerms;
        try {
            luckPerms = LuckPermsProvider.get();
        } catch (IllegalStateException e) {
            return null;
        }
        User user = luckPerms.getUserManager().getUser(player.getUUID());
        if (user == null) {
            return null;
        }
        return user.getCachedData().getMetaData().getSuffix();
    }

    public static String[] getPlayerPrefixAndSuffix(ServerPlayer player) {
        try {
            User usr = getLuckperms().getUserManager().getUser(player.getUUID());
            if(usr == null) return null;
            CachedMetaData mdat = usr.getCachedData().getMetaData();
            return new String[] { mdat.getPrefix(), mdat.getSuffix() };
        } catch(IllegalStateException ise) {
            return null;
        }
    }

    public static String getRawText(ServerPlayer player) {
        String dat[] = LuckpermsIntegration.getPlayerPrefixAndSuffix(player);
        String pfx = "", sfx = "";
        if(dat != null) {
            pfx = dat[0] != null ? dat[0] : "";
            sfx = dat[1] != null ? dat[1] : "";
        }
        String fmat = "$prefix$name";
        return fmat.replace("$prefix", pfx).replace("$name", player.getName().getString()).replace("$suffix", sfx);

    }

    public static MutableComponent getFormattedText(ServerPlayer player) {
    return TextFormatter.stringToFormattedText(getRawText(player));
    }

}
