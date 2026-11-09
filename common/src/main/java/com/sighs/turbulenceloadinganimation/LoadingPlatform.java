package com.sighs.turbulenceloadinganimation;

import java.nio.file.Path;

public interface LoadingPlatform {
    Path configDirectory();

    String selectedLanguage();

    void onClientTick(Runnable callback);
}
