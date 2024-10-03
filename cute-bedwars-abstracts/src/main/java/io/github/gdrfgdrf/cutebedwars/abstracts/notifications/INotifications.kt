package io.github.gdrfgdrf.cutebedwars.abstracts.notifications

import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ILocalizationContext
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ILocalizationMessage
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ITranslationAgent
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import org.bukkit.entity.Player

@Service("notifications")
@KotlinSingleton
interface INotifications {
    fun messageAdministrator(messageGetter: ILocalizationContext.() -> Array<ITranslationAgent>)
    fun notifyOffline(player: Player)

    companion object {
        fun instance(): INotifications = Mediator.get(INotifications::class.java)!!
    }
}