package com.figure8.util;

import com.figure8.Networktests.ModNetworkRegisters;
import com.figure8.fpaore;
import com.figure8.sound.ModSounds;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;

public class SquiggleAdd {
    public static int addSquiggles(IEntityDataSaver player, int amount) {
        NbtCompound nbt = player.getPersistentData();
        int squiggles = nbt.getInt("squiggles");
        squiggles += amount;
        nbt.putInt("squiggles", squiggles);
        syncSquiggles(squiggles, (ServerPlayerEntity) player);

        if (amount > 100){
            ItemStack item = new ItemStack(fpaore.mayor_of_undying);
            ((ServerPlayerEntity) player).getInventory().addPickBlock(item);

        }
        return squiggles;
    }

    public static void syncSquiggles(int thirst, ServerPlayerEntity player) {
        PacketByteBuf buffer = PacketByteBufs.create();
        buffer.writeInt(thirst);
        ServerPlayNetworking.send(player, ModNetworkRegisters.SQUIGGLE_SYNC_ID, buffer);
    }

}
