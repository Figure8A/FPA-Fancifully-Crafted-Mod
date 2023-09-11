package com.figure8.mixin;

import com.figure8.fpaore;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.sound.SoundEvents;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
	@Shadow public abstract ItemStack getStackInHand(Hand hand);

	@Shadow public abstract void setHealth(float health);

	@Shadow public abstract boolean clearStatusEffects();

	@Shadow public abstract boolean addStatusEffect(StatusEffectInstance effect);

	@Inject(at = @At("HEAD"), method = "tryUseTotem", cancellable = true)
	private void tryUseTotem(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
		if (source.isIn(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
			return;
		} else {
			ItemStack itemStack = null;
			Hand[] var4 = Hand.values();
			int var5 = var4.length;

			for(int var6 = 0; var6 < var5; ++var6) {
				Hand hand = var4[var6];
				ItemStack itemStack2 = this.getStackInHand(hand);
				if (itemStack2.isOf(Items.TOTEM_OF_UNDYING) || itemStack2.isOf(fpaore.mayor_of_undying)) {
					itemStack = itemStack2.copy();
					itemStack2.decrement(1);
					break;
				}
			}

			if(itemStack != null){
				if (itemStack.isOf(Items.TOTEM_OF_UNDYING)) {

					this.setHealth(1.0F);
					this.clearStatusEffects();
					this.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 900, 1));
					this.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 100, 1));
					this.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 800, 0));
					((LivingEntity) (Object) this).getWorld().sendEntityStatus(((LivingEntity)(Object)this), (byte)35);
				}
				if(itemStack.isOf(fpaore.mayor_of_undying)){

					this.setHealth(10.0F);
					this.clearStatusEffects();
					this.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 1205, 4));
					this.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 1500, 5));
					this.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 500, 2));
					this.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 500, 2));
					((LivingEntity) (Object) this).getWorld().sendEntityStatus(((LivingEntity)(Object)this), (byte)100);

				}
			}

			cir.setReturnValue(itemStack != null);
		}
	}

}