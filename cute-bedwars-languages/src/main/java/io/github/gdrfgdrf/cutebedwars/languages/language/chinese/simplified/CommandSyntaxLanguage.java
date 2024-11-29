package io.github.gdrfgdrf.cutebedwars.languages.language.chinese.simplified;

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands;
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ILanguageString;
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.IILanguageBlock;

/**
 * @author gdrfgdrf
 */
class CommandSyntaxLanguage implements IILanguageBlock {
    public static final ILanguageString HELP = string("HELP");
    public static final ILanguageString RELOAD = string("RELOAD");
    public static final ILanguageString QUERY_DESCRIPTION = string("QUERY_DESCRIPTION");
    public static final ILanguageString INFO_COMMANDS = string("INFO_COMMANDS");

    public static final ILanguageString CREATE_AREA = string("CREATE_AREA");
    public static final ILanguageString INFO_AREA = string("INFO_AREA");
    public static final ILanguageString EDITOR_AREA = string("EDITOR_AREA");

    public static final ILanguageString CREATE_GAME = string("CREATE_GAME");
    public static final ILanguageString INFO_GAME = string("INFO_GAME");
    public static final ILanguageString EDITOR_GAME = string("EDITOR_GAME");

    public static final ILanguageString EDIT_NEW_CHANGES = string("EDIT_NEW_CHANGES");
    public static final ILanguageString EDIT_MAKE = string("EDIT_MAKE");
    public static final ILanguageString EDIT_UNMAKE = string("EDIT_UNMAKE");
    public static final ILanguageString EDIT_LIST_CHANGES = string("EDIT_LIST_CHANGES");
    public static final ILanguageString EDIT_COMMIT = string("EDIT_COMMIT");
    public static final ILanguageString EDIT_REVERT_COMMIT = string("EDIT_REVERT_COMMIT");
    public static final ILanguageString EDIT_EXIT = string("EDIT_EXIT");

    public static final ILanguageString EDIT_LIST_AREA_COMMITS = string("EDIT_LIST_AREA_COMMITS");

    private static ILanguageString string(String name) {
        return ILanguageString.Companion.create(ICommands.Companion.valueOf(name).getShort());
    }
}
