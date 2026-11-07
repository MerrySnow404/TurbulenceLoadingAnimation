package com.sighs.turbulenceloadinganimation.mixin;

import com.sighs.turbulenceloadinganimation.Loading;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.DownloadTerrainScreen;
import net.minecraft.util.text.ITextComponent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = DownloadTerrainScreen.class)
public class ReceivingLevelScreenMixin {
    @Shadow
    @Final
    private static ITextComponent DOWNLOADING_TERRAIN_TEXT;

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/DownloadTerrainScreen;drawCenteredString(Lcom/mojang/blaze3d/matrix/MatrixStack;Lnet/minecraft/client/gui/FontRenderer;Lnet/minecraft/util/text/ITextComponent;III)V"), cancellable = true)
    private void cancel(MatrixStack p_281489_, int p_282902_, int p_283018_, float p_281251_, CallbackInfo ci) {
        Loading.text = DOWNLOADING_TERRAIN_TEXT.getString();
        ci.cancel();
    }
}
