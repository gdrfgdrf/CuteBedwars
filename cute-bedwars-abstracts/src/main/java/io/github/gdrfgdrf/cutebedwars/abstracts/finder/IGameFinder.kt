package io.github.gdrfgdrf.cutebedwars.abstracts.finder

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IFindStrategy
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IFindType
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.area.IAreaManager
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.game.IGameContext
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import org.bukkit.command.CommandSender

@Service("game_finder")
@KotlinSingleton
interface IGameFinder {
    fun find(
        sender: CommandSender,
        findType: IFindType,
        areaManager: IAreaManager,
        identifier: String,
        vararg findStrategy: IFindStrategy,
        onFound: (IGameContext) -> Unit,
    ): IFindResult

    companion object {
        fun instance(): IGameFinder = Mediator.get(IGameFinder::class.java)!!
    }
}