package io.github.gdrfgdrf.cutebedwars.abstracts.editing

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet

@Service("commit", singleton = false)
interface ICommit<T> {
    var id: Long
    var time: String?
    var submitter: String?
    var message: String?
    val changes: IChanges<T>

    fun tryApply(any: Any): Boolean
    fun apply(t: T)
    fun revert(submitter: String): ICommit<T>
    fun finish(submitter: String, message: String)

    companion object {
        fun <T> new(changes: IChanges<T>): ICommit<T> =
            Mediator.get(ICommit::class.java, ArgumentSet(arrayOf(changes)))!!
    }
}