package io.github.gdrfgdrf.cutebedwars.languages.language.chinese.simplified;

import io.github.gdrfgdrf.cuteframework.locale.LanguageString;
import io.github.gdrfgdrf.cuteframework.locale.base.LanguageBlock;

/**
 * @author gdrfgdrf
 */
class DescriptionLanguage implements LanguageBlock {
    public static final LanguageString DESCRIPTION = new LanguageString("描述");

    public static final LanguageString LANGUAGE = new LanguageString("语言设置，默认为 chinese_simplified，即简体中文");
    public static final LanguageString WORKER_ID = new LanguageString("机器 id，雪花算法生成 id 需要使用的参数，默认值为 0。若有条件可以生成一个自己的机器 id，为了生成出来的 id 不重复，该值必须唯一，并且始终唯一，若该值在中途发生变化则可能生成重复 id");
    public static final LanguageString DATABASE_IMPL = new LanguageString("数据库实现，插件默认使用 sqlite 作为数据库。当该值为 default-sqlite 时使用默认 sqlite 实现，若有其他数据库实现，需填写其 jar 包的绝对路径");
    public static final LanguageString ENABLE_DATABASE_LOGGING = new LanguageString("启用数据库的日志输出，输出日志内包含玩家数据等重要数据，默认为 false，即关闭日志输出。默认 sqlite 实现会遵守该值，其他数据库实现不一定");
    public static final LanguageString DATABASE_USERNAME = new LanguageString("访问数据库所需的用户名，默认值为空，若数据库没有用户名则无需填写");
    public static final LanguageString DATABASE_PASSWORD = new LanguageString("访问数据库所需的密码，默认值为空，若数据库没有密码则无需填写");
    public static final LanguageString REQUEST_TIMEOUT = new LanguageString("请求超时时间，有些时候执行某个操作需要二次确认，需要在请求超时时间内进行确认，否则请求失效。默认值为 10000，单位为毫秒");
//    public static final LanguageString AREA_AUTO_SAVE_DELAY = new LanguageString("每个区域自动保存的延迟，该值仅对正在编辑中的区域有效。默认值为 300000，即五分钟，单位为毫秒");

    public static final LanguageString AREA = new LanguageString("区域，一个区域下可以有多个游戏，所有游戏都使用同一个地图");
    public static final LanguageString AREA_ID = new LanguageString("区域 ID，不可重复，不可自定义，由程序进行生成，并作为唯一 ID 使用。该值对于玩家的作用和区域名称相同，但若有区域重名，则需使用该值进行索引");
    public static final LanguageString AREA_NAME = new LanguageString("区域名称，可以重复，但不建议重复，重复时则需使用区域 ID。管理员可使用该值索引到对应区域下的所有游戏，玩家可用该值索引到对应区域下的所有已启用的游戏。");
    public static final LanguageString AREA_DEFAULT_TEMPLATE_ID = new LanguageString("区域的默认模板，当区域完成时将自动创建一个模板并应用到该值，也可手动修改该值。模板被成功应用到该值后，以后在该区域创建的游戏都会是模板内的游戏的副本");
    public static final LanguageString AREA_STATUS = new LanguageString("区域的状态。" +
            "当值为 DISABLED (已禁用) 时，玩家不可进入该区域下的任何游戏，即使游戏已的状态为 ENABLED (已启用)，" +
//            "当值为 EDITING (编辑中) 时，表明该区域下有游戏正在被编辑，自动保存将会对该区域启用，玩家可以仅可以进入状态为 ENABLED (已启用) 的游戏，" +
            "当值为 EDITING (编辑中) 时，表明该区域下有游戏正在被编辑，玩家可以仅可以进入状态为 ENABLED (已启用) 的游戏，" +
            "当值为 ENABLED (已启用) 时，表明该区域下的游戏可以被进入，并且不可编辑。" +
            "当值不是以上三者时，将会被直接切换到 DISABLED (已禁用)");
    public static final LanguageString AREA_WORLD_NAME = new LanguageString("区域地图名，该区域下的所有游戏都将使用该地图");
    public static final LanguageString AREA_LOBBY_WORLD_NAME = new LanguageString("区域大厅地图名，游戏结束后若玩家无操作将会被传送到该地图");
    public static final LanguageString AREA_LOBBY_SPAWNPOINT_COORDINATE = new LanguageString("区域大厅重生点，游戏结束后若玩家无操作将会被传送到大厅的该坐标");
    public static final LanguageString AREA_GAMES = new LanguageString("区域下的游戏");
    public static final LanguageString AREA_PROPERTY = new LanguageString("区域属性");

