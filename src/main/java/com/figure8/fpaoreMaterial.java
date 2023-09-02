package com.figure8;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class fpaoreMaterial implements ToolMaterial {
    public static final fpaoreMaterial INSTANCE = new fpaoreMaterial();

    @Override
    public int getDurability() {
        return 2500;
    }
    @Override
    public float getMiningSpeedMultiplier() {
        return 10.0F;
    }
    @Override
    public float getAttackDamage() {
        return 5.0F;
    }
    @Override
    public int getMiningLevel() {
        return 5;
    }
    @Override
    public int getEnchantability() {
        return 5;
    }
    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(fpaore.pbradium);
    }
}
