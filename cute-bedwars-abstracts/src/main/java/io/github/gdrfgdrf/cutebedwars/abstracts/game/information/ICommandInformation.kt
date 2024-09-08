package io.github.gdrfgdrf.cutebedwars.abstracts.game.information

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ILocalizationMessage
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import org.bukkit.command.CommandSender

@Service("command_information")
@KotlinSingleton
interface ICommandInformation {
    fun convert(sender: CommandSender, command: ICommands): List<ILocalizationMessage>

    companion object {
        fun get(): ICommandInformation = Mediator.get(ICommandInformation::class.java)!!
    }
}