package io.github.gdrfgdrf.cutebedwars.abstracts.locale

import net.md_5.bungee.api.chat.TextComponent

interface ITranslationTextAgent {
    val cuteText: ICuteText
    val enablePart: Boolean
    val string: String

    fun rebuildParts()

    fun openUrl(value: String): ITranslationTextAgent
    fun openFile(value: String): ITranslationTextAgent
    fun runCommand(value: String): ITranslationTextAgent
    fun suggestCommand(value: String): ITranslationTextAgent
    fun changePage(value: String): ITranslationTextAgent

    fun showText(vararg value: String): ITranslationTextAgent
    fun showAchievement(vararg value: String): ITranslationTextAgent
    fun showItem(vararg value: String): ITranslationTextAgent
    fun showEntity(vararg value: String): ITranslationTextAgent

    fun showText(vararg cuteText: ICuteText): ITranslationTextAgent
    fun showAchievement(vararg cuteText: ICuteText): ITranslationTextAgent
    fun showItem(vararg cuteText: ICuteText): ITranslationTextAgent
    fun showEntity(vararg cuteText: ICuteText): ITranslationTextAgent

    fun openUrlInPart(partIndex: Int, value: String): ITranslationTextAgent
    fun openFileInPart(partIndex: Int, value: String): ITranslationTextAgent
    fun runCommandInPart(partIndex: Int, value: String): ITranslationTextAgent
    fun suggestCommandInPart(partIndex: Int, value: String): ITranslationTextAgent
    fun changePageInPart(partIndex: Int, value: String): ITranslationTextAgent

    fun showTextInPart(partIndex: Int, vararg value: String): ITranslationTextAgent
    fun showAchievementInPart(partIndex: Int, vararg value: String): ITranslationTextAgent
    fun showItem(partIndex: Int, vararg value: String): ITranslationTextAgent
    fun showEntity(partIndex: Int, vararg value: String): ITranslationTextAgent

    fun showTextInPart(partIndex: Int, vararg cuteText: ICuteText): ITranslationTextAgent
    fun showAchievementInPart(partIndex: Int, vararg cuteText: ICuteText): ITranslationTextAgent
    fun showItemInPart(partIndex: Int, vararg cuteText: ICuteText): ITranslationTextAgent
    fun showEntityInPart(partIndex: Int, vararg cuteText: ICuteText): ITranslationTextAgent

    fun format(vararg any: Any): ITranslationTextAgent
    fun build(): TextComponent
}