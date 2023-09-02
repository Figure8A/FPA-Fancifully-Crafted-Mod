package com.figure8.blocks;

import com.figure8.fpaore;
import com.figure8.sound.ModSounds;
import com.google.common.collect.ImmutableMap;
import net.minecraft.block.*;
import net.minecraft.block.enums.SlabType;
import net.minecraft.block.enums.Tilt;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

import static com.ibm.icu.text.PluralRules.Operand.e;
import static java.awt.Color.*;
import static net.minecraft.block.SlabBlock.TYPE;
import static net.minecraft.block.enums.SlabType.DOUBLE;
import static net.minecraft.block.enums.SlabType.TOP;

public class spring extends SlimeBlock implements Waterloggable {

    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public static final EnumProperty<springy> CROUCHES = EnumProperty.of("crouches", springy.class);

    public static final DirectionProperty FACING = Properties.FACING;
    private static final Map<springy, VoxelShape> SHAPES_FOR_CROUCH = ImmutableMap.of(springy.CROUCH, Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 14.0, 16.0), springy.CROUCH1, Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 10.0, 16.0), springy.CROUCH2, Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 8.0, 16.0), springy.CROUCHF, Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0));

    private final Map<BlockState, VoxelShape> shapes;


    public spring(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)this.getDefaultState().with(WATERLOGGED, false)).with(FACING, Direction.UP).with(CROUCHES, springy.CROUCH2));
        this.shapes = this.getShapesForStates(spring::getShapeForState);
    }
    private static VoxelShape getShapeForState(BlockState state) {
        return VoxelShapes.union(SHAPES_FOR_CROUCH.get(state.get(CROUCHES)));
    }
    @Override
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        if (entity.bypassesLandingEffects()) {
            super.onLandedUpon(world, state, pos, entity, fallDistance);
        } else {
            entity.handleFallDamage(fallDistance, 0.0f, world.getDamageSources().fall());
        }
    }
    @Override
    public boolean hasRandomTicks(BlockState state) {
        return (state.get(CROUCHES) != springy.CROUCH1);
    }


    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return this.shapes.get(state);
    }

    @Override
    public void onEntityLand(BlockView world, Entity entity) {
        if (entity.bypassesLandingEffects()) {
            super.onEntityLand(world, entity);
        } else {
            this.bounce(entity);
        }
    }

    private void bounce(Entity entity) {
        Vec3d vec3d = entity.getVelocity();
        if (vec3d.y < 0.0) {
            double d = 2.0;
            entity.setVelocity(vec3d.x * d, -vec3d.y * d, vec3d.z * d);
        }
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        double d = Math.abs(entity.getVelocity().y);
        if (state.get(CROUCHES) == springy.CROUCH) {
            double e = 1 + d * 1;
            entity.setVelocity(entity.getVelocity().multiply(e, 3.5, e));
            return;
        }
        if (state.get(CROUCHES) == springy.CROUCHF) {
            double e = 1 + d * 1;
            entity.setVelocity(entity.getVelocity().multiply(e, 4, e));
            return;
        }
        if (state.get(CROUCHES) == springy.CROUCH1) {
            double e = 1 + d * 1;
            entity.setVelocity(entity.getVelocity().multiply(e, 2, e));
            return;
        }
        if (state.get(CROUCHES) == springy.CROUCH2) {
            double e = 1 + d * 1;
            entity.setVelocity(entity.getVelocity().multiply(e, 1.5, e));
            return;
        }

        super.onSteppedOn(world, pos, state, entity);
    }
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        world.playSound(player, pos, ModSounds.SPRING_BLOCK_HIT, SoundCategory.BLOCKS, 1f,1f);
        world.setBlockState(pos, state.cycle(CROUCHES), 4, 1);
        return super.onUse(state, world, pos, player, hand, hit);


    }
    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED).booleanValue()) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        if (direction == state.get(FACING).getOpposite() && !state.canPlaceAt(world, pos)) {
            return Blocks.AIR.getDefaultState();
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        World worldAccess = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();
        return (BlockState)((BlockState)this.getDefaultState().with(WATERLOGGED, worldAccess.getFluidState(blockPos).getFluid() == Fluids.WATER)).with(FACING, ctx.getSide());
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return (BlockState)state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        if (state.get(WATERLOGGED).booleanValue()) {
            return Fluids.WATER.getStill(false);
        }
        return super.getFluidState(state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED, FACING, CROUCHES);
    }
    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        tooltip.add(Text.literal("Right Click To Change The Strength!").formatted(Formatting.DARK_AQUA));
        super.appendTooltip(stack, world, tooltip, options);
    }


}
