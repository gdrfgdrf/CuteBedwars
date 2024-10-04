package io.github.gdrfgdrf.cutebedwars.utils.abstracts

import io.github.gdrfgdrf.cutebedwars.abstracts.utils.ITimes
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*

@ServiceImpl("times")
object Times : ITimes {
    override fun now(): String {
        val now = Instant.now()
        val date = Date.from(now)
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return formatter.format(date)
    }
}