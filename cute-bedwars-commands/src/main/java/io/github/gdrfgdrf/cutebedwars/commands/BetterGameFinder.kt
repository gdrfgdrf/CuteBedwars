package io.github.gdrfgdrf.cutebedwars.commands

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IFindStrategy
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IFindType
import io.github.gdrfgdrf.cutebedwars.abstracts.finder.IGameFinder
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.area.IAreaManager
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.game.IGameContext
import org.bukkit.command.CommandSender

object BetterGameFinder {
    fun find(
        sender: CommandSender,
        findType: String,
        areaManager: IAreaManager,
        identifier: String,
    ): IGameContext? {
        var gameContext: IGameContext? = null

        val findResult = IGameFinder.get().find(
            sender,
            IFindType.find(findType),
            areaManager,
            identifier,
            IFindStrategy.valueOf("ONLY_ONE"),
            IFindStrategy.valueOf("NOTICE_WHEN_MULTIPLE_RESULT")
        ) {
            gameContext = it
        }
        if (!findResult.found() ||
            findResult.isStrategyMatched("NOTICE_WHEN_MULTIPLE_RESULT") ||
            gameContext == null) {
            return null
        }
        return gameContext
    }
}