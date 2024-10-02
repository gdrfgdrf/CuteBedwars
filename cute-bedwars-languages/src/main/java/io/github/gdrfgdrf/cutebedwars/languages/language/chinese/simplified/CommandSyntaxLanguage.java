package io.github.gdrfgdrf.cutebedwars.languages.language.chinese.simplified;

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands;
import io.github.gdrfgdrf.cuteframework.locale.LanguageString;
import io.github.gdrfgdrf.cuteframework.locale.base.LanguageBlock;

/**
 * @author gdrfgdrf
 */
class CommandSyntaxLanguage implements LanguageBlock {
    public static final LanguageString HELP = string("HELP");
    public static final LanguageString RELOAD = string("RELOAD");
    public static final LanguageString QUERY_DESCRIPTION = string("QUERY_DESCRIPTION");
    public static final LanguageString INFO_COMMANDS = string("INFO_COMMANDS");

    public static final LanguageString CREATE_AREA = string("CREATE_AREA");
    public static final LanguageString INFO_AREA = string("INFO_AREA");
    public static final LanguageString EDITOR_AREA = string("EDITOR_AREA");

    public static final LanguageString CREATE_GAME = string("CREATE_GAME");
    public static final LanguageString INFO_GAME = string("INFO_GAME");

    public static final LanguageString EDIT_NEW_CHANGES = string("EDIT_NEW_CHANGES");
    public static final LanguageString EDIT_MAKE = string("EDIT_MAKE");
    public static final LanguageString EDIT_LIST_CHANGES = string("EDIT_LIST_CHANGES");
    public static final LanguageString EDIT_COMMIT = string("EDIT_COMMIT");
    public static final LanguageString EDIT_EXIT = string("EDIT_EXIT");

    private static LanguageString string(String name) {
        return new LanguageString(ICommands.Companion.valueOf(name).getShort());
    }
}
