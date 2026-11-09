package com.sighs.turbulenceloadinganimation.mixin;

import com.sighs.turbulenceloadinganimation.Loading;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.LevelLoadingScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = LevelLoadingScreen.class)
public abstract class LevelLoadingScreenMixin {
    @Shadow
    protected abstract Component getFormattedProgress();

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/LevelLoadingScreen;renderChunks(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/server/level/progress/StoringChunkProgressListener;IIII)V"), cancellable = true)
    private void cancel(GuiGraphics guiGraphics, int p_96146_, int p_96147_, float p_96148_, CallbackInfo ci) {
        Loading.text = getFormattedProgress().getString();
        Loading.update();
        ci.cancel();
    }
}
