package com.figure8.blocks;

import com.figure8.fpaore;
import net.minecraft.block.*;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;

public class fgrass extends PlantBlock {
    public fgrass(AbstractBlock.Settings settings) {
        super(settings);
    }

    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isIn((RegistryEntryList<Block>) fpaore.grasblockbutgood) || floor.isOf(Blocks.FARMLAND);
    }
    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.down();
        return this.canPlantOnTop(world.getBlockState(blockPos), world, blockPos);
    }


}



