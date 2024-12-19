# CuteBedwars
[English](https://github.com/gdrfgdrf/CuteBedwars/blob/dev/README.md) | **[简体中文](https://github.com/gdrfgdrf/CuteBedwars/blob/dev/README_chinese.md)**

一个为Minecraft设计的起床战争插件，基于 1.12.2 版本开发，并使用 [multi-module-mediator](https://github.com/gdrfgdrf/multi-module-mediator) 实现模块化。  
⚠ 目前仍在开发中，不能正常使用 ⚠

对于数学和几何模块的一些具体信息请前往 [Math README]("https://github.com/gdrfgdrf/CuteBedwars/blob/dev/math/math-readme.md")

## 特性

- **模块化设计**：解耦架构，易于维护。
- **区域系统**：支持在单个区域内进行多个游戏，共享公共属性。
- **队伍系统**：游戏可以包含多个队伍进行竞争。
- **物品生成器**：生成物品并自动升级。
- **模板系统**：允许复制游戏数据，便于快速设置和分享模板。
- **数据库抽象**：支持自定义数据库实现，默认提供 SQLite 和 MySQL。

## 数据信息

- **区域**：托管多个游戏，共享设置，如共同的游戏世界。
- **游戏**：包括队伍、物品生成器和等待房间。
- **队伍**：包括村民和队伍物品生成器。

## 开发

克隆项目并运行`dependency/install-to-maven.bat`或执行：
```shell
mvn install:install-file -Dfile=multi-module-mediator-0.1.0.jar -DgroupId=io.github.gdrfgdrf.multimodulemediator -DartifactId=multi-module-mediator -Dversion=0.1.0 -Dpackaging=jar
```
可能需要使用 Intellij IDEA 工具栏的 Build -> Rebuild Project 一次过后才能正常的使用 maven 进行打包

## 贡献

欢迎各种形式的贡献，包括代码、错误报告和文档改进。

模块列表
-----------------

| 模块名                                      | 作用                                                   |
|------------------------------------------|------------------------------------------------------|
| cute-bedwars-core                        | 插件入口，用于启动各种服务                                        |  
| cute-bedwars-beans                       | 所有数据类                                                |  
| cute-bedwars-database                    | 对数据库的抽象                                              |
| cute-bedwars-database-default-impl       | 默认 SQLite 数据库实现                                      |
| cute-bedwars-database-default-impl-beans | 默认 SQLite 数据库实现所有的数据类                                |
| cute-bedwars-commons                     | 公共模块                                                 |
| cute-bedwars-locale                      | 本地化，根据设置提供不同的语言信息                                    |
| cute-bedwars-languages                   | 所有语言字符串                                              |
| cute-bedwars-commands                    | 所有命令实现                                               |
| cute-bedwars-requests                    | 请求模块，在指定时间内进行操作，否则超时。例如某些危险操作需要二次确认，并且若干秒后超时，则需使用该模块 |
| cute-bedwars-game-management             | 对游戏的管理                                               |
| cute-bedwars-notification                | 玩家通知模块                                               |
| cute-bedwars-tasks                       | 任务模块，同步和异步任务                                         |
| cute-bedwars-chat-page                   | 聊天中的页面模块，可将多行文字进行分页显示，支持缓存                           |
| cute-bedwars-finder                      | 查询模块，用于查询各种数据，找不到或不满足条件时会输出提示信息                      |
| cute-bedwars-information                 | 对数据进行翻译，将其翻译为可以查看的版本                                 |
| cute-bedwars-editing                     | 编辑模块，对数据进行编辑，使用了 Git 的设计                             |
| cute-bedwars-storage                     | 存储模块，对数据进行实际的存储，该存储不会使用数据库，而是直接存到一个文件                |
| cute-bedwars-items                       | 物品模块，对游戏中的物品操作进行包装                                   |
| cute-bedwars-events                      | 事件模块，存储一些事件类和事件监听器                                   |
| cute-bedwars-protobuf                    | 所有 Protobuf 类                                        |
| cute-bedwars-selection                   | 选区模块，会通过粒子效果显示出来，并且会通过计算来添加辅助线，以便检查每个位置              |
| cute-bedwars-particles                   | 粒子效果模块，对粒子效果进行一定程度的抽象                                |
| cute-bedwars-frequency-tasks             | 频率任务，按照一定的频率执行某个任务                                   |
| cute-bedwars-math                        | 数学模块，进行各种数学计算                                        |
| cute-bedwars-geometry                    | 几何模块，位于数学模块下                                         |
| cute-bedwars-enums                       | 所有枚举类                                                |
| cute-bedwars-utils                       | 工具模块                                                 |
| cute-bedwars-abstracts                   | 解耦模块，对上面所有的模块进行解耦，包含大量的接口，并且使用中介进行通讯                 |
| cute-bedwars-packer                      | 打包模块，将上面所有的模块打包为一个 Jar 包                             |