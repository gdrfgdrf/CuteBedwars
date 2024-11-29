package io.github.gdrfgdrf.cutebedwars.languages.language.chinese.simplified;

import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ILanguageString;
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.IILanguageBlock;

/**
 * @author gdrfgdrf
 */
class CommandLanguage implements IILanguageBlock {
    public static final ILanguageString COMMAND_FORMAT = ILanguageString.Companion.create("&e&l%s");
    public static final ILanguageString COMMAND_FORMAT_FOR_CONSOLE = ILanguageString.Companion.create("&e&l%s &l&7- &r&9&l%s");
    public static final ILanguageString COMMAND_HELP_ADMIN_INFIX = ILanguageString.Companion.create("&7&l----------- &r&e&l管理员指令 &r&7&l-----------");

    public static final ILanguageString NO_PERMISSION = ILanguageString.Companion.create("&c您没有权限执行该指令");
    public static final ILanguageString SYNTAX_ERROR = ILanguageString.Companion.create("&c语法错误: %s");
    public static final ILanguageString ONLY_PLAYER = ILanguageString.Companion.create("&c该指令仅玩家可执行");
    public static final ILanguageString NOT_FOUND = ILanguageString.Companion.create("&c没有找到该指令");
}
