package io.github.gdrfgdrf.cutebedwars.commons.extension

import io.github.gdrfgdrf.cutebedwars.commons.common.logger

fun String.logInfo() {
    logger().info(this)
}

fun String.error(e: Throwable) {
    logger().severe(this)
    e.printStackTrace()
}