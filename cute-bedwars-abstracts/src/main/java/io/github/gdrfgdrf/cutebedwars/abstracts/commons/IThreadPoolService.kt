package io.github.gdrfgdrf.cutebedwars.abstracts.commons

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.Service

@Service("thread_pool")
@KotlinSingleton
interface IThreadPoolService {
    fun newTask(runnable: () -> Unit)
    fun newTask(runnable: Runnable)
    fun terminate()

    companion object {
        fun get(): IThreadPoolService = Mediator.get(IThreadPoolService::class.java)!!
    }
}