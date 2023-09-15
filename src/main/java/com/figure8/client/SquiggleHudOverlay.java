package com.figure8.client;

import com.figure8.fpaore;
import com.figure8.util.IEntityDataSaver;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.render.GameRenderer;

import net.minecraft.util.Identifier;

public class SquiggleHudOverlay implements HudRenderCallback {
    private static final Identifier SQUIGGLEAMOUNT = new Identifier(fpaore.MOD_ID,
            "textures/squigglehud/squiggleamount.png");


    @Override
    public void onHudRender(DrawContext context, float tickDelta) {
        var client = MinecraftClient.getInstance();
        var textRenderer = MinecraftClient.getInstance();
        int thirst = ((IEntityDataSaver) MinecraftClient.getInstance().player).getPersistentData().getInt("squiggles");
        RenderSystem.setShaderTexture(0, SQUIGGLEAMOUNT);

        context.drawTexture(SQUIGGLEAMOUNT, 100, 50, 0, 0, 32, 16, 32, 16);
        context.drawText(client.textRenderer, String.valueOf(thirst), 100, 60, 16777215, true);
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
    }
}
