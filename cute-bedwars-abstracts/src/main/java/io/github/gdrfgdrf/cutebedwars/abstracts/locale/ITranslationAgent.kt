package io.github.gdrfgdrf.cutebedwars.abstracts.locale

import net.md_5.bungee.api.chat.TextComponent

interface ITranslationAgent {
    val cuteTranslation: ICuteTranslation

    fun get0(): ITranslationTextAgent
    fun append(string: String): ITranslationAgent
    fun append(translationTextAgent: ITranslationTextAgent): ITranslationAgent
    fun append(translationAgent: ITranslationAgent): ITranslationAgent
    fun insert(index: Int, string: String): ITranslationAgent
    fun insert(index: Int, translationTextAgent: ITranslationTextAgent): ITranslationAgent
    fun format0(vararg any: Any): ITranslationAgent
    fun build(): TextComponent
    fun send(customPrefix: String)
    fun send(customPrefix: ITranslationTextAgent? = null)
    fun buildString(): String
}