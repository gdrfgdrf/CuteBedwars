package io.github.gdrfgdrf.cutebedwars.abstracts.utils

import io.github.gdrfgdrf.cutebedwars.abstracts.core.IPlugin
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.ICommit
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.change.AbstractChange
import io.github.gdrfgdrf.cutebedwars.abstracts.frequencytasks.IFrequencyTask
import io.github.gdrfgdrf.cutebedwars.protobuf.storage.StorageProto.Change
import io.github.gdrfgdrf.cutebedwars.protobuf.storage.StorageProto.Commit
import org.bukkit.command.CommandSender
import java.util.concurrent.TimeUnit
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

fun String.logDebug() {
    ILogs.instance().debug(this)
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

fun asyncTask(runnable: () -> Unit) {
    ITasks.instance().asyncTask(runnable)
}

fun <T> syncTask(lock: Any, supplier: () -> T?) {
    ITasks.instance().syncTask(lock, supplier)
}

fun frequencyTask(frequency: Long, runnable: (IFrequencyTask) -> Unit): IStopSignal {
    return ITasks.instance().frequencyTask(frequency, runnable)
}

fun frequencyTask(frequency: Long, timeUnit: TimeUnit, runnable: (IFrequencyTask) -> Unit): IStopSignal {
    return ITasks.instance().frequencyTask(frequency, timeUnit, runnable)
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

fun CommandSender.uuid(): String {
    return IConvertors.instance().uuid(this)
}

fun logger(): Logger {
    return IPlugin.instance().javaPlugin()?.logger ?: throw IllegalStateException("java plugin is null")
}

fun now(): String {
    return ITimes.instance().now()
}

fun <E : Any> customList(): ICustomList<E> {
    return ICustomList.new()
}