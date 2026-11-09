package com.sighs.turbulenceloadinganimation;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Config {
    private static final Pattern LEGACY_VALUE = Pattern.compile("(?m)^\\s*([\\w-]+)\\s*=\\s*(.*?)\\s*$");
    private static List<String> words = List.of();
    private static int fontSize = 12;
    private static int areaSize = 64;

    private Config() {}

    public static void load(Path configDirectory) throws IOException {
        Path configFile = configDirectory.resolve(TLA.MODID + ".json");
        if (Files.exists(configFile)) {
            readJson(configFile);
            return;
        }

        Path legacyFile = configDirectory.resolve(TLA.MODID + "-client.toml");
        if (Files.exists(legacyFile)) {
            readLegacyToml(legacyFile);
            writeJson(configFile);
        } else {
            Files.createDirectories(configDirectory);
            writeJson(configFile);
        }
    }

    public static List<String> words() {
        return words;
    }

    public static int fontSize() {
        return fontSize;
    }

    public static int areaSize() {
        return areaSize;
    }

    private static void readJson(Path file) throws IOException {
        JsonObject root = new JsonParser().parse(Files.readString(file)).getAsJsonObject();
        if (root.has("words")) {
            JsonArray values = root.getAsJsonArray("words");
            List<String> parsed = new ArrayList<>(values.size());
            for (JsonElement value : values) {
                if (!value.isJsonPrimitive() || !value.getAsJsonPrimitive().isString()) {
                    throw new IOException("Invalid words entry in " + file);
                }
                parsed.add(value.getAsString());
            }
            words = List.copyOf(parsed);
        }
        if (root.has("font-size")) fontSize = root.get("font-size").getAsInt();
        if (root.has("area-size")) areaSize = root.get("area-size").getAsInt();
    }

    private static void readLegacyToml(Path file) throws IOException {
        Matcher matcher = LEGACY_VALUE.matcher(Files.readString(file));
        while (matcher.find()) {
            String key = matcher.group(1);
            String value = matcher.group(2);
            try {
                switch (key) {
                    case "words" -> {
                        JsonArray values = new JsonParser().parse(value).getAsJsonArray();
                        List<String> parsed = new ArrayList<>(values.size());
                        for (JsonElement entry : values) {
                            if (!entry.isJsonPrimitive() || !entry.getAsJsonPrimitive().isString()) {
                                throw new IOException("Invalid words entry in " + file);
                            }
                            parsed.add(entry.getAsString());
                        }
                        words = List.copyOf(parsed);
                    }
                    case "font-size" -> fontSize = Integer.parseInt(value);
                    case "area-size" -> areaSize = Integer.parseInt(value);
                }
            } catch (RuntimeException exception) {
                throw new IOException("Invalid " + key + " value in " + file, exception);
            }
        }
    }

    private static void writeJson(Path file) throws IOException {
        JsonObject root = new JsonObject();
        JsonArray values = new JsonArray();
        for (String word : words) values.add(new JsonPrimitive(word));
        root.add("words", values);
        root.addProperty("font-size", fontSize);
        root.addProperty("area-size", areaSize);
        Files.createDirectories(file.getParent());
        Files.writeString(file, new GsonBuilder().setPrettyPrinting().create().toJson(root) + System.lineSeparator());
    }
}
