package io.github.gdrfgdrf.cutebedwars.abstracts.requests

import io.github.gdrfgdrf.cutebedwars.abstracts.commons.IConstants
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IRequestStatuses
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet
import java.util.concurrent.TimeUnit

@Service("request", singleton = false)
interface IRequest {
    val timeout: Long
    val timeUnit: TimeUnit
    val eachSecond: IRequest.() -> Unit
    val endRun: IRequest.() -> Unit

    var status: IRequestStatuses
    var passedSecond: Long
    var lastEachSecondRun: Long

    companion object {
        fun new(eachSecond: IRequest.() -> Unit, endRun: IRequest.() -> Unit): IRequest = Mediator.get(IRequest::class.java, ArgumentSet(arrayOf(
            IConstants["GLOBAL_TIMEOUT"],
            TimeUnit.MILLISECONDS,
            eachSecond,
            endRun
        )))!!
    }
}