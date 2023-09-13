package com.figure8.Networktests;

import com.figure8.fpaore;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
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
