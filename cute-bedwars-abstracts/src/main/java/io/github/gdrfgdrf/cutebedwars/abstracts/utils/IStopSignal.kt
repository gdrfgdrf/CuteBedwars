package io.github.gdrfgdrf.cutebedwars.abstracts.utils

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service

@Service("stop_signal", singleton = false)
interface IStopSignal {
    var stopped: Boolean

    fun stop()

    companion object {
        fun new(): IStopSignal = Mediator.get(IStopSignal::class.java)!!
    }
}