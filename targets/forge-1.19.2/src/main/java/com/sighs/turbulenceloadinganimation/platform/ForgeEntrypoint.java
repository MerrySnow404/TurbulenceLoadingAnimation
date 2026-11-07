package com.sighs.turbulenceloadinganimation.platform;

import com.sighs.turbulenceloadinganimation.TLA;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLEnvironment;

@Mod(TLA.MODID)
public final class ForgeEntrypoint {
    public ForgeEntrypoint() {
        if (FMLEnvironment.dist == Dist.CLIENT) TLA.initialize();
    }
}
