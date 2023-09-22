package com.figure8.blocks;

import com.figure8.Networktests.ModNetworkRegisters;
import com.figure8.effects.ModEffects;
import com.figure8.fpaore;
import com.figure8.sound.ModSounds;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class greensquiggleblock
        extends HorizontalFacingBlock {
    protected static final VoxelShape BOUNDING_SHAPE = Block.createCuboidShape(2, 2, 2, 14, 14, 14);
    public greensquiggleblock(Settings settings) {
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
            livingEntity.addStatusEffect(new StatusEffectInstance(ModEffects.HPSQUIGGLEHEALGREEN, 1, 1, false, false, false));
        }

    }


    private void breakSquiggle(World world, BlockPos pos, BlockState state) {

        if (world.isClient()) {
            ClientPlayNetworking.send(ModNetworkRegisters.SQUIGGLE_ID, PacketByteBufs.create());
            ClientPlayNetworking.send(ModNetworkRegisters.SQUIGGLE_ID, PacketByteBufs.create());
            ClientPlayNetworking.send(ModNetworkRegisters.SQUIGGLE_ID, PacketByteBufs.create());
            ClientPlayNetworking.send(ModNetworkRegisters.SQUIGGLE_ID, PacketByteBufs.create());
            ClientPlayNetworking.send(ModNetworkRegisters.SQUIGGLE_ID, PacketByteBufs.create());
        }
        world.playSound(null, pos, ModSounds.FIVESQUIGGLE, SoundCategory.NEUTRAL, 2f, 1f);
        world.setBlockState(pos, (BlockState)state, Block.NOTIFY_LISTENERS);
        world.breakBlock(pos, false);
        world.addParticle(fpaore.SQUIGGLETHINGMGREEN, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 1, 0, 0, -0.0005);
        world.addParticle(fpaore.SQUIGGLETHINGGREEN, (double)pos.getX() + 0.05, (double)pos.getY() + 0.05, (double)pos.getZ() + 0.05, 0.05, 0.05, 0.05);
        world.addParticle(fpaore.SQUIGGLETHINGGREEN, (double)pos.getX() + 0.05, (double)pos.getY() + 0.05, (double)pos.getZ() + 0.05, 0.0, -0.05, 0.0);
        world.addParticle(fpaore.SQUIGGLETHINGGREEN, (double)pos.getX() + 0.05, (double)pos.getY() + 0.05, (double)pos.getZ() + 0.05, 0.0, -0.05, 0.0);
        world.addParticle(fpaore.SQUIGGLETHINGGREEN, (double)pos.getX() + 0.05, (double)pos.getY() + 0.05, (double)pos.getZ() + 0.05, 0.0, 0.0, 0.05);
        world.addParticle(fpaore.SQUIGGLETHINGMGREEN, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 1, 0, 0, -0.0005);
        world.addParticle(fpaore.SQUIGGLETHINGGREEN, (double)pos.getX() + 0.05, (double)pos.getY() + 0.05, (double)pos.getZ() + 0.05, 0.05, 0.05, 0.05);
        world.addParticle(fpaore.SQUIGGLETHINGGREEN, (double)pos.getX() + 0.05, (double)pos.getY() + 0.05, (double)pos.getZ() + 0.05, 0.0, -0.05, 0.0);
        world.addParticle(fpaore.SQUIGGLETHINGGREEN, (double)pos.getX() + 0.05, (double)pos.getY() + 0.05, (double)pos.getZ() + 0.05, 0.0, -0.05, 0.0);
        world.addParticle(fpaore.SQUIGGLETHINGGREEN, (double)pos.getX() + 0.05, (double)pos.getY() + 0.05, (double)pos.getZ() + 0.05, 0.0, 0.0, 0.05);
        world.addParticle(fpaore.SQUIGGLETHINGMGREEN, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 1, 0, 0, -0.0005);
        world.addParticle(fpaore.SQUIGGLETHINGGREEN, (double)pos.getX() + 0.05, (double)pos.getY() + 0.05, (double)pos.getZ() + 0.05, 0.05, 0.05, 0.05);
        world.addParticle(fpaore.SQUIGGLETHINGGREEN, (double)pos.getX() + 0.05, (double)pos.getY() + 0.05, (double)pos.getZ() + 0.05, 0.0, -0.05, 0.0);
        world.addParticle(fpaore.SQUIGGLETHINGGREEN, (double)pos.getX() + 0.05, (double)pos.getY() + 0.05, (double)pos.getZ() + 0.05, 0.0, -0.5, 0.0);
        world.addParticle(fpaore.SQUIGGLETHINGGREEN, (double)pos.getX() + 0.05, (double)pos.getY() + 0.05, (double)pos.getZ() + 0.5, 0.0, 0.0, 0.05);
        world.addParticle(fpaore.SQUIGGLETHINGMGREEN, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 1, 0, 0, -0.0005);
        world.addParticle(fpaore.SQUIGGLETHINGGREEN, (double)pos.getX() + 0.05, (double)pos.getY() + 0.05, (double)pos.getZ() + 0.05, 0.05, 0.05, 0.05);
        world.addParticle(fpaore.SQUIGGLETHINGGREEN, (double)pos.getX() + 0.05, (double)pos.getY() + 0.05, (double)pos.getZ() + 0.05, 0.0, -0.5, 0.0);
        world.addParticle(fpaore.SQUIGGLETHINGGREEN, (double)pos.getX() + 0.05, (double)pos.getY() + 0.05, (double)pos.getZ() + 0.05, 0.0, -0.05, 0.0);
        world.addParticle(fpaore.SQUIGGLETHINGGREEN, (double)pos.getX() + 0.05, (double)pos.getY() + 0.05, (double)pos.getZ() + 0.05, 0.0, 0.0, 0.05);
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {

        super.afterBreak(world, player, pos, state, blockEntity, tool);
    }
    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        tooltip.add(Text.literal("Walk Into These To Restore 5 HP!").formatted(Formatting.DARK_AQUA));
        super.appendTooltip(stack, world, tooltip, options);
    }

}


