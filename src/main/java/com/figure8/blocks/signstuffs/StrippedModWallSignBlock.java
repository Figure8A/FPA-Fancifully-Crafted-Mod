package com.figure8.blocks.signstuffs;

import com.figure8.entity.ModSignBlockEntity;
import com.figure8.entity.StrippedModSignBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.WallSignBlock;
import net.minecraft.block.WoodType;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class StrippedModWallSignBlock extends WallSignBlock {
    public StrippedModWallSignBlock(Settings settings, WoodType woodType) {
        super(settings, woodType);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new StrippedModSignBlockEntity(pos, state);
    }
}