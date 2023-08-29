package com.figure8.blocks;


import com.figure8.entity.ThrowableBlobEntity;
import com.figure8.fpaore;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.block.dispenser.ProjectileDispenserBehavior;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;

public class dispenser implements DispenserBehavior {
    @Override
    public ItemStack dispense(BlockPointer pointer, ItemStack stack) {
        return null;
    }

    public static void registerDefaults() {

        DispenserBlock.registerBehavior(fpaore.inkblob, new ProjectileDispenserBehavior(){

            @Override
            protected ProjectileEntity createProjectile(World world, Position position, ItemStack stack) {
                return Util.make(new ThrowableBlobEntity(world, position.getX(), position.getY(), position.getZ()), entity -> entity.setItem(stack));

            }
        });
    }
}