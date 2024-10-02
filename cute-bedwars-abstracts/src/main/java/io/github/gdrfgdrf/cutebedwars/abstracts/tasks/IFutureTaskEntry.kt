package io.github.gdrfgdrf.cutebedwars.abstracts.tasks

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet
import java.util.concurrent.TimeUnit

@Service("future_task_entry", singleton = false)
interface IFutureTaskEntry<T> : ITaskEntry<T> {
    fun result(result: Any?)
    fun get(): T?
    fun get(timeout: Long, unit: TimeUnit): T?

    companion object {
        fun <T> new(supplier: () -> T?): IFutureTaskEntry<T> = Mediator.get(
            IFutureTaskEntry::class.java, ArgumentSet(
                arrayOf(supplier)
            )
        )!!
    }
}