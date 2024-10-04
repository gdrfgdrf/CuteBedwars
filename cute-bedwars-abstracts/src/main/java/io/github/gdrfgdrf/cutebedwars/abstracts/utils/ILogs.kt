package io.github.gdrfgdrf.cutebedwars.abstracts.utils

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.Service

@Service("logs")
@KotlinSingleton
interface ILogs {
    fun info(string: String)
    fun warn(string: String)
    fun error(string: String, throwable: Throwable)

    companion object {
        fun instance(): ILogs = Mediator.get(ILogs::class.java)!!
    }
}