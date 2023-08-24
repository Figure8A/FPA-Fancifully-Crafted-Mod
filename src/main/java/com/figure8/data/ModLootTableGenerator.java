package com.figure8.data;

import com.figure8.fpaore;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class ModLootTableGenerator extends FabricBlockLootTableProvider {
    public ModLootTableGenerator(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {


        addDrop(fpaore.stripped_fwood_planks);
        addDrop(fpaore.stripped_fwood_planksvar);
        addDrop(fpaore.stripped_fwood_slab);
        addDrop(fpaore.stripped_fwood_stair);
        addDrop(fpaore.stripped_fwood_gate);
        addDrop(fpaore.stripped_fwood_plate);
        addDrop(fpaore.stripped_fwood_fence);
        addDrop(fpaore.stripped_fwood_button);
        addDrop(fpaore.stripped_fwood_trapdoor);

        addDrop(fpaore.stripped_fwood_door, doorDrops(fpaore.stripped_fwood_door));
        addDrop(fpaore.stripped_fwood_slab, slabDrops(fpaore.stripped_fwood_slab));


    }
}
