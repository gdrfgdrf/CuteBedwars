package io.github.gdrfgdrf.cutebedwars.tasks

import io.github.gdrfgdrf.cutebedwars.tasks.entry.FutureTaskEntry
import io.github.gdrfgdrf.cutebedwars.tasks.entry.TaskEntry

fun runAsyncTask(runnable: () -> Unit) {
    TaskEntry<Any>(runnable)
        .run()
}

fun <T> runSyncTask(lock: Any, supplier: () -> T?) {
    FutureTaskEntry.create(supplier)
        .customLock(lock)
        .run()
}
