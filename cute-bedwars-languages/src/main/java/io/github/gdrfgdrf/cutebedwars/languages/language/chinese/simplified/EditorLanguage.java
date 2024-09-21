package io.github.gdrfgdrf.cutebedwars.languages.language.chinese.simplified;

import io.github.gdrfgdrf.cuteframework.locale.LanguageString;
import io.github.gdrfgdrf.cuteframework.locale.base.LanguageBlock;

/**
 * @author gdrfgdrf
 */
class EditorLanguage implements LanguageBlock {
    public static final LanguageString LOADING_AREA_EDITOR = new LanguageString("&e正在加载区域编辑器");
    public static final LanguageString EDITOR_LOAD_FINISHED = new LanguageString("&a编辑器加载完成");

    public static final LanguageString ALREADY_IN_EDITING_MODE = new LanguageString("&c您正处于编辑模式，不能再次开启一个编辑器，若要退出可使用 /cbw edit exit <apply_changes>");
    public static final LanguageString NOT_IN_EDITING_MODE = new LanguageString("&c无法进行该操作，因为您不处于编辑模式");
}
