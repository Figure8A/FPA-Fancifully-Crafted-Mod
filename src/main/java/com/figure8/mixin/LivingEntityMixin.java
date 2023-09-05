package com.figure8.mixin;

import com.figure8.fpaore;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.ServerTask;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/*The goal of this Mixin class is to give the totems the same ability to save the player from death, along with
some unique custom features*/

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin  extends Entity {


	@Shadow
	public native ItemStack getStackInHand(Hand hand_1);

	@Shadow
	public native boolean hasStatusEffect(StatusEffect effect);

	@Shadow
	public native void setHealth(float health);

	@Shadow
	public native boolean clearStatusEffects();

	@Shadow
	public native boolean addStatusEffect(StatusEffectInstance statusEffectInstance_1);

	@Shadow
	public native EntityGroup getGroup();


	public MinecraftServer the_server = getServer();

	protected LivingEntityMixin(EntityType<?> entityType_1, World world_1) {
		super(entityType_1, world_1);
	}


	@Inject(at = @At("HEAD"), method = "tryUseTotem", cancellable = true)
	public void usemayor_of_undying(DamageSource damageSource_1, CallbackInfoReturnable<Boolean> callback) {

		Entity entity = this;


		ItemStack offhand_stack = ((LivingEntityMixin) entity).getStackInHand(Hand.OFF_HAND);

		ItemStack mainhand_stack = ((LivingEntityMixin) entity).getStackInHand(Hand.MAIN_HAND);


		if ((offhand_stack.getItem() == fpaore.mayor_of_undying) || (mainhand_stack.getItem() == fpaore.mayor_of_undying)) {


			if (damageSource_1.getType().equals(DamageTypes.OUT_OF_WORLD)) {

				callback.setReturnValue(false);
			} else {

				if ((offhand_stack.getItem() == fpaore.mayor_of_undying)) {
					offhand_stack.decrement(1);
				} else if ((mainhand_stack.getItem() == fpaore.mayor_of_undying)) {

					mainhand_stack.decrement(1);

				}

				this.setHealth(10.0F);
				this.clearStatusEffects();
				this.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 1205, 4));
				this.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 1500, 5));
				this.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 500, 2));
				this.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 500, 2));
				this.getWorld().sendEntityStatus(this, (byte) 69);
				callback.setReturnValue(true);
			}
		}
	}
}
