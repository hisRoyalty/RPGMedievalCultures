package com.hisroyalty.rmg;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;

public class Util2 {
    public static void sendPlayerToDimension(ServerPlayer serverPlayer, ServerLevel targetLevel, Vec3 targetVec)
    {
        targetLevel.getChunk(new BlockPos((int)targetVec.x, (int)targetVec.y, (int)targetVec.z));
        serverPlayer.teleportTo(targetLevel, targetVec.x(), targetVec.y(), targetVec.z(), serverPlayer.getYRot(), serverPlayer.getXRot());
    }
}
