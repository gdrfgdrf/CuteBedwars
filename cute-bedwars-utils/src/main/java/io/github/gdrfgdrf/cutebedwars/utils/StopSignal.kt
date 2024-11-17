package io.github.gdrfgdrf.cutebedwars.utils

import io.github.gdrfgdrf.cutebedwars.abstracts.utils.IStopSignal
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl

@ServiceImpl("stop_signal")
class StopSignal: IStopSignal {
    override var stopped: Boolean = false

    override fun stop() {
        stopped = true
    }
}