    public static final LanguageString GAME = new LanguageString("游戏，不可独立存在，只能存在于区域下");
    public static final LanguageString GAME_ID = new LanguageString("游戏 id，不可重复，不可自定义，由程序进行生成，并作为唯一 ID 使用。若游戏名称与同区域下的其他游戏有重复，则可使用 区域 ID 或 区域名称 与该值进入游戏");
    public static final LanguageString GAME_AREA_ID = new LanguageString("游戏所对应的区域 ID，由程序进行定义。若该值所对应的那个区域不存在，游戏状态将转为 INDEPENDENT (独立的)，并且可以将该游戏添加到其他区域");
    public static final LanguageString GAME_NAME = new LanguageString("游戏名称，可重复，但不建议重复。玩家可使用 区域 ID 或 区域名称 与该值进入游戏，若该值与同区域下的其他游戏有重复，则需使用 区域 ID 或 区域名称 与游戏 ID 进入");
    public static final LanguageString GAME_MIN_PLAYER = new LanguageString("游戏开始所需的最少人数，必须满足以下条件: >= 0, >= 该游戏下所有队伍的最小人数的总和, <= 最大玩家数");
    public static final LanguageString GAME_MAX_PLAYER = new LanguageString("游戏所能容纳的最大人数，必须满足以下条件: >= 0, <= 该游戏下所有队伍的最大人数的总和, >= 最小玩家数");
    public static final LanguageString GAME_REGION = new LanguageString("游戏的游玩范围，玩家无法在该范围外进行任何操作");
    public static final LanguageString GAME_WAITING_ROOM = new LanguageString("游戏的等待房间，游戏开始时将会被清空");
    public static final LanguageString GAME_SPECTATOR_SPAWNPOINT_COORDINATE = new LanguageString("游戏内玩家死亡后重生为旁观者的重生点");
    public static final LanguageString GAME_SECONDARY_GENERATORS = new LanguageString("游戏的第二级别生成器，通常会自动升级");
    public static final LanguageString GAME_TERTIARY_GENERATORS = new LanguageString("游戏的第三级别生成器，也是最高一级的生成器，通常会自动升级");
    public static final LanguageString GAME_TEAMS = new LanguageString("游戏的队伍");

    public static final LanguageString TEAM = new LanguageString("队伍，不可独立存在，只能存在于游戏下");
    public static final LanguageString TEAM_ID = new LanguageString("队伍 ID，不可重复，不可自定义，由程序进行生成，并作为唯一 ID 使用");
    public static final LanguageString TEAM_NAME = new LanguageString("队伍名称，可重复，但不建议重复，不然玩家分不清");
    public static final LanguageString TEAM_COLOR = new LanguageString("队伍的颜色信息，与原版相同，以下为可用的值: &0BLACK (黑), &1DARK_BLUE (深蓝), &2DARK_GREEN (深绿), &3DARK_AQUA (深青), &4DARK_RED (深红), &5DARK_PURPLE (深紫), &6GOLD (金), &7GARY (灰), &8DARK_GRAY (深灰), &9BLUE (蓝), &aGREEN (绿), &bAQUA (青), &cRED (红), &dLIGHT_PURPLE (浅紫), &eYELLOW (黄), &fWHITE (白), &kMAGIC (随机字符), &lBOLD (粗体), &mSTRIKETHROUGH (删除线), &nUNDERLINE (下划线), &oITALIC (斜体), &rRESET (重置)");
    public static final LanguageString TEAM_MIN_PLAYER = new LanguageString("队伍的最小人数，需要满足以下条件: >= 0, <= 队伍的最大人数");
    public static final LanguageString TEAM_MAX_PLAYER = new LanguageString("队伍的最大人数，需要满足以下条件: >= 0, >= 队伍的最小人数");
    public static final LanguageString TEAM_REGION = new LanguageString("队伍所对应的岛的范围");
    public static final LanguageString TEAM_OPERABLE_COORDINATES = new LanguageString("队伍所对应的岛可操作的所有坐标");
    public static final LanguageString TEAM_SPAWNPOINT_COORDINATE = new LanguageString("队伍的重生点坐标");
    public static final LanguageString TEAM_BED_COORDINATE = new LanguageString("队伍的床的坐标");
    public static final LanguageString TEAM_VILLAGERS = new LanguageString("队伍的村民");
    public static final LanguageString TEAM_GENERATOR = new LanguageString("队伍的岛上自带的生成器，为第一级别生成器，只能由玩家手动进行升级");

    public static final LanguageString WAITING_ROOM = new LanguageString("等待房间，不可独立存在，只能存在于游戏下");
    public static final LanguageString WAITING_ROOM_GAME_ID = new LanguageString("等待房间所对应的游戏 ID");
    public static final LanguageString WAITING_ROOM_REGION = new LanguageString("等待房间的范围，游戏开始后，范围内的所有方块将被清空");
    public static final LanguageString WAITING_ROOM_SPAWNPOINT_COORDINATE = new LanguageString("玩家加入未开启的游戏时的出生点");

