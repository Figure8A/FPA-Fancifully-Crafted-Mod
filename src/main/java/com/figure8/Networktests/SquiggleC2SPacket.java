package com.figure8.Networktests;

import com.figure8.fpaore;
import com.figure8.sound.ModSounds;
import com.figure8.util.IEntityDataSaver;
import com.figure8.util.SquiggleAdd;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.Sound;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.message.MessageType;
import net.minecraft.network.message.SentMessage;
import net.minecraft.network.message.SignedMessage;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.entry.RegistryEntry;
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
import net.minecraft.util.math.Position;
import net.minecraft.util.math.Vec3d;

import java.util.Objects;
import java.util.Scanner;

import static net.fabricmc.fabric.api.networking.v1.ServerLoginNetworking.getServer;

public class SquiggleC2SPacket {

    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender) {

        ServerWorld world = (ServerWorld) player.getWorld();



        SquiggleAdd.addSquiggles(((IEntityDataSaver) player), 1);


        SquiggleAdd.syncSquiggles(((IEntityDataSaver) player).getPersistentData().getInt("squiggles"), player);



        ItemStack item = new ItemStack(fpaore.pants);
        NbtCompound nbt = ((IEntityDataSaver) player).getPersistentData();
        int squiggles = nbt.getInt("squiggles");
        nbt.putInt("squiggles", squiggles);
        int step = 1000;
        int step2 = 10000;

        for (int i = 0; i <= 10000000; i += step)
            for (int b = 0; b <= 1000000; b += step2)
                if (squiggles == i) {
                    player.getInventory().offer(item, true);
                    if (squiggles == b) {
                        player.getInventory().offer(item, true);
                    }
                    world.playSound(null, player.getBlockPos(), ModSounds.EXTRALIFE, SoundCategory.MASTER, 2f, 1.0f);
                    world.spawnParticles(fpaore.SQUIGGLETHINGMGREEN, player.getX(), player.getY(), player.getZ(), 100, 0.5, 0.5, 0.5, 0.1);
                    if(server.isDedicated() && squiggles == b) {
                        server.getPlayerManager().broadcast((Text.literal(player.getEntityName() + " Just Got: " + ((IEntityDataSaver) player).getPersistentData().getInt("squiggles") + " Squiggles!!!").fillStyle(Style.EMPTY.withColor(Formatting.ITALIC).withColor(Formatting.DARK_AQUA))), true);
                    } else {
                        server.getPlayerManager().broadcast((Text.literal(player.getEntityName() + " Just Got: " + ((IEntityDataSaver) player).getPersistentData().getInt("squiggles") + " Squiggles!").fillStyle(Style.EMPTY.withColor(Formatting.GOLD))), true);
                    }
                    return;
                } else if (squiggles == b) {
                    player.getInventory().offer(item, true);
                    player.takeKnockback( 10, 10, 10);
                    for (ServerPlayerEntity playersound : world.getPlayers())
                        playersound.playSound(ModSounds.SQUGGLEANNOUNCE, SoundCategory.PLAYERS, 0.50f, 1);
                    world.spawnParticles(fpaore.SQUIGGLETHINGMGREEN, player.getX(), player.getY(), player.getZ(), 500, 0.5, 0.5, 0.5, 0.2);
            }
    }
}