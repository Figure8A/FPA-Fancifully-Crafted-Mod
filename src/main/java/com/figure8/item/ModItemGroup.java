package com.figure8.item;

import com.figure8.fpaore;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroup {    public static ItemGroup fpablocks = Registry.register(Registries.ITEM_GROUP, new Identifier(fpaore.MOD_ID, "fpaore"),
        FabricItemGroup.builder().displayName(Text.translatable("itemgroup.fpaore"))
                .icon(() -> new ItemStack(fpaore.bradium)).entries((displayContext, entries) -> {
                    entries.add(fpaore.BRADIUM_HELMET);
                    entries.add(fpaore.BRADIUM_CHESTPLATE);
                    entries.add(fpaore.BRADIUM_LEGGINGS);
                    entries.add(fpaore.BRADIUM_BOOTS);
                    entries.add(fpaore.spring);
                    entries.add(fpaore.sketch);
                    entries.add(fpaore.fgrass);
                    entries.add(fpaore.grasblockbutgood);
                    entries.add(fpaore.coursegrasblockbutgood);
                    entries.add(fpaore.grasspop);
                    entries.add(fpaore.squiggle);
                    entries.add(fpaore.squiggleblock);
                    entries.add(fpaore.squiggleblockgreen);
                    entries.add(fpaore.icecream);
                    entries.add(fpaore.inkblob);
                    entries.add(fpaore.mayor_of_undying);
                    entries.add(fpaore.pencilsword);
                    entries.add(fpaore.lounge_inside_music_disk);
                    entries.add(fpaore.angry_baddie_music_disk);
                    entries.add(fpaore.mayor_theme_music_disk);
                    entries.add(fpaore.level_four_music_disk);
                    entries.add(fpaore.mayorglass);
                    entries.add(fpaore.fcactus);
                    entries.add(fpaore.toughf);
                    entries.add(fpaore.toughf_wall);
                    entries.add(fpaore.fpagrounda);
                    entries.add(fpaore.fpagrounda_SLAB);
                    entries.add(fpaore.fpagrounda_verticalslab);
                    entries.add(fpaore.fpagrounda_stair);
                    entries.add(fpaore.fpagroundatrimed);
                    entries.add(fpaore.fpagroundatrimed_slab);
                    entries.add(fpaore.fpagroundatrimed_verticalslab);
                    entries.add(fpaore.fpagroundatrimed_stair);
                    entries.add(fpaore.fwood_leaves);
                    entries.add(fpaore.fwood_log);
                    entries.add(fpaore.fwood_planks);
                    entries.add(fpaore.fwood_door);
                    entries.add(fpaore.fwood_gate);
                    entries.add(fpaore.fwood_fence);
                    entries.add(fpaore.fwood_plate);
                    entries.add(fpaore.fwood_button);
                    entries.add(fpaore.fwood_trapdoor);
                    entries.add(fpaore.fwood_sign_item);
                    entries.add(fpaore.FWOOD_BOAT);
                    entries.add(fpaore.FWOOD_CHEST_BOAT);
                    entries.add(fpaore.fwood_slab);
                    entries.add(fpaore.fwood_stair);
                    entries.add(fpaore.fwood_sapling);
                    entries.add(fpaore.fwood_wood);
                    entries.add(fpaore.stripped_fwood_log);
                    entries.add(fpaore.stripped_fwood_wood);
                    entries.add(fpaore.stripped_fwood_planks);
                    entries.add(fpaore.stripped_fwood_planksvar);
                    entries.add(fpaore.stripped_fwood_door);
                    entries.add(fpaore.stripped_fwood_gate);
                    entries.add(fpaore.stripped_fwood_fence);
                    entries.add(fpaore.stripped_fwood_plate);
                    entries.add(fpaore.stripped_fwood_button);
                    entries.add(fpaore.stripped_fwood_slab);
                    entries.add(fpaore.stripped_fwood_stair);
                    entries.add(fpaore.stripped_fwood_trapdoor);
                    entries.add(fpaore.stripped_fwood_sign_item);
                    entries.add(fpaore.STRIPPED_FWOOD_BOAT);
                    entries.add(fpaore.STRIPPED_FWOOD_CHEST_BOAT);
                    entries.add(fpaore.fwood_hanging_sign_item);
                    entries.add(fpaore.fpvground);
                    entries.add(fpaore.packed_fpvground);
                    entries.add(fpaore.packed_fpvground_column);
                    entries.add(fpaore.spike);
                    entries.add(fpaore.spikebuth);
                    entries.add(fpaore.BradOre);
                    entries.add(fpaore.bradoredsl);
                    entries.add(fpaore.bradblock);
                    entries.add(fpaore.bradium);
                    entries.add(fpaore.pbradium);
                    entries.add(fpaore.fchiseled_sandstone);
                    entries.add(fpaore.smoothsandstone_verticalslab);
                    entries.add(fpaore.sandstonebedrock);







                }).build());

    public static void registerItemGroups() {

    }
}
