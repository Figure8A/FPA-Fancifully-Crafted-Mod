package com.figure8.data;

import com.figure8.fpaore;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;

import net.minecraft.block.Block;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.TexturedModel;


public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        BlockStateModelGenerator.BlockTexturePool fwoodPool = blockStateModelGenerator.registerCubeAllModelTexturePool(fpaore.stripped_fwood_planks);


        blockStateModelGenerator.registerLog(fpaore.fwood_log).log(fpaore.fwood_log).wood(fpaore.fwood_wood);
        blockStateModelGenerator.registerLog(fpaore.stripped_fwood_log).log(fpaore.stripped_fwood_log).wood(fpaore.stripped_fwood_wood);

        blockStateModelGenerator.registerCubeAllModelTexturePool(fpaore.fwood_planks);
        blockStateModelGenerator.registerCubeAllModelTexturePool(fpaore.stripped_fwood_planksvar);
        blockStateModelGenerator.registerCubeAllModelTexturePool(fpaore.fwood_leaves);

        blockStateModelGenerator.registerTintableCrossBlockState(fpaore.fwood_sapling, BlockStateModelGenerator.TintType.NOT_TINTED);

        fwoodPool.stairs(fpaore.stripped_fwood_stair);
        fwoodPool.slab(fpaore.stripped_fwood_slab);
        fwoodPool.button(fpaore.stripped_fwood_button);
        fwoodPool.pressurePlate(fpaore.stripped_fwood_plate);
        fwoodPool.fence(fpaore.stripped_fwood_fence);
        fwoodPool.fenceGate(fpaore.stripped_fwood_gate);
        blockStateModelGenerator.registerDoor(fpaore.stripped_fwood_door);
        blockStateModelGenerator.registerTrapdoor(fpaore.stripped_fwood_trapdoor);



    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {


    }
}
