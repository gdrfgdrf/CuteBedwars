package io.github.gdrfgdrf.cutebedwars.languages.language.chinese.simplified;

import io.github.gdrfgdrf.cuteframework.locale.LanguageString;
import io.github.gdrfgdrf.cuteframework.locale.base.LanguageBlock;

/**
 * @author gdrfgdrf
 */
class CommonLanguage implements LanguageBlock {
    public static final LanguageString PREFIX = new LanguageString("&9▌ &a&lCuteBedwars &8&l>> ");
    public static final LanguageString COMMON_TOP = new LanguageString("&8&l=========== &r&a&lCuteBedwars &r&8&l===========");
    public static final LanguageString COMMON_BOTTOM = new LanguageString("&8&l=========== &r&a&lCuteBedwars &r&8&l===========");
    public static final LanguageString NONE = new LanguageString("&c&l无");
    public static final LanguageString PHASE_ERROR = new LanguageString("&c当前阶段不能进行该操作");

    public static final LanguageString RELOAD_WARRING = new LanguageString("&c这将会重载整个插件，可能会出现无法预料的问题，请在 &e%s &c秒内再次输入 &e/cbw reload &c以确认");
    public static final LanguageString RELOADING_PLUGIN = new LanguageString("&a重载插件中...");
    public static final LanguageString RELOAD_FINISHED = new LanguageString("&a重载插件成功");
}
