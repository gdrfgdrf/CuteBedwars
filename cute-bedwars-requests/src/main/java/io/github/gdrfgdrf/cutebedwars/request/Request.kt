package io.github.gdrfgdrf.cutebedwars.request

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IRequestStatuses
import io.github.gdrfgdrf.cutebedwars.abstracts.requests.IRequest
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet
import java.util.concurrent.TimeUnit

@Suppress("UNCHECKED_CAST")
@ServiceImpl("request")
class Request(
    override val timeout: Long,
    override val timeUnit: TimeUnit,
    override val eachSecond: IRequest.() -> Unit,
    override val endRun: IRequest.() -> Unit
) : IRequest {
    override var status = IRequestStatuses.valueOf("NONE")
    override var passedSecond = 0L
    override var lastEachSecondRun = 0L

    constructor(eachSecond: IRequest.() -> Unit, endRun: IRequest.() -> Unit): this(
        Requests.GLOBAL_TIMEOUT!!,
        TimeUnit.MILLISECONDS,
        eachSecond,
        endRun
    )

    constructor(argumentSet: ArgumentSet): this(
        argumentSet.args[0] as Long,
        argumentSet.args[1] as TimeUnit,
        argumentSet.args[2] as IRequest.() -> Unit,
        argumentSet.args[3] as IRequest.() -> Unit
    )
}