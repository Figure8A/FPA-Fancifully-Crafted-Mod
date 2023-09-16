package com.figure8.effects;

import com.figure8.fpaore;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEffects {
    public static StatusEffect HPSQUIGGLEHEAL;
    public static StatusEffect HPSQUIGGLEHEALGREEN;

    public static StatusEffect registerStatusEffect(String name) {
        Registry.register(Registries.STATUS_EFFECT, new Identifier(fpaore.MOD_ID, name), new hphealbutfive(StatusEffectCategory.BENEFICIAL, 4056576));
        return Registry.register(Registries.STATUS_EFFECT, new Identifier(fpaore.MOD_ID, name), new hphealbutone(StatusEffectCategory.BENEFICIAL, 10270440));



    }


    public static void registerEffects() {
        HPSQUIGGLEHEAL = registerStatusEffect("squiggleheal");
        HPSQUIGGLEHEALGREEN = registerStatusEffect("squigglehealgreen");
    }
}