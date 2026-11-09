package com.sighs.turbulenceloadinganimation.platform;

import com.sighs.turbulenceloadinganimation.LoadingPlatform;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

public final class ForgeLoadingPlatform implements LoadingPlatform {
    @Override
    public Path configDirectory() {
        return FMLPaths.CONFIGDIR.get();
    }

    @Override
    public String selectedLanguage() {
        return Minecraft.getInstance().getLanguageManager().getSelected().getCode();
    }

    @Override
    public void onClientTick(Runnable callback) {
        MinecraftForge.EVENT_BUS.addListener((TickEvent.ClientTickEvent event) -> {
            if (event.phase == TickEvent.Phase.END) callback.run();
        });
    }
}
