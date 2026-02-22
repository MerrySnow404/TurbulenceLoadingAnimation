package com.sighs.turbulenceloadinganimation;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = TLA.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> WORDS = BUILDER.defineList("words", List.of(), entry -> entry instanceof String);
    public static final ForgeConfigSpec.ConfigValue<Integer> FONT_SIZE = BUILDER.define("font-size", 12);
    public static final ForgeConfigSpec.ConfigValue<Integer> AREA_SIZE = BUILDER.define("area-size", 64);

    static final ForgeConfigSpec SPEC = BUILDER.build();
}
