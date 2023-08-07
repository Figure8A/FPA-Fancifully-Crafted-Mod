package com.figure8.blocks;

import com.figure8.fpaore;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;

public class spike extends FlowerBlock {
    public spike(StatusEffect effect, AbstractBlock.Settings settings) {
        super(effect, 8, settings);
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return super.canPlantOnTop(floor, world, pos) || floor.isIn(BlockTags.PICKAXE_MINEABLE) || floor.isIn(BlockTags.HOE_MINEABLE) || floor.isIn(BlockTags.SHOVEL_MINEABLE) ||  floor.isIn(BlockTags.AXE_MINEABLE) || floor.isOf(Blocks.SANDSTONE) || floor.isOf(Blocks.SAND) || floor.isOf(Blocks.STONE) ||  floor.isOf(Blocks.OAK_PLANKS) ||  floor.isOf(Blocks.DEEPSLATE) ||  floor.isOf(fpaore.grasblockbutgood) || floor.isOf(fpaore.coursegrasblockbutgood) || floor.isOf(fpaore.sandstonebedrock) ||floor.isOf(Blocks.SMOOTH_SANDSTONE);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        VoxelShape voxelShape = this.getOutlineShape(state, world, pos, ShapeContext.absent());
        Vec3d vec3d = voxelShape.getBoundingBox().getCenter();
        double d = (double)pos.getX() + vec3d.x;
        double e = (double)pos.getZ() + vec3d.z;
        for (int i = 0; i < 3; ++i) {
            if (!random.nextBoolean()) continue;
            world.addParticle(ParticleTypes.DRAGON_BREATH, d + random.nextDouble() / 5.0, (double)pos.getY() + (0.5 - random.nextDouble()), e + random.nextDouble() / 5.0, 0.0, 0.005, 0.0);
        }
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        LivingEntity livingEntity;
        if (world.isClient || world.getDifficulty() == Difficulty.PEACEFUL) {
            return;
        }
        if (entity instanceof LivingEntity && !(livingEntity = (LivingEntity)entity).isInvulnerableTo(world.getDamageSources().wither())) {
            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 1, 10));
        }
    }
}

