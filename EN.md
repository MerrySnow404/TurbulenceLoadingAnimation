## Turbulence Loading Animation

A sleek, dynamic loading animation that displays random words from a custom library.

However, I think it's perfect for a modpack that really needs a certain atmosphere.

**Demo Video:** [Youtube](https://youtu.be/OBmEY8V8K04)

### ### Animation Features

The core of this mod is the word library. Every **two seconds**, ten words are displayed using a smooth fade-in/out animation (check the video for the full effect).

* **Dynamic Info:** Two of these words are reserved for real-time loading information, such as "Loading Terrain," "Saving World," or world progress percentages.
* **Custom Backgrounds:** You can specify a custom background image at `apricity/tla_ms/bg.png`. If left blank, the mod defaults to the classic Minecraft dirt background.
* **Fully Configurable:** Easily adjust the **font size** and **animation area** within the configuration file.

### ### Word Libraries (Thesaurus)

The mod comes pre-packaged with two built-in libraries that automatically switch based on your language settings:

1. **The Eight Great Traditions of Chinese Cuisine** (798 entries)
2. **Common Firearms** (823 entries)

#### **How to Customize**

You can create your own word libraries in the config file using a **JSON array** format. While intended for modpack authors to add pack-related lore or tips, you can use any external data.

**Resources for finding word lists:**

* **English:** [UsingEnglish](https://www.usingenglish.com/) is a great starting point.
* **Chinese:** [Sogou Pinyin Cell Dict](https://pinyin.sogou.com/dict/) offers a massive variety of specialized vocabularies.

**Useful Tools for Conversion:**

* **Extract .scel to text:** [Toolzt - SCEL Converter](https://www.toolzt.com/dev/scelToText.html)
* **Convert text to JSON array:** [Bugscaner String2Array](http://tools.bugscaner.com/text/string2array.html) (Use `\n` as the separator for line breaks).

### ### Future Roadmap

* Support for **GIFs** and **Custom Fonts** (Internal support exists; config options coming soon).
* Expanded animation customization.
* Support for multiple randomized word libraries.
* Animation support for the initial game splash screen.