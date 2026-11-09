package com.sighs.turbulenceloadinganimation.mixin;

import com.sighs.turbulenceloadinganimation.Loading;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.screens.LevelLoadingScreen;
import net.minecraft.client.gui.screens.GenericMessageScreen;
import net.minecraft.client.gui.screens.ProgressScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Gui.class, remap = false)
public class GuiMixin {
    @Inject(method = "setScreen", at = @At("HEAD"), remap = false)
    private void listen(Screen screen, CallbackInfo ci) {
        if (screen instanceof GenericMessageScreen message) {
            Loading.text = message.getTitle().getString();
        }
        if (screen instanceof LevelLoadingScreen || screen instanceof ProgressScreen || screen instanceof GenericMessageScreen) Loading.toggle(true);
        if (screen == null || screen instanceof TitleScreen || screen instanceof CreateWorldScreen) Loading.toggle(false);
    }
}
