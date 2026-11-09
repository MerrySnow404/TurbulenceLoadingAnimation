package com.sighs.turbulenceloadinganimation.platform;

import com.sighs.turbulenceloadinganimation.LoadingPlatform;
import net.minecraft.client.Minecraft;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.common.NeoForge;

import java.nio.file.Path;

public final class NeoForgeLoadingPlatform implements LoadingPlatform {
    @Override
    public Path configDirectory() {
        return FMLPaths.CONFIGDIR.get();
    }

    @Override
    public String selectedLanguage() {
        return Minecraft.getInstance().getLanguageManager().getSelected();
    }

    @Override
    public void onClientTick(Runnable callback) {
        NeoForge.EVENT_BUS.addListener((ClientTickEvent.Post event) -> callback.run());
    }
}
