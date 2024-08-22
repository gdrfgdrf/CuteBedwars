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

    public static final LanguageString GAME_TEAM_NEED_POSITIVE_ERROR = new LanguageString("队伍 &e&l%s &c的参数 &e&l%s &c配置错误，必须为正数");
}
