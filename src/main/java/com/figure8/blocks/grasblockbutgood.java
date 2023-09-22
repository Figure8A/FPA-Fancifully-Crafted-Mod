package com.figure8.blocks;

import com.figure8.fpaore;
import com.figure8.world.ModConfiguredFeatures;
import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockRenderView;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.light.ChunkLightProvider;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.*;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

import static net.minecraft.state.property.Properties.SNOWY;

public class grasblockbutgood extends SpreadableBlock
        implements Fertilizable {

    public static final BooleanProperty SNOWY = Properties.SNOWY;
    public grasblockbutgood(AbstractBlock.Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH).with(SNOWY, false));
    }

    private static boolean stayAlive(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.up();
        BlockState blockState = world.getBlockState(blockPos);
        int i = ChunkLightProvider.getRealisticOpacity(world, state, pos, blockState, blockPos, Direction.UP, blockState.getOpacity(world, blockPos));
        return i < world.getMaxLightLevel();
    }




    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        return world.getBlockState(pos.up()).isAir();
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        BlockState blockState = world.getBlockState(pos);
        BlockPos blockPos = pos.up();
        ChunkGenerator chunkGenerator = world.getChunkManager().getChunkGenerator();
        Registry<ConfiguredFeature<?, ?>> registry = world.getRegistryManager().get(RegistryKeys.CONFIGURED_FEATURE);
        if (blockState.isOf(fpaore.grasblockbutgood)) {
            this.generate(registry, ModConfiguredFeatures.SQUIGGLEPOPS, world, chunkGenerator, random, blockPos);
            this.generate(registry, ModConfiguredFeatures.FANCYGRASS, world, chunkGenerator, random, blockPos);
        } else if (blockState.isOf(fpaore.coursegrasblockbutgood)) {
            this.generate(registry, ModConfiguredFeatures.SQUIGGLEPOPS, world, chunkGenerator, random, blockPos);
            this.generate(registry, ModConfiguredFeatures.FANCYGRASS, world, chunkGenerator, random, blockPos);
            if (random.nextInt(8) == 0) {
                this.generate(registry, ModConfiguredFeatures.SQUIGGLEPOPS, world, chunkGenerator, random, blockPos);
                this.generate(registry, ModConfiguredFeatures.FANCYGRASS, world, chunkGenerator, random, blockPos);
            }
        }
    }

    private void generate(Registry<ConfiguredFeature<?, ?>> registry, RegistryKey<ConfiguredFeature<?, ?>> key, ServerWorld world, ChunkGenerator chunkGenerator, Random random, BlockPos pos) {
        registry.getEntry(key).ifPresent(entry -> ((ConfiguredFeature)entry.value()).generate(world, chunkGenerator, random, pos));
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.HORIZONTAL_FACING);
        builder.add(Properties.SNOWY);
    }


    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return super.getPlacementState(ctx).with(Properties.HORIZONTAL_FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }
    private static boolean canSurvive(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.up();
        BlockState blockState = world.getBlockState(blockPos);
        if (blockState.isOf(Blocks.SNOW) && blockState.get(SnowBlock.LAYERS) == 1) {
            return true;
        }
        if (blockState.getFluidState().getLevel() == 8) {
            return false;
        }
        int i = ChunkLightProvider.getRealisticOpacity(world, state, pos, blockState, blockPos, Direction.UP, blockState.getOpacity(world, blockPos));
        return i < world.getMaxLightLevel();
    }

    private static boolean canSpread(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.up();
        return grasblockbutgood.canSurvive(state, world, pos) && !world.getFluidState(blockPos).isIn(FluidTags.WATER);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!grasblockbutgood.canSurvive(state, world, pos)) {
            world.setBlockState(pos, fpaore.coursegrasblockbutgood.getDefaultState());
            return;
        }
        if (!grasblockbutgood.stayAlive(state, world, pos)) {
            world.setBlockState(pos, fpaore.coursegrasblockbutgood.getDefaultState());
        }
        if (world.getLightLevel(pos.up()) >= 9) {
            BlockState blockState = this.getDefaultState();
            for (int i = 0; i < 4; ++i) {
                BlockPos blockPos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                if (!world.getBlockState(blockPos).isOf(fpaore.coursegrasblockbutgood) || !grasblockbutgood.canSpread(blockState, world, blockPos)) continue;
                world.setBlockState(blockPos, (BlockState)blockState.with(SNOWY, world.getBlockState(blockPos.up()).isOf(Blocks.SNOW)));
            }
        }
    }

    @Override
    public boolean isEnabled(FeatureSet enabledFeatures) {
        return super.isEnabled(enabledFeatures);
    }
}
