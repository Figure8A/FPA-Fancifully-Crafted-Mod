package com.figure8.world.gen;

import com.figure8.world.ModPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;

import static net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags.DESERT;

public class ModFlowerGeneration {
    public static void generateFlowers() {
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.PLAINS, BiomeKeys.FOREST, (BiomeKeys.BADLANDS),(BiomeKeys.DESERT),
        (BiomeKeys.ERODED_BADLANDS), (BiomeKeys.WOODED_BADLANDS), (BiomeKeys.BAMBOO_JUNGLE), (BiomeKeys.JUNGLE), (BiomeKeys.SPARSE_JUNGLE),
        (BiomeKeys.SAVANNA_PLATEAU), (BiomeKeys.SAVANNA), (BiomeKeys.WINDSWEPT_SAVANNA), (BiomeKeys.SNOWY_BEACH), (BiomeKeys.SNOWY_TAIGA), (BiomeKeys.SNOWY_PLAINS), (BiomeKeys.GROVE), (BiomeKeys.SNOWY_SLOPES),
        (BiomeKeys.FROZEN_PEAKS), (BiomeKeys.JAGGED_PEAKS), (BiomeKeys.SWAMP),
        (BiomeKeys.MANGROVE_SWAMP), (BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA), (BiomeKeys.OLD_GROWTH_PINE_TAIGA), (BiomeKeys.WINDSWEPT_GRAVELLY_HILLS), (BiomeKeys.WINDSWEPT_HILLS), (BiomeKeys.TAIGA), (BiomeKeys.WINDSWEPT_FOREST)), GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.SQUIGGLEPOP_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.PLAINS, BiomeKeys.FOREST, (BiomeKeys.BADLANDS),(BiomeKeys.DESERT),
                        (BiomeKeys.ERODED_BADLANDS), (BiomeKeys.WOODED_BADLANDS), (BiomeKeys.BAMBOO_JUNGLE), (BiomeKeys.JUNGLE), (BiomeKeys.SPARSE_JUNGLE),
                        (BiomeKeys.SAVANNA_PLATEAU), (BiomeKeys.SAVANNA), (BiomeKeys.WINDSWEPT_SAVANNA), (BiomeKeys.SNOWY_BEACH), (BiomeKeys.SNOWY_TAIGA), (BiomeKeys.SNOWY_PLAINS), (BiomeKeys.GROVE), (BiomeKeys.SNOWY_SLOPES),
                        (BiomeKeys.FROZEN_PEAKS), (BiomeKeys.JAGGED_PEAKS), (BiomeKeys.SWAMP),
                        (BiomeKeys.MANGROVE_SWAMP), (BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA), (BiomeKeys.OLD_GROWTH_PINE_TAIGA), (BiomeKeys.WINDSWEPT_GRAVELLY_HILLS), (BiomeKeys.WINDSWEPT_HILLS), (BiomeKeys.TAIGA), (BiomeKeys.WINDSWEPT_FOREST)),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.FANCYGRASS_PLACED_KEY);
    }
}
