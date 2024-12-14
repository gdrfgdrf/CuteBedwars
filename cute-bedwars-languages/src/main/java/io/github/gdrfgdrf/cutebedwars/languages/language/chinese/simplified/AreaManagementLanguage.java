package io.github.gdrfgdrf.cutebedwars.languages.language.chinese.simplified;

import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ILanguageString;
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.IILanguageBlock;

/**
 * @author gdrfgdrf
 */
class AreaManagementLanguage implements IILanguageBlock {
    public static final ILanguageString AREA_VALIDATE_FAILED = ILanguageString.Companion.create("&c区域 &e&l%s 校验失败");
    public static final ILanguageString GAME_VALIDATE_FAILED = ILanguageString.Companion.create("&c区域 &e&l%s 的游戏 &e&l%s 校验失败，已禁用该游戏: ");

    public static final ILanguageString GAME_MIN_PLAYER_ERROR = ILanguageString.Companion.create("&c最小玩家数配置错误，必须 &e>= &a所有队伍的最小玩家数的总和，&c且必须 &e<= &a最大玩家数");
    public static final ILanguageString GAME_MAX_PLAYER_ERROR = ILanguageString.Companion.create("&c最大玩家数配置错误，必须 &e<= &a所有队伍的最大玩家数的总和，&c且必须 &e>= &a最小玩家数");

    public static final ILanguageString GAME_TEAM_NEED_POSITIVE_ERROR = ILanguageString.Companion.create("&c队伍 &e&l%s &c的参数 &e&l%s &c配置错误，必须为正数");
    public static final ILanguageString GAME_TEAM_COUNT_ERROR = ILanguageString.Companion.create("&c必须拥有 > 1 个队伍");
    public static final ILanguageString GAME_TEAM_MIN_PLAYER_BIGGER_THAN_MAX_PLAYER = ILanguageString.Companion.create("&c队伍 &e&l%s 的最小人数 > 最大人数");

    public static final ILanguageString DUPLICATE_AREA_NAME_WARNING = ILanguageString.Companion.create("&c名为 &e&l%s &r&c的区域已经存在，若继续创建，对其进行操作则需要使用 &earea_id &c加入。请在 &e&l%s &r&c秒内再次输入以确定");
    public static final ILanguageString DUPLICATE_AREA_ID_ERROR = ILanguageString.Companion.create("&c无法创建区域，因为程序生成的 &earea_id %s &c和其他区域重复，出现该错误一般为服务器的系统时间被回拨，请检查服务器的系统时间是否为最新");
    public static final ILanguageString CREATING_AREA = ILanguageString.Companion.create("&e正在创建区域 %s");
    public static final ILanguageString CREATE_AREA_FINISHED = ILanguageString.Companion.create("&a区域 &e&l%s &r&a已创建，&earea_id &a为 &e&l%s");

    public static final ILanguageString NOT_FOUND_AREA_BY_ID = ILanguageString.Companion.create("&c无法找到 area_id 为 &e&l%s &r&c的区域");
    public static final ILanguageString NOT_FOUND_AREA_BY_NAME = ILanguageString.Companion.create("&c无法找到名称为 &e&l%s &r&c的区域");
    public static final ILanguageString AREA_ID_IS = ILanguageString.Companion.create("&a区域 id&8: &e&l%s");
    public static final ILanguageString AREA_NAME_IS = ILanguageString.Companion.create("&a区域名&8: &e&l%s");
    public static final ILanguageString AREA_PROPERTY_IS = ILanguageString.Companion.create("&a以下为该区域的属性&8: ");
    public static final ILanguageString AREA_PROPERTY_FORMAT = ILanguageString.Companion.create("&a&l%s&8&l: &r&e%s");
    public static final ILanguageString AREA_GAMES_IS = ILanguageString.Companion.create("&a以下为该区域的游戏&8:");
    public static final ILanguageString AREA_GAMES_FORMAT = ILanguageString.Companion.create(" &8- &l&e%s &8(%s&8)");
    public static final ILanguageString AREA_GAMES_EMPTY = ILanguageString.Companion.create(" &8- &l&c无");

    public static final ILanguageString DUPLICATE_GAME_NAME_WARNING = ILanguageString.Companion.create("&c在区域 &e&l%s &r&c下与 &e&l%s &r&c同名的游戏已经存在，若继续创建，对其进行操作时则需要使用 &egame_id &c加入。请在 &e&l%s &r&c内再次输入以确定");
    public static final ILanguageString DUPLICATE_GAME_ID_ERROR = ILanguageString.Companion.create("&c无法创建游戏，因为程序生成的 &egame_id %s &c和该区域下其他游戏重复，出现该错误一般为服务器的系统时间被回拨，请检查服务器的系统时间是否为最新");
    public static final ILanguageString CREATING_GAME = ILanguageString.Companion.create("&e正在区域 &l%s &r&e下创建游戏 &l%s");
    public static final ILanguageString CREATE_GAME_FINISHED = ILanguageString.Companion.create("&a成功在区域 &e&l%s &r&a下创建游戏 &e&l%s");

