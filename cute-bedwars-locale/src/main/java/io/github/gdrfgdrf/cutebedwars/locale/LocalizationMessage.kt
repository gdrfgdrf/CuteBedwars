package io.github.gdrfgdrf.cutebedwars.locale

import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ILocalizationMessage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommonLanguage
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet
import net.md_5.bungee.api.chat.BaseComponent
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.ComponentBuilder
import net.md_5.bungee.api.chat.HoverEvent
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

@ServiceImpl("localization_message")
class LocalizationMessage(
    argumentSet: ArgumentSet,
) : ILocalizationMessage {
    private var sender = argumentSet.args[0] as CommandSender
    private var finalString = argumentSet.args[1] as String

    private var clickEvent: ClickEvent? = null
    private var hoverEvent: HoverEvent? = null

    init {
        finalString = argumentSet.args[1] as String
    }

    constructor(sender: CommandSender, raw: String) :
            this(ArgumentSet(arrayOf(sender, raw)))

    override fun format(vararg any: Any): LocalizationMessage {
        finalString = finalString.format(*any)
        return this
    }

    override fun openUrl(value: String): LocalizationMessage {
        clickEvent(ClickEvent.Action.OPEN_URL, value)
        return this
    }

    override fun openFile(value: String): LocalizationMessage {
        clickEvent(ClickEvent.Action.OPEN_FILE, value)
        return this
    }

    override fun runCommand(value: String): LocalizationMessage {
        clickEvent(ClickEvent.Action.RUN_COMMAND, value)
        return this
    }

    override fun suggestCommand(value: String): LocalizationMessage {
        clickEvent(ClickEvent.Action.SUGGEST_COMMAND, value)
        return this
    }

    override fun changePage(value: String): LocalizationMessage {
        clickEvent(ClickEvent.Action.CHANGE_PAGE, value)
        return this
    }

    private fun clickEvent(action: ClickEvent.Action, value: String) {
        this.clickEvent = ClickEvent(action, value)
    }

    override fun showText(vararg value: ILocalizationMessage): LocalizationMessage {
        hoverEvent(HoverEvent.Action.SHOW_TEXT, *value)
        return this
    }

    override fun showAchievement(vararg value: ILocalizationMessage): LocalizationMessage {
        hoverEvent(HoverEvent.Action.SHOW_ACHIEVEMENT, *value)
        return this
    }

    override fun showItem(vararg value: ILocalizationMessage): LocalizationMessage {
        hoverEvent(HoverEvent.Action.SHOW_ITEM, *value)
        return this
    }

    override fun showEntity(vararg value: ILocalizationMessage): LocalizationMessage {
        hoverEvent(HoverEvent.Action.SHOW_ENTITY, *value)
        return this
    }

    private fun hoverEvent(action: HoverEvent.Action, vararg value: ILocalizationMessage) {
        val array = value.map {
            it.build()
        }.toTypedArray()

        this.hoverEvent = HoverEvent(action, array)
    }

    override fun build(): BaseComponent {
        return builder().create()[0]
    }

    override fun builder(): ComponentBuilder {
        val builder = ComponentBuilder(replaceFormatSymbol("&", finalString))

        clickEvent?.let {
            builder.event(it)
        }
        hoverEvent?.let {
            builder.event(it)
        }

        return builder
    }

    override fun send() {
        val message = LocalizationMessage(sender, io.github.gdrfgdrf.cutebedwars.languages.collect.CommonLanguage.PREFIX.get().string)
        send(message)
    }

    override fun send(prefix: String) {
        send(LocalizationMessage(sender, prefix))
    }

    override fun send(prefix: ILocalizationMessage?) {
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
            finalString = replaceFormatSymbol("&", (prefix as LocalizationMessage).finalString) + finalString
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