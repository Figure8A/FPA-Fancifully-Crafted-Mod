package com.figure8.Networktests;

import com.figure8.fpaore;
import com.figure8.util.IEntityDataSaver;
import com.figure8.util.SquiggleAdd;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.CustomPayloadS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class ModNetworkRegisters {
    public static final Identifier SQUIGGLE_ID = new Identifier(fpaore.MOD_ID, "squiggles");
    public static final Identifier SQUIGGLE_SYNC_ID = new Identifier(fpaore.MOD_ID, "squiggle_sync");


    public static void registerC2SPackets() {

        ServerPlayNetworking.registerGlobalReceiver(SQUIGGLE_ID, SquiggleC2SPacket::receive);
    }

    public static void registerS2CPackets() {
        ClientPlayNetworking.registerGlobalReceiver(SQUIGGLE_SYNC_ID, SquiggleSyncDataS2CPacket::receive);
    }


}
