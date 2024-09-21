package io.github.gdrfgdrf.cutebedwars.finder

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IFindStrategy
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IFindType
import io.github.gdrfgdrf.cutebedwars.abstracts.finder.IAreaFinder
import io.github.gdrfgdrf.cutebedwars.abstracts.finder.IFindResult
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.IManagers
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.area.IAreaManager
import io.github.gdrfgdrf.cutebedwars.finder.result.FindResult
import io.github.gdrfgdrf.cutebedwars.languages.collect.AreaManagementLanguage
import io.github.gdrfgdrf.cutebedwars.locale.localizationScope
import io.github.gdrfgdrf.cutebedwars.utils.extension.isLong
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import org.bukkit.command.CommandSender

@ServiceImpl("area_finder")
object AreaFinder : IAreaFinder {
    override fun find(
        sender: CommandSender,
        findType: IFindType,
        identifier: String,
        vararg findStrategy: IFindStrategy,
        onFound: (IAreaManager) -> Unit,
    ): IFindResult {
        val findResult = FindResult()
        var noticeWhenMultipleResult = false

        if (findType == IFindType.valueOf("BY_ID")) {
            if (!identifier.isLong()) {
                return findResult
            }

            IManagers.get().get(identifier.toLong())?.let {
                findResult.found(true)
                onFound(it)
            }
        }
        if (findType == IFindType.valueOf("BY_NAME")) {
            IManagers.get().get(identifier)?.forEach {
                if (findResult.found() && findStrategy.contains(IFindStrategy.valueOf("NOTICE_WHEN_MULTIPLE_RESULT"))) {
                    findResult.addMatchedStrategy("NOTICE_WHEN_MULTIPLE_RESULT")

                    if (!noticeWhenMultipleResult) {
                        noticeWhenMultipleResult = true

                        localizationScope(sender) {
                            message(AreaManagementLanguage.DUPLICATE_AREA_NAME_ERROR)
                                .format(identifier)
                                .send()
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
        }

        if (!findResult.found()) {
            localizationScope(sender) {
                val message = if (findType == IFindType.valueOf("BY_ID")) {
                    message(AreaManagementLanguage.NOT_FOUND_AREA_BY_ID)
                        .format(identifier)
                } else {
                    message(AreaManagementLanguage.NOT_FOUND_AREA_BY_NAME)
                        .format(identifier)
                }

                message.send()
            }
        }

        return findResult
    }
}