package com.figure8.mixin;

import com.figure8.fpaore;

import com.figure8.item.PantsItem;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.ChestBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {


	@Unique
	private ItemStack totemStackInventoryFallback(LivingEntity entity, Hand hand) {
		if (hand == Hand.OFF_HAND && !entity.getOffHandStack().isOf(fpaore.pants) && entity instanceof PlayerEntity player && player.getInventory().contains(fpaore.pants.getDefaultStack())) {
			return player.getInventory().getStack(player.getInventory().getSlotWithStack(fpaore.pants.getDefaultStack()));
		}
		return entity.getStackInHand(hand);
	}
	@Unique
	private final LivingEntity  entity = (LivingEntity)(Object) this;

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

				ItemStack itemStack2 = this.totemStackInventoryFallback(entity,hand);

				if (itemStack2.isOf(Items.TOTEM_OF_UNDYING) || itemStack2.isOf(fpaore.mayor_of_undying) || itemStack2.isOf(fpaore.pants)) {
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
				if(itemStack.isOf(fpaore.pants)) {
					this.setHealth(12.0F);
					this.clearStatusEffects();
					this.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 2));
					((LivingEntity) (Object) this).getWorld().sendEntityStatus(((LivingEntity)(Object)this), (byte)110);

				}
			}
			cir.setReturnValue(itemStack != null);
		}
	}

}