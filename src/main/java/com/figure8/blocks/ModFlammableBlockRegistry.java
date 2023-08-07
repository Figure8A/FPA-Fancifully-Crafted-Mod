package com.figure8.blocks;

import com.figure8.fpaore;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;

public class ModFlammableBlockRegistry {
    public static void registerFlammableBlocks() {
        FlammableBlockRegistry registry = FlammableBlockRegistry.getDefaultInstance();
        registry.add(fpaore.fwood_log, 5, 5);
        registry.add(fpaore.fwood_wood, 5, 5);
        registry.add(fpaore.stripped_fwood_log, 5, 5);
        registry.add(fpaore.stripped_fwood_wood, 5, 5);

        registry.add(fpaore.fwood_leaves, 30, 60);
        registry.add(fpaore.fwood_planks, 5, 20);
    }
}
