package com.sighs.turbulenceloadinganimation.platform;

import com.sighs.turbulenceloadinganimation.TLA;
import net.fabricmc.api.ClientModInitializer;

public final class FabricEntrypoint implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        TLA.initialize();
    }
}
