package com.figure8.world;


import com.figure8.fpaore;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.noise.DoublePerlinNoiseSampler;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.ThreeLayersFeatureSize;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.NoiseBlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.List;
import java.util.OptionalInt;

import static com.figure8.world.ModPlacedFeatures.SQUIGGLEPOP_PLACED_KEY;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> FWOOD_KEY = registerKey("fwood");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SQUIGGLEPOPS = registerKey("squigglepop");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FANCYGRASS = registerKey("fancygrass");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        register(context, FWOOD_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(fpaore.fwood_log),
                new StraightTrunkPlacer(7, 3, 2),
                BlockStateProvider.of(fpaore.fwood_leaves),
                new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 6),
                new TwoLayersFeatureSize(2, 5, 6)).build());
        register(context, SQUIGGLEPOPS, Feature.FLOWER,
                        ConfiguredFeatures.createRandomPatchFeatureConfig(50, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                                new SimpleBlockFeatureConfig(BlockStateProvider.of(fpaore.grasspop)))));
        register(context, FANCYGRASS, Feature.FLOWER,
                ConfiguredFeatures.createRandomPatchFeatureConfig(60, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                        new SimpleBlockFeatureConfig(BlockStateProvider.of(fpaore.fgrass)))));


    }


    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(fpaore.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}