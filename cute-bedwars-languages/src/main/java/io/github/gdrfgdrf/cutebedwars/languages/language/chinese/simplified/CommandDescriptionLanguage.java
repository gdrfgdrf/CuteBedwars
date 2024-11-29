package io.github.gdrfgdrf.cutebedwars.languages.language.chinese.simplified;

import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ILanguageString;
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.IILanguageBlock;

/**
 * @author gdrfgdrf
 */
class CommandDescriptionLanguage implements IILanguageBlock {
    public static final ILanguageString HELP = ILanguageString.Companion.create("显示帮助");
    public static final ILanguageString RELOAD = ILanguageString.Companion.create("重载插件");
    public static final ILanguageString QUERY_DESCRIPTION = ILanguageString.Companion.create("查询描述");
    public static final ILanguageString INFO_COMMANDS = ILanguageString.Companion.create("查询指令详细信息");

    public static final ILanguageString CREATE_AREA = ILanguageString.Companion.create("创建区域");
    public static final ILanguageString INFO_AREA = ILanguageString.Companion.create("查看区域属性");
    public static final ILanguageString EDITOR_AREA = ILanguageString.Companion.create("编辑区域");

    public static final ILanguageString CREATE_GAME = ILanguageString.Companion.create("在指定区域下创建一个游戏");
    public static final ILanguageString INFO_GAME = ILanguageString.Companion.create("查看游戏属性");
    public static final ILanguageString EDITOR_GAME = ILanguageString.Companion.create("编辑游戏");

    public static final ILanguageString EDIT_NEW_CHANGES = ILanguageString.Companion.create("新建一个修改列表");
    public static final ILanguageString EDIT_MAKE = ILanguageString.Companion.create("向当前修改列表中添加一个修改");
    public static final ILanguageString EDIT_UNMAKE = ILanguageString.Companion.create("从当前修改列表中移除修改");
    public static final ILanguageString EDIT_LIST_CHANGES = ILanguageString.Companion.create("列出当前修改列表中的所有修改");
    public static final ILanguageString EDIT_COMMIT = ILanguageString.Companion.create("提交修改");
    public static final ILanguageString EDIT_REVERT_COMMIT = ILanguageString.Companion.create("撤回提交");
    public static final ILanguageString EDIT_EXIT = ILanguageString.Companion.create("退出编辑模式");

    public static final ILanguageString EDIT_LIST_AREA_COMMITS = ILanguageString.Companion.create("查看区域的提交");
}
