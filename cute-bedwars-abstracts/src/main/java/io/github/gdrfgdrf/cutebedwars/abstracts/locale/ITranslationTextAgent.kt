package io.github.gdrfgdrf.cutebedwars.abstracts.locale

import net.md_5.bungee.api.chat.TextComponent

interface ITranslationTextAgent {
    fun cuteText(): ICuteText

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

    fun format(vararg any: Any): ITranslationTextAgent
    fun build(): TextComponent
}