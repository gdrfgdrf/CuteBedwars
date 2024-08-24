package io.github.gdrfgdrf.cutebedwars.tasks.entry

import io.github.gdrfgdrf.cutebedwars.utils.extension.logError
import io.github.gdrfgdrf.cutebedwars.tasks.TaskManager
import java.util.concurrent.CountDownLatch

open class TaskEntry<T> protected constructor(
    val supplier: () -> T?,
) {
    var customLock: Any? = null
    private var syncLock: CountDownLatch = CountDownLatch(1)
    private var syncLockTimeout: Long = 0
    private var enableSync = false

    constructor(runnable: Runnable): this({
        runnable.run()
        null
    })

    open fun syncLockTimeout(syncLockTimeout: Long): TaskEntry<T> {
        this.syncLockTimeout = syncLockTimeout
        return this
    }

    open fun sync(sync: Boolean): TaskEntry<T> {
        this.enableSync = sync
        return this
    }

    open fun customLock(customLock: Any): TaskEntry<T> {
        this.customLock = customLock
        return this
    }

    open fun run() {
        TaskManager.add(this)
        if (enableSync) {
            runCatching {
                syncLock.await()
            }.onFailure {
                "An error occurred while the method was executing".logError(it)
            }
        }
    }

    open fun notifyMethodFinished() {
        if (this.enableSync) {
            syncLock.countDown()
        }
    }

    companion object {
        fun <T> create(runnable: Runnable) = TaskEntry<T>(runnable)
        fun <T> create(supplier: () -> T) = TaskEntry<T>(supplier)
    }
}