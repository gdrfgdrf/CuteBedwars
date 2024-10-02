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

    public static final LanguageString COMMITTING_CHANGES = new LanguageString("&e正在提交修改");
    public static final LanguageString COMMIT_FINISHED = new LanguageString("&a提交完成");
    public static final LanguageString COMMIT_ERROR = new LanguageString("&c无法提交修改");
    public static final LanguageString NEED_ONE_CHANGE_AT_LEAST = new LanguageString("&c至少需要一条修改才能提交");

    public static final LanguageString APPLYING_CHANGES = new LanguageString("&e正在应用修改");
    public static final LanguageString APPLY_ERROR = new LanguageString("&c无法应用修改");
    public static final LanguageString COMMIT_SAVED = new LanguageString("&a提交保存完成");

    public static final LanguageString REVERTING_COMMIT = new LanguageString("&e正在撤回 %s 个提交");
    public static final LanguageString REVERT_COMMIT_FINISHED = new LanguageString("&a撤回提交完成");
    public static final LanguageString REVERT_COMMIT_ERROR = new LanguageString("&c撤回提交失败");

    public static final LanguageString EXITING_EDITOR = new LanguageString("&e正在退出编辑器");
    public static final LanguageString EXIT_FINISHED = new LanguageString("&a编辑器已退出");

    public static final LanguageString ARGUMENT_ERROR = new LanguageString("&c参数错误，请使用 /cbw query-description 查看具体说明");

    public static final LanguageString COMMIT_ID_IS = new LanguageString("&a提交 id&8: &e&l%s");
    public static final LanguageString COMMIT_TIME_IS = new LanguageString("&a提交时间&8: &e&l%s");
    public static final LanguageString COMMIT_SUBMITTER_IS = new LanguageString("&a提交者&8: &e&l%s");
    public static final LanguageString COMMIT_MESSAGE_IS = new LanguageString("&a提交信息&8: &e&l%s");
    public static final LanguageString COMMIT_CHANGES_IS = new LanguageString("&a提交的修改&8: ");
    public static final LanguageString COMMIT_CHANGES_FORMAT = new LanguageString("&8- &a修改类型&8: &e&l%s &r&8- &a修改名&8: &e&l%s");

    public static final LanguageString NOT_FOUND_COMMIT = new LanguageString("找不到指定的提交");
}
