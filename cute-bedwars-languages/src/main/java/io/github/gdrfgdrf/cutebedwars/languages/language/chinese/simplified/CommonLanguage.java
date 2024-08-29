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
    public static final LanguageString RELOADING_PLUGIN = new LanguageString("&e重载插件中...");
    public static final LanguageString RELOAD_FINISHED = new LanguageString("&a重载插件成功");

    public static final LanguageString DESCRIPTION_FORMAT = new LanguageString("&a&l%s&8&l: &r&e%s");
    public static final LanguageString NOT_FOUND_DESCRIPTION = new LanguageString("&c没有找到描述 %s");
    public static final LanguageString DESCRIPTION_ERROR = new LanguageString("&c描述 %s 加载错误，无法查询");
    public static final LanguageString DESCRIPTION_TIPS = new LanguageString("&a若有不清楚的属性可使用 &e&l/cbw query-description &9&l<属性名> &r&a进行查询");

    public static final LanguageString PAGE_TOP = new LanguageString("&6&l--------------------- &b&l%d &l&7/ &b&l%d &6&l---------------------");
    public static final LanguageString PAGE_BOTTOM = new LanguageString("&6&l--------------------- &b&l%d &l&7/ &b&l%d &6&l---------------------");
    public static final LanguageString PAGE_INDEX_OUT_OF_BOUNDS = new LanguageString("&c只有 &e&l%s &r&c页");

    public static final LanguageString STATUS_UNKNOWN = new LanguageString("&f未知");
    public static final LanguageString STATUS_DISABLED = new LanguageString("&c已禁用");
    public static final LanguageString STATUS_EDITING = new LanguageString("&b编辑中");
    public static final LanguageString STATUS_ENABLED = new LanguageString("&a已启用");
    public static final LanguageString STATUS_INDEPENDENT = new LanguageString("&6独立的");

    public static final LanguageString COORDINATE_FULL = new LanguageString("&9[&e%d&8, &e%d&8, &e%d&9]");

    public static final LanguageString REGION_ERROR = new LanguageString("错误的范围表达，两点坐标的 X, Y, Z 不能有一个相同，即范围大小至少为 2x2x2");
    public static final LanguageString REGION_1 = new LanguageString("     %s\n" +
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
            "|    *----------------|----*\n" +
            "|   /             *   |   /\n" +
            "|  /               *  |  /\n" +
            "| /                 * | /\n" +
            "|/                   *|/\n" +
            "*---------------------*\n" +
            "                      ↓\n" +
            "                      %s");
    public static final LanguageString REGION_2 = new LanguageString("                           %s\n" +
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
            "|    *----------------|----*\n" +
            "|   /   *             |   /\n" +
            "|  /  *               |  /\n" +
            "| / *                 | /\n" +
            "|/*                   |/\n" +
            "*---------------------*\n" +
            "↓\n" +
            "%s");
    public static final LanguageString REGION_3 = new LanguageString("         *---------------------*\n" +
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
    public static final LanguageString REGION_4 = new LanguageString("            *---------------------*\n" +
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
