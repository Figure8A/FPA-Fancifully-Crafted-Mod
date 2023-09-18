package com.figure8.potion;

import com.figure8.effects.ModEffects;
import com.figure8.fpaore;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModPotions {
    public static final Potion SQUIGGLE_POTION = registerPotion("squiggle_potion",
            new Potion(new StatusEffectInstance(ModEffects.HPSQUIGGLEHEAL, 150, 0)));

    private static Potion registerPotion(String name, Potion potion) {
        return Registry.register(Registries.POTION, new Identifier(fpaore.MOD_ID, name), potion);
    }

    public static void registerPotions() {
        fpaore.LOGGER.info("Registering Potions for " + fpaore.MOD_ID);
    }
}