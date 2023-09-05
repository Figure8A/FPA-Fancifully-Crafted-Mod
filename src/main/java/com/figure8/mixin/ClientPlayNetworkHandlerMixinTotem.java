package com.figure8.mixin;

import com.figure8.fpaore;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.sound.GuardianAttackSoundInstance;
import net.minecraft.client.sound.SnifferDigSoundInstance;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.GuardianEntity;
import net.minecraft.entity.passive.SnifferEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.NetworkThreadUtils;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.listener.TickablePacketListener;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;



/*The goal of this Mixin class is to give the totems the same ability to save the player from death, along with
some unique custom features*/

@Mixin(ClientPlayNetworkHandler.class)
public abstract class ClientPlayNetworkHandlerMixinTotem implements TickablePacketListener, ClientPlayPacketListener {


	protected ClientPlayNetworkHandlerMixinTotem(MinecraftClient client) {
		this.client = client;
	}



	private final MinecraftClient client;
	private ClientWorld world;


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

	@Inject(at = @At("HEAD"), method = "onEntityStatus", cancellable = true)
	public void onEntityStatusMayor(EntityStatusS2CPacket packet, CallbackInfo ci) {
		NetworkThreadUtils.forceMainThread(packet, this, this.client);
		Entity entity = packet.getEntity(this.world);
		if (entity != null) {
			switch (packet.getStatus()) {
				case 21:
					this.client.getSoundManager().play(new GuardianAttackSoundInstance((GuardianEntity)entity));
					break;
				case 69:
					boolean i = true;
					this.client.particleManager.addEmitter(entity, fpaore.SQUIGGLETHINGM, 30);
					this.world.playSound(entity.getX(), entity.getY(), entity.getZ(), SoundEvents.ITEM_TOTEM_USE, entity.getSoundCategory(), 1.0F, 1.0F, false);
					if (entity == this.client.player) {
						this.client.gameRenderer.showFloatingItem(getActiveTotemOfUndyingM(this.client.player));
					}
					break;
				case 63:
					this.client.getSoundManager().play(new SnifferDigSoundInstance((SnifferEntity)entity));
					break;
				default:
					entity.handleStatus(packet.getStatus());
			}
		}

	}

}
