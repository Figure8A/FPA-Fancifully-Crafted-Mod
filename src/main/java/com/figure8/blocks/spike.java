package com.figure8.blocks;

import com.figure8.fpaore;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

import static net.minecraft.state.property.Properties.HORIZONTAL_FACING;

public class spike extends HorizontalFacingBlock implements Waterloggable {

    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public spike(AbstractBlock.Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(HORIZONTAL_FACING, Direction.NORTH));

    }

    private static final VoxelShape SHAPE_N = Stream.of(
            Block.createCuboidShape(4, 0, 4, 12, 2, 12),
            Block.createCuboidShape(5, 2, 5, 11, 3, 11),
            Block.createCuboidShape(6, 3, 6, 10, 5, 10),
            Block.createCuboidShape(5, 4, 5, 6, 6, 7),
            Block.createCuboidShape(7, 5, 7, 8, 9, 9),
            Block.createCuboidShape(8, 5, 7, 10, 6, 9),
            Block.createCuboidShape(9, 6, 8, 10, 7, 9),
            Block.createCuboidShape(10, 4, 7, 11, 5, 10)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    private static final VoxelShape SHAPE_E = Stream.of(
            Block.createCuboidShape(4, 0, 4, 12, 2, 12),
            Block.createCuboidShape(5, 2, 5, 11, 3, 11),
            Block.createCuboidShape(6, 3, 6, 10, 5, 10),
            Block.createCuboidShape(9, 4, 5, 11, 6, 6),
            Block.createCuboidShape(7, 5, 7, 9, 9, 8),
            Block.createCuboidShape(7, 5, 8, 9, 6, 10),
            Block.createCuboidShape(7, 6, 9, 8, 7, 10),
            Block.createCuboidShape(6, 4, 10, 9, 5, 11)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    private static final VoxelShape SHAPE_S = Stream.of(
            Block.createCuboidShape(4, 0, 4, 12, 2, 12),
            Block.createCuboidShape(5, 2, 5, 11, 3, 11),
            Block.createCuboidShape(6, 3, 6, 10, 5, 10),
            Block.createCuboidShape(10, 4, 9, 11, 6, 11),
            Block.createCuboidShape(8, 5, 7, 9, 9, 9),
            Block.createCuboidShape(6, 5, 7, 8, 6, 9),
            Block.createCuboidShape(6, 6, 7, 7, 7, 8),
            Block.createCuboidShape(5, 4, 6, 6, 5, 9)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    private static final VoxelShape SHAPE_W = Stream.of(
            Block.createCuboidShape(4, 0, 4, 12, 2, 12),
            Block.createCuboidShape(5, 2, 5, 11, 3, 11),
            Block.createCuboidShape(6, 3, 6, 10, 5, 10),
            Block.createCuboidShape(5, 4, 10, 7, 6, 11),
            Block.createCuboidShape(7, 5, 8, 9, 9, 9),
            Block.createCuboidShape(7, 5, 6, 9, 6, 8),
            Block.createCuboidShape(8, 6, 6, 9, 7, 7),
            Block.createCuboidShape(7, 4, 5, 10, 5, 6)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        switch (state.get(FACING)) {
            case NORTH:
                return SHAPE_N;
            case SOUTH:
                return SHAPE_S;
            case WEST:
                return SHAPE_W;
            case EAST:
                return SHAPE_E;
            default:
                return SHAPE_N;
        }
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        VoxelShape voxelShape = this.getOutlineShape(state, world, pos, ShapeContext.absent());
        Vec3d vec3d = voxelShape.getBoundingBox().getCenter();
        double d = (double)pos.getX() + vec3d.x;
        double e = (double)pos.getZ() + vec3d.z;
        for (int i = 0; i < 3; ++i) {
            if (!random.nextBoolean()) continue;
            world.addParticle(ParticleTypes.DRAGON_BREATH, d + random.nextDouble() / 5.0, (double)pos.getY() + (0.5 - random.nextDouble()), e + random.nextDouble() / 5.0, 0.0, 0.005, 0.0);
        }
    }
    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (direction.getOpposite() == state.get(FACING) && !state.canPlaceAt(world, pos)) {
            return Blocks.AIR.getDefaultState();
        }
        if (state.get(WATERLOGGED).booleanValue()) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockState;
        if (!ctx.canReplaceExisting() && (blockState = ctx.getWorld().getBlockState(ctx.getBlockPos().offset(ctx.getSide().getOpposite()))).isOf(this) && blockState.get(HORIZONTAL_FACING) == ctx.getSide()) {
            return null;
        }
        blockState = this.getDefaultState();
        World worldView = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        for (Direction direction : ctx.getPlacementDirections()) {
            if (!direction.getAxis().isHorizontal() || !(blockState = (BlockState)blockState.with(FACING, direction.getOpposite())).canPlaceAt(worldView, blockPos)) continue;
            return (BlockState)blockState.with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
        }
        return null;
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        LivingEntity livingEntity;
        if (world.isClient || world.getDifficulty() == Difficulty.PEACEFUL) {
            return;
        }
        if (entity instanceof LivingEntity && !(livingEntity = (LivingEntity)entity).isInvulnerableTo(world.getDamageSources().wither())) {
            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 1, 10));
        }
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HORIZONTAL_FACING, WATERLOGGED);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        if (state.get(WATERLOGGED).booleanValue()) {
            return Fluids.WATER.getStill(false);
        }
        return super.getFluidState(state);
    }

}

