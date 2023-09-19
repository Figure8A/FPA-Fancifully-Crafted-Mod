package com.figure8.paintings;

import com.figure8.fpaore;
import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModPaintings {

    public static final PaintingVariant THUMBSUP = registerPainting("thumbsup", new PaintingVariant(32, 32));


    private static PaintingVariant registerPainting(String name, PaintingVariant paintingVariant) {
        return Registry.register(Registries.PAINTING_VARIANT, new Identifier(fpaore.MOD_ID, name), paintingVariant);
    }

    public static void registerPaintings() {
        fpaore.LOGGER.info("Registering Paintings for " + fpaore.MOD_ID);
    }
}