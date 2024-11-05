package io.github.gdrfgdrf.cutebedwars.abstracts.utils

import io.github.gdrfgdrf.cutebedwars.abstracts.editing.ICommit
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.change.AbstractChange
import io.github.gdrfgdrf.cutebedwars.protobuf.storage.StorageProto.Change
import io.github.gdrfgdrf.cutebedwars.protobuf.storage.StorageProto.Commit
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import org.bukkit.command.CommandSender

@Service("convertors")
@KotlinSingleton
interface IConvertors {
    fun fieldNameToJsonKey(string: String): String
    fun jsonKeyToFieldName(string: String): String
    fun uncapitalize(string: String): String
    fun isLong(string: String): Boolean
    fun isInt(string: String): Boolean
    fun toBooleanOrNull(string: String): Boolean?
    fun toIntOrDefault(string: String, defaultValue: Int): Int
    fun toKotlinChange(change: Change): AbstractChange<*>
    fun toKotlinCommit(commit: Commit): ICommit<*>
    fun uuid(sender: CommandSender): String

    companion object {
        fun instance(): IConvertors = Mediator.get(IConvertors::class.java)!!
    }
}