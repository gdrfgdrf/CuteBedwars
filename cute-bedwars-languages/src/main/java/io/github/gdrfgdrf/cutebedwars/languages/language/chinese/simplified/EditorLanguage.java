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

    public static final LanguageString CHANGE_LIST_IS_NULL = new LanguageString("&c无法进行该操作，因为修改列表不存在");
    public static final LanguageString REPLACING_CHANGE_LIST_WARNING = new LanguageString("&c当前已经存在了一个修改列表，若继续创建，将会覆盖现有修改列表。请在 &e&l%s &r&c秒内再次输入以确定");

    public static final LanguageString CREATING_CHANGE_LIST = new LanguageString("&e正在创建修改列表");
    public static final LanguageString CREATE_CHANGE_LIST_FINISHED = new LanguageString("&a修改列表已创建");

    public static final LanguageString ADD_CHANGE_ERROR = new LanguageString("&c无法添加修改");
    public static final LanguageString ADD_CHANGE_SUCCESS = new LanguageString("&a添加修改成功");

    public static final LanguageString CHANGE_LIST_IS_EMPTY = new LanguageString("&c当前修改列表为空");
    public static final LanguageString CHANGE_FORMAT = new LanguageString("&a修改类型&8: &e&l%s &r&8- &a修改名&8: &e&l%s");

    public static final LanguageString EXITING_EDITOR = new LanguageString("&e正在退出编辑器");
    public static final LanguageString EXITING_WITHOUT_APPLYING_WARNING = new LanguageString("&c将会丢弃所有更改，若继续操作，请在 &e&l%s &r&c秒内再次输入以确定");
    public static final LanguageString APPLYING_CHANGES = new LanguageString("&e正在应用修改");
    public static final LanguageString EXIT_FINISHED = new LanguageString("&a编辑器已退出");

    public static final LanguageString ARGUMENT_ERROR = new LanguageString("&c参数错误，请使用 /cbw query-description 查看具体说明");
}
