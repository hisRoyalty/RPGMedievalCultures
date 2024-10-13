package com.hisroyalty.rmg.command;

import com.hisroyalty.rmg.Config;
import com.hisroyalty.rmg.Util;
import com.hisroyalty.rmg.Util2;
import com.hisroyalty.rmg.lp.LuckpermsIntegration;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

import javax.swing.text.html.parser.Entity;

import static net.minecraft.commands.Commands.argument;

import java.util.Objects;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.phys.Vec3;

import java.util.HashMap;
import java.util.Map;

public class CultureCommand {
    private static final Map<String, BlockPos> cultureSpawns = new HashMap<>();

    public CultureCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("culture")
                        .then(
                                Commands.literal("set")
                                        .then(
                                                Commands.argument("target", EntityArgument.player())
                                                        .then(
                                                                Commands.argument("cultureType", StringArgumentType.word())
                                                                        .executes((command) -> {
                                                                            ServerPlayer source = command.getSource().getPlayerOrException();

                                                                            ServerPlayer target = EntityArgument.getPlayer(command, "target");
                                                                            String cultureType = StringArgumentType.getString(command, "cultureType");
                                                                            if (cultureType == null) {
                                                                                target.sendSystemMessage(
                                                                                        Component.literal("You must specify a culture type!")
                                                                                                .withStyle(ChatFormatting.RED), false);
                                                                                return 0;
                                                                            }
                                                                                    switch (cultureType) {
                                                                                        case "empire":
                                                                                            LuckpermsIntegration.setCulture(target, "empire");
                                                                                            source.sendSystemMessage(
                                                                                                    Component.literal("You have set " + target.getName().getString() + "'s culture to The Empire")
                                                                                                            .withStyle(ChatFormatting.GREEN), false);
                                                                                            break;
                                                                                        case "kingdom":
                                                                                            LuckpermsIntegration.setCulture(target, "kingdom");
                                                                                            source.sendSystemMessage(
                                                                                                    Component.literal("You have set " + target.getName().getString() + "'s culture to The Kingdom")
                                                                                                            .withStyle(ChatFormatting.GREEN), false);
                                                                                            break;
                                                                                        case "motherland":
                                                                                            LuckpermsIntegration.setCulture(target, "motherland");
                                                                                            source.sendSystemMessage(
                                                                                                    Component.literal("You have set " + target.getName().getString() + "'s culture to The Motherland")
                                                                                                            .withStyle(ChatFormatting.GREEN), false);
                                                                                            break;

                                                                                        case "commonwealth":
                                                                                            LuckpermsIntegration.setCulture(target, "commonwealth");
                                                                                            source.sendSystemMessage(
                                                                                                    Component.literal("You have set " + target.getName().getString() + "'s culture to The Commonwealth")
                                                                                                            .withStyle(ChatFormatting.GREEN), false);
                                                                                            break;
                                                                                        case "highland":
                                                                                            LuckpermsIntegration.setCulture(target, "highland");
                                                                                            source.sendSystemMessage(
                                                                                                    Component.literal("You have set " + target.getName().getString() + "'s culture to The Highland")
                                                                                                            .withStyle(ChatFormatting.GREEN), false);
                                                                                            break;
                                                                                        case "nomad":
                                                                                            LuckpermsIntegration.setCulture(target, "nomad");
                                                                                            source.sendSystemMessage(
                                                                                                    Component.literal("You have set " + target.getName().getString() + "'s culture to The Nomad")
                                                                                                            .withStyle(ChatFormatting.GREEN), false);
                                                                                            break;
                                                                                        case "kor":
                                                                                            LuckpermsIntegration.setCulture(target, "kor");
                                                                                            source.sendSystemMessage(
                                                                                                    Component.literal("You have set " + target.getName().getString() + "'s culture to The Kingdom of Rose")
                                                                                                            .withStyle(ChatFormatting.GREEN), false);
                                                                                            break;
                                                                                        default:
                                                                                            source.sendSystemMessage(
                                                                                                    Component.literal("Invalid culture type! Types are: empire, kingdom, motherland, commonwealth, highland, nomad")
                                                                                                            .withStyle(ChatFormatting.RED), false);
                                                                                            break;
                                                                                    }


                                                                            return 1;
                                                                        })
                                                        )
                                        )
                        )
                        .then(
                                Commands.literal("spawn")
                                        .executes((command) -> {
                                            ServerPlayer player = command.getSource().getPlayerOrException();
                                                    Vec3 empSpawn = new Vec3(Config.empSpawnX, Config.empSpawnY, Config.empSpawnZ);
                                                    Vec3 kngSpawn = new Vec3(Config.kngSpawnX, Config.kngSpawnY, Config.kngSpawnZ);
                                                    Vec3 mthSpawn = new Vec3(Config.mthSpawnX, Config.mthSpawnY, Config.mthSpawnZ);
                                                    Vec3 cmwSpawn = new Vec3(Config.cmwSpawnX, Config.cmwSpawnY, Config.cmwSpawnZ);
                                                    Vec3 hghSpawn = new Vec3(Config.hghSpawnX, Config.hghSpawnY, Config.hghSpawnZ);
                                                    Vec3 nmdSpawn = new Vec3(Config.nmdSpawnX, Config.nmdSpawnY, Config.nmdSpawnZ);
                                                    Vec3 korSpawn = new Vec3(Config.korSpawnX, Config.korSpawnY, Config.korSpawnZ);
                                                    String culture = LuckpermsIntegration.getCulture(player);
                                                    MinecraftServer server = player.getServer();
                                                    ServerLevel targetLevel = server.getLevel(ServerLevel.OVERWORLD);

                                                    if (Objects.equals(culture, "empire")) {
                                                        Util2.sendPlayerToDimension(player, targetLevel, empSpawn);
                                                    } else if (Objects.equals(culture, "kingdom")) {
                                                        Util2.sendPlayerToDimension(player, targetLevel, kngSpawn);
                                                    } else if (Objects.equals(culture, "motherland")) {
                                                        Util2.sendPlayerToDimension(player, targetLevel, mthSpawn);
                                                    } else if (Objects.equals(culture, "commonwealth")) {
                                                        Util2.sendPlayerToDimension(player, targetLevel, cmwSpawn);
                                                    } else if (Objects.equals(culture, "highland")) {
                                                        Util2.sendPlayerToDimension(player, targetLevel, hghSpawn);
                                                    } else if (Objects.equals(culture, "nomad")) {
                                                        Util2.sendPlayerToDimension(player, targetLevel, nmdSpawn);
                                                    }
                                                    else if (Objects.equals(culture, "kor")) {
                                                        Util2.sendPlayerToDimension(player, targetLevel, korSpawn);
                                                    }
                                                    else if (culture == null) {
                                                        player.sendSystemMessage(
                                                                Component.literal("You must set your culture before using this command!")
                                                                        .withStyle(ChatFormatting.RED), false);
                                                    } else {
                                                        player.sendSystemMessage(
                                                                Component.literal("An Unexpected Error Occurred!")
                                                                        .withStyle(ChatFormatting.RED), false);

                                                    }


                                            return 1;
                                        })
                        )
        );
    }
}

