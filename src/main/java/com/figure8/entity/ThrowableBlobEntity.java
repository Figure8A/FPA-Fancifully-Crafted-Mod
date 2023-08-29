package com.figure8.entity;

import com.figure8.blocks.Wallinkblot;
import com.figure8.fpaore;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

import java.util.Objects;

public class ThrowableBlobEntity extends ThrownItemEntity {


    public PersistentProjectileEntity.PickupPermission pickupType;

    public ThrowableBlobEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);

    }

    public ThrowableBlobEntity(EntityType<? extends ThrownItemEntity> entityType, LivingEntity livingEntity, World world) {
        super(entityType, livingEntity, world);
    }
    public ThrowableBlobEntity(World world, double x, double y, double z) {
        super(fpaore.THROWABLE_BLOB_ENTITY, x, y, z, world);
    }




    @Override
    public Packet<ClientPlayPacketListener> createSpawnPacket() {
        return super.createSpawnPacket();
    }

    @Override
    protected Item getDefaultItem() {
        return null;
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        var world = this.getWorld();
        super.onBlockHit(blockHitResult);
        if (!world.isClient()) {
            final Direction dir = blockHitResult.getSide();
            final BlockPos sidePos = blockHitResult.getBlockPos().offset(dir);
            if (world.getBlockState(sidePos).isAir()) {
                switch (dir) {
                    case UP -> {
                        final BlockState torch = fpaore.inkblot.getDefaultState();
                        if (torch.canPlaceAt(world, sidePos)) {
                            world.setBlockState(sidePos, torch);
                        } else {
                            onFailed();
                        }
                    }
                    case DOWN -> onFailed();
                    default -> {
                        final BlockState state = fpaore.inkblot_wall.getDefaultState().with(Wallinkblot.FACING, dir);
                        if (state.canPlaceAt(world, sidePos)) {
                            world.setBlockState(sidePos, state);
                        } else {
                            onFailed();
                        }
                    }
                }
            } else {
                onFailed();
            }
        }
    }

    void onFailed() {
        drop();
    }
    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.getWorld().isClient) {
            this.damage(this.getDamageSources().thrown(this, this.getOwner()), 4.0f);
            this.discard();
        }
        super.discard();

    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = this.getOwner();
        if (entity instanceof PlayerEntity) {
            entityHitResult.getEntity().damage(this.getDamageSources().thrown(this, this.getOwner()), 4.0f);
        }
    }




    private void drop() {
        getWorld().spawnEntity(new ItemEntity(getWorld(), getX(), getY(), getZ(), new ItemStack(fpaore.inkblob)));
    }
}
