package io.github.gdrfgdrf.cutebedwars.abstracts.chatpage

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPageRequestTypes
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ITranslationAgent
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet
import org.bukkit.command.CommandSender

@Service("chat_pages")
@KotlinSingleton
interface IChatPages {
    fun initialize()
    fun disable()

    fun cache(
        sender: CommandSender,
        pageRequestTypes: IPageRequestTypes,
        flagContent: String,
        loader: () -> List<ITranslationAgent>,
    ): IChatPage

    companion object {
        fun instance(): IChatPages = Mediator.get(IChatPages::class.java)!!
    }
}