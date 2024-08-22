package io.github.gdrfgdrf.cutebedwars.utils.extension

import io.github.gdrfgdrf.cutebedwars.utils.logger

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