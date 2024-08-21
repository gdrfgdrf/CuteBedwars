package io.github.gdrfgdrf.cutebedwars.locale.language.chinese.simplified;

import io.github.gdrfgdrf.cuteframework.locale.LanguageString;
import io.github.gdrfgdrf.cuteframework.locale.base.LanguageBlock;

/**
 * @author gdrfgdrf
 */
class CommandLanguage implements LanguageBlock {
    public static final LanguageString COMMAND_FORMAT = new LanguageString("&e&l%s &l&7- &r&9&l%s");
    public static final LanguageString COMMAND_HELP_ADMIN_INFIX = new LanguageString("&7&l----------- &r&e&l管理员指令 &r&7&l-----------");

    public static final LanguageString NO_PERMISSION = new LanguageString("&c您没有权限执行该指令");
    public static final LanguageString SYNTAX_ERROR = new LanguageString("&c语法错误: %s");
    public static final LanguageString ONLY_PLAYER = new LanguageString("&c该指令仅玩家可执行");
    public static final LanguageString NOT_FOUND = new LanguageString("&c没有找到该指令");

    public static final LanguageString RELOAD_WARRING = new LanguageString("&c这将会重载整个插件，可能会出现无法预料的问题，请在 &e%s &c秒内再次输入 &e/cbw reload &c以确认");
    public static final LanguageString RELOADING_PLUGIN = new LanguageString("&a重载插件中...");
}
