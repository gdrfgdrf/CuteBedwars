package io.github.gdrfgdrf.cutebedwars.utils.extension

import java.text.SimpleDateFormat
import java.time.Instant
import java.util.Date

fun now(): String {
    val now = Instant.now()
    val date = Date.from(now)
    val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    return formatter.format(date)
}