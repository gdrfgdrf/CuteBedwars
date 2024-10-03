package io.github.gdrfgdrf.cutebedwars.locale.next

import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ICuteText
import net.md_5.bungee.api.chat.BaseComponent
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.ClickEvent.Action
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.TextComponent

class CuteText private constructor(raw: String) : ICuteText {
    private var finalString = raw

    private var clickAction: Action? = null
    private var clickActionValue: String? = null

    private var hoverAction: HoverEvent.Action? = null
    private var hoverActionValue: Array<out ICuteText>? = null

    private var cache: TextComponent? = null

    override fun clickAction(action: Action): ICuteText {
        this.clickAction = action
        return this
    }

    override fun clickActionValue(value: String): ICuteText {
        this.clickActionValue = value
        return this
    }

    override fun openUrl(value: String): ICuteText {
        clickAction(Action.OPEN_URL)
        clickActionValue(value)
        return this
    }

    override fun openFile(value: String): ICuteText {
        clickAction(Action.OPEN_FILE)
        clickActionValue(value)
        return this
    }

    override fun runCommand(value: String): ICuteText {
        clickAction(Action.RUN_COMMAND)
        clickActionValue(value)
        return this
    }

    override fun suggestCommand(value: String): ICuteText {
        clickAction(Action.SUGGEST_COMMAND)
        clickActionValue(value)
        return this
    }

    override fun changePage(value: String): ICuteText {
        clickAction(Action.CHANGE_PAGE)
        clickActionValue(value)
        return this
    }

    override fun hoverAction(action: HoverEvent.Action): ICuteText {
        this.hoverAction = action
        return this
    }

    override fun hoverActionValue(vararg cuteText: ICuteText): ICuteText {
        this.hoverActionValue = cuteText
        return this
    }

    override fun showText(vararg cuteText: ICuteText): ICuteText {
        hoverAction(HoverEvent.Action.SHOW_TEXT)
        hoverActionValue(*cuteText)
        return this
    }

    override fun showAchievement(vararg cuteText: ICuteText): ICuteText {
        hoverAction(HoverEvent.Action.SHOW_ACHIEVEMENT)
        hoverActionValue(*cuteText)
        return this
    }

    override fun showItem(vararg cuteText: ICuteText): ICuteText {
        hoverAction(HoverEvent.Action.SHOW_ITEM)
        hoverActionValue(*cuteText)
        return this
    }

    override fun showEntity(vararg cuteText: ICuteText): ICuteText {
        hoverAction(HoverEvent.Action.SHOW_ENTITY)
        hoverActionValue(*cuteText)
        return this
    }

    override fun format(vararg any: Any): CuteText {
        finalString = finalString.format(*any)
        return this
    }

    override fun build(): TextComponent {
        if (cache != null) {
            return cache!!
        }

        finalString = replaceFormatSymbol("&", finalString)
        cache = TextComponent(finalString)

        cache!!.clickEvent = if (clickAction != null && clickActionValue != null) {
            ClickEvent(clickAction, clickActionValue)
        } else {
            null
        }

        cache!!.hoverEvent = if (hoverAction != null && hoverActionValue != null) {
            val result = arrayListOf<TextComponent>()
            hoverActionValue!!.forEach { cuteText ->
                result.add(cuteText.build())
            }

            HoverEvent(hoverAction, result.toTypedArray())
        } else {
            null
        }

        return cache!!
    }

    companion object {
        fun of(raw: String): CuteText = CuteText(raw)

        fun replaceFormatSymbol(formatSymbol: String, string: String): String {
            return string
                .replace(formatSymbol + "0", "§0")
                .replace(formatSymbol + "1", "§1")
                .replace(formatSymbol + "2", "§2")
                .replace(formatSymbol + "3", "§3")
                .replace(formatSymbol + "4", "§4")
                .replace(formatSymbol + "5", "§5")
                .replace(formatSymbol + "6", "§6")
                .replace(formatSymbol + "7", "§7")
                .replace(formatSymbol + "8", "§8")
                .replace(formatSymbol + "9", "§9")
                .replace(formatSymbol + "a", "§a")
                .replace(formatSymbol + "b", "§b")
                .replace(formatSymbol + "c", "§c")
                .replace(formatSymbol + "d", "§d")
                .replace(formatSymbol + "e", "§e")
                .replace(formatSymbol + "f", "§f")
                .replace(formatSymbol + "k", "§k")
                .replace(formatSymbol + "l", "§l")
                .replace(formatSymbol + "m", "§m")
                .replace(formatSymbol + "n", "§n")
                .replace(formatSymbol + "o", "§o")
                .replace(formatSymbol + "r", "§r")
        }
    }

}