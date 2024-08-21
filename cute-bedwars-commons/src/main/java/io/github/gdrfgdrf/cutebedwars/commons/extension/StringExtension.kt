package io.github.gdrfgdrf.cutebedwars.commons.extension

import io.github.gdrfgdrf.cutebedwars.commons.logger

fun String.logInfo() {
    logger().info(this)
}

fun String.logWarn() {
    logger().warning(this)
}

fun String.logError(e: Throwable) {
    logger().severe(this)
    e.printStackTrace()
}