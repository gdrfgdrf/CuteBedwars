package io.github.gdrfgdrf.cutebedwars.finder.result

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IFindStrategy
import io.github.gdrfgdrf.cutebedwars.abstracts.finder.IFindResult
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl

@ServiceImpl("find_result")
class FindResult : IFindResult {
    private var found: Boolean = false
    private val matchedStrategy = mutableSetOf<IFindStrategy>()

    override fun found(): Boolean = found

    override fun found(found: Boolean) {
        this.found = found
    }

    override fun addMatchedStrategy(findStrategy: IFindStrategy) {
        this.matchedStrategy.add(findStrategy)
    }
    override fun addMatchedStrategy(name: String) {
        this.matchedStrategy.add(IFindStrategy.valueOf(name))
    }

    override fun isStrategyMatched(findStrategy: IFindStrategy) = matchedStrategy.contains(findStrategy)
    override fun isStrategyMatched(name: String) = matchedStrategy.contains(IFindStrategy.valueOf(name))
}