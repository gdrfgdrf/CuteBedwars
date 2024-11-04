package io.github.gdrfgdrf.cutebedwars.commands.finder

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IFindStrategy
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IFindType
import io.github.gdrfgdrf.cutebedwars.abstracts.finder.IAreaFinder
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.area.IAreaManager
import org.bukkit.command.CommandSender

object BetterAreaFinder {
    fun find(sender: CommandSender, findType: IFindType, identifier: String): IAreaManager? {
        var areaManager: IAreaManager? = null

        val findResult = IAreaFinder.instance().find(
            sender,
            findType,
            identifier,
            IFindStrategy.valueOf("ONLY_ONE"),
            IFindStrategy.valueOf("NOTICE_WHEN_MULTIPLE_RESULT")
        ) {
            areaManager = it
        }
        if (!findResult.found() ||
            findResult.isStrategyMatched("NOTICE_WHEN_MULTIPLE_RESULT") ||
            areaManager == null) {
            return null
        }
        return areaManager
    }
}