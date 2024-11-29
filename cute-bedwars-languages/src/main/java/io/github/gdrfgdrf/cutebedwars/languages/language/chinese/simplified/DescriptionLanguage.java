package io.github.gdrfgdrf.cutebedwars.languages.language.chinese.simplified;

import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ILanguageString;
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.IILanguageBlock;

/**
 * @author gdrfgdrf
 */
class DescriptionLanguage implements IILanguageBlock {
    public static final ILanguageString DESCRIPTION = ILanguageString.Companion.create("描述");

    public static final ILanguageString LANGUAGE = ILanguageString.Companion.create("语言设置，默认为 chinese_simplified，即简体中文");
    public static final ILanguageString WORKER_ID = ILanguageString.Companion.create("机器 id，雪花算法生成 id 需要使用的参数，默认值为 0。若有条件可以生成一个自己的机器 id，为了生成出来的 id 不重复，该值必须唯一，并且始终唯一，若该值在中途发生变化则可能生成重复 id");
    public static final ILanguageString DATABASE_IMPL = ILanguageString.Companion.create("数据库实现，插件默认使用 sqlite 作为数据库。当该值为 default-sqlite 时使用默认 sqlite 实现，若有其他数据库实现，需填写其 jar 包的绝对路径");
    public static final ILanguageString ENABLE_DATABASE_LOGGING = ILanguageString.Companion.create("启用数据库的日志输出，输出日志内包含玩家数据等重要数据，默认为 false，即关闭日志输出。默认 sqlite 实现会遵守该值，其他数据库实现不一定");
    public static final ILanguageString DATABASE_USERNAME = ILanguageString.Companion.create("访问数据库所需的用户名，默认值为空，若数据库没有用户名则无需填写");
    public static final ILanguageString DATABASE_PASSWORD = ILanguageString.Companion.create("访问数据库所需的密码，默认值为空，若数据库没有密码则无需填写");
    public static final ILanguageString REQUEST_TIMEOUT = ILanguageString.Companion.create("请求超时时间，有些时候执行某个操作需要二次确认，需要在请求超时时间内进行确认，否则请求失效。默认值为 10000，单位为毫秒");
    public static final ILanguageString THREAD_POOL_SERVICE_IMPL = ILanguageString.Companion.create("线程池的实现，默认值为 KOTLIN_COROUTINE，即 Kotlin 协程，可以修改为 JAVA_THREAD 以使用 Java 原版的线程");

    public static final ILanguageString AREA = ILanguageString.Companion.create("区域，一个区域下可以有多个游戏，所有游戏都使用同一个地图");
    public static final ILanguageString AREA_ID = ILanguageString.Companion.create("区域 ID，不可重复，不可自定义，由程序进行生成，并作为唯一 ID 使用。该值对于玩家的作用和区域名称相同，但若有区域重名，则需使用该值进行索引");
    public static final ILanguageString AREA_NAME = ILanguageString.Companion.create("区域名称，可以重复，但不建议重复，重复时则需使用区域 ID。管理员可使用该值索引到对应区域下的所有游戏，玩家可用该值索引到对应区域下的所有已启用的游戏。");
    public static final ILanguageString AREA_DEFAULT_TEMPLATE_ID = ILanguageString.Companion.create("区域的默认模板，当区域完成时将自动创建一个模板并应用到该值，也可手动修改该值。模板被成功应用到该值后，以后在该区域创建的游戏都会是模板内的游戏的副本");
    public static final ILanguageString AREA_STATUS = ILanguageString.Companion.create("区域的状态。" +
            "当值为 DISABLED (已禁用) 时，玩家不可进入该区域下的任何游戏，即使游戏已的状态为 ENABLED (已启用)，" +
//            "当值为 EDITING (编辑中) 时，表明该区域下有游戏正在被编辑，自动保存将会对该区域启用，玩家可以仅可以进入状态为 ENABLED (已启用) 的游戏，" +
            "当值为 EDITING (编辑中) 时，表明该区域下有游戏正在被编辑，玩家可以仅可以进入状态为 ENABLED (已启用) 的游戏，" +
            "当值为 ENABLED (已启用) 时，表明该区域下的游戏可以被进入，并且不可编辑。" +
            "当值不是以上三者时，将会被直接切换到 DISABLED (已禁用)");
    public static final ILanguageString AREA_WORLD_NAME = ILanguageString.Companion.create("区域地图名，该区域下的所有游戏都将使用该地图");
    public static final ILanguageString AREA_LOBBY_WORLD_NAME = ILanguageString.Companion.create("区域大厅地图名，游戏结束后若玩家无操作将会被传送到该地图");
    public static final ILanguageString AREA_LOBBY_SPAWNPOINT_COORDINATE = ILanguageString.Companion.create("区域大厅重生点，游戏结束后若玩家无操作将会被传送到大厅的该坐标");
    public static final ILanguageString AREA_GAMES = ILanguageString.Companion.create("区域下的游戏");
    public static final ILanguageString AREA_PROPERTY = ILanguageString.Companion.create("区域属性");

