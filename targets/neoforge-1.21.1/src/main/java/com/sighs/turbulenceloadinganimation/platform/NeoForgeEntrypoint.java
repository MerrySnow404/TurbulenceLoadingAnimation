package com.sighs.turbulenceloadinganimation.platform;

import com.sighs.turbulenceloadinganimation.TLA;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;

@Mod(TLA.MODID)
public final class NeoForgeEntrypoint {
    public NeoForgeEntrypoint() {
        if (FMLEnvironment.dist == Dist.CLIENT) TLA.initialize();
    }
}
