package com.figure8.item;


import com.figure8.entity.ThrowableBlobEntity;
import com.figure8.sound.ModSounds;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;

import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;


public class inkblob extends Item {
    final EntityType<? extends ThrownItemEntity> entityType;

    public inkblob(Settings settings, EntityType<? extends ThrownItemEntity> entityType) {
        super(settings);
        this.entityType = entityType;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        final ItemStack stack = user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), ModSounds.INKTOSS, SoundCategory.NEUTRAL, 2.0F, 1.0F);
        if (!user.isCreative()) {
            stack.decrement(1);
        }
        if (!world.isClient()) {
            final ThrowableBlobEntity entity = new ThrowableBlobEntity(entityType, user, world);
            entity.setItem(stack);
            entity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 2.0F, 0.0F);
            world.spawnEntity(entity);
        }
        return TypedActionResult.success(stack, world.isClient());
    }



}
