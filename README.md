CuteBedwars
===================
一个我的世界起床战争的插件  
目前仍在开发中  
开发版本为 1.12.2

使用了项目 [multi-module-mediator](https://github.com/gdrfgdrf/multi-module-mediator) 进行解耦  
编辑模块使用了 Git 的设计  
项目进行了高度的抽象化，不同模块间的通讯都是用中介进行，包括枚举  

数据
-----------------
区域  
支持模板系统，包含一个模板 id  
类似于 Docker 的设计，每个区域下会有多个游戏  
这些游戏相互独立，但同时又会使用区域内的一些公共属性  
比如同一个区域下的所有游戏都共用同一张地图

游戏  
每个游戏都会有其归属区域，游戏内包含队伍，物品生成器，等待房间等等信息

队伍  
每个队伍都会有其归属游戏，队伍内包含村民，队伍物品生成器等等信息

物品生成器  
不可独立存在，必须依附在物品生成器组下  
在指定的倒计时结束后会生成一个或多个物品，也可在指定的倒计时结束后自动升级  
通常升级后生成新物品的倒计时缩短

物品生成器组  
顾名思义，是由多个物品生成器组成一个组  
里面的所有物品生成器共用一些属性，比如说等级信息

等待房间  
必须依附在一个游戏下，游戏开始后等待房间将会被清空

模板
-----------------
模板系统将允许用户复制一个或多个数据  
  
比如说  
若某个区域的模板 id 为空，  
那么当这个区域下的某个游戏完成创建后将会把该游戏制作成为一个模板  
并将该模板的 id 赋值给区域的模板 id  
那么下次在该区域新建游戏时将会直接从该模板复制一个游戏出来，而不需要从头开始配置

允许从外界导入新的模板以更加灵活，  
比如说
网络上有人做好了一个游戏，而该游戏的地图和你的某个区域的地图正好相同  
那么你就可以向他索要模板文件（如果可以），并导入到你自己的服务器中  
这样你就可以创建一个和他一模一样的游戏

支持将游戏转为模板，或从模板转为游戏

数据库
-----------------
为了适配多个场景，该项目的数据库模块采用抽象设计  
支持使用自定义的数据库实现，
比如说你想要 MySql 的数据库实现，  
那么你就可以自己写一个 MySql 的数据库实现或到网上寻找受信任的提供者下载，  
然后在配置文件中应用

默认为 SQLite 数据库实现，默认的数据库仅能承受很小的并发量，  
若数据量大将会拖慢整个服务器

本地化
-----------------
语言模块不使用 key-value 的设计，而是直接通过引用的方式进行获取  
加载时自动将实际的字符串赋值到变量，然后其他类通过引用变量进行获取

命令
-----------------
项目的命令系统略显复杂，并不直接指明实际的命令，而是使用了节点到节点的设计  
使用了多参数方案，内部可以识别使用的是哪个参数方案  
调用命令时将会对所有参数进行校验，保证进入到实际的命令方法中的参数都是正确的

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

开发
-----------------
将本项目 clone 下来，然后运行 dependency 下的 install-to-maven.bat，  
或在 dependency 目录下手动运行以下内容
```text
mvn install:install-file -Dfile=multi-module-mediator-0.1.0.jar -DgroupId=io.github.gdrfgdrf.multimodulemediator -DartifactId=multi-module-mediator -Dversion=0.1.0 -Dpackaging=jar
```
这个脚本会把 dependency/multi-module-mediator-0.1.0.jar 安装到本地 maven 仓库  
安装之后项目将会 sync 成功  
所有依赖均已包括在编译成功后的 Jar 中，运行时无需添加依赖