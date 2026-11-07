## 乱流加载动画

随机展示词库的酷炫加载动画，非常适合追求氛围感的整合包。

演示视频：https://www.bilibili.com/video/BV1zWfwBDEVj/

### 动画

以词库为核心，每两秒以渐入渐出动画展示十个词，推荐直接观看视频感受一下。

其中两个词为当前加载信息，如世界加载进度、“加载地形中”、“保存世界中”。

apricity/tla_ms/bg.png可以指定加载时的背景图片，不指定就是原汁原味的泥土背景。

配置文件中可以调整字体大小和动画区域大小。

### 词库
本模组默认自带中国八大菜系词库(798个)和常见枪械名称词库(823个)，会根据语言自动切换。

可以在配置文件中自定义词库，格式为JSON数组。

本意是整合包作者自己编写整合包相关的词库，不过也有其它已有的词库可用：

英文词库似乎不太好找，我只找到一个UsingEnglish：  
https://www.usingenglish.com/

中文词库可以去搜狗输入法的细胞词库里找，非常齐全：  
https://pinyin.sogou.com/dict/

然后再用下面的小工具提取成列表：  
https://www.toolzt.com/dev/scelToText.html

最后用下面的小工具把多行字符串转成JSON数组，分隔字符串输入"\n"也就是换行符：  
http://tools.bugscaner.com/text/string2array.html

### 未来计划
- 支持gif、自定义字体(其实已支持但还没做配置)
- 更多动画配置
- 支持多词库随机
- 支持游戏启动界面动画

### 构建与支持版本

需要 ApricityUI 1.3.1。支持以下目标：Forge 1.16.5、1.18.2、1.19.2、1.20.1；Fabric 1.20.1、1.21.1、26.1；NeoForge 1.21.1、26.1、26.3。

Forge 1.16.5 需要 Java 17（与 AUI 1.3.1 一致）；NeoForge 26.3 需要 Java 25。

从项目根目录构建单个目标或所有可用目标：

```powershell
.\gradlew.bat -Ptarget=fabric-1.20.1 build
.\gradlew.bat -PallTargets=true build
```

每个目标使用自己的 Gradle wrapper 和 `ci.properties` 中声明的 Java 版本。

`common` 只提供共享源码、资源和测试；Minecraft、加载器与 AUI 依赖均由各 target 声明。根目录直接执行 `build` 时，会构建当前 JDK 支持的所有目标。