    public static final ILanguageString GAME = ILanguageString.Companion.create("游戏，不可独立存在，只能存在于区域下");
    public static final ILanguageString GAME_ID = ILanguageString.Companion.create("游戏 id，不可重复，不可自定义，由程序进行生成，并作为唯一 ID 使用。若游戏名称与同区域下的其他游戏有重复，则可使用 区域 ID 或 区域名称 与该值进入游戏");
    public static final ILanguageString GAME_AREA_ID = ILanguageString.Companion.create("游戏所对应的区域 ID，由程序进行定义。若该值所对应的那个区域不存在，游戏状态将转为 INDEPENDENT (独立的)，并且可以将该游戏添加到其他区域");
    public static final ILanguageString GAME_NAME = ILanguageString.Companion.create("游戏名称，可重复，但不建议重复。玩家可使用 区域 ID 或 区域名称 与该值进入游戏，若该值与同区域下的其他游戏有重复，则需使用 区域 ID 或 区域名称 与游戏 ID 进入");
    public static final ILanguageString GAME_MIN_PLAYER = ILanguageString.Companion.create("游戏开始所需的最少人数，必须满足以下条件: >= 0, >= 该游戏下所有队伍的最小人数的总和, <= 最大玩家数");
    public static final ILanguageString GAME_MAX_PLAYER = ILanguageString.Companion.create("游戏所能容纳的最大人数，必须满足以下条件: >= 0, <= 该游戏下所有队伍的最大人数的总和, >= 最小玩家数");
    public static final ILanguageString GAME_REGION = ILanguageString.Companion.create("游戏的游玩范围，玩家无法在该范围外进行任何操作");
    public static final ILanguageString GAME_WAITING_ROOM = ILanguageString.Companion.create("游戏的等待房间，游戏开始时将会被清空");
    public static final ILanguageString GAME_SPECTATOR_SPAWNPOINT_COORDINATE = ILanguageString.Companion.create("游戏内玩家死亡后重生为旁观者的重生点");
    public static final ILanguageString GAME_GENERATOR_GROUPS = ILanguageString.Companion.create("游戏的物品生成器组，通常把所有生成同一类型物品的生成器看作为一个生成器组");
    public static final ILanguageString GAME_TEAMS = ILanguageString.Companion.create("游戏的队伍");

    public static final ILanguageString TEAM = ILanguageString.Companion.create("队伍，不可独立存在，只能存在于游戏下");
    public static final ILanguageString TEAM_ID = ILanguageString.Companion.create("队伍 ID，不可重复，不可自定义，由程序进行生成，并作为唯一 ID 使用");
    public static final ILanguageString TEAM_NAME = ILanguageString.Companion.create("队伍名称，可重复，但不建议重复，不然玩家分不清");
    public static final ILanguageString TEAM_COLOR = ILanguageString.Companion.create("队伍的颜色信息，与原版相同，以下为可用的值: &0BLACK (黑), &1DARK_BLUE (深蓝), &2DARK_GREEN (深绿), &3DARK_AQUA (深青), &4DARK_RED (深红), &5DARK_PURPLE (深紫), &6GOLD (金), &7GARY (灰), &8DARK_GRAY (深灰), &9BLUE (蓝), &aGREEN (绿), &bAQUA (青), &cRED (红), &dLIGHT_PURPLE (浅紫), &eYELLOW (黄), &fWHITE (白), &kMAGIC (随机字符), &lBOLD (粗体), &mSTRIKETHROUGH (删除线), &nUNDERLINE (下划线), &oITALIC (斜体), &rRESET (重置)");
    public static final ILanguageString TEAM_MIN_PLAYER = ILanguageString.Companion.create("队伍的最小人数，需要满足以下条件: >= 0, <= 队伍的最大人数");
    public static final ILanguageString TEAM_MAX_PLAYER = ILanguageString.Companion.create("队伍的最大人数，需要满足以下条件: >= 0, >= 队伍的最小人数");
    public static final ILanguageString TEAM_REGION = ILanguageString.Companion.create("队伍所对应的岛的范围");
    public static final ILanguageString TEAM_OPERABLE_COORDINATES = ILanguageString.Companion.create("队伍所对应的岛可操作的所有坐标");
    public static final ILanguageString TEAM_SPAWNPOINT_COORDINATE = ILanguageString.Companion.create("队伍的重生点坐标");
    public static final ILanguageString TEAM_BED_COORDINATE = ILanguageString.Companion.create("队伍的床的坐标");
    public static final ILanguageString TEAM_VILLAGERS = ILanguageString.Companion.create("队伍的村民");
    public static final ILanguageString TEAM_GENERATOR = ILanguageString.Companion.create("队伍的岛上自带的生成器，为第一级别生成器，只能由玩家手动进行升级");

