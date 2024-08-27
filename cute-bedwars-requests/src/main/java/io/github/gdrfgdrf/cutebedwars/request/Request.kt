package io.github.gdrfgdrf.cutebedwars.request

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IRequestStatuses
import io.github.gdrfgdrf.cutebedwars.abstracts.requests.IRequest
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet
import java.util.concurrent.TimeUnit

@Suppress("UNCHECKED_CAST")
@ServiceImpl("request")
class Request(
    argumentSet: ArgumentSet
): IRequest {
    private val timeout: Long = argumentSet.args[0] as Long
    private val timeUnit: TimeUnit = argumentSet.args[1] as TimeUnit
    private val eachSecond: IRequest.() -> Unit = argumentSet.args[2] as IRequest.() -> Unit
    private val endRun: IRequest.() -> Unit = argumentSet.args[3] as IRequest.() -> Unit

    constructor(eachSecond: IRequest.() -> Unit, endRun: IRequest.() -> Unit):
            this(ArgumentSet(arrayOf(
                Requests.GLOBAL_TIMEOUT,
                TimeUnit.MILLISECONDS,
                eachSecond,
                endRun
            )))

    private var status = IRequestStatuses.valueOf("NONE")
    private var passedSecond = 0L
    private var lastEachSecondRun = 0L

    override fun timeout(): Long = timeout
    override fun timeUnit(): TimeUnit = timeUnit
    override fun eachSecond(): IRequest.() -> Unit = eachSecond
    override fun endRun(): IRequest.() -> Unit = endRun
    override fun status(): IRequestStatuses = status

    override fun status(requestStatuses: IRequestStatuses) {
        status = requestStatuses
    }

    override fun passedSecond(): Long = passedSecond

    override fun passedSecond(passed: Long) {
        passedSecond = passed
    }

    override fun lastEachSecondRun(): Long = lastEachSecondRun

    override fun lastEachSecondRun(time: Long) {
        lastEachSecondRun = time
    }

}