package io.github.gdrfgdrf.cutebedwars.utils.abstracts

import io.github.gdrfgdrf.cutebedwars.abstracts.utils.IThreads
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl

@ServiceImpl("threads")
object Threads : IThreads {
    override fun sleepSafely(
        millis: Long
    ) {
        runCatching {
            Thread.sleep(millis)
        }.onFailure {

        }
    }
}