package io.github.gdrfgdrf.cutebedwars.abstracts.finder

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IFindStrategy
import io.github.gdrfgdrf.multimodulemediator.annotation.Service

@Service("find_result")
interface IFindResult {
    var found: Boolean

    fun addMatchedStrategy(findStrategy: IFindStrategy)
    fun addMatchedStrategy(name: String)

    fun isStrategyMatched(findStrategy: IFindStrategy): Boolean
    fun isStrategyMatched(name: String): Boolean
}