package com.figure8.entity;

import com.figure8.fpaore;
import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class ModBoats {
    public static final Identifier FWOOD_BOAT_ID = new Identifier(fpaore.MOD_ID, "fwood_boat");
    public static final Identifier FWOOD_CHEST_BOAT_ID = new Identifier(fpaore.MOD_ID, "fwood_chest_boat");

    public static final RegistryKey<TerraformBoatType> FWOOD_BOAT_KEY = TerraformBoatTypeRegistry.createKey(FWOOD_BOAT_ID);
    public static final Identifier STRIPPED_FWOOD_BOAT_ID = new Identifier(fpaore.MOD_ID, "stripped_fwood_boat");
    public static final Identifier STRIPPED_FWOOD_CHEST_BOAT_ID = new Identifier(fpaore.MOD_ID, "stripped_fwood_chest_boat");

    public static final RegistryKey<TerraformBoatType> STRIPPED_FWOOD_BOAT_KEY = TerraformBoatTypeRegistry.createKey(STRIPPED_FWOOD_BOAT_ID);

    public static void registerBoats() {
        TerraformBoatType fwoodwoodBoat = new TerraformBoatType.Builder()
                .item(fpaore.FWOOD_BOAT)
                .chestItem(fpaore.FWOOD_CHEST_BOAT)
                .planks(fpaore.fwood_planks.asItem())
                .build();

        Registry.register(TerraformBoatTypeRegistry.INSTANCE, FWOOD_BOAT_KEY, fwoodwoodBoat);

        TerraformBoatType strippedfwoodwoodBoat = new TerraformBoatType.Builder()
                .item(fpaore.STRIPPED_FWOOD_BOAT)
                .chestItem(fpaore.STRIPPED_FWOOD_CHEST_BOAT)
                .planks(fpaore.stripped_fwood_planks.asItem())
                .build();

        Registry.register(TerraformBoatTypeRegistry.INSTANCE, STRIPPED_FWOOD_BOAT_KEY, strippedfwoodwoodBoat);
    }
}