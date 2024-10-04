package io.github.gdrfgdrf.cutebedwars.abstracts.utils

import io.github.gdrfgdrf.cutebedwars.abstracts.core.IPlugin
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.ICommit
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.change.AbstractChange
import io.github.gdrfgdrf.cutebedwars.protobuf.storage.StorageProto.Change
import io.github.gdrfgdrf.cutebedwars.protobuf.storage.StorageProto.Commit
import java.util.logging.Logger

fun String.logInfo() {
    ILogs.instance().info(this)
}

fun String.logWarn() {
    ILogs.instance().warn(this)
}

fun String.logError(e: Throwable) {
    ILogs.instance().error(this, e)
}

fun String.isLong(): Boolean {
    return IConvertors.instance().isLong(this)
}

fun String.isInt(): Boolean {
    return IConvertors.instance().isInt(this)
}

fun String.toBooleanOrNull(): Boolean? {
    return IConvertors.instance().toBooleanOrNull(this)
}

fun String.toIntOrDefault(defaultValue: Int): Int {
    return IConvertors.instance().toIntOrDefault(this, defaultValue)
}

fun runAsyncTask(runnable: () -> Unit) {
    ITasks.instance().runAsyncTask(runnable)
}

fun <T> runSyncTask(lock: Any, supplier: () -> T?) {
    ITasks.instance().runSyncTask(lock, supplier)
}

fun sleepSafely(millis: Long) {
    IThreads.instance().sleepSafely(millis)
}

fun Change.toKotlinChange(): AbstractChange<*> {
    return IConvertors.instance().toKotlinChange(this)
}

fun Commit.toKotlinCommit(): ICommit<*> {
    return IConvertors.instance().toKotlinCommit(this)
}

fun logger(): Logger {
    return IPlugin.instance().javaPlugin()?.logger ?: throw IllegalStateException("java plugin is null")
}

fun now(): String {
    return ITimes.instance().now()
}