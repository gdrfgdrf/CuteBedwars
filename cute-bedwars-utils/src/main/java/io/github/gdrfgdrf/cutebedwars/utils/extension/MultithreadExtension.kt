package io.github.gdrfgdrf.cutebedwars.utils.extension

fun sleepSafely(
    millis: Long
) {
    runCatching {
        Thread.sleep(millis)
    }.onFailure {

    }
}