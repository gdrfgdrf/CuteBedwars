package io.github.gdrfgdrf.cutebedwars.abstracts.locale

import net.md_5.bungee.api.chat.ClickEvent.Action
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.TextComponent

interface ICuteText {
    fun clickAction(action: Action): ICuteText
    fun clickActionValue(value: String): ICuteText
    fun openUrl(value: String): ICuteText
    fun openFile(value: String): ICuteText
    fun runCommand(value: String): ICuteText
    fun suggestCommand(value: String): ICuteText
    fun changePage(value: String): ICuteText

    fun hoverAction(action: HoverEvent.Action): ICuteText
    fun hoverActionValue(vararg cuteText: ICuteText): ICuteText
    fun showText(vararg cuteText: ICuteText): ICuteText
    fun showAchievement(vararg cuteText: ICuteText): ICuteText
    fun showItem(vararg cuteText: ICuteText): ICuteText
    fun showEntity(vararg cuteText: ICuteText): ICuteText

    fun format(vararg any: Any): ICuteText
    fun build(): TextComponent
}