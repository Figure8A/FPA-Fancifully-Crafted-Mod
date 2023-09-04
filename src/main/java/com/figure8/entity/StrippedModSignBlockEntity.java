package com.figure8.entity;

import com.figure8.fpaore;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.util.math.BlockPos;

public class StrippedModSignBlockEntity extends SignBlockEntity {
    public StrippedModSignBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
    }

    @Override
    public BlockEntityType<?> getType() {
        return fpaore.STRIPPED_MOD_SIGN_BLOCK_ENTITY;
    }
}