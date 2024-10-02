package io.github.gdrfgdrf.cutebedwars.abstracts.editing

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet

@Service("commit", singleton = false)
interface ICommit<T> {
    fun tryApply(any: Any): Boolean
    fun apply(t: T)
    fun revert(submitter: String): ICommit<T>
    fun finish(submitter: String, message: String)

    fun id(): Long
    fun time(): String?
    fun submitter(): String?
    fun message(): String?

    fun id(id: Long)
    fun time(time: String)
    fun submitter(submitter: String)
    fun message(message: String)

    fun changes(): IChanges<T>

    companion object {
        fun <T> new(changes: IChanges<T>): ICommit<T> =
            Mediator.get(ICommit::class.java, ArgumentSet(arrayOf(changes)))!!
    }
}