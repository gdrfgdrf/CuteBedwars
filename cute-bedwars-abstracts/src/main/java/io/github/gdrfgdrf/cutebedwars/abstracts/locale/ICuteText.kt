package io.github.gdrfgdrf.cutebedwars.abstracts.locale

import net.md_5.bungee.api.chat.ClickEvent.Action
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.TextComponent

interface ICuteText {
    fun rebuildParts()
    fun enablePart(): Boolean

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

    fun clickActionInPart(partIndex: Int, action: Action): ICuteText
    fun clickActionValueInPart(partIndex: Int, value: String): ICuteText
    fun openUrlInPart(partIndex: Int, value: String): ICuteText
    fun openFileInPart(partIndex: Int, value: String): ICuteText
    fun runCommandInPart(partIndex: Int, value: String): ICuteText
    fun suggestCommandInPart(partIndex: Int, value: String): ICuteText
    fun changePageInPart(partIndex: Int, value: String): ICuteText

    fun hoverActionInPart(partIndex: Int, action: HoverEvent.Action): ICuteText
    fun hoverActionValueInPart(partIndex: Int, vararg cuteText: ICuteText): ICuteText
    fun showTextInPart(partIndex: Int, vararg cuteText: ICuteText): ICuteText
    fun showAchievementInPart(partIndex: Int, vararg cuteText: ICuteText): ICuteText
    fun showItemInPart(partIndex: Int, vararg cuteText: ICuteText): ICuteText
    fun showEntityInPart(partIndex: Int, vararg cuteText: ICuteText): ICuteText

    fun format(vararg any: Any): ICuteText
    fun build(): TextComponent
    fun string(): String
}