package io.github.gdrfgdrf.cutebedwars.abstracts.chatpage

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPageRequestTypes
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ILocalizationMessage
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet
import org.bukkit.command.CommandSender

@Service("chat_page", singleton = false)
interface IChatPage {
    fun send(index: Int)
    fun size(): Int
    fun addPage(loader: () -> List<ILocalizationMessage>)
    fun lineCountEveryPages(): Int
    fun lineCountEveryPages(lineCount: Int)
    fun changeable(): Boolean
    fun changeable(changeable: Boolean)

    companion object {
        fun cache(
            sender: CommandSender,
            pageRequestTypes: IPageRequestTypes,
            flagContent: String,
            loader: () -> List<ILocalizationMessage>,
        ): IChatPage = Mediator.get(
            IChatPage::class.java, ArgumentSet(
                arrayOf(sender, pageRequestTypes, flagContent, loader)
            )
        )!!
    }

}