package com.figure8.sound;

import com.figure8.fpaore;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

public class ModSounds {

    public static SoundEvent SPRING_BLOCK_BREAK = registerSoundEvent("spring_block_break");
    public static SoundEvent SPRING_BLOCK_WALK = registerSoundEvent("spring_block_walk");
    public static SoundEvent SPRING_BLOCK_PLACE = registerSoundEvent("spring_block_place");
    public static SoundEvent SPRING_BLOCK_HIT = registerSoundEvent("spring_block_hit");

    public static final BlockSoundGroup SPRING_BLOCK_SOUNDS = new BlockSoundGroup(3f, 1f,
            ModSounds.SPRING_BLOCK_BREAK, ModSounds.FOOTSTEP, ModSounds.SPRING_BLOCK_WALK, ModSounds.SPRING_BLOCK_HIT, ModSounds.SPRING_BLOCK_PLACE);

    public static SoundEvent lounge_inside = registerSoundEvent("lounge_inside_music_disk");
    public static SoundEvent angry_baddie_music_disk = registerSoundEvent("angry_baddie_music_disk");
    public static SoundEvent mayor_theme_music_disk = registerSoundEvent("mayor_theme_music_disk");
    public static SoundEvent level_four_music_disk = registerSoundEvent("level_four_music_disk");

    public static SoundEvent BRADBLOCK_BLOCK_PLACE = registerSoundEvent("bradblock_block_place");

    public static SoundEvent BRADBLOCK_BLOCK_WALK = registerSoundEvent("bradblock_block_step");
    public static SoundEvent BRADBLOCK_BLOCK_BREAK = registerSoundEvent("bradblock_block_break");
    public static SoundEvent SPIKE_OW_OWHIT = registerSoundEvent("spike_ow_onhit");
    public static SoundEvent POPPER = registerSoundEvent("popper");
    public static SoundEvent DOORCLOSE = registerSoundEvent("doorclose");
    public static SoundEvent DOOROPEN = registerSoundEvent("dooropen");

    public static SoundEvent FOOTSTEP = registerSoundEvent("footstep");

    public static final BlockSoundGroup BRADBLOCK_BLOCK_SOUNDS = new BlockSoundGroup(2f, 1f,
            ModSounds.BRADBLOCK_BLOCK_BREAK, ModSounds.FOOTSTEP, ModSounds.BRADBLOCK_BLOCK_PLACE, ModSounds.BRADBLOCK_BLOCK_WALK,ModSounds.BRADBLOCK_BLOCK_WALK);

    public static SoundEvent GRASSPOP_BLOCK_BREAK = registerSoundEvent("grasspop_block_break");

    public static final BlockSoundGroup GRASSPOP_BLOCK_SOUNDS = new BlockSoundGroup(2f, 1.25f,
            ModSounds.POPPER, ModSounds.FOOTSTEP, SoundEvents.BLOCK_GRASS_PLACE, SoundEvents.BLOCK_GRASS_PLACE, ModSounds.GRASSPOP_BLOCK_BREAK);










    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(fpaore.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));

    }
}
