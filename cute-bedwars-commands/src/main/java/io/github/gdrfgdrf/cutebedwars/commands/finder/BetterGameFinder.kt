package io.github.gdrfgdrf.cutebedwars.commands.finder

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IFindStrategy
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IFindType
import io.github.gdrfgdrf.cutebedwars.abstracts.finder.IGameFinder
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.area.IAreaManager
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.game.IGameContext
import org.bukkit.command.CommandSender

object BetterGameFinder {
    fun find(sender: CommandSender, findType: IFindType, areaManager: IAreaManager, identifier: String): IGameContext? {
        var gameContext: IGameContext? = null

        val findResult = IGameFinder.instance().find(
            sender,
            findType,
            areaManager,
            identifier,
            IFindStrategy.valueOf("ONLY_ONE"),
            IFindStrategy.valueOf("NOTICE_WHEN_MULTIPLE_RESULT")
        ) {
            gameContext = it
        }
        if (!findResult.found ||
            findResult.isStrategyMatched("NOTICE_WHEN_MULTIPLE_RESULT") ||
            gameContext == null) {
            return null
        }
        return gameContext
    }

    fun multipleResult(
        sender: CommandSender,
        findType: IFindType,
        areaManager: IAreaManager,
        identifier: String
    ): List<IGameContext>? {
        val gameContexts = arrayListOf<IGameContext>()

        val findResult = IGameFinder.instance().find(
            sender,
            findType,
            areaManager,
            identifier,
        ) {
            gameContexts.add(it)
        }
        if (!findResult.found || gameContexts.isEmpty()) {
            return null
        }
        return gameContexts
    }
}