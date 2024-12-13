package io.github.gdrfgdrf.cutebedwars.languages.language.chinese.simplified;

import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ILanguageString;
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.IILanguageBlock;

/**
 * @author gdrfgdrf
 */
class CommonLanguage implements IILanguageBlock {
    public static final ILanguageString PREFIX = ILanguageString.Companion.create("&9▌ &a&lCuteBedwars &8&l>> ");
    public static final ILanguageString COMMON_TOP = ILanguageString.Companion.create("&8&l=========== &r&a&lCuteBedwars &r&8&l===========");
    public static final ILanguageString COMMON_BOTTOM = ILanguageString.Companion.create("&8&l=========== &r&a&lCuteBedwars &r&8&l===========");
    public static final ILanguageString NONE = ILanguageString.Companion.create("&c&l无");
    public static final ILanguageString PHASE_ERROR = ILanguageString.Companion.create("&c当前阶段不能进行该操作");
    public static final ILanguageString BOOLEAN_TRUE = ILanguageString.Companion.create("&a&l是");
    public static final ILanguageString BOOLEAN_FALSE = ILanguageString.Companion.create("&c&l否");

    public static final ILanguageString RELOAD_WARRING = ILanguageString.Companion.create("&c这将会重载整个插件，可能会出现无法预料的问题，请在 &e%s &c秒内再次输入 &e/cbw reload &c以确认");
    public static final ILanguageString RELOADING_PLUGIN = ILanguageString.Companion.create("&e重载插件中...");
    public static final ILanguageString RELOAD_FINISHED = ILanguageString.Companion.create("&a重载插件成功");

    public static final ILanguageString DESCRIPTION_FORMAT = ILanguageString.Companion.create("&a&l%s&8&l: &r&e%s");
    public static final ILanguageString NOT_FOUND_DESCRIPTION = ILanguageString.Companion.create("&c没有找到描述 %s");
    public static final ILanguageString DESCRIPTION_ERROR = ILanguageString.Companion.create("&c描述 %s 加载错误，无法查询");
    public static final ILanguageString DESCRIPTION_TIP = ILanguageString.Companion.create("&a若有不清楚的属性可使用 &e&l/cbw query description args &9&l<属性名> &r&a进行查询");

    public static final ILanguageString NOT_FOUND_COMMANDS = ILanguageString.Companion.create("&c没有找到任何您可以查询详细信息的指令");
    public static final ILanguageString COMMAND_RAW_IS = ILanguageString.Companion.create("&a原指令&8: &e&l%s");
    public static final ILanguageString COMMAND_DESCRIPTION_IS = ILanguageString.Companion.create("&a指令描述&8: &9&l%s");
    public static final ILanguageString COMMAND_IS_ALLOW_EMPTY_PARAM = ILanguageString.Companion.create("&a允许空参数&8: &e&l%s");
    public static final ILanguageString COMMAND_PARAM_SCHEME_IS = ILanguageString.Companion.create("&a参数方案&8: ");
    public static final ILanguageString COMMAND_INFO_TIP = ILanguageString.Companion.create("&a要使用参数方案，需要先写 &e&l<空格>args<空格> &r&a然后再接上参数方案");

    public static final ILanguageString PARAM_SCHEME_FORMAT = ILanguageString.Companion.create(" &8- &e&l%s");

    public static final ILanguageString PAGE_TOP = ILanguageString.Companion.create("&6&l--------------------- &b&l%d &l&7/ &b&l%d &6&l---------------------");
    public static final ILanguageString PAGE_BOTTOM = ILanguageString.Companion.create("&6&l--------------------- &b&l%d &l&7/ &b&l%d &6&l---------------------");
    public static final ILanguageString PAGE_INDEX_OUT_OF_BOUNDS = ILanguageString.Companion.create("&c只有 &e&l%s &r&c页");
    public static final ILanguageString PAGE_INDEX_EMPTY = ILanguageString.Companion.create("&c列表为空");

