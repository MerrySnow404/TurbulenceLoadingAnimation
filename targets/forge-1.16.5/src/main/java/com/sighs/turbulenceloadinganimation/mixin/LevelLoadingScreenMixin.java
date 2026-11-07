package com.sighs.turbulenceloadinganimation.mixin;

import com.sighs.turbulenceloadinganimation.Loading;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.WorldLoadProgressScreen;
import net.minecraft.world.chunk.listener.TrackingChunkStatusListener;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = WorldLoadProgressScreen.class)
public abstract class LevelLoadingScreenMixin {
    @Shadow
    @Final
    private TrackingChunkStatusListener progressListener;

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/WorldLoadProgressScreen;renderChunks(Lcom/mojang/blaze3d/matrix/MatrixStack;Lnet/minecraft/world/chunk/listener/TrackingChunkStatusListener;IIII)V"), cancellable = true)
    private void cancel(MatrixStack guiGraphics, int p_96146_, int p_96147_, float p_96148_, CallbackInfo ci) {
        Loading.text = Math.max(0, Math.min(100, progressListener.getProgress())) + "%";
        ci.cancel();
    }
}
