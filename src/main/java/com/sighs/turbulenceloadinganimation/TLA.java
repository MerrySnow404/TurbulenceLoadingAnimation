package com.sighs.turbulenceloadinganimation;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod(TLA.MODID)
public class TLA {
    public static final String MODID = "turbulenceloadinganimation";

    public TLA() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.SPEC);
    }
}
