package io.github.gdrfgdrf.cutebedwars.abstracts.utils

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.Service

@Service("threads")
@KotlinSingleton
interface IThreads {
    fun sleepSafely(millis: Long)

    companion object {
        fun instance(): IThreads = Mediator.get(IThreads::class.java)!!
    }
}