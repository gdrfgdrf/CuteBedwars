package io.github.gdrfgdrf.cutebedwars.tasks.entry

import io.github.gdrfgdrf.cutebedwars.abstracts.tasks.ITaskEntry
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logError
import io.github.gdrfgdrf.cutebedwars.tasks.Tasks
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet
import java.util.concurrent.CountDownLatch

@Suppress("UNCHECKED_CAST")
@ServiceImpl("task_entry", needArgument = true, instanceGetter = "create")
open class TaskEntry<T> protected constructor(
    argumentSet: ArgumentSet,
): ITaskEntry<T> {
    private val supplier: () -> T? = argumentSet.args[0] as (() -> T?)

    private var customLock: Any? = null
    private var syncLock: CountDownLatch = CountDownLatch(1)
    private var syncLockTimeout: Long = 0
    private var enableSync = false

    protected constructor(runnable: Runnable): this({
        runnable.run()
        null
    })

    protected constructor(supplier: () -> T?): this(ArgumentSet(arrayOf(supplier)))

    override fun syncLockTimeout(syncLockTimeout: Long): ITaskEntry<T> {
        this.syncLockTimeout = syncLockTimeout
        return this
    }

    override fun sync(sync: Boolean): ITaskEntry<T> {
        this.enableSync = sync
        return this
    }

    override fun customLock(customLock: Any): ITaskEntry<T> {
        this.customLock = customLock
        return this
    }

    override fun notifyMethodFinished() {
        if (this.enableSync) {
            syncLock.countDown()
        }
    }

    override fun run() {
        Tasks.add(this)
        if (enableSync) {
            runCatching {
                syncLock.await()
            }.onFailure {
                "An error occurred while the method was executing".logError(it)
            }
        }
    }

    override fun supplier(): () -> T? = supplier
    override fun customLock(): Any? = customLock

    companion object {
        fun <T> create(runnable: Runnable) = TaskEntry<T>(runnable)
        fun <T> create(supplier: () -> T?) = TaskEntry(supplier)
        @JvmStatic
        fun <T> create(argumentSet: ArgumentSet) = TaskEntry(argumentSet.args[0] as (() -> T?))
    }
}