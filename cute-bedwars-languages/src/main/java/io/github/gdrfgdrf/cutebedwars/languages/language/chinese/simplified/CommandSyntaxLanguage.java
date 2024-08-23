package io.github.gdrfgdrf.cutebedwars.languages.language.chinese.simplified;

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands;
import io.github.gdrfgdrf.cuteframework.locale.LanguageString;
import io.github.gdrfgdrf.cuteframework.locale.base.LanguageBlock;

/**
 * @author gdrfgdrf
 */
class CommandSyntaxLanguage implements LanguageBlock {
    public static final LanguageString HELP = new LanguageString(ICommands.Companion.get("HELP").get());
    public static final LanguageString RELOAD = new LanguageString(ICommands.Companion.get("RELOAD").get());
    public static final LanguageString QUERY_DESCRIPTION = new LanguageString(ICommands.Companion.get("QUERY_DESCRIPTION").get());

    public static final LanguageString CREATE_AREA = new LanguageString(ICommands.Companion.get("CREATE_AREA").get());
}
