package com.figure8.blocks;

import com.figure8.fpaore;
import net.minecraft.block.*;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class coursegrasblockbutgood extends Block implements Fertilizable {
    public coursegrasblockbutgood(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state, boolean isClient) {
        if (!world.getBlockState(pos.up()).isTransparent(world, pos)) {
            return false;
        }
        for (BlockPos blockPos : BlockPos.iterate(pos.add(-1, -1, -1), pos.add(1, 1, 1))) {
            if (!world.getBlockState(blockPos).isIn(BlockTags.SHOVEL_MINEABLE)) continue;
            return true;
        }
        return false;
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        boolean bl = false;
        boolean bl2 = false;
        for (BlockPos blockPos : BlockPos.iterate(pos.add(-1, -1, -1), pos.add(1, 1, 1))) {
            BlockState blockState = world.getBlockState(blockPos);
            if (blockState.isOf(fpaore.grasblockbutgood)) {
                bl2 = true;
            }
            if (blockState.isOf(fpaore.grasblockbutgood)) {
                bl = true;
            }
            if (!bl2 || !bl) continue;
            break;
        }
        if (bl2 && bl) {
            world.setBlockState(pos, fpaore.grasblockbutgood.getDefaultState(), Block.NOTIFY_ALL);
        } else if (bl2) {
            world.setBlockState(pos, fpaore.grasblockbutgood.getDefaultState(), Block.NOTIFY_ALL);
        } else if (bl) {
            world.setBlockState(pos, fpaore.grasblockbutgood.getDefaultState(), Block.NOTIFY_ALL);
        }
    }
}

