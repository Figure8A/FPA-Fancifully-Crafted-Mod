package com.figure8.blocks;

import com.figure8.Networktests.ModNetworkRegisters;
import com.figure8.effects.ModEffects;
import com.figure8.fpaore;
import com.figure8.sound.ModSounds;
import com.figure8.util.IEntityDataSaver;
import com.figure8.util.SquiggleAdd;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.RavagerEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;


public class squiggleblock
        extends HorizontalFacingBlock {
    protected static final VoxelShape BOUNDING_SHAPE = Block.createCuboidShape(4, 2, 4, 12, 13, 12);
    public squiggleblock(AbstractBlock.Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
    }



    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return BOUNDING_SHAPE;
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.HORIZONTAL_FACING);
    }


    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return super.getPlacementState(ctx).with(Properties.HORIZONTAL_FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        LivingEntity livingEntity;
        this.breakSquiggle(world, pos, state);
        world.breakBlock(pos, false, entity);
        super.onEntityCollision(state, world, pos, entity);
        if (entity instanceof LivingEntity && !(livingEntity = (LivingEntity)entity).isInvulnerableTo(world.getDamageSources().wither())) {
            livingEntity.addStatusEffect(new StatusEffectInstance(ModEffects.HPSQUIGGLEHEAL, 0, 0, false, false, false));
        }
        if (world.isClient()) {
            ClientPlayNetworking.send(ModNetworkRegisters.SQUIGGLE_ID, PacketByteBufs.create());
        }
        if (!world.isClient()) {
            world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), ModSounds.GRASSPOP_BLOCK_BREAK, SoundCategory.NEUTRAL, 1.0f, 0.75f + world.random.nextFloat() * 0.5f);

        }
    }


    private void breakSquiggle(World world, BlockPos pos, BlockState state) {

        world.setBlockState(pos, (BlockState)state, Block.NOTIFY_LISTENERS);
        world.breakBlock(pos, false);
        world.addParticle(fpaore.SQUIGGLETHINGM, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 1, 0, 0, -0.0005);
        world.addParticle(fpaore.SQUIGGLETHING, (double)pos.getX() + 0.05, (double)pos.getY() + 0.05, (double)pos.getZ() + 0.05, 0.05, 0.05, 0.05);
        world.addParticle(fpaore.SQUIGGLETHING, (double)pos.getX() + 0.05, (double)pos.getY() + 0.05, (double)pos.getZ() + 0.05, 0.0, -0.05, 0.0);
        world.addParticle(fpaore.SQUIGGLETHING, (double)pos.getX() + 0.05, (double)pos.getY() + 0.05, (double)pos.getZ() + 0.05, 0.0, -0.05, 0.0);
        world.addParticle(fpaore.SQUIGGLETHING, (double)pos.getX() + 0.05, (double)pos.getY() + 0.05, (double)pos.getZ() + 0.05, 0.0, 0.0, 0.05);

    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {

        super.afterBreak(world, player, pos, state, blockEntity, tool);
    }
    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        tooltip.add(Text.literal("Walk Into This To Restore HP!").formatted(Formatting.DARK_AQUA));
        super.appendTooltip(stack, world, tooltip, options);
    }

}


