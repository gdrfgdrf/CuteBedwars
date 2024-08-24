package io.github.gdrfgdrf.cutebedwars.languages.language.chinese.simplified;

import io.github.gdrfgdrf.cuteframework.locale.LanguageString;
import io.github.gdrfgdrf.cuteframework.locale.base.LanguageBlock;

/**
 * @author gdrfgdrf
 */
class DescriptionLanguage implements LanguageBlock {
    public static final LanguageString DESCRIPTION = new LanguageString("描述");

    public static final LanguageString LANGUAGE = new LanguageString("语言设置，默认为 chinese_simplified，即简体中文");
    public static final LanguageString DATABASE_IMPL = new LanguageString("数据库实现，插件默认使用 sqlite 作为数据库。当该值为 default-sqlite 时使用默认 sqlite 实现，若有其他数据库实现，需填写其 jar 包的绝对路径");
    public static final LanguageString ENABLE_DATABASE_LOGGING = new LanguageString("启用数据库的日志输出，输出日志内包含玩家数据等重要数据，默认为 false，即关闭日志输出。默认 sqlite 实现会遵守该值，其他数据库实现不一定");
    public static final LanguageString DATABASE_USERNAME = new LanguageString("访问数据库所需的用户名，默认值为空，若数据库没有用户名则无需填写");
    public static final LanguageString DATABASE_PASSWORD = new LanguageString("访问数据库所需的密码，默认值为空，若数据库没有密码则无需填写");
    public static final LanguageString REQUEST_TIMEOUT = new LanguageString("请求超时时间，有些时候执行某个操作需要二次确认，需要在请求超时时间内进行确认，否则请求失效。默认值为 10000，单位为毫秒");
    public static final LanguageString AREA_AUTO_SAVE_DELAY = new LanguageString("每个区域自动保存的延迟，该值仅对正在编辑中的区域有效。默认值为 300000，即五分钟，单位为 毫秒");

    public static final LanguageString AREA_ID = new LanguageString("区域 ID，不可重复，不可自定义，由程序进行生成，并作为唯一 ID 使用。" +
            "该值对于用户的作用和区域名称相同，但若有区域重名，则需使用该值进行索引");
    public static final LanguageString AREA_NAME = new LanguageString("区域名称，可以重复，但不建议重复，重复时则需使用区域 ID。" +
            "管理员可使用该值索引到对应区域下的所有游戏，玩家可用该值索引到对应区域下的所有已启用的游戏。");

}
