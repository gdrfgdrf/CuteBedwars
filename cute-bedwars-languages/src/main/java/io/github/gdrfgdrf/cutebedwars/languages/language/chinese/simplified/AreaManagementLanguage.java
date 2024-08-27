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

    public static final LanguageString NOT_FOUND_PROPERTY = new LanguageString("&c没有找到名为 &e&l%s &c的属性");
    public static final LanguageString PROPERTY_CANNOT_ACCESS = new LanguageString("&c无法访问名为 &e&l%s &c的属性，该错误不应该出现，请确认插件是否为最新版本");
    public static final LanguageString PROPERTY_IS_UNDEFINABLE = new LanguageString("&c属性 &e&l%s &c无法通过指令进行定义，需要使用其他手段");
    public static final LanguageString NOT_POSITIVE_NUMBER = new LanguageString("&c属性 &e&l%s &c需要正整数");
    public static final LanguageString CONVERT_ERROR = new LanguageString("&c类型转换失败");
    public static final LanguageString SET_PROPERTY_SUCCESS = new LanguageString("&a成功将属性 &e&l%s &a的值设为 &e&l%s");
}
