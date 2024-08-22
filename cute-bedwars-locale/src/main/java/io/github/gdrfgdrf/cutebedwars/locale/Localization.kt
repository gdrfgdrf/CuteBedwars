package io.github.gdrfgdrf.cutebedwars.locale

import io.github.gdrfgdrf.cutebedwars.locale.collect.CommonLanguage
import io.github.gdrfgdrf.cuteframework.locale.LanguageString
import net.md_5.bungee.api.chat.BaseComponent
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.ClickEvent.Action
import net.md_5.bungee.api.chat.ComponentBuilder
import net.md_5.bungee.api.chat.HoverEvent
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

fun <R> localizationScope(sender: CommandSender, work: LocalizationContext.() -> R): R {
    val localizationContext = LocalizationContext(sender)
    return work(localizationContext)
}

class LocalizationMessage(
    private var sender: CommandSender,
    private val raw: String
) {
    private var finalString = raw

    private var clickEvent: ClickEvent? = null
    private var hoverEvent: HoverEvent? = null

    fun format(vararg any: Any): LocalizationMessage {
        finalString = finalString.format(*any)
        return this
    }

    fun openUrl(value: String): LocalizationMessage {
        clickEvent(Action.OPEN_URL, value)
        return this
    }

    fun openFile(value: String): LocalizationMessage {
        clickEvent(Action.OPEN_FILE, value)
        return this
    }

    fun runCommand(value: String): LocalizationMessage {
        clickEvent(Action.RUN_COMMAND, value)
        return this
    }

    fun suggestCommand(value: String): LocalizationMessage {
        clickEvent(Action.SUGGEST_COMMAND, value)
        return this
    }

    fun changePage(value: String): LocalizationMessage {
        clickEvent(Action.CHANGE_PAGE, value)
        return this
    }

    private fun clickEvent(action: Action, value: String) {
        this.clickEvent = ClickEvent(action, value)
    }

    fun showText(vararg value: LocalizationMessage): LocalizationMessage {
        hoverEvent(HoverEvent.Action.SHOW_TEXT, *value)
        return this
    }

    fun showAchievement(vararg value: LocalizationMessage): LocalizationMessage {
        hoverEvent(HoverEvent.Action.SHOW_ACHIEVEMENT, *value)
        return this
    }

    fun showItem(vararg value: LocalizationMessage): LocalizationMessage {
        hoverEvent(HoverEvent.Action.SHOW_ITEM, *value)
        return this
    }

    fun showEntity(vararg value: LocalizationMessage): LocalizationMessage {
        hoverEvent(HoverEvent.Action.SHOW_ENTITY, *value)
        return this
    }

    private fun hoverEvent(action: HoverEvent.Action, vararg value: LocalizationMessage) {
        val array = value.map {
            it.build()
        }.toTypedArray()

        this.hoverEvent = HoverEvent(action, array)
    }

    fun build(): BaseComponent {
        return builder().create()[0]
    }

    fun builder(): ComponentBuilder {
        val builder = ComponentBuilder(replaceFormatSymbol("&", finalString))

        clickEvent?.let {
            builder.event(it)
        }
        hoverEvent?.let {
            builder.event(it)
        }

        return builder
    }

    fun send() {
        val message = LocalizationMessage(sender, CommonLanguage.PREFIX.get().string)
        send(message)
    }

    fun send(prefix: String) {
        send(LocalizationMessage(sender, prefix))
    }

    fun send(prefix: LocalizationMessage? = null) {
        if (sender is Player) {
            var finalBuilt: BaseComponent = build()
            if (prefix != null) {
                finalBuilt = prefix.build()
                finalBuilt.addExtra(build())
            }

            sender.spigot().sendMessage(finalBuilt)
            return
        }

        var finalString: String = replaceFormatSymbol("&", this.finalString)
        if (prefix != null) {
            finalString = replaceFormatSymbol("&", prefix.finalString) + finalString
        }

        sender.sendMessage(finalString)
    }

    companion object {
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

class LocalizationContext(val sender: CommandSender) {
    fun message(string: String): LocalizationMessage {
        return LocalizationMessage(sender, string)
    }

    fun message(languageString: LanguageString): LocalizationMessage {
        return LocalizationMessage(sender, languageString.get().string)
    }
}







