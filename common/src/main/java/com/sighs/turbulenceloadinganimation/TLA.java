package com.sighs.turbulenceloadinganimation;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ServiceLoader;

public final class TLA {
    public static final String MODID = "turbulenceloadinganimation";
    private static LoadingPlatform platform;

    private TLA() {}

    public static void initialize() {
        if (platform != null) return;
        platform = ServiceLoader.load(LoadingPlatform.class).findFirst()
                .orElseThrow(() -> new IllegalStateException("No loading platform service is installed"));
        try {
            Config.load(platform.configDirectory());
        } catch (IOException exception) {
            throw new UncheckedIOException("Could not load " + MODID + " configuration", exception);
        }
        platform.onClientTick(Loading::setup);
    }

    public static LoadingPlatform platform() {
        if (platform == null) throw new IllegalStateException("Loading platform has not been initialized");
        return platform;
    }
}
