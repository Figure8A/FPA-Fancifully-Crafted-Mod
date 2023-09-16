package com.figure8.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class hphealbutfive extends StatusEffect {
    public hphealbutfive(StatusEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }
    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity.getHealth() < entity.getMaxHealth()) {
            entity.heal(5);}
    }
    @Override
    public boolean canApplyUpdateEffect(int pDuration, int pAmplifier) {
        return true;
    }
}