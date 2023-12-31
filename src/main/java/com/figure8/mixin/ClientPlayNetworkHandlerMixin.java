package com.figure8.mixin;

import com.figure8.fpaore;
import com.figure8.item.PantsItem;
import com.figure8.item.mayor_of_undying;
import com.figure8.sound.ModSounds;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientCommonNetworkHandler;
import net.minecraft.client.network.ClientConnectionState;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.sound.GuardianAttackSoundInstance;
import net.minecraft.client.sound.SnifferDigSoundInstance;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.GuardianEntity;
import net.minecraft.entity.passive.SnifferEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.NetworkThreadUtils;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.listener.TickablePacketListener;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public abstract class ClientPlayNetworkHandlerMixin extends ClientCommonNetworkHandler implements TickablePacketListener,
        ClientPlayPacketListener {
    @Shadow private ClientWorld world;

    protected ClientPlayNetworkHandlerMixin(MinecraftClient client, ClientConnection connection, ClientConnectionState connectionState) {
        super(client, connection, connectionState);
    }


    @Shadow
    private static ItemStack getActiveTotemOfUndying(PlayerEntity player) {
        return null;
    }


    @Inject(at = @At("HEAD"), method = "onEntityStatus")
    public void onEntityStatus(EntityStatusS2CPacket packet, CallbackInfo ci) {
        NetworkThreadUtils.forceMainThread(packet, this, this.client);
        Entity entity = packet.getEntity(this.world);
        if (entity != null) {
            switch (packet.getStatus()) {
                case 63: {
                    this.client.getSoundManager().play(new SnifferDigSoundInstance((SnifferEntity)entity));
                    break;
                }
                case 21: {
                    this.client.getSoundManager().play(new GuardianAttackSoundInstance((GuardianEntity)entity));
                    break;
                }
                case 35: {
                    int i = 40;
                    this.client.particleManager.addEmitter(entity, ParticleTypes.TOTEM_OF_UNDYING, 30);
                    this.world.playSound(entity.getX(), entity.getY(), entity.getZ(), SoundEvents.ITEM_TOTEM_USE, entity.getSoundCategory(), 1.0f, 1.0f, false);
                    if (entity != this.client.player) break;
                    this.client.gameRenderer.showFloatingItem(ClientPlayNetworkHandlerMixin.getActiveTotemOfUndying(this.client.player));
                    break;
                }
                case 110: {
                    int i = 40;
                    this.client.particleManager.addEmitter(entity, fpaore.SQUIGGLETHINGMGREEN, 15);
                    this.world.playSound(entity.getX(), entity.getY(), entity.getZ(), ModSounds.SPIKE_OW_OWHIT, entity.getSoundCategory(), 1.0f, 1.0f, false);
                    if (entity != this.client.player) break;
                    this.client.gameRenderer.showFloatingItem(ClientPlayNetworkHandlerMixin.getActiveTotemOfUndyingP(this.client.player));
                    break;
                }
                case 100:
                    int i = 40;
                    this.client.particleManager.addEmitter(entity, fpaore.SQUIGGLETHINGM, 30);
                    this.world.playSound(entity.getX(), entity.getY(), entity.getZ(), ModSounds.MAYOR_TOTEM_USE, entity.getSoundCategory(), 1.0F, 1.0F, false);
                    if (entity == this.client.player) {
                        this.client.gameRenderer.showFloatingItem(ClientPlayNetworkHandlerMixin.getActiveTotemOfUndyingM(this.client.player));
                    }
                    break;
                default: {
                    entity.handleStatus(packet.getStatus());
                }
            }
        }
    }
    private static ItemStack getActiveTotemOfUndyingP(PlayerEntity player) {
        Hand[] var1 = Hand.values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            Hand hand = var1[var3];
            ItemStack itemStack = player.getStackInHand(hand);
            if (itemStack.isOf(fpaore.pants)) {
                return itemStack;
            }
        }

        return new ItemStack(fpaore.pants);
    }
    private static ItemStack getActiveTotemOfUndyingM(PlayerEntity player) {
        Hand[] var1 = Hand.values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            Hand hand = var1[var3];
            ItemStack itemStack = player.getStackInHand(hand);
            if (itemStack.isOf(fpaore.mayor_of_undying)) {
                return itemStack;
            }
        }

        return new ItemStack(fpaore.mayor_of_undying);
    }

}