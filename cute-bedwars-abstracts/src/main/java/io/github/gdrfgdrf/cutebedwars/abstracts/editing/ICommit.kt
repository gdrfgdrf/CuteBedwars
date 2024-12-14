package io.github.gdrfgdrf.cutebedwars.abstracts.editing

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet
import org.bukkit.command.CommandSender

@Service("commit", singleton = false)
interface ICommit<T> {
    var id: Long
    var time: String?
    var submitter: String?
    var message: String?
    val changes: IChanges<T>

    fun tryApply(any: Any, sender: CommandSender): Boolean
    fun apply(t: T, sender: CommandSender)
    fun revert(submitter: String): ICommit<T>
    fun finish(submitter: String, message: String)

    companion object {
        fun <T> new(changes: IChanges<T>): ICommit<T> =
            Mediator.get(ICommit::class.java, ArgumentSet(arrayOf(changes)))!!
    }
}