package io.github.gdrfgdrf.cutebedwars.languages.language.chinese.simplified;

import io.github.gdrfgdrf.cuteframework.locale.LanguageString;
import io.github.gdrfgdrf.cuteframework.locale.base.LanguageBlock;

/**
 * @author gdrfgdrf
 */
class DescriptionLanguage implements LanguageBlock {
    public static final LanguageString DESCRIPTION = new LanguageString("描述");

    public static final LanguageString AREA_ID = new LanguageString("区域 ID，不可重复，不可自定义，由程序进行生成，并作为唯一 ID 使用。" +
            "该值对于用户的作用和区域名称相同，但若有区域重名，则需使用该值进行索引");
    public static final LanguageString AREA_NAME = new LanguageString("区域名称，可以重复，但不建议重复，重复时则需使用区域 ID。" +
            "管理员可使用该值索引到对应区域下的所有游戏，玩家可用该值索引到对应区域下的所有已启用的游戏。");

}
