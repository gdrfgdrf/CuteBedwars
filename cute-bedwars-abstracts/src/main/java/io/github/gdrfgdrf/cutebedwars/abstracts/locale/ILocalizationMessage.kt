package io.github.gdrfgdrf.cutebedwars.abstracts.locale

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet
import net.md_5.bungee.api.chat.BaseComponent
import net.md_5.bungee.api.chat.ComponentBuilder
import org.bukkit.command.CommandSender

@Service("localization_message", singleton = false)
interface ILocalizationMessage {
    fun format(vararg any: Any): ILocalizationMessage
    fun openUrl(value: String): ILocalizationMessage
    fun openFile(value: String): ILocalizationMessage
    fun runCommand(value: String): ILocalizationMessage
    fun suggestCommand(value: String): ILocalizationMessage
    fun changePage(value: String): ILocalizationMessage
    fun showText(vararg value: ILocalizationMessage): ILocalizationMessage
    fun showAchievement(vararg value: ILocalizationMessage): ILocalizationMessage
    fun showItem(vararg value: ILocalizationMessage): ILocalizationMessage
    fun showEntity(vararg value: ILocalizationMessage): ILocalizationMessage
    fun build(): BaseComponent
    fun builder(): ComponentBuilder
    fun send()
    fun send(prefix: String)
    fun send(prefix: ILocalizationMessage? = null)

    companion object {
        fun new(sender: CommandSender, raw: String): ILocalizationMessage = Mediator.get(
            ILocalizationMessage::class.java, ArgumentSet(
                arrayOf(sender, raw)
            )
        )!!
    }
}