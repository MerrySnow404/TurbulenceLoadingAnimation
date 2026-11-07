package com.sighs.turbulenceloadinganimation;

import com.sighs.apricityui.init.Document;
import com.sighs.apricityui.init.Element;
import com.sighs.apricityui.parser.HTML;
import com.sighs.apricityui.parser.Color;
import com.sighs.apricityui.style.Background;
import com.sighs.apricityui.style.StyleFrameCache;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LoadingTest {
    @TempDir
    Path configDirectory;

    @Test
    void loadingScreenUpdatesWithoutClientTicksAndStopsChangingWhenHidden() throws Exception {
        Files.writeString(configDirectory.resolve(TLA.MODID + ".json"),
                "{\"words\":[\"词库测试\"],\"font-size\":12,\"area-size\":64}");
        Config.load(configDirectory);
        try (var stream = getClass().getResourceAsStream("/assets/apricityui/apricity/tla_ms/index.html")) {
            assertNotNull(stream);
            HTML.putTemple("tla_ms/index.html", new String(stream.readAllBytes(), StandardCharsets.UTF_8));
        }

        Loading.setup();
        Document document = Document.get("tla_ms/index.html").get(0);
        try {
            assertTrue(Background.resolveImagePaths(document.getPath(), document.body.getComputedStyle().backgroundImage).isEmpty(),
                    "A missing optional background must not be submitted to AUI's image loader");
            assertTrue(document.isReloadPersistent(), "Native loading screens only draw persistent AUI documents");
            assertEquals("display:none;", document.body.getAttribute("style"));
            document.body.setInlineStyleProperty("background-size", "contain");

            Loading.text = "加载进度";
            Loading.update(1_000_000_000L);
            List<Element> words = document.querySelectorAll("span");
            assertEquals(10, words.size());
            assertEquals("加载进度", words.get(0).getTextContent());
            assertEquals("词库测试", words.get(1).getTextContent());
            assertEquals("flex", document.body.getComputedStyle().display);
            assertEquals("contain", document.body.getComputedStyle().backgroundSize,
                    "Showing the overlay must preserve custom background styles");
            assertTrue(document.body.getBoundingClientRect().height > 0, "Loading overlay must cover the viewport");
            assertTrue(words.get(1).getBoundingClientRect().width > 0, "Dictionary text must have visible geometry");
            assertTrue(words.get(1).getBoundingClientRect().y > 0, "Words must be inside the viewport");
            String style = words.get(1).parentElement.getAttribute("style");
            assertTrue(style.contains("12px"));
            assertTrue(style.contains("infinite"));

            Loading.update(1_100_000_000L);
            assertEquals(style, words.get(1).parentElement.getAttribute("style"));
            Loading.text = "下一阶段";
            Loading.update(3_000_000_000L);
            assertEquals("下一阶段", words.get(0).getTextContent());

            Element word = words.get(1);
            word.parentElement.setAttribute("style", style.replace("linear 200ms", "linear -500ms"));
            document.tickFrame();
            StyleFrameCache.begin();
            try {
                document.stepMotionRender();
                assertTrue((Color.parse(word.parentElement.getComputedStyle().color) >>> 24) > 0,
                        () -> "Animation must reach a visible frame: " + word.parentElement.getComputedStyle().animation);
                assertTrue((Color.parse(word.getComputedStyle().color) >>> 24) > 0,
                        "Dictionary text must inherit the animation's visible color");
            } finally {
                StyleFrameCache.end();
            }

            Loading.toggle(false);
            assertEquals("none", words.get(1).parentElement.getComputedStyle().animation);
            String hiddenStyle = words.get(1).parentElement.getAttribute("style");
            for (int i = 0; i < 100; i++) Loading.setup();
            assertEquals("none", document.body.getComputedStyle().display);
            assertEquals(hiddenStyle, words.get(1).parentElement.getAttribute("style"));

            Element previousBody = document.body;
            document.refresh();
            Loading.setup();
            assertNotSame(previousBody, document.body);
            assertEquals("none", document.body.getComputedStyle().display);
            assertTrue(Background.resolveImagePaths(document.getPath(), document.body.getComputedStyle().backgroundImage).isEmpty(),
                    "Resource reload must not restore a missing optional background");
            Loading.update(4_000_000_000L);
            assertEquals("下一阶段", document.querySelectorAll("span").get(0).getTextContent());
        } finally {
            Loading.toggle(false);
            document.remove();
        }
    }
}
