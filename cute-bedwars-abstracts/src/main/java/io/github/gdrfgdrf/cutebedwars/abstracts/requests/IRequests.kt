package io.github.gdrfgdrf.cutebedwars.abstracts.requests

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IRequestTypes
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import org.bukkit.command.CommandSender

@Service("requests")
@KotlinSingleton
interface IRequests {
    fun initialize()
    fun reset()
    fun auto(high: Boolean = false, type: IRequestTypes, sender: CommandSender): Pair<Boolean, IRequest>
    fun removeForAuto(high: Boolean = false, type: IRequestTypes, sender: CommandSender)
    fun make(high: Boolean = false, endRun: IRequest.() -> Unit): IRequest
    fun make(high: Boolean = false, eachSecond: IRequest.() -> Unit, endRun: IRequest.() -> Unit): IRequest

    companion object {
        fun get(): IRequests = Mediator.get(IRequests::class.java)!!
    }
}