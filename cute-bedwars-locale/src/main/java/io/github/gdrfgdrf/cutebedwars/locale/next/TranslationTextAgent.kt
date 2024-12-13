package io.github.gdrfgdrf.cutebedwars.locale.next

import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ICuteText
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ITranslationTextAgent
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.HoverEvent.Action
import net.md_5.bungee.api.chat.TextComponent
import kotlin.streams.toList

class TranslationTextAgent(
    override var cuteText: ICuteText
) : ITranslationTextAgent {
    override val enablePart: Boolean
        get() {
            return cuteText.enablePart
        }
    override val string: String = cuteText.string

    private fun clickAction(action: ClickEvent.Action, value: String): ITranslationTextAgent {
        cuteText.clickAction = action
        cuteText.clickActionValue = value
        return this
    }

    override fun rebuildParts() {
        cuteText.rebuildParts()
    }

    override fun openUrl(value: String): ITranslationTextAgent {
        return clickAction(ClickEvent.Action.OPEN_URL, value)
    }

    override fun openFile(value: String): ITranslationTextAgent {
        return clickAction(ClickEvent.Action.OPEN_FILE, value)
    }

    override fun runCommand(value: String): ITranslationTextAgent {
        return clickAction(ClickEvent.Action.RUN_COMMAND, value)
    }

    override fun suggestCommand(value: String): ITranslationTextAgent {
        return clickAction(ClickEvent.Action.SUGGEST_COMMAND, value)
    }

    override fun changePage(value: String): ITranslationTextAgent {
        return clickAction(ClickEvent.Action.CHANGE_PAGE, value)
    }

    private fun hoverAction(action: Action, vararg cuteText: ICuteText): ITranslationTextAgent {
        this.cuteText.hoverAction = action
        this.cuteText.hoverActionValue = cuteText
        return this
    }

    override fun showText(vararg value: String): ITranslationTextAgent {
        return showText(*toCuteTextArray(*value))
    }

    override fun showAchievement(vararg value: String): ITranslationTextAgent {
        return showAchievement(*toCuteTextArray(*value))
    }

    override fun showItem(vararg value: String): ITranslationTextAgent {
        return showItem(*toCuteTextArray(*value))
    }

    override fun showEntity(vararg value: String): ITranslationTextAgent {
        return showEntity(*toCuteTextArray(*value))
    }

    override fun showText(vararg cuteText: ICuteText): ITranslationTextAgent {
        return hoverAction(Action.SHOW_TEXT, *cuteText)
    }

    override fun showAchievement(vararg cuteText: ICuteText): ITranslationTextAgent {
        return hoverAction(Action.SHOW_ACHIEVEMENT, *cuteText)
    }

    override fun showItem(vararg cuteText: ICuteText): ITranslationTextAgent {
        return hoverAction(Action.SHOW_ITEM, *cuteText)
    }

    override fun showEntity(vararg cuteText: ICuteText): ITranslationTextAgent {
        return hoverAction(Action.SHOW_ENTITY, *cuteText)
    }

    private fun clickActionInPart(partIndex: Int, action: ClickEvent.Action, value: String): ITranslationTextAgent {
        cuteText.clickActionInPart(partIndex, action)
        cuteText.clickActionValueInPart(partIndex, value)
        return this
    }

    override fun openUrlInPart(partIndex: Int, value: String): ITranslationTextAgent {
        return clickActionInPart(partIndex, ClickEvent.Action.OPEN_URL, value)
    }

    override fun openFileInPart(partIndex: Int, value: String): ITranslationTextAgent {
        return clickActionInPart(partIndex, ClickEvent.Action.OPEN_FILE, value)
    }

    override fun runCommandInPart(partIndex: Int, value: String): ITranslationTextAgent {
        return clickActionInPart(partIndex, ClickEvent.Action.RUN_COMMAND, value)
    }

    override fun suggestCommandInPart(partIndex: Int, value: String): ITranslationTextAgent {
        return clickActionInPart(partIndex, ClickEvent.Action.SUGGEST_COMMAND, value)
    }

    override fun changePageInPart(partIndex: Int, value: String): ITranslationTextAgent {
        return clickActionInPart(partIndex, ClickEvent.Action.CHANGE_PAGE, value)
    }

    private fun hoverActionInPart(partIndex: Int, action: Action, vararg cuteText: ICuteText): ITranslationTextAgent {
        this.cuteText.hoverActionInPart(partIndex, action)
        this.cuteText.hoverActionValueInPart(partIndex, *cuteText)
        return this
    }

    override fun showTextInPart(partIndex: Int, vararg value: String): ITranslationTextAgent {
        return hoverActionInPart(partIndex, Action.SHOW_TEXT, *toCuteTextArray(*value))
    }

    override fun showAchievementInPart(partIndex: Int, vararg value: String): ITranslationTextAgent {
        return hoverActionInPart(partIndex, Action.SHOW_ACHIEVEMENT, *toCuteTextArray(*value))
    }

    override fun showItem(partIndex: Int, vararg value: String): ITranslationTextAgent {
        return hoverActionInPart(partIndex, Action.SHOW_ITEM, *toCuteTextArray(*value))
    }

    override fun showEntity(partIndex: Int, vararg value: String): ITranslationTextAgent {
        return hoverActionInPart(partIndex, Action.SHOW_ENTITY, *toCuteTextArray(*value))
    }

    override fun showTextInPart(partIndex: Int, vararg cuteText: ICuteText): ITranslationTextAgent {
        return hoverActionInPart(partIndex, Action.SHOW_TEXT, *cuteText)
    }

    override fun showAchievementInPart(partIndex: Int, vararg cuteText: ICuteText): ITranslationTextAgent {
        return hoverActionInPart(partIndex, Action.SHOW_ACHIEVEMENT, *cuteText)
    }

    override fun showItemInPart(partIndex: Int, vararg cuteText: ICuteText): ITranslationTextAgent {
        return hoverActionInPart(partIndex, Action.SHOW_ITEM, *cuteText)
    }

    override fun showEntityInPart(partIndex: Int, vararg cuteText: ICuteText): ITranslationTextAgent {
        return hoverActionInPart(partIndex, Action.SHOW_ENTITY, *cuteText)
    }

    private fun toCuteTextArray(vararg value: String): Array<ICuteText> {
        return value.toList().stream()
            .map {
                CuteText.of(it)
            }
            .toList()
            .toTypedArray()
    }

    override fun format(vararg any: Any): TranslationTextAgent {
        cuteText.format(*any)
        return this
    }

    override fun build(): TextComponent {
        return cuteText.build()
    }

    companion object {
        fun of(raw: String): TranslationTextAgent {
            return TranslationTextAgent(CuteText.of(raw))
        }
    }

}