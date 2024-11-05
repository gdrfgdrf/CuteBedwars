package io.github.gdrfgdrf.cutebedwars.finder

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IFindStrategy
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IFindType
import io.github.gdrfgdrf.cutebedwars.abstracts.finder.IFindResult
import io.github.gdrfgdrf.cutebedwars.abstracts.finder.IGameFinder
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.area.IAreaManager
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.game.IGameContext
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.isLong
import io.github.gdrfgdrf.cutebedwars.finder.result.FindResult
import io.github.gdrfgdrf.cutebedwars.languages.collect.AreaManagementLanguage
import io.github.gdrfgdrf.cutebedwars.locale.localizationScope
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import org.bukkit.command.CommandSender
import java.util.regex.Pattern

@ServiceImpl("game_finder")
object GameFinder : IGameFinder{
    override fun find(
        sender: CommandSender,
        findType: IFindType,
        areaManager: IAreaManager,
        identifier: String,
        vararg findStrategy: IFindStrategy,
        onFound: (IGameContext) -> Unit
    ): IFindResult {
        val context = areaManager.context()
        val findResult = FindResult()
        var noticeWhenMultipleResult = false

        if (findType == IFindType.valueOf("BY_ID")) {
            if (!identifier.isLong()) {
                return findResult
            }
        }

        val pattern = Pattern.compile(identifier, Pattern.CASE_INSENSITIVE)
        val list = context.games().stream()
            .filter {
                val matcher = if (findType == IFindType.valueOf("BY_ID")) {
                    pattern.matcher(it.game().id.toString())
                } else {
                    pattern.matcher(it.game().name)
                }
                return@filter matcher.find()
            }
            .toList()

        list?.forEach {
            if (findResult.found() && findStrategy.contains(IFindStrategy.valueOf("NOTICE_WHEN_MULTIPLE_RESULT"))) {
                findResult.addMatchedStrategy("NOTICE_WHEN_MULTIPLE_RESULT")

                if (!noticeWhenMultipleResult) {
                    noticeWhenMultipleResult = true

                    if (findType == IFindType.valueOf("BY_ID")) {
                        localizationScope(sender) {
                            message(AreaManagementLanguage.DUPLICATE_GAME_NAME_ERROR_FIND_BY_ID)
                                .format0(areaManager.area().name, identifier)
                                .send()
                        }
                    } else {
                        localizationScope(sender) {
                            message(AreaManagementLanguage.DUPLICATE_GAME_NAME_ERROR_FIND_BY_NAME)
                                .format0(areaManager.area().name, identifier)
                                .send()
                        }
                    }
                }
            }
            if (findResult.found() && findStrategy.contains(IFindStrategy.valueOf("ONLY_ONE"))) {
                findResult.addMatchedStrategy("ONLY_ONE")
                return@forEach
            }

            findResult.found(true)
            onFound(it)
        }

        if (!findResult.found()) {
            localizationScope(sender) {
                val areaName = areaManager.area().name

                val message = if (findType == IFindType.valueOf("BY_ID")) {
                    message(AreaManagementLanguage.NOT_FOUND_GAME_BY_ID)
                        .format0(areaName, identifier)
                } else {
                    message(AreaManagementLanguage.NOT_FOUND_GAME_BY_NAME)
                        .format0(areaName, identifier)
                }

                message.send()
            }
        }

        return findResult
    }

}