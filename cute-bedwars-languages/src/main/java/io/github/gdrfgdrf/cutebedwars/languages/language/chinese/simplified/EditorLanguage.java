package io.github.gdrfgdrf.cutebedwars.languages.language.chinese.simplified;

import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ILanguageString;
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.IILanguageBlock;

/**
 * @author gdrfgdrf
 */
class EditorLanguage implements IILanguageBlock {
    public static final ILanguageString LOADING_AREA_EDITOR = ILanguageString.Companion.create("&e正在加载区域编辑器");
    public static final ILanguageString LOADING_GAME_EDITOR = ILanguageString.Companion.create("&e正在加载游戏编辑器");
    public static final ILanguageString EDITOR_LOAD_FINISHED = ILanguageString.Companion.create("&a编辑器加载完成");
    public static final ILanguageString EDITOR_LOAD_ERROR = ILanguageString.Companion.create("&c编辑器加载错误");

    public static final ILanguageString ALREADY_IN_EDITING_MODE = ILanguageString.Companion.create("&c您正处于编辑模式，不能再次开启一个编辑器，若要退出可使用 /cbw edit exit <apply_changes>");
    public static final ILanguageString NOT_IN_EDITING_MODE = ILanguageString.Companion.create("&c无法进行该操作，因为您不处于编辑模式");

    public static final ILanguageString CHANGE_LIST_IS_NULL = ILanguageString.Companion.create("&c无法进行该操作，因为修改列表不存在");
    public static final ILanguageString REPLACING_CHANGE_LIST_WARNING = ILanguageString.Companion.create("&c当前已经存在了一个修改列表，若继续创建，将会覆盖现有修改列表。请在 &e&l%s &r&c秒内再次输入以确定");

    public static final ILanguageString CREATING_CHANGE_LIST = ILanguageString.Companion.create("&e正在创建修改列表");
    public static final ILanguageString CREATE_CHANGE_LIST_FINISHED = ILanguageString.Companion.create("&a修改列表已创建");

    public static final ILanguageString PRELOADING_CHANGE = ILanguageString.Companion.create("&e正在预加载修改");
    public static final ILanguageString PRELOAD_CHANGE_SUCCESS = ILanguageString.Companion.create("&a预加载修改成功");
    public static final ILanguageString CANNOT_PRELOAD_CHANGE = ILanguageString.Companion.create("&c无法预加载修改");

    public static final ILanguageString ADD_CHANGE_ERROR = ILanguageString.Companion.create("&c无法添加修改");
    public static final ILanguageString ADD_CHANGE_SUCCESS = ILanguageString.Companion.create("&a添加修改成功");

    public static final ILanguageString CHANGE_LIST_IS_EMPTY = ILanguageString.Companion.create("&c当前修改列表为空");
    public static final ILanguageString CHANGE_FORMAT = ILanguageString.Companion.create("&a修改类型&8: &e&l%s &r&8- &a修改名&8: &e&l%s");

    public static final ILanguageString COMMITTING_CHANGES = ILanguageString.Companion.create("&e正在提交修改");
    public static final ILanguageString COMMIT_FINISHED = ILanguageString.Companion.create("&a提交完成");
    public static final ILanguageString COMMIT_ERROR = ILanguageString.Companion.create("&c无法提交修改");
    public static final ILanguageString NEED_ONE_CHANGE_AT_LEAST = ILanguageString.Companion.create("&c至少需要一条修改才能提交");

    public static final ILanguageString APPLYING_CHANGES = ILanguageString.Companion.create("&e正在应用修改");
    public static final ILanguageString APPLY_ERROR = ILanguageString.Companion.create("&c无法应用修改");
    public static final ILanguageString COMMIT_SAVED = ILanguageString.Companion.create("&a提交保存完成");

    public static final ILanguageString UNMAKING_CHANGE = ILanguageString.Companion.create("&e正在移除 %s 个修改");
    public static final ILanguageString UNMAKING_CHANGE_FINISHED = ILanguageString.Companion.create("&a修改移除完成");
    public static final ILanguageString UNMAKING_CHANGE_ERROR = ILanguageString.Companion.create("&c移除修改失败");

    public static final ILanguageString REVERTING_COMMIT = ILanguageString.Companion.create("&e正在撤回 %s 个提交");
    public static final ILanguageString REVERT_COMMIT_FINISHED = ILanguageString.Companion.create("&a撤回提交完成");
    public static final ILanguageString REVERT_COMMIT_ERROR = ILanguageString.Companion.create("&c撤回提交失败");

    public static final ILanguageString EXITING_EDITOR = ILanguageString.Companion.create("&e正在退出编辑器");
    public static final ILanguageString EXIT_FINISHED = ILanguageString.Companion.create("&a编辑器已退出");

    public static final ILanguageString ARGUMENT_ERROR = ILanguageString.Companion.create("&c参数错误，请使用 /cbw query description 查看具体说明");

    public static final ILanguageString COMMIT_ID_IS = ILanguageString.Companion.create("&a提交 id&8: &e&l%s");
    public static final ILanguageString COMMIT_TIME_IS = ILanguageString.Companion.create("&a提交时间&8: &e&l%s");
    public static final ILanguageString COMMIT_SUBMITTER_IS = ILanguageString.Companion.create("&a提交者&8: &e&l%s");
    public static final ILanguageString COMMIT_MESSAGE_IS = ILanguageString.Companion.create("&a提交信息&8: &e&l%s");
    public static final ILanguageString COMMIT_CHANGES_IS = ILanguageString.Companion.create("&a提交的修改&8: ");
    public static final ILanguageString COMMIT_CHANGES_FORMAT = ILanguageString.Companion.create("&8- &a修改类型&8: &e&l%s &r&8- &a修改名&8: &e&l%s");

    public static final ILanguageString NOT_FOUND_CHANGE = ILanguageString.Companion.create("&c找不到 id 为 &e&l%s &r&c的修改");
    public static final ILanguageString NOT_FOUND_COMMIT = ILanguageString.Companion.create("&c找不到指定的提交");

    public static final ILanguageString AREA_PROPERTY_CHANGE = ILanguageString.Companion.create("区域属性修改");
    public static final ILanguageString AREA_PROPERTY_CHANGE_NAME = ILanguageString.Companion.create("将 %s 从 %s 修改为 %s");

    public static final ILanguageString GAME_PROPERTY_CHANGE = ILanguageString.Companion.create("游戏属性修改");
    public static final ILanguageString GAME_PROPERTY_CHANGE_NAME = ILanguageString.Companion.create("将 %s 从 %s 修改为 %s");

    public static final ILanguageString GAME_REGION_CHANGE = ILanguageString.Companion.create("游戏区域修改");
    public static final ILanguageString GAME_REGION_CHANGE_NAME = ILanguageString.Companion.create("将 %s -> %s 修改为 %s -> %s");

    public static final ILanguageString GAME_COORDINATE_CHANGE = ILanguageString.Companion.create("游戏坐标属性修改");
    public static final ILanguageString GAME_COORDINATE_CHANGE_NAME = ILanguageString.Companion.create("将坐标属性 %s 从 %s 修改为 %s");
}
