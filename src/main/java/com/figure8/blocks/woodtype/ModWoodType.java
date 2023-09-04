package com.figure8.blocks.woodtype;

import com.figure8.fpaore;
import com.figure8.util.CommonPlatformHelper;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeRegistry;
import net.minecraft.block.BlockSetType;
import net.minecraft.block.WoodType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

public class ModWoodType {
        public static final WoodType FWOOD = WoodTypeRegistry.register(new Identifier(fpaore.MOD_ID, "fwood"), ModBlockSetType.FWOOD, BlockSoundGroup.CHERRY_WOOD, BlockSoundGroup.CHERRY_WOOD_HANGING_SIGN, SoundEvents.BLOCK_CHERRY_WOOD_FENCE_GATE_CLOSE, SoundEvents.BLOCK_CHERRY_WOOD_FENCE_GATE_OPEN);
        public static final WoodType STRIPPED_FWOOD = WoodTypeRegistry.register(new Identifier(fpaore.MOD_ID, "stripped_fwood"), ModBlockSetType.FWOOD, BlockSoundGroup.CHERRY_WOOD, BlockSoundGroup.CHERRY_WOOD_HANGING_SIGN, SoundEvents.BLOCK_CHERRY_WOOD_FENCE_GATE_CLOSE, SoundEvents.BLOCK_CHERRY_WOOD_FENCE_GATE_OPEN);

}