    public static final ILanguageString NOT_FOUND_GAME_BY_ID = ILanguageString.Companion.create("&c无法在区域 &e&l%s &r&c下找到 area_id 为 &e&l%s &r&c的游戏");
    public static final ILanguageString NOT_FOUND_GAME_BY_NAME = ILanguageString.Companion.create("&c无法在区域 &e&l%s &r&c下找到名称为 &e&l%s &r&c的游戏");
    public static final ILanguageString GAME_ID_IS = ILanguageString.Companion.create("&a游戏 id&8: &e&l%s");
    public static final ILanguageString GAME_AREA_ID_IS = ILanguageString.Companion.create("&a对应的区域 id&8: &e&l%s");
    public static final ILanguageString GAME_NAME_IS = ILanguageString.Companion.create("&a游戏名&8: &e&l%s");
    public static final ILanguageString GAME_PROPERTY_IS = ILanguageString.Companion.create("&a以下为该游戏的属性");
    public static final ILanguageString GAME_PROPERTY_FORMAT = ILanguageString.Companion.create("&a&l%s&8&l: &r&e%s");
    public static final ILanguageString GAME_REGION_IS = ILanguageString.Companion.create("&a游戏范围&8: ");
    public static final ILanguageString GAME_WAITING_ROOM_IS = ILanguageString.Companion.create("&a等待室&8: ");
    public static final ILanguageString GAME_GENERATOR_GROUPS_IS = ILanguageString.Companion.create("&a生成器组&8: ");
    public static final ILanguageString GAME_TEAMS_IS = ILanguageString.Companion.create("&a队伍&8: ");

    public static final ILanguageString GAME_GENERATOR_GROUP_FORMAT = ILanguageString.Companion.create(" &8- &e&l%s");

    public static final ILanguageString TEAM_FORMAT = ILanguageString.Companion.create(" &8- &l%s");

    public static final ILanguageString WAITING_ROOM_REGION_IS = ILanguageString.Companion.create(" &a范围&8: ");
    public static final ILanguageString WAITING_ROOM_SPAWNPOINT_COORDINATE = ILanguageString.Companion.create(" &a重生点&8: %s");

    public static final ILanguageString AREA_IS_EMPTY = ILanguageString.Companion.create("&c当前没有任何一个区域");
    public static final ILanguageString DUPLICATE_AREA_NAME_ERROR_FIND_BY_ID = ILanguageString.Companion.create("&c存在多个 area_id 包括 &e%s &c的区域，请使用完整的 &earea_id &c进行索引");
    public static final ILanguageString DUPLICATE_AREA_NAME_ERROR_FIND_BY_NAME = ILanguageString.Companion.create("&c存在多个名为 &e%s &c的区域，请使用 &earea_id &c进行索引");
    public static final ILanguageString DUPLICATE_GAME_NAME_ERROR_FIND_BY_ID = ILanguageString.Companion.create("&c在 &e&l%s &r&c区域下存在多个 game_id 包括 &e%s &c的游戏，请使用完整的 &egame_id &c进行索引");
    public static final ILanguageString DUPLICATE_GAME_NAME_ERROR_FIND_BY_NAME = ILanguageString.Companion.create("&c在 &e&l%s &r&c区域下存在多个与 &e&l%s &r&c同名的游戏，请使用 &egame_id &r&c进行索引");

    public static final ILanguageString AREA_COMMITS_IS_NULL = ILanguageString.Companion.create("&c无法加载该区域的提交记录");
    public static final ILanguageString AREA_COMMITS_IS_EMPTY = ILanguageString.Companion.create("&c该区域没有提交记录");
    public static final ILanguageString AREA_COMMITS_TOP = ILanguageString.Companion.create("&6&l--------------------- &d&l%s 的提交 &r&8(&b&l%d &l&7/ &b&l%d&8) &6&l---------------------");
    public static final ILanguageString AREA_COMMITS_BOTTOM = ILanguageString.Companion.create("&6&l--------------------- &d&l%s 的提交 &r&8(&b&l%d &l&7/ &b&l%d&8) &6&l---------------------");

    public static final ILanguageString GAME_COMMITS_IS_NULL = ILanguageString.Companion.create("&c无法加载该游戏的提交记录");
    public static final ILanguageString GAME_COMMITS_IS_EMPTY = ILanguageString.Companion.create("&c该游戏没有提交记录");
    public static final ILanguageString GAME_COMMITS_TOP = ILanguageString.Companion.create("&6&l--------------------- &d&l%s 的提交 &r&8(&b&l%d &l&7/ &b&l%d&8) &6&l---------------------");
    public static final ILanguageString GAME_COMMITS_BOTTOM = ILanguageString.Companion.create("&6&l--------------------- &d&l%s 的提交 &r&8(&b&l%d &l&7/ &b&l%d&8) &6&l---------------------");
}