    public static final LanguageString GENERATOR = new LanguageString("物品生成器，这种类型的物品生成器无法自动升级");
    public static final LanguageString GENERATOR_REGION = new LanguageString("物品生成器的范围");
    public static final LanguageString GENERATOR_OPERABLE_COORDINATES = new LanguageString("物品生成器的范围内能够操作的坐标");
    public static final LanguageString GENERATOR_GENERATE_COORDINATE = new LanguageString("生成器的生成物坐标");
    public static final LanguageString GENERATOR_DISPLAY = new LanguageString("物品生成器显示的物品");
    public static final LanguageString GENERATOR_DISPLAY_NAME = new LanguageString("物品生成器的显示名");
    public static final LanguageString GENERATOR_PRODUCTS = new LanguageString("物品生成器能够生成的物品");
    public static final LanguageString GENERATOR_LEVELS = new LanguageString("物品生成器的等级");
    public static final LanguageString GENERATOR_PROPERTY = new LanguageString("物品生成器属性");

    public static final LanguageString AUTOMATIC_GENERATOR = new LanguageString("自动升级的物品生成器，除等级外的所有属性均和 普通物品生成器 相同");
    public static final LanguageString AUTOMATIC_GENERATOR_PROPERTY = new LanguageString("自动升级的物品生成器属性");

    public static final LanguageString GENERATOR_LEVEL = new LanguageString("物品生成器的等级，不会自动升级");
    public static final LanguageString GENERATOR_LEVEL_ORDER = new LanguageString("物品生成器的等级顺序，0 为第一级，1 为第二级，3为第三级，以此类推，游戏开始时会以 0 作为起始点，通常该值越高的生成器生成新物品所需的时间越少");
    public static final LanguageString GENERATOR_LEVEL_GENERATE_COST = new LanguageString("物品生成器的当前等级，生成新物品所需的时间");
    public static final LanguageString GENERATOR_LEVEL_DISPLAY_NAME = new LanguageString("物品生成器的等级的显示名，通常为罗马数字，第一级为 I，第二级为 II，第三级为 III，第四级为 IV，第五级为 V 等等");
    public static final LanguageString GENERATOR_LEVEL_PROPERTY = new LanguageString("物品生成器等级属性");

    public static final LanguageString AUTOMATIC_GENERATOR_LEVEL = new LanguageString("物品生成器的自动等级，会自动升级，添加一个新属性，其他所有属性均和 普通物品生成器的等级 相同");
    public static final LanguageString AUTOMATIC_GENERATOR_LEVEL_NEXT_LEVEL_COST = new LanguageString("物品生成器的自动等级，到下一个等级所需时间，单位为毫秒，到达最高级应为 -1");
    public static final LanguageString AUTOMATIC_GENERATOR_LEVEL_PROPERTY = new LanguageString("物品生成器的自动等级属性");

    public static final LanguageString VILLAGER = new LanguageString("村民，不可独立存在，必须存在于队伍下");
    public static final LanguageString VILLAGER_TYPE = new LanguageString("村民的类型，仅允许 STORE (商店), UPGRADE (升级)");

    public static final LanguageString STATUS = new LanguageString("状态，有以下值 DISABLED (已禁用), EDITING (编辑中), ENABLED (已启用), INDEPENDENT (独立的)");

    public static final LanguageString COORDINATE = new LanguageString("坐标");
    public static final LanguageString COORDINATE_X = new LanguageString("坐标 X 轴");
    public static final LanguageString COORDINATE_Y = new LanguageString("坐标 Y 轴");
    public static final LanguageString COORDINATE_Z = new LanguageString("坐标 Z 轴");

    public static final LanguageString ITEM = new LanguageString("物品");
    public static final LanguageString ITEM_NBT = new LanguageString("物品的 nbt");

    public static final LanguageString REGION = new LanguageString("范围");
    public static final LanguageString REGION_FIRST_COORDINATE = new LanguageString("范围的第一个点的坐标");
    public static final LanguageString REGION_SECOND_COORDINATE = new LanguageString("范围的第二个点的坐标");

    public static final LanguageString TEMPLATE = new LanguageString("模板，模板内会存储一个游戏。若某个区域应用该模板，那么之后该区域创建的游戏将会是模板内的游戏的副本。模板将在区域完成后自动创建并自动应用到区域，也可手动创建模板并应用到某个区域");
    public static final LanguageString TEMPLATE_ID = new LanguageString("模板 ID，不可重复，不可自定义，由程序进行生成，并作为唯一 ID 使用。若区域要应用模板，但目标模板的名称有重复，则需要使用该值进行应用");
    public static final LanguageString TEMPLATE_NAME = new LanguageString("模板名称，可重复，但不建议重复。若区域要应用模板，可使用该值进行应用，但若该值于其他模板有重复，则需要使用模板 ID 进行应用");
    public static final LanguageString TEMPLATE_PROPERTY = new LanguageString("模板属性");

    public static final LanguageString APPLY_CHANGES = new LanguageString("应用修改，true 则应用，false 则不应用，其他值无效");

    public static final LanguageString FIND_BY_ID_OR_NAME = new LanguageString("通过 id 或名称来查找，值为 by-id 或 by-name");
    public static final LanguageString BY_ID = new LanguageString("通过 id 进行查找，值为 by-id");
    public static final LanguageString BY_NAME = new LanguageString("通过名称来查找，值为 by-name");

    public static final LanguageString VALUE = new LanguageString("值，类型视情况而定");
    public static final LanguageString PAGE_INDEX = new LanguageString("页码");

}
