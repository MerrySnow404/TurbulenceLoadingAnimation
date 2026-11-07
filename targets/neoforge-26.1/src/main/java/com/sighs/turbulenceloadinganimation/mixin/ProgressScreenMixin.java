package com.sighs.turbulenceloadinganimation.mixin;

import com.sighs.turbulenceloadinganimation.Loading;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.ProgressScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ProgressScreen.class, remap = false)
public class ProgressScreenMixin {
    @Shadow(remap = false)
    private Component header;

    @Shadow(remap = false)
    private Component stage;

    @Shadow(remap = false)
    private int progress;

    @Shadow(remap = false)
    private boolean stop;

    @Inject(method = "extractRenderState", at = @At("HEAD"), cancellable = true, remap = false)
    private void text(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
        if (!this.stop) {
            if (this.header != null) {
                Loading.text = this.header.getString();
            }
            ci.cancel();
        }
    }
}
