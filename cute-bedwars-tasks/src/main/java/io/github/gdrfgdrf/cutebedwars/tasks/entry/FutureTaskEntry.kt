package io.github.gdrfgdrf.cutebedwars.tasks.entry

import io.github.gdrfgdrf.cutebedwars.tasks.SyncFuture
import io.github.gdrfgdrf.cutebedwars.tasks.TaskManager
import java.util.concurrent.TimeUnit

class FutureTaskEntry<T> private constructor(
    supplier: () -> T,
): TaskEntry<T>(
    supplier
) {
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
    internal fun result(result: Any?) {
        syncFuture.result(result as T)
    }

    fun get(): T? {
        return syncFuture.getSafety()
    }

    fun get(timeout: Long, unit: TimeUnit): T? {
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
        fun <T> create(supplier: () -> T): FutureTaskEntry<T> = FutureTaskEntry(supplier)
    }
}