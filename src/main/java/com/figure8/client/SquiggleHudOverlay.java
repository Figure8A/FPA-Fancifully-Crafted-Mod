package com.figure8.client;

import com.figure8.fpaore;
import com.figure8.item.PantsItem;
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

import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class SquiggleHudOverlay implements HudRenderCallback {

    private boolean isPant(final ItemStack stack) {
        return !stack.isEmpty() && stack.getItem() instanceof PantsItem;
    }

    private static final Identifier SQUIGGLEAMOUNT = new Identifier(fpaore.MOD_ID,
            "textures/squigglehud/squiggleamount.png");

    private static final Identifier PANTS = new Identifier(fpaore.MOD_ID,
            "textures/squigglehud/pants_icon.png");

    public SquiggleHudOverlay() {
    }


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
        int pants = 0;
        for (final ItemStack stack : client.player.getInventory().main) {
            if (isPant(stack)) {
                pants += stack.getCount();
            }
        }
        if (pants > 0) {
            RenderSystem.setShaderTexture(0, PANTS);
            context.drawTexture(PANTS, x+150, y-15, 0, 0, 16, 16, 16, 16);
            context.drawText(client.textRenderer, String.valueOf(pants), x+166, y-8, 0xffffff, true);
        }
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
    }
}
