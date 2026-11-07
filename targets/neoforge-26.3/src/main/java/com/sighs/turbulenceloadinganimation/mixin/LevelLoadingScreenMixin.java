package com.sighs.turbulenceloadinganimation.mixin;

import com.sighs.turbulenceloadinganimation.Loading;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.LevelLoadingScreen;
import net.minecraft.client.multiplayer.LevelLoadTracker;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = LevelLoadingScreen.class, remap = false)
public abstract class LevelLoadingScreenMixin {
    @Shadow(remap = false)
    @Final
    private static Component DOWNLOADING_TERRAIN_TEXT;

    @Shadow(remap = false)
    private LevelLoadTracker loadTracker;

    @Inject(method = "extractRenderState", at = @At("HEAD"), cancellable = true, remap = false)
    private void cancel(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
        Loading.text = loadTracker.hasProgress()
                ? Math.round(loadTracker.serverProgress() * 100.0F) + "%"
                : DOWNLOADING_TERRAIN_TEXT.getString();
        ci.cancel();
    }
}
