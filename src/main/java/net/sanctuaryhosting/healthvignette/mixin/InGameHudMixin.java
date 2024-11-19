package net.sanctuaryhosting.healthvignette.mixin;

import net.minecraft.client.render.RenderTickCounter;
import net.sanctuaryhosting.healthvignette.HealthVignetteRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Inject(method = "render", at = @At(value = "HEAD", target = "Lnet/minecraft/client/render/RenderPhase;enableBlend()V"))
    private void renderHealthVignette(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        HealthVignetteRenderer.render.renderHud(context);
    }
}