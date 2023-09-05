package com.figure8;

import com.figure8.blocks.woodtype.ModWoodType;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.render.RenderLayer;

import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.block.entity.HangingSignBlockEntityRenderer;
import net.minecraft.client.render.block.entity.SignBlockEntityRenderer;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;

import static com.figure8.fpaore.THROWABLE_BLOB_ENTITY;


public class fpaoreClient implements ClientModInitializer {
	public static final Identifier PacketID = new Identifier(fpaore.MOD_ID, "spawn_packet");

	@Override
	public void onInitializeClient() {

		EntityRendererRegistry.register(THROWABLE_BLOB_ENTITY, FlyingItemEntityRenderer::new);



		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		BlockRenderLayerMap.INSTANCE.putBlock(fpaore.sketch, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(fpaore.spring, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(fpaore.grasspop, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(fpaore.fgrass, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(fpaore.inkblot, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(fpaore.mayorglass, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(fpaore.fwood_sapling, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(fpaore.fwood_leaves, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(fpaore.fwood_trapdoor, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(fpaore.stripped_fwood_trapdoor, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(fpaore.spike, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(fpaore.inkblot_wall, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(fpaore.squiggleblock, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(fpaore.potted_fwood_sapling, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(fpaore.potted_fgrass, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(fpaore.potted_grasspop, RenderLayer.getCutout());

		ParticleFactoryRegistry.getInstance().register(fpaore.SQUIGGLETHING, FlameParticle.Factory::new);
		ParticleFactoryRegistry.getInstance().register(fpaore.SQUIGGLETHINGM, FlameParticle.Factory::new);

		BlockEntityRendererFactories.register(fpaore.MOD_SIGN_BLOCK_ENTITY, SignBlockEntityRenderer::new);
		BlockEntityRendererFactories.register(fpaore.STRIPPED_MOD_SIGN_BLOCK_ENTITY, SignBlockEntityRenderer::new);
		BlockEntityRendererFactories.register(fpaore.MOD_HANGING_SIGN_BLOCK_ENTITY, HangingSignBlockEntityRenderer::new);

		TexturedRenderLayers.SIGN_TYPE_TEXTURES.put(ModWoodType.FWOOD, TexturedRenderLayers.getSignTextureId(ModWoodType.FWOOD));
		TexturedRenderLayers.SIGN_TYPE_TEXTURES.put(ModWoodType.STRIPPED_FWOOD, TexturedRenderLayers.getSignTextureId(ModWoodType.STRIPPED_FWOOD));
	}


}