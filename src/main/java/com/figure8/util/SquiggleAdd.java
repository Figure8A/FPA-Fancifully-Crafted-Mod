package com.figure8.util;

import com.figure8.Networktests.ModNetworkRegisters;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

public class SquiggleAdd {
    public static int addSquiggles(IEntityDataSaver player, int amount) {
        NbtCompound nbt = player.getPersistentData();
        int squiggles = nbt.getInt("squiggles");
        squiggles += amount;
        nbt.putInt("squiggles", squiggles);
        syncSquiggles(squiggles, (ServerPlayerEntity) player);
        return squiggles;
    }

    public static void syncSquiggles(int thirst, ServerPlayerEntity player) {
        PacketByteBuf buffer = PacketByteBufs.create();
        buffer.writeInt(thirst);
        ServerPlayNetworking.send(player, ModNetworkRegisters.SQUIGGLE_SYNC_ID, buffer);
    }

}
