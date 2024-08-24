package io.github.gdrfgdrf.cutebedwars.tasks.entry

import io.github.gdrfgdrf.cutebedwars.abstracts.tasks.IFutureTaskEntry
import io.github.gdrfgdrf.cutebedwars.tasks.SyncFuture
import io.github.gdrfgdrf.cutebedwars.tasks.TaskManager
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet
import java.util.concurrent.TimeUnit

@ServiceImpl("future_task_entry", needArgument = true, instanceGetter = "create")
class FutureTaskEntry<T> private constructor(
    argumentSet: ArgumentSet,
): TaskEntry<T>(
    argumentSet
), IFutureTaskEntry<T> {
    private constructor(supplier: () -> T?): this(ArgumentSet(arrayOf(supplier)))

    private val syncFuture = SyncFuture<T>()

    override fun syncLockTimeout(syncLockTimeout: Long): TaskEntry<T> {
        throw UnsupportedOperationException()
    }

    override fun sync(sync: Boolean): TaskEntry<T> {
        throw UnsupportedOperationException()
    }

    override fun notifyMethodFinished() {
        throw UnsupportedOperationException()
    }

    @Suppress("unchecked_cast")
    override fun result(result: Any?) {
        syncFuture.result(result as T)
    }

    override fun get(): T? {
        return syncFuture.getSafety()
    }

    override fun get(timeout: Long, unit: TimeUnit): T? {
        return syncFuture.getSafety(timeout, unit)
    }

    override fun customLock(customLock: Any): FutureTaskEntry<T> {
        super.customLock(customLock)
        return this
    }

    override fun run() {
        TaskManager.add(this)
    }

    companion object {
        fun <T> create(supplier: () -> T?) = FutureTaskEntry(supplier)
        fun <T> create(argumentSet: ArgumentSet) = create(argumentSet.args[0] as (() -> T?))
    }
}