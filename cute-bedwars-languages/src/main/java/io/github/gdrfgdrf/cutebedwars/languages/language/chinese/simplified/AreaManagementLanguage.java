package io.github.gdrfgdrf.cutebedwars.languages.language.chinese.simplified;

import io.github.gdrfgdrf.cuteframework.locale.LanguageString;
import io.github.gdrfgdrf.cuteframework.locale.base.LanguageBlock;

/**
 * @author gdrfgdrf
 */
class AreaManagementLanguage implements LanguageBlock {
    public static final LanguageString GAME_MIN_PLAYER_FIXED = new LanguageString("&a已修正游戏 &e&l%s &a的最小玩家数为 %d");
    public static final LanguageString GAME_MAX_PLAYER_FIXED = new LanguageString("&a已修正游戏 &e&l%s &a的最大玩家数为 %d");

    public static final LanguageString AREA_VALIDATE_FAILED = new LanguageString("&c区域 &e&l%s 校验失败");
    public static final LanguageString GAME_VALIDATE_FAILED = new LanguageString("&c区域 &e&l%s 的游戏 &e&l%s 校验失败，已禁用该游戏: ");

    public static final LanguageString GAME_MIN_PLAYER_ERROR = new LanguageString("&c最小玩家数配置错误，必须 &e>= &a所有队伍的最小玩家数的总和，&c且必须 &e<= &a最大玩家数");
    public static final LanguageString GAME_MAX_PLAYER_ERROR = new LanguageString("&c最大玩家数配置错误，必须 &e<= &a所有队伍的最大玩家数的总和，&c且必须 &e>= &a最小玩家数");

    public static final LanguageString GAME_TEAM_NEED_POSITIVE_ERROR = new LanguageString("&c队伍 &e&l%s &c的参数 &e&l%s &c配置错误，必须为正数");
    public static final LanguageString GAME_TEAM_COUNT_ERROR = new LanguageString("&c必须拥有 > 1 个队伍");
    public static final LanguageString GAME_TEAM_MIN_PLAYER_BIGGER_THAN_MAX_PLAYER = new LanguageString("&c队伍 &e&l%s 的最小人数 > 最大人数");

    public static final LanguageString DUPLICATE_AREA_NAME_WARNING = new LanguageString("&c名为 &e&l%s &r&c的区域已经存在，若继续创建，对其进行操作则需要使用 &earea_id &c加入。请在 &e&l%s &r&c秒内再次输入以确定");
    public static final LanguageString DUPLICATE_AREA_ID_ERROR = new LanguageString("&c无法创建区域，因为程序生成的 &earea_id %s &c和其他区域重复，出现该错误一般为服务器的系统时间被回拨，请检查服务器的系统时间是否为最新");
    public static final LanguageString CREATING_AREA = new LanguageString("&e正在创建区域 %s");
    public static final LanguageString CREATE_AREA_FINISHED = new LanguageString("&a区域 &e&l%s &r&a已创建，&earea_id &a为 &e&l%s");

    public static final LanguageString NOT_FOUND_AREA_BY_ID = new LanguageString("&c无法找到 area_id 为 &e&l%s &r&c的区域");
    public static final LanguageString NOT_FOUND_AREA_BY_NAME = new LanguageString("&c无法找到名称为 &e&l%s &r&c的区域");
    public static final LanguageString AREA_ID_IS = new LanguageString("&a区域 id&8: &e&l%s");
    public static final LanguageString AREA_NAME_IS = new LanguageString("&a区域名&8: &e&l%s");
    public static final LanguageString AREA_PROPERTY_IS = new LanguageString("&a以下为该区域的属性&8: ");
    public static final LanguageString AREA_PROPERTY_FORMAT = new LanguageString("&a&l%s&8&l: &r&e%s");
    public static final LanguageString AREA_GAMES_IS = new LanguageString("&a以下为该区域的游戏&8:");
    public static final LanguageString AREA_GAMES_FORMAT = new LanguageString(" &8- &l&e%s &8(%s&8)");
    public static final LanguageString AREA_GAMES_EMPTY = new LanguageString(" &8- &l&c无");

    public static final LanguageString DUPLICATE_GAME_NAME_WARNING = new LanguageString("&c在区域 &e&l%s &r&c下与 &e&l%s &r&c同名的游戏已经存在，若继续创建，对其进行操作时则需要使用 &egame_id &c加入。请在 &el&l%s &r&c内再次输入以确定");
    public static final LanguageString DUPLICATE_GAME_ID_ERROR = new LanguageString("&c无法创建游戏，因为程序生成的 &egame_id %s &c和该区域下其他游戏重复，出现该错误一般为服务器的系统时间被回拨，请检查服务器的系统时间是否为最新");
    public static final LanguageString CREATING_GAME = new LanguageString("&e正在区域 &l%s &r&e下创建游戏 &l%s");
    public static final LanguageString CREATE_GAME_FINISHED = new LanguageString("&a成功在区域 &e&l%s &r&a下创建游戏 &e&l%s");

    public static final LanguageString NOT_FOUND_PROPERTY = new LanguageString("&c没有找到名为 &e&l%s &c的属性");
    public static final LanguageString PROPERTY_CANNOT_ACCESS = new LanguageString("&c无法访问名为 &e&l%s &c的属性，该错误不应该出现，请确认插件是否为最新版本");
    public static final LanguageString PROPERTY_IS_UNDEFINABLE = new LanguageString("&c属性 &e&l%s &c无法通过指令进行定义，需要使用其他手段");
    public static final LanguageString NOT_POSITIVE_NUMBER = new LanguageString("&c属性 &e&l%s &c需要正整数");
    public static final LanguageString CONVERT_ERROR = new LanguageString("&c类型转换失败");
    public static final LanguageString SET_PROPERTY_SUCCESS = new LanguageString("&a成功将属性 &e&l%s &a的值设为 &e&l%s");

    public static final LanguageString AREA_IS_EMPTY = new LanguageString("&c当前没有任何一个区域");
    public static final LanguageString DUPLICATE_AREA_NAME_ERROR = new LanguageString("&c存在多个名为 &e%s &c的区域，请使用 &earea_id &c进行索引");
}
