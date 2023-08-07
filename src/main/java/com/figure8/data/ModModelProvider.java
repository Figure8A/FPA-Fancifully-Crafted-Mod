package com.figure8.data;

import com.figure8.fpaore;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;

import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;


public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

        blockStateModelGenerator.registerLog(fpaore.fwood_log).log(fpaore.fwood_log).wood(fpaore.fwood_wood);
        blockStateModelGenerator.registerLog(fpaore.stripped_fwood_log).log(fpaore.stripped_fwood_log).wood(fpaore.stripped_fwood_wood);

        blockStateModelGenerator.registerCubeAllModelTexturePool(fpaore.fwood_planks);
        blockStateModelGenerator.registerCubeAllModelTexturePool(fpaore.fwood_leaves);

        blockStateModelGenerator.registerTintableCrossBlockState(fpaore.fwood_sapling, BlockStateModelGenerator.TintType.NOT_TINTED);


    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {


    }
}