    public static final ILanguageString WAITING_ROOM = ILanguageString.Companion.create("等待房间，不可独立存在，只能存在于游戏下");
    public static final ILanguageString WAITING_ROOM_GAME_ID = ILanguageString.Companion.create("等待房间所对应的游戏 ID");
    public static final ILanguageString WAITING_ROOM_REGION = ILanguageString.Companion.create("等待房间的范围，游戏开始后，范围内的所有方块将被清空");
    public static final ILanguageString WAITING_ROOM_SPAWNPOINT_COORDINATE = ILanguageString.Companion.create("玩家加入未开启的游戏时的出生点");

    public static final ILanguageString GENERATOR_GROUP = ILanguageString.Companion.create("物品生成器组，通常把所有生成同一类型物品的生成器看作为一个生成器组，并且同一个组的所有物品生成器共用一个等级排序");
    public static final ILanguageString GENERATOR_GROUP_DISPLAY_ORDER = ILanguageString.Companion.create("物品生成器组在计分板中的显示顺序，计分板通常会显示该物品生成器组的等级信息");
    public static final ILanguageString GENERATOR_GROUP_DISPLAY_NAME = ILanguageString.Companion.create("物品生成器组的名字");
    public static final ILanguageString GENERATOR_GROUP_GENERATORS = ILanguageString.Companion.create("物品生成器组下所有的物品生成器，这些生成器将共用一个等级排序");
    public static final ILanguageString GENERATOR_GROUP_LEVELS = ILanguageString.Companion.create("物品生成器组的等级排序，该组下所有物品生成器将共用此等级排序，也就是说当触发升级时，该组下所有的物品生成器都将在同时升级");

    public static final ILanguageString GENERATOR = ILanguageString.Companion.create("物品生成器，这种类型的物品生成器无法自动升级");
    public static final ILanguageString GENERATOR_REGION = ILanguageString.Companion.create("物品生成器的范围");
    public static final ILanguageString GENERATOR_OPERABLE_COORDINATES = ILanguageString.Companion.create("物品生成器的范围内能够操作的坐标");
    public static final ILanguageString GENERATOR_GENERATE_COORDINATE = ILanguageString.Companion.create("生成器的生成物坐标");
    public static final ILanguageString GENERATOR_DISPLAY = ILanguageString.Companion.create("物品生成器显示的物品");
    public static final ILanguageString GENERATOR_DISPLAY_NAME = ILanguageString.Companion.create("物品生成器的显示名");
    public static final ILanguageString GENERATOR_PRODUCTS = ILanguageString.Companion.create("物品生成器能够生成的物品");
    public static final ILanguageString GENERATOR_PROPERTY = ILanguageString.Companion.create("物品生成器属性");

    public static final ILanguageString AUTOMATIC_GENERATOR = ILanguageString.Companion.create("自动升级的物品生成器，除等级外的所有属性均和 普通物品生成器 相同");
    public static final ILanguageString AUTOMATIC_GENERATOR_PROPERTY = ILanguageString.Companion.create("自动升级的物品生成器属性");

    public static final ILanguageString GENERATOR_LEVEL = ILanguageString.Companion.create("物品生成器的等级，不会自动升级");
    public static final ILanguageString GENERATOR_LEVEL_ORDER = ILanguageString.Companion.create("物品生成器的等级顺序，0 为第一级，1 为第二级，3为第三级，以此类推，游戏开始时会以 0 作为起始点，通常该值越高的生成器生成新物品所需的时间越少");
    public static final ILanguageString GENERATOR_LEVEL_GENERATE_COST = ILanguageString.Companion.create("物品生成器的当前等级，生成新物品所需的时间");
    public static final ILanguageString GENERATOR_LEVEL_DISPLAY_NAME = ILanguageString.Companion.create("物品生成器的等级的显示名，通常为罗马数字，第一级为 I，第二级为 II，第三级为 III，第四级为 IV，第五级为 V 等等");
    public static final ILanguageString GENERATOR_LEVEL_PROPERTY = ILanguageString.Companion.create("物品生成器等级属性");

