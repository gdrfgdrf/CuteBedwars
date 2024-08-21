package io.github.gdrfgdrf.cutebedwars.request

import io.github.gdrfgdrf.cutebedwars.request.enums.RequestStatuses
import java.util.concurrent.TimeUnit

class Request(
    val timeout: Long = Requests.GLOBAL_TIMEOUT!!,
    val timeUnit: TimeUnit = TimeUnit.MILLISECONDS,
    val eachSecond: Request.() -> Unit,
    val endRun: Request.() -> Unit,
) {
    var status = RequestStatuses.NONE
    var passedSecond = 0L

    internal var lastEachSecondRun = 0L

}