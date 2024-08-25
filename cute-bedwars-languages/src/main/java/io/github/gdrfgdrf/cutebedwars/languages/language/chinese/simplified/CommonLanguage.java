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

    public static final LanguageString DUPLICATE_AREA_NAME_WARNING = new LanguageString("&c名为 &e%s &c的区域已经存在，若继续创建，加入时则需要使用 &earea_id &c加入。请在 &e%s &c秒内再次输入以确定");
    public static final LanguageString DUPLICATE_AREA_ID_ERROR = new LanguageString("&c无法创建区域，因为程序生成的 &earea_id %s &c和其他区域重复，出现该错误一般为服务器的系统时间被回拨，请检查服务器的系统时间是否为最新");
    public static final LanguageString CREATING_AREA = new LanguageString("&e正在创建区域 %s");
    public static final LanguageString CREATE_AREA_FINISHED = new LanguageString("&a区域 &e&l%s &r&a已创建，&earea_id &a为 &e&l%s");

    public static final LanguageString PAGE_TOP = new LanguageString("&6&l--------------------- &b&l%d &l&7/ &b&l%d &6&l---------------------");
    public static final LanguageString PAGE_BOTTOM = new LanguageString("&6&l--------------------- &b&l%d &l&7/ &b&l%d &6&l---------------------");
    public static final LanguageString PAGE_INDEX_OUT_OF_BOUNDS = new LanguageString("&c只有 &e&l%s &r&c页");
}
