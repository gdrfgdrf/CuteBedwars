package io.github.gdrfgdrf.cutebedwars.utils

import io.github.gdrfgdrf.cutebedwars.abstracts.editing.IChangeTypeRegistry
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.IChanges
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.ICommit
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.change.AbstractChange
import io.github.gdrfgdrf.cutebedwars.abstracts.tasks.IFutureTaskEntry
import io.github.gdrfgdrf.cutebedwars.abstracts.tasks.ITaskEntry
import io.github.gdrfgdrf.cutebedwars.abstracts.tasks.ITaskManager
import io.github.gdrfgdrf.cutebedwars.holders.javaPluginHolder
import io.github.gdrfgdrf.cutebedwars.protobuf.storage.StorageProto.Change
import io.github.gdrfgdrf.cutebedwars.protobuf.storage.StorageProto.Commit
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.Registry
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

fun Change.toKotlinChange(): AbstractChange<*> {
    val changeClassHolder = IChangeTypeRegistry.instance().get(type)
        ?: throw IllegalArgumentException("Cannot find change class holder by type $type")

    val args = argsList.toTypedArray()
    if (!changeClassHolder.validateArgsLength(true, *args)) {
        throw IllegalArgumentException("The provided length does not match the expected length")
    }

    return changeClassHolder.create(*args)
}

fun Commit.toKotlinCommit(): ICommit<*> {
    val changes = IChanges.new<Any>()
    changesList.forEach {
        if (!changes.tryAdd(it.toKotlinChange())) {
            throw IllegalArgumentException("Change ${it.type} (${it.name}) cannot be added to the change list")
        }
    }

    val kotlinCommit = changes.finish()
    kotlinCommit.id(id.toLongOrNull() ?: throw IllegalArgumentException("id is not a long"))
    kotlinCommit.time(time)
    kotlinCommit.submitter(submitter)
    kotlinCommit.message(message)

    return kotlinCommit
}