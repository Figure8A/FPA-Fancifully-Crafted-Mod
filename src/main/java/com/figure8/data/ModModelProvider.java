package com.figure8.data;

import com.figure8.fpaore;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;

import net.minecraft.block.Block;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.TexturedModel;
import net.minecraft.data.family.BlockFamilies;
import net.minecraft.item.ArmorItem;


public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        BlockStateModelGenerator.BlockTexturePool fwoodPool = blockStateModelGenerator.registerCubeAllModelTexturePool(fpaore.stripped_fwood_planks);


        blockStateModelGenerator.registerLog(fpaore.fwood_log).log(fpaore.fwood_log).wood(fpaore.fwood_wood);
        blockStateModelGenerator.registerLog(fpaore.stripped_fwood_log).log(fpaore.stripped_fwood_log).wood(fpaore.stripped_fwood_wood);

        BlockStateModelGenerator.BlockTexturePool tPlnaks = blockStateModelGenerator.registerCubeAllModelTexturePool(fpaore.fwood_planks);
        tPlnaks.family(BlockFamilies.register(fpaore.fwood_planks).sign(fpaore.fwood_sign, fpaore.fwood_wall_sign).build());

        blockStateModelGenerator.registerHangingSign(fpaore.stripped_fwood_log, fpaore.fwood_hanging_sign, fpaore.fwood_hanging_wall_sign);


        blockStateModelGenerator.registerCubeAllModelTexturePool(fpaore.stripped_fwood_planksvar);
        blockStateModelGenerator.registerCubeAllModelTexturePool(fpaore.fwood_leaves);

        blockStateModelGenerator.registerTintableCrossBlockState(fpaore.fwood_sapling, BlockStateModelGenerator.TintType.NOT_TINTED);

        fwoodPool.stairs(fpaore.stripped_fwood_stair);
        fwoodPool.slab(fpaore.stripped_fwood_slab);
        fwoodPool.button(fpaore.stripped_fwood_button);
        fwoodPool.pressurePlate(fpaore.stripped_fwood_plate);
        fwoodPool.fence(fpaore.stripped_fwood_fence);
        fwoodPool.fenceGate(fpaore.stripped_fwood_gate);
        fwoodPool.family(BlockFamilies.register(fpaore.stripped_fwood_planks).sign(fpaore.stripped_fwood_sign, fpaore.stripped_fwood_wall_sign).build());
        blockStateModelGenerator.registerDoor(fpaore.stripped_fwood_door);
        blockStateModelGenerator.registerTrapdoor(fpaore.stripped_fwood_trapdoor);



    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

        itemModelGenerator.registerArmor(((ArmorItem) fpaore.BRADIUM_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) fpaore.BRADIUM_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) fpaore.BRADIUM_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) fpaore.BRADIUM_BOOTS));
    }
}
