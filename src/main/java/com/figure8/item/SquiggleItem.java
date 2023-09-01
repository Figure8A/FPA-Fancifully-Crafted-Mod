package com.figure8.item;

import com.figure8.entity.ThrowableBlobEntity;
import com.figure8.sound.ModSounds;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.minecraft.entity.effect.StatusEffects.CONDUIT_POWER;
import static net.minecraft.entity.effect.StatusEffects.INSTANT_HEALTH;

public class SquiggleItem extends Item {
    public SquiggleItem(Settings settings) {
        super(settings);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand ) {
        final ItemStack stack = user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), ModSounds.GRASSPOP_BLOCK_BREAK, SoundCategory.NEUTRAL, 1.0f, 0.75f + world.random.nextFloat() * 0.5f);

        if (!user.isCreative()) {
            stack.decrement(1);
        }
        if (!world.isClient()) {
            user.heal(1);
        }
        return TypedActionResult.success(stack, world.isClient());
    }
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.fpaore.squiggle.tooltip").formatted(Formatting.DARK_AQUA));
        super.appendTooltip(stack, world, tooltip, context);
    }
}