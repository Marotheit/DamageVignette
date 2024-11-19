package net.sanctuaryhosting.healthvignette;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.MathHelper;
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

            float healthPercentage = player.getHealth() / player.getMaxHealth();
            float minimumStrength = HealthVignette.configuration.introductionThreshold / 100F;
            float maximumStrength = HealthVignette.configuration.severeThreshold / 100F;

            if (healthPercentage <= minimumStrength) {
                float healthRange = healthPercentage - maximumStrength;
                float thresholdRange = minimumStrength - maximumStrength;

                float intensity = 0.0F;
                if (healthRange < thresholdRange) {
                    intensity = 1.0F - (healthRange / thresholdRange);
                }

                if (intensity > 0.0F) {
                    intensity = MathHelper.clamp(intensity, 0.0F, 1.0F);
                    intensity *= HealthVignette.configuration.vignetteIntensity / 100F;

                    int color = HealthVignette.configuration.vignetteColor;
                    float baseRed = ((color >> 16) & 0xFF) / 255.0F;
                    float baseGreen = ((color >> 8) & 0xFF) / 255.0F;
                    float baseBlue = (color & 0xFF) / 255.0F;
                    float r = 1.0F - (intensity * baseRed);
                    float g = 1.0F - (intensity * baseGreen);
                    float b = 1.0F - (intensity * baseBlue);

                    RenderSystem.enableBlend();
                    RenderSystem.blendFunc(GlStateManager.SrcFactor.ZERO, GlStateManager.DstFactor.ONE_MINUS_SRC_COLOR);

                    context.drawTexture(RenderLayer::getVignette, vignetteTexture, 0, 0, 0.0F, 0.0F, client.getWindow().getScaledWidth(), client.getWindow().getScaledHeight(), client.getWindow().getScaledWidth(), client.getWindow().getScaledHeight(), ColorHelper.fromFloats(1.0F, r, g, b));

                    RenderSystem.disableBlend();
                    RenderSystem.defaultBlendFunc();
                }
            }
        }
    }
}