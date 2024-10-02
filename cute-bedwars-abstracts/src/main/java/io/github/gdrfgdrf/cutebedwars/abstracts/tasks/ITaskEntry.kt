package io.github.gdrfgdrf.cutebedwars.abstracts.tasks

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet

@Service("task_entry", singleton = false)
interface ITaskEntry<T> {
    fun syncLockTimeout(syncLockTimeout: Long): ITaskEntry<T>
    fun sync(sync: Boolean): ITaskEntry<T>
    fun customLock(customLock: Any): ITaskEntry<T>
    fun notifyMethodFinished()
    fun run()

    fun supplier(): () -> T?
    fun customLock(): Any?

    companion object {
        fun <T> new(supplier: () -> T?): ITaskEntry<T> =
            Mediator.get(ITaskEntry::class.java, ArgumentSet(arrayOf(supplier)))!!
    }
}