    public static final ILanguageString AUTOMATIC_GENERATOR_LEVEL = ILanguageString.Companion.create("物品生成器的自动等级，会自动升级，添加一个新属性，其他所有属性均和 普通物品生成器的等级 相同");
    public static final ILanguageString AUTOMATIC_GENERATOR_LEVEL_NEXT_LEVEL_COST = ILanguageString.Companion.create("物品生成器的自动等级，到下一个等级所需时间，单位为毫秒，到达最高级应为 -1");
    public static final ILanguageString AUTOMATIC_GENERATOR_LEVEL_PROPERTY = ILanguageString.Companion.create("物品生成器的自动等级属性");

    public static final ILanguageString VILLAGER = ILanguageString.Companion.create("村民，不可独立存在，必须存在于队伍下");
    public static final ILanguageString VILLAGER_TYPE = ILanguageString.Companion.create("村民的类型，仅允许 STORE (商店), UPGRADE (升级)");

    public static final ILanguageString STATUS = ILanguageString.Companion.create("状态，有以下值 DISABLED (已禁用), EDITING (编辑中), ENABLED (已启用), INDEPENDENT (独立的)");

    public static final ILanguageString COORDINATE = ILanguageString.Companion.create("坐标");
    public static final ILanguageString COORDINATE_X = ILanguageString.Companion.create("坐标 X 轴");
    public static final ILanguageString COORDINATE_Y = ILanguageString.Companion.create("坐标 Y 轴");
    public static final ILanguageString COORDINATE_Z = ILanguageString.Companion.create("坐标 Z 轴");

    public static final ILanguageString ITEM = ILanguageString.Companion.create("物品");
    public static final ILanguageString ITEM_NBT = ILanguageString.Companion.create("物品的 nbt");

    public static final ILanguageString REGION = ILanguageString.Companion.create("范围");
    public static final ILanguageString REGION_FIRST_COORDINATE = ILanguageString.Companion.create("范围的第一个点的坐标");
    public static final ILanguageString REGION_SECOND_COORDINATE = ILanguageString.Companion.create("范围的第二个点的坐标");

    public static final ILanguageString TEMPLATE = ILanguageString.Companion.create("模板，模板内会存储一个游戏。若某个区域应用该模板，那么之后该区域创建的游戏将会是模板内的游戏的副本。模板将在区域完成后自动创建并自动应用到区域，也可手动创建模板并应用到某个区域");
    public static final ILanguageString TEMPLATE_ID = ILanguageString.Companion.create("模板 ID，不可重复，不可自定义，由程序进行生成，并作为唯一 ID 使用。若区域要应用模板，但目标模板的名称有重复，则需要使用该值进行应用");
    public static final ILanguageString TEMPLATE_NAME = ILanguageString.Companion.create("模板名称，可重复，但不建议重复。若区域要应用模板，可使用该值进行应用，但若该值于其他模板有重复，则需要使用模板 ID 进行应用");
    public static final ILanguageString TEMPLATE_PROPERTY = ILanguageString.Companion.create("模板属性");

    public static final ILanguageString COMMIT_ID = ILanguageString.Companion.create("提交 id");

    public static final ILanguageString COMMIT_MESSAGE = ILanguageString.Companion.create("提交描述信息");
    public static final ILanguageString CHANGE_TYPE = ILanguageString.Companion.create("修改类型");
    public static final ILanguageString AREA_PROPERTY_CHANGE = ILanguageString.Companion.create("区域属性修改，可接受两个参数，参数一为需要修改的属性，参数二为目标值。可修改的属性为 default-template-id，status，world-name，lobby-world-name，lobby-spawnpoint-coordinate");
    public static final ILanguageString GAME_PROPERTY_CHANGE = ILanguageString.Companion.create("游戏属性修改，可接受两个二参数，参数一为需要修改的属性，参数二为目标值。可修改的属性为 status、min-player、max-player、spectator-spawnpoint-coordinate");

    public static final ILanguageString FIND_BY_ID_OR_NAME = ILanguageString.Companion.create("通过 id 或名称来查找，值为 by-id 或 by-name");
    public static final ILanguageString BY_ID = ILanguageString.Companion.create("通过 id 进行查找，值为 by-id");
    public static final ILanguageString BY_NAME = ILanguageString.Companion.create("通过名称来查找，值为 by-name");

    public static final ILanguageString CHANGE_ID = ILanguageString.Companion.create("修改的 id，玩家将鼠标放在上方即可查看，控制台会直接显示");
    public static final ILanguageString VALUE = ILanguageString.Companion.create("值，类型视情况而定");
    public static final ILanguageString PAGE_INDEX = ILanguageString.Companion.create("页码");

}
