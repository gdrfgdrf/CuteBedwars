package io.github.gdrfgdrf.cutebedwars.abstracts.utils

import com.github.fierioziy.particlenativeapi.api.ParticleNativeAPI
import com.github.fierioziy.particlenativeapi.api.particle.ParticleList_1_8
import com.github.fierioziy.particlenativeapi.api.particle.type.ParticleType
import io.github.gdrfgdrf.cutebedwars.abstracts.core.IPlugin
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.ICommit
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.change.AbstractChange
import io.github.gdrfgdrf.cutebedwars.abstracts.frequencytasks.IFrequencyTask
import io.github.gdrfgdrf.cutebedwars.abstracts.particles.IParticles
import io.github.gdrfgdrf.cutebedwars.protobuf.storage.StorageProto.Change
import io.github.gdrfgdrf.cutebedwars.protobuf.storage.StorageProto.Commit
import org.bukkit.Particle
import org.bukkit.command.CommandSender
import java.util.concurrent.TimeUnit
import java.util.logging.Logger

fun String.logInfo() {
    ILogs.instance().info(this)
}

fun String.logWarn() {
    ILogs.instance().warn(this)
}

fun String.logError() {
    ILogs.instance().error(this)
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

fun String.replaceToColorSymbol(formatSymbol: String = "&"): String {
    return this
        .replace(formatSymbol + "0", "§0")
        .replace(formatSymbol + "1", "§1")
        .replace(formatSymbol + "2", "§2")
        .replace(formatSymbol + "3", "§3")
        .replace(formatSymbol + "4", "§4")
        .replace(formatSymbol + "5", "§5")
        .replace(formatSymbol + "6", "§6")
        .replace(formatSymbol + "7", "§7")
        .replace(formatSymbol + "8", "§8")
        .replace(formatSymbol + "9", "§9")
        .replace(formatSymbol + "a", "§a")
        .replace(formatSymbol + "b", "§b")
        .replace(formatSymbol + "c", "§c")
        .replace(formatSymbol + "d", "§d")
        .replace(formatSymbol + "e", "§e")
        .replace(formatSymbol + "f", "§f")
        .replace(formatSymbol + "k", "§k")
        .replace(formatSymbol + "l", "§l")
        .replace(formatSymbol + "m", "§m")
        .replace(formatSymbol + "n", "§n")
        .replace(formatSymbol + "o", "§o")
        .replace(formatSymbol + "r", "§r")
        .replace(formatSymbol + formatSymbol, formatSymbol)
}

@Suppress("UNCHECKED_CAST")
fun <T : ParticleType> particle(particle: Particle): T {
    val api = IParticles.instance().api() ?: throw IllegalStateException("particle api is not got")
    val name = particle.name.uppercase()
    val particleList18 = api.LIST_1_8
    val particleList18class = ParticleList_1_8::class.java

    val declaredField = particleList18class.getDeclaredField(name)
    return declaredField.get(particleList18) as T
}