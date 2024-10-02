package io.github.gdrfgdrf.cutebedwars.abstracts.editing

interface ICommit<T> {
    fun tryApply(any: Any): Boolean
    fun apply(t: T)
    fun revert(submitter: String): ICommit<T>
    fun finish(submitter: String, message: String)

    fun id(): Long
    fun time(): String?
    fun submitter(): String?
    fun message(): String?

    fun time(time: String)
    fun submitter(submitter: String)
    fun message(message: String)

    fun changes(): IChanges<T>
}