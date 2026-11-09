package com.sighs.turbulenceloadinganimation.platform;

import com.sighs.turbulenceloadinganimation.LoadingPlatform;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;

import java.nio.file.Path;

public final class FabricLoadingPlatform implements LoadingPlatform {
    @Override
    public Path configDirectory() {
        return FabricLoader.getInstance().getConfigDir();
    }

    @Override
    public String selectedLanguage() {
        return Minecraft.getInstance().getLanguageManager().getSelected();
    }

    @Override
    public void onClientTick(Runnable callback) {
        ClientTickEvents.END_CLIENT_TICK.register(client -> callback.run());
    }
}
