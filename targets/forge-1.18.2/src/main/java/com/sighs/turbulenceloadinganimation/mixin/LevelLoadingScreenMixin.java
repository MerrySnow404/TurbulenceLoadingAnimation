package com.sighs.turbulenceloadinganimation.mixin;

import com.sighs.turbulenceloadinganimation.Loading;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.LevelLoadingScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = LevelLoadingScreen.class)
public abstract class LevelLoadingScreenMixin {
    @Shadow
    protected abstract String getFormattedProgress();

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/LevelLoadingScreen;renderChunks(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/server/level/progress/StoringChunkProgressListener;IIII)V"), cancellable = true)
    private void cancel(PoseStack guiGraphics, int p_96146_, int p_96147_, float p_96148_, CallbackInfo ci) {
        Loading.text = getFormattedProgress();
        Loading.update();
        ci.cancel();
    }
}