    public static final ILanguageString STATUS_UNKNOWN = ILanguageString.Companion.create("&f未知");
    public static final ILanguageString STATUS_DISABLED = ILanguageString.Companion.create("&c已禁用");
    public static final ILanguageString STATUS_EDITING = ILanguageString.Companion.create("&b编辑中");
    public static final ILanguageString STATUS_ENABLED = ILanguageString.Companion.create("&a已启用");
    public static final ILanguageString STATUS_INDEPENDENT = ILanguageString.Companion.create("&6独立的");

    public static final ILanguageString SELECTED_POS_1 = ILanguageString.Companion.create("&e已选择点一: &a&l%s");
    public static final ILanguageString SELECTED_POS_2 = ILanguageString.Companion.create("&e已选择点二: &a&l%s");

    public static final ILanguageString COORDINATE_FULL = ILanguageString.Companion.create("&9&l[&e&l%s&8&l, &e&l%s&8&l, &e&l%s&9&l]&r");

    public static final ILanguageString REGION_ERROR = ILanguageString.Companion.create("错误的范围表达，两点坐标的 X, Y, Z 不能有一个相同，即范围大小至少为 2x2x2");
    public static final ILanguageString REGION_1 = ILanguageString.Companion.create("\n     %s\n" +
            "     ↑\n" +
            "     *---------------------*\n" +
            "    /|*                   /|\n" +
            "   / | *                 / |\n" +
            "  /  |  *               /  |\n" +
            " /   |   *             /   |\n" +
            "*---------------------*    |\n" +
            "|    |     *          |    |\n" +
            "|    |      *         |    |\n" +
            "|    |        *       |    |\n" +
            "|    |         *      |    |\n" +
            "|    *----------*-----|----*\n" +
            "|   /             *   |   /\n" +
            "|  /               *  |  /\n" +
            "| /                 * | /\n" +
            "|/                   *|/\n" +
            "*---------------------*\n" +
            "                      ↓\n" +
            "                      %s");
    public static final ILanguageString REGION_2 = ILanguageString.Companion.create("\n                           %s\n" +
            "                           ↑\n" +
            "     *---------------------*\n" +
            "    /|                   */|\n" +
            "   / |                 * / |\n" +
            "  /  |               *  /  |\n" +
            " /   |             *   /   |\n" +
            "*---------------------*    |\n" +
            "|    |          *     |    |\n" +
            "|    |        *       |    |\n" +
            "|    |       *        |    |\n" +
            "|    |     *          |    |\n" +
            "|    *----*-----------|----*\n" +
            "|   /   *             |   /\n" +
            "|  /  *               |  /\n" +
            "| / *                 | /\n" +
            "|/*                   |/\n" +
            "*---------------------*\n" +
            "↓\n" +
            "%s");
    public static final ILanguageString REGION_3 = ILanguageString.Companion.create("\n         *---------------------*\n" +
            "        /|                    /|\n" +
            "       / |                   / |\n" +
            "      /  |                  /  |\n" +
            "     /   |                 /   |\n" +
            "%s← *---------------------*    |\n" +
            "    |   *|                |    |\n" +
            "    |    |   *            |    |\n" +
            "    |    |        *       |    |\n" +
            "    |    |              * |    |\n" +
            "    |    *----------------|----* →%s\n" +
            "    |   /                 |   /\n" +
            "    |  /                  |  /\n" +
            "    | /                   | /\n" +
            "    |/                    |/\n" +
            "    *---------------------*");
    public static final ILanguageString REGION_4 = ILanguageString.Companion.create("\n            *---------------------*\n" +
            "           /|                    /|\n" +
            "          / |                   / |\n" +
            "         /  |                  /  |\n" +
            "        /   |                 /   |\n" +
            "       *---------------------* > > > > >%s\n" +
            "       |    |              * |    |\n" +
            "       |    |          *     |    |\n" +
            "       |    |      *         |    |\n" +
            "       |    |  *             |    |\n" +
            "%s< < < < < *----------------|----*\n" +
            "       |   /                 |   /\n" +
            "       |  /                  |  /\n" +
            "       | /                   | /\n" +
            "       |/                    |/\n" +
            "       *---------------------*");
}
