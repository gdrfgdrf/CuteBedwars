package io.github.gdrfgdrf.cutebedwars.locale.next

import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ICuteText
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

    private var parts = arrayListOf<String>()

    private val clickActionInPart: Array<Action?>?
    private var clickActionValueInPart: Array<String?>?

    private var hoverActionInPart: Array<HoverEvent.Action?>?
    private var hoverActionValueInPart: Array<Array<out ICuteText>?>?

    private var cache: TextComponent? = null
    private val enablePart: Boolean

    init {
        enablePart = countMatches(finalString, "&|") > 0
        if (enablePart) {
            val split = finalString.split("&|")
            parts.addAll(split)

            clickActionInPart = arrayOfNulls(parts.size)
            clickActionValueInPart = arrayOfNulls(parts.size)
            hoverActionInPart = arrayOfNulls(parts.size)
            hoverActionValueInPart = arrayOfNulls(parts.size)
        } else {
            clickActionInPart = null
            clickActionValueInPart = null
            hoverActionInPart = null
            hoverActionValueInPart = null
        }
    }

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

    private fun partCheck() {
        if (clickActionInPart == null ||
            clickActionValueInPart == null ||
            hoverActionInPart == null ||
            hoverActionValueInPart == null) {
            throw IllegalStateException()
        }
    }

    override fun clickActionInPart(partIndex: Int, action: Action): ICuteText {
        partCheck()
        clickActionInPart?.set(partIndex, action)
        return this
    }

    override fun clickActionValueInPart(partIndex: Int, value: String): ICuteText {
        partCheck()
        clickActionValueInPart?.set(partIndex, value)
        return this
    }

    override fun openUrlInPart(partIndex: Int, value: String): ICuteText {
        clickActionInPart(partIndex, Action.OPEN_URL)
        clickActionValueInPart(partIndex, value)
        return this
    }

    override fun openFileInPart(partIndex: Int, value: String): ICuteText {
        clickActionInPart(partIndex, Action.OPEN_FILE)
        clickActionValueInPart(partIndex, value)
        return this
    }

    override fun runCommandInPart(partIndex: Int, value: String): ICuteText {
        clickActionInPart(partIndex, Action.RUN_COMMAND)
        clickActionValueInPart(partIndex, value)
        return this
    }

    override fun suggestCommandInPart(partIndex: Int, value: String): ICuteText {
        clickActionInPart(partIndex, Action.SUGGEST_COMMAND)
        clickActionValueInPart(partIndex, value)
        return this
    }

    override fun changePageInPart(partIndex: Int, value: String): ICuteText {
        clickActionInPart(partIndex, Action.CHANGE_PAGE)
        clickActionValueInPart(partIndex, value)
        return this
    }

    override fun hoverActionInPart(partIndex: Int, action: HoverEvent.Action): ICuteText {
        partCheck()
        hoverActionInPart?.set(partIndex, action)
        return this
    }

    override fun hoverActionValueInPart(partIndex: Int, vararg cuteText: ICuteText): ICuteText {
        partCheck()
        hoverActionValueInPart?.set(partIndex, cuteText)
        return this
    }

    override fun showTextInPart(partIndex: Int, vararg cuteText: ICuteText): ICuteText {
        hoverActionInPart(partIndex, HoverEvent.Action.SHOW_TEXT)
        hoverActionValueInPart(partIndex, *cuteText)
        return this
    }

    override fun showAchievementInPart(partIndex: Int, vararg cuteText: ICuteText): ICuteText {
        hoverActionInPart(partIndex, HoverEvent.Action.SHOW_ACHIEVEMENT)
        hoverActionValueInPart(partIndex, *cuteText)
        return this
    }

    override fun showItemInPart(partIndex: Int, vararg cuteText: ICuteText): ICuteText {
        hoverActionInPart(partIndex, HoverEvent.Action.SHOW_ITEM)
        hoverActionValueInPart(partIndex, *cuteText)
        return this
    }

    override fun showEntityInPart(partIndex: Int, vararg cuteText: ICuteText): ICuteText {
        hoverActionInPart(partIndex, HoverEvent.Action.SHOW_ENTITY)
        hoverActionValueInPart(partIndex, *cuteText)
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
        if (enablePart &&
            clickActionInPart != null &&
            clickActionValueInPart != null &&
            hoverActionInPart != null &&
            hoverActionValueInPart != null) {

            cache = TextComponent("")
            parts.forEachIndexed { index, part ->
                val replaced = TextComponent(replaceFormatSymbol("&", part))

                val clickAction = clickActionInPart[index]
                val clickActionValue = clickActionValueInPart!![index]
                if (clickAction != null && clickActionValue != null) {
                    replaced.clickEvent = ClickEvent(clickAction, clickActionValue)
                }

                val hoverAction = hoverActionInPart!![index]
                val hoverActionValue = hoverActionValueInPart!![index]
                if (hoverAction != null && hoverActionValue != null) {
                    val result = arrayListOf<TextComponent>()
                    hoverActionValue.forEach { cuteText ->
                        result.add(cuteText.build())
                    }

                    replaced.hoverEvent = HoverEvent(hoverAction, result.toTypedArray())
                }

                cache!!.addExtra(replaced)
            }

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

    override fun string(): String {
        return finalString
    }

    private fun countMatches(string: String, pattern: String): Int {
        return string.split(pattern)
            .dropLastWhile { it.isEmpty() }
            .toTypedArray().size - 1
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