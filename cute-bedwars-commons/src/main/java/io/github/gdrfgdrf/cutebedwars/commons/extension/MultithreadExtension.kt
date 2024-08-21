package io.github.gdrfgdrf.cutebedwars.commons.extension

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

fun CoroutineScope.launchIO(block: suspend CoroutineScope.() -> Unit): Job =
    launch(Dispatchers.IO, block = block)

fun sleepSafely(
    millis: Long
) {
    runCatching {
        Thread.sleep(millis)
    }.onFailure {

    }
}