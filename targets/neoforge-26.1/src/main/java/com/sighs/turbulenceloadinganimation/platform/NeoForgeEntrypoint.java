package com.sighs.turbulenceloadinganimation.platform;

import com.sighs.turbulenceloadinganimation.TLA;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

@Mod(value = TLA.MODID, dist = Dist.CLIENT)
public final class NeoForgeEntrypoint {
    public NeoForgeEntrypoint() {
        TLA.initialize();
    }
}
