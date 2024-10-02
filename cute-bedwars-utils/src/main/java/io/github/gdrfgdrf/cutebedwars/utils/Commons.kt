package io.github.gdrfgdrf.cutebedwars.utils

import io.github.gdrfgdrf.cutebedwars.abstracts.tasks.IFutureTaskEntry
import io.github.gdrfgdrf.cutebedwars.abstracts.tasks.ITaskEntry
import io.github.gdrfgdrf.cutebedwars.holders.javaPluginHolder
import org.bukkit.plugin.java.JavaPlugin
import java.util.logging.Logger

fun logger(): Logger {
    return (javaPluginHolder().get(javaPluginHolder().JAVA_PLUGIN_NAME) as JavaPlugin).logger
}

fun runAsyncTask(runnable: () -> Unit) {
    ITaskEntry.new<Any>(runnable)
        .run()
}

fun <T> runSyncTask(lock: Any, supplier: () -> T?) {
    IFutureTaskEntry.new(supplier)
        .customLock(lock)
        .run()
}