package io.github.gdrfgdrf.cutebedwars.locale.next

import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ICuteText
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ITranslationTextAgent
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.HoverEvent.Action
import net.md_5.bungee.api.chat.TextComponent
import kotlin.streams.toList

class TranslationTextAgent(
    private var cuteText: ICuteText
) : ITranslationTextAgent {
    private fun clickAction(action: ClickEvent.Action, value: String): TranslationTextAgent {
        cuteText.clickAction(action)
        cuteText.clickActionValue(value)
        return this
    }

    override fun cuteText(): ICuteText = cuteText

    override fun openUrl(value: String): TranslationTextAgent {
        return clickAction(ClickEvent.Action.OPEN_URL, value)
    }

    override fun openFile(value: String): TranslationTextAgent {
        return clickAction(ClickEvent.Action.OPEN_FILE, value)
    }

    override fun runCommand(value: String): TranslationTextAgent {
        return clickAction(ClickEvent.Action.RUN_COMMAND, value)
    }

    override fun suggestCommand(value: String): TranslationTextAgent {
        return clickAction(ClickEvent.Action.SUGGEST_COMMAND, value)
    }

    override fun changePage(value: String): TranslationTextAgent {
        return clickAction(ClickEvent.Action.CHANGE_PAGE, value)
    }

    private fun hoverAction(action: Action, vararg cuteText: ICuteText): TranslationTextAgent {
        this.cuteText.hoverAction(action)
        this.cuteText.hoverActionValue(*cuteText)
        return this
    }

    override fun showText(vararg value: String): ITranslationTextAgent {
        val cuteText = value.toList().stream()
            .map {
                CuteText.of(it)
            }
            .toList()
            .toTypedArray()
        return showText(*cuteText)
    }

    override fun showAchievement(vararg value: String): ITranslationTextAgent {
        val cuteText = value.toList().stream()
            .map {
                CuteText.of(it)
            }
            .toList()
            .toTypedArray()
        return showAchievement(*cuteText)
    }

    override fun showItem(vararg value: String): ITranslationTextAgent {
        val cuteText = value.toList().stream()
            .map {
                CuteText.of(it)
            }
            .toList()
            .toTypedArray()
        return showItem(*cuteText)
    }

    override fun showEntity(vararg value: String): ITranslationTextAgent {
        val cuteText = value.toList().stream()
            .map {
                CuteText.of(it)
            }
            .toList()
            .toTypedArray()
        return showEntity(*cuteText)
    }

    override fun showText(vararg cuteText: ICuteText): TranslationTextAgent {
        return hoverAction(Action.SHOW_TEXT, *cuteText)
    }

    override fun showAchievement(vararg cuteText: ICuteText): TranslationTextAgent {
        return hoverAction(Action.SHOW_ACHIEVEMENT, *cuteText)
    }

    override fun showItem(vararg cuteText: ICuteText): TranslationTextAgent {
        return hoverAction(Action.SHOW_ITEM, *cuteText)
    }

    override fun showEntity(vararg cuteText: ICuteText): TranslationTextAgent {
        return hoverAction(Action.SHOW_ENTITY, *cuteText)
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