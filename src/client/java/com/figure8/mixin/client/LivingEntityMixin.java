package com.figure8.mixin.client;

import com.figure8.fpaore;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/*The goal of this Mixin class is to give the totems the same ability to save the player from death, along with
some unique custom features*/

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity{


	@Shadow
	public  native ItemStack getStackInHand(Hand hand_1);

	@Shadow
	public native boolean hasStatusEffect(StatusEffect effect);

	@Shadow public native void setHealth(float health);

	@Shadow public native boolean clearStatusEffects();

	@Shadow public native boolean addStatusEffect(StatusEffectInstance statusEffectInstance_1);

	@Shadow public native EntityGroup getGroup();

	public MinecraftServer the_server = getServer();

	protected LivingEntityMixin(EntityType<?> entityType_1, World world_1) {
		super(entityType_1, world_1);
	}


	@Inject(at = @At("HEAD"), method = "tryUseTotem", cancellable = false)
	public void usemayor_of_undying(DamageSource damageSource_1, CallbackInfoReturnable<Boolean> callback) {

		/*inits PlayerEntity entity, which is a copy of this casted to Living Entity and then PlayerEntity*/
		LivingEntity entity = (LivingEntity)(Object)this;

		/*ItemStack object that is set to the offhand item that entity is carrying*/
		ItemStack offhand_stack = entity.getStackInHand(Hand.OFF_HAND);
		ItemStack mainhand_stack = entity.getStackInHand(Hand.MAIN_HAND);

		//Executes if the item in offhand_stack is equal to the explosive totem of Undying
		if ((offhand_stack.getItem() == fpaore.mayor_of_undying) || (mainhand_stack.getItem() == fpaore.mayor_of_undying)) {

			/*sets copy to offhand_stack*/

			if((offhand_stack.getItem() == fpaore.mayor_of_undying)) {
				offhand_stack.decrement(1);
			}
			else {

				mainhand_stack.decrement(1);

			}
			/*totem saves player from an untimely death*/
			this.setHealth(10.0F);
			this.clearStatusEffects();
			this.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 125, 2));
			this.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 350, 4));
			this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 100, 2));
			this.getWorld().sendEntityStatus(this, (byte)35);
			this.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 17500, 5));
		}
	}
}
