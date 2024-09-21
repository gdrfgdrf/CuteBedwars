package io.github.gdrfgdrf.cutebedwars.abstracts.information

import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.game.IGameContext
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ILocalizationMessage
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import org.bukkit.command.CommandSender

@Service("game_information")
@KotlinSingleton
interface IGameInformation {
    fun convert(sender: CommandSender, gameContext: IGameContext): List<ILocalizationMessage>

    companion object {
        fun get(): IGameInformation = Mediator.get(IGameInformation::class.java)!!
    }
}