package com.sighs.turbulenceloadinganimation.mixin;

import com.sighs.turbulenceloadinganimation.Loading;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.WorkingScreen;
import net.minecraft.util.text.ITextComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = WorkingScreen.class)
public class ProgressScreenMixin {
    @Shadow
    private ITextComponent header;

    @Shadow
    private ITextComponent stage;

    @Shadow
    private int progress;

    @Shadow
    private boolean stop;

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void text(MatrixStack p_283582_, int p_96516_, int p_96517_, float p_96518_, CallbackInfo ci) {
        if (!this.stop) {
            if (this.header != null) {
                Loading.text = this.header.getString();
            }
            ci.cancel();
        }
    }
}
