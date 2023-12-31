package com.figure8.blocks.woodtype;

import com.figure8.fpaore;
import com.figure8.sound.ModSounds;
import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeRegistry;
import net.minecraft.block.BlockSetType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

public class ModBlockSetType {
        public static final BlockSetType FWOOD = BlockSetTypeRegistry.register(new Identifier(fpaore.MOD_ID, "fwood"), (true), BlockSoundGroup.BAMBOO_WOOD, ModSounds.DOORCLOSE, ModSounds.DOOROPEN, ModSounds.DOORCLOSE, ModSounds.DOOROPEN, SoundEvents.BLOCK_BAMBOO_WOOD_PRESSURE_PLATE_CLICK_OFF, SoundEvents.BLOCK_BAMBOO_WOOD_PRESSURE_PLATE_CLICK_ON, SoundEvents.BLOCK_BAMBOO_WOOD_BUTTON_CLICK_OFF, SoundEvents.BLOCK_BAMBOO_WOOD_BUTTON_CLICK_ON);
}
