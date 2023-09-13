package com.figure8.Networktests;

import com.figure8.util.IEntityDataSaver;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;

public class SquiggleSyncDataS2CPacket {
    public static <PacketSender> void receive(MinecraftClient client, ClientPlayNetworkHandler handler,
                                              PacketByteBuf buf, PacketSender responseSender) {
        ((IEntityDataSaver) client.player).getPersistentData().putInt("squiggles", buf.readInt());
    }
}
