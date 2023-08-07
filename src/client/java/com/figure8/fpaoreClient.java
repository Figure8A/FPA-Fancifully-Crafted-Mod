package com.figure8;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;

import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
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
		BlockRenderLayerMap.INSTANCE.putBlock(fpaore.spike, RenderLayer.getCutout());



	}


}