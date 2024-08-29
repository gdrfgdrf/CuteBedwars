package io.github.gdrfgdrf.cutebedwars.abstracts.game.information

import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.area.IAreaManager
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ILocalizationMessage
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import org.bukkit.command.CommandSender

@Service("area_information")
@KotlinSingleton
interface IAreaInformation {
    fun convert(sender: CommandSender, areaManager: IAreaManager): List<ILocalizationMessage>

    companion object {
        fun get(): IAreaInformation = Mediator.get(IAreaInformation::class.java)!!
    }
}