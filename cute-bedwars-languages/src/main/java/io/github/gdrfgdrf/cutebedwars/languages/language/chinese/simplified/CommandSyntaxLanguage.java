package io.github.gdrfgdrf.cutebedwars.languages.language.chinese.simplified;

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands;
import io.github.gdrfgdrf.cuteframework.locale.LanguageString;
import io.github.gdrfgdrf.cuteframework.locale.base.LanguageBlock;

/**
 * @author gdrfgdrf
 */
class CommandSyntaxLanguage implements LanguageBlock {
    public static final LanguageString HELP = new LanguageString(ICommands.Companion.valueOf("HELP").getShort());
    public static final LanguageString RELOAD = new LanguageString(ICommands.Companion.valueOf("RELOAD").getShort());
    public static final LanguageString QUERY_DESCRIPTION = new LanguageString(ICommands.Companion.valueOf("QUERY_DESCRIPTION").getShort());
    public static final LanguageString INFO_COMMANDS = new LanguageString(ICommands.Companion.valueOf("INFO_COMMANDS").getShort());

    public static final LanguageString CREATE_AREA = new LanguageString(ICommands.Companion.valueOf("CREATE_AREA").getShort());
    public static final LanguageString INFO_AREA = new LanguageString(ICommands.Companion.valueOf("INFO_AREA").getShort());
    public static final LanguageString EDITOR_AREA = new LanguageString(ICommands.Companion.valueOf("EDITOR_AREA").getShort());

    public static final LanguageString CREATE_GAME = new LanguageString(ICommands.Companion.valueOf("CREATE_GAME").getShort());
    public static final LanguageString INFO_GAME = new LanguageString(ICommands.Companion.valueOf("INFO_GAME").getShort());

    public static final LanguageString EDIT_MAKE = new LanguageString(ICommands.Companion.valueOf("EDIT_MAKE").getShort());
}
