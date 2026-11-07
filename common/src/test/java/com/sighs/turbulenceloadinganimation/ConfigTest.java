package com.sighs.turbulenceloadinganimation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConfigTest {
    @TempDir
    Path configDirectory;

    @Test
    void migratesExistingForgeSettingsWithoutDeletingTheTomlFile() throws Exception {
        Path legacy = configDirectory.resolve("turbulenceloadinganimation-client.toml");
        Files.writeString(legacy, "words = [\"one\", \"two\"]\nfont-size = 18\narea-size = 72\n");

        Config.load(configDirectory);

        assertEquals(List.of("one", "two"), Config.words());
        assertEquals(18, Config.fontSize());
        assertEquals(72, Config.areaSize());
        assertTrue(Files.exists(configDirectory.resolve("turbulenceloadinganimation.json")));
        assertTrue(Files.exists(legacy));

        Files.writeString(legacy, "words = [\"ignored\"]\nfont-size = 99\n");
        Config.load(configDirectory);
        assertEquals(List.of("one", "two"), Config.words());
        assertEquals(18, Config.fontSize());
        assertEquals(72, Config.areaSize());
    }
}
