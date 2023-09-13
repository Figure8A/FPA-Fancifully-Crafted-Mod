package com.figure8.Networktests;

import com.figure8.util.IEntityDataSaver;
import com.figure8.util.SquiggleAdd;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.block.Blocks;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;

public class SquiggleC2SPacket {
    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender) {
        // Everything here happens ONLY on the Server!
        ServerWorld world = (ServerWorld) player.getWorld();
            // Notify the

            // actually add the water level to the player
            SquiggleAdd.addSquiggles(((IEntityDataSaver) player), 1);


            // outputting the current thirst level of player
            player.sendMessage(Text.literal("Squiggles: " + ((IEntityDataSaver) player).getPersistentData().getInt("squiggles"))
                    .fillStyle(Style.EMPTY.withColor(Formatting.GOLD)), true);


        SquiggleAdd.syncSquiggles(((IEntityDataSaver) player).getPersistentData().getInt("squiggles"), player);
        }

}
