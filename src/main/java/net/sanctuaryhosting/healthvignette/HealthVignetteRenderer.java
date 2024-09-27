package net.sanctuaryhosting.healthvignette;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.world.GameMode;

public class HealthVignetteRenderer {
    private static final Identifier vignetteTexture = Identifier.of(HealthVignette.modID, "textures/health-vignette.png");

    public static class render {
        public static void renderHud(DrawContext context) {
            MinecraftClient client = MinecraftClient.getInstance();
            PlayerEntity player = client.player;

            if (player == null || client.interactionManager == null || !HealthVignette.configuration.enabled) {
                return;
            }

            GameMode gameMode = client.interactionManager.getCurrentGameMode();
            if (gameMode == GameMode.CREATIVE || gameMode == GameMode.SPECTATOR) {
                return;
            }

            float health = player.getHealth();
            float maxHealth = player.getMaxHealth();
            float healthPercentage = health / maxHealth;

            float minStrength = HealthVignette.configuration.introductionThreshold / 100f;
            float maxStrength = HealthVignette.configuration.severeThreshold / 100f;

            if (healthPercentage <= minStrength) {
                float intensity = 1.0f - (healthPercentage - maxStrength) / (minStrength - maxStrength);
                intensity = Math.max(0.0f, Math.min(1.0f, intensity));
                intensity *= HealthVignette.configuration.vignetteIntensity / 100f;

                int color = HealthVignette.configuration.vignetteColor;
                float r = ((color >> 16) & 0xFF) / 255.0f;
                float g = ((color >> 8) & 0xFF) / 255.0f;
                float b = (color & 0xFF) / 255.0f;

                RenderSystem.disableDepthTest();
                RenderSystem.depthMask(false);
                RenderSystem.enableBlend();
                RenderSystem.defaultBlendFunc();
                RenderSystem.setShaderColor(r, g, b, intensity);
                RenderSystem.setShaderTexture(0, vignetteTexture);

                int screenWidth = client.getWindow().getScaledWidth();
                int screenHeight = client.getWindow().getScaledHeight();

                context.drawTexture(vignetteTexture, 0, 0, -90, 0, 0, screenWidth, screenHeight, screenWidth, screenHeight);

                RenderSystem.depthMask(true);
                RenderSystem.enableDepthTest();
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            }
        }
    }
}