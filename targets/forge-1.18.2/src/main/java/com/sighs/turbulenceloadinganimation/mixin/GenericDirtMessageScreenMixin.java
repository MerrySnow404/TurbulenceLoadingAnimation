package com.sighs.turbulenceloadinganimation.mixin;

import com.sighs.turbulenceloadinganimation.Loading;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.GenericDirtMessageScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = GenericDirtMessageScreen.class)
public class GenericDirtMessageScreenMixin extends Screen {
    protected GenericDirtMessageScreenMixin(Component p_96550_) {
        super(p_96550_);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/GenericDirtMessageScreen;drawCenteredString(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;III)V"), cancellable = true)
    private void text(PoseStack p_281274_, int p_283012_, int p_282072_, float p_282608_, CallbackInfo ci) {
        Loading.text = title.getString();
        Loading.update();
        ci.cancel();
    }
}
