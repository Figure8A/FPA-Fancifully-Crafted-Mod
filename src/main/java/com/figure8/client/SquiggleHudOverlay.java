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
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.GameRenderer;

import net.minecraft.util.Identifier;

public class SquiggleHudOverlay implements HudRenderCallback {
    private static final Identifier SQUIGGLEAMOUNT = new Identifier(fpaore.MOD_ID,
            "textures/squigglehud/squiggleamount.png");


    @Override
    public void onHudRender(DrawContext context, float tickDelta) {
        int x = 0;
        int y = 0;
        MinecraftClient client = MinecraftClient.getInstance();
        if (client != null) {
            int width = client.getWindow().getScaledWidth();
            int height = client.getWindow().getScaledHeight();

            x = width / 2;
            y = height;
        }
        var textRenderer = MinecraftClient.getInstance();
        int squiggle = ((IEntityDataSaver) MinecraftClient.getInstance().player).getPersistentData().getInt("squiggles");
        RenderSystem.setShaderTexture(0, SQUIGGLEAMOUNT);
        context.drawTexture(SQUIGGLEAMOUNT, x+92, y-23, 0, 0, 76, 22, 76, 22);
        context.drawText(client.textRenderer, String.valueOf(squiggle), x+122, y-16, 0x000000, false);

        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
    }
}
