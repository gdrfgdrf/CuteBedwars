package io.github.gdrfgdrf.cutebedwars.abstracts.finder

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IFindStrategy
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IFindType
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.area.IAreaManager
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import org.bukkit.command.CommandSender

@Service("area_finder")
@KotlinSingleton
interface IAreaFinder {
    fun find(
        sender: CommandSender,
        findType: IFindType,
        identifier: String,
        vararg findStrategy: IFindStrategy,
        onFound: (IAreaManager) -> Unit,
    ): IFindResult

    companion object {
        fun instance(): IAreaFinder = Mediator.get(IAreaFinder::class.java)!!
    }
}