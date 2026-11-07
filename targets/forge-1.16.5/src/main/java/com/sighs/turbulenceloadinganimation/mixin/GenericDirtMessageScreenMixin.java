package com.sighs.turbulenceloadinganimation.mixin;

import com.sighs.turbulenceloadinganimation.Loading;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.DirtMessageScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.ITextComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = DirtMessageScreen.class)
public class GenericDirtMessageScreenMixin extends Screen {
    protected GenericDirtMessageScreenMixin(ITextComponent p_96550_) {
        super(p_96550_);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/DirtMessageScreen;drawCenteredString(Lcom/mojang/blaze3d/matrix/MatrixStack;Lnet/minecraft/client/gui/FontRenderer;Lnet/minecraft/util/text/ITextComponent;III)V"), cancellable = true)
    private void text(MatrixStack p_281274_, int p_283012_, int p_282072_, float p_282608_, CallbackInfo ci) {
        Loading.text = title.getString();
        ci.cancel();
        Loading.toggle(true);
    }
}
