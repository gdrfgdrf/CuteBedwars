package io.github.gdrfgdrf.cutebedwars.utils.abstracts

import io.github.gdrfgdrf.cutebedwars.abstracts.editing.IChangeTypeRegistry
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.IChanges
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.ICommit
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.change.AbstractChange
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.IConvertors
import io.github.gdrfgdrf.cutebedwars.protobuf.storage.StorageProto.Change
import io.github.gdrfgdrf.cutebedwars.protobuf.storage.StorageProto.Commit
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.*

@ServiceImpl("convertors")
object Convertors : IConvertors {
    override fun fieldNameToJsonKey(string: String): String {
        val uncapitalize = uncapitalize(string)
        val stringBuilder = StringBuilder()

        for (element in uncapitalize) {
            if (Character.isUpperCase(element)) {
                stringBuilder.append("-")
            }
            stringBuilder.append(element.lowercaseChar())
        }

        return stringBuilder.toString()
    }

    override fun jsonKeyToFieldName(string: String): String {
        val uncapitalize = uncapitalize(string)
        val stringBuilder = StringBuilder()

        var i = 0
        while (i < uncapitalize.length) {
            val charAt = uncapitalize[i]
            if (charAt == '-') {
                i++
                val charAt2 = uncapitalize[i]
                stringBuilder.append(charAt2.uppercaseChar())
            } else {
                stringBuilder.append(charAt)
            }
            i++
        }

        return stringBuilder.toString()
    }

    override fun uncapitalize(string: String): String {
        if (string.isEmpty()) {
            return ""
        }
        if (string.length == 1) {
            return string.lowercase(Locale.getDefault())
        }

        val temp = string.substring(1)
        val charAt = string[0]
        return charAt.lowercaseChar().toString() + temp
    }

    override fun isLong(string: String): Boolean {
        return isInt(string)
    }

    override fun isInt(string: String): Boolean {
        for (c in string.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false
            }
        }

        return true
    }

    override fun toBooleanOrNull(string: String): Boolean? {
        runCatching {
            return string.toBoolean()
        }.onFailure {
            return null
        }
        return null
    }

    override fun toIntOrDefault(string: String, defaultValue: Int): Int {
        runCatching {
            return string.toInt()
        }.onFailure {
            return defaultValue
        }
        return defaultValue
    }

    override fun toKotlinChange(change: Change): AbstractChange<*> {
        val identifier = change.identifier
        val changeClassHolder = IChangeTypeRegistry.instance().get(identifier)
            ?: throw IllegalArgumentException("Cannot find change class holder by identifier $identifier")

        val args = change.argsList.toTypedArray()
        if (!changeClassHolder.validateArgsLength(true, *args)) {
            throw IllegalArgumentException("The provided length does not match the expected length")
        }

        return changeClassHolder.create(*args)
    }

    override fun toKotlinCommit(commit: Commit): ICommit<*> {
        val changes = IChanges.new<Any>()
        commit.changesList.forEach {
            if (!changes.tryAdd(toKotlinChange(it))) {
                throw IllegalArgumentException("Change ${it.identifier} (${it.name}) cannot be added to the change list")
            }
        }

        val kotlinCommit = changes.finish()
        kotlinCommit.id(commit.id.toLongOrNull() ?: throw IllegalArgumentException("id is not a long"))
        kotlinCommit.time(commit.time)
        kotlinCommit.submitter(commit.submitter)
        kotlinCommit.message(commit.message)

        return kotlinCommit
    }

    override fun uuid(sender: CommandSender): String {
        if (sender !is Player) {
            return "Console"
        }
        return sender.uniqueId.toString()
    }
}