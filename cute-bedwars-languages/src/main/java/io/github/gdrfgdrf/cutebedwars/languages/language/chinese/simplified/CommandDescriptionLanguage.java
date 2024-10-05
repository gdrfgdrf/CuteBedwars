package io.github.gdrfgdrf.cutebedwars.languages.language.chinese.simplified;

import io.github.gdrfgdrf.cuteframework.locale.LanguageString;
import io.github.gdrfgdrf.cuteframework.locale.base.LanguageBlock;

/**
 * @author gdrfgdrf
 */
class CommandDescriptionLanguage implements LanguageBlock {
    public static final LanguageString HELP = new LanguageString("显示帮助");
    public static final LanguageString RELOAD = new LanguageString("重载插件");
    public static final LanguageString QUERY_DESCRIPTION = new LanguageString("查询描述");
    public static final LanguageString INFO_COMMANDS = new LanguageString("查询指令详细信息");

    public static final LanguageString CREATE_AREA = new LanguageString("创建区域");
    public static final LanguageString INFO_AREA = new LanguageString("查看区域属性");
    public static final LanguageString EDITOR_AREA = new LanguageString("编辑区域");

    public static final LanguageString CREATE_GAME = new LanguageString("在指定区域下创建一个游戏");
    public static final LanguageString INFO_GAME = new LanguageString("查看游戏属性");
    public static final LanguageString EDITOR_GAME = new LanguageString("编辑游戏");

    public static final LanguageString EDIT_NEW_CHANGES = new LanguageString("新建一个修改列表");
    public static final LanguageString EDIT_MAKE = new LanguageString("向当前修改列表中添加一个修改");
    public static final LanguageString EDIT_UNMAKE = new LanguageString("从当前修改列表中移除修改");
    public static final LanguageString EDIT_LIST_CHANGES = new LanguageString("列出当前修改列表中的所有修改");
    public static final LanguageString EDIT_COMMIT = new LanguageString("提交修改");
    public static final LanguageString EDIT_REVERT_COMMIT = new LanguageString("撤回提交");
    public static final LanguageString EDIT_EXIT = new LanguageString("退出编辑模式");

    public static final LanguageString EDIT_LIST_AREA_COMMITS = new LanguageString("查看区域的提交");
}
