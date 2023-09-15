package com.figure8.util;

import com.figure8.Networktests.ModNetworkRegisters;
import com.figure8.fpaore;
import com.figure8.sound.ModSounds;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SquiggleAdd {
    public static int addSquiggles(IEntityDataSaver player, int amount, ServerWorld world, BlockPos pos) {
        NbtCompound nbt = player.getPersistentData();
        int squiggles = nbt.getInt("squiggles");
        squiggles += amount;
        nbt.putInt("squiggles", squiggles);
        int step = 20;
        for (int i = 0; i <= 100000; i+=step)
            if (squiggles == i){
            MinecraftClient client = MinecraftClient.getInstance();
            client.particleManager.addEmitter(((ServerPlayerEntity) player), ParticleTypes.TOTEM_OF_UNDYING, 30);
            ItemStack item = new ItemStack(fpaore.mayor_of_undying);
            ((ServerPlayerEntity) player).getInventory().offer(item, false);
            world.playSound(null, pos, ModSounds.EXTRALIFE, SoundCategory.MASTER, 2f, 1.0f);
        }
        syncSquiggles(squiggles, (ServerPlayerEntity) player);
        return squiggles;
    }


    public static void syncSquiggles(int thirst, ServerPlayerEntity player) {
        PacketByteBuf buffer = PacketByteBufs.create();
        buffer.writeInt(thirst);
        ServerPlayNetworking.send(player, ModNetworkRegisters.SQUIGGLE_SYNC_ID, buffer);
    }

}
