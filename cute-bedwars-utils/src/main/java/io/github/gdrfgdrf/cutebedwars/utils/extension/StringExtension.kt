package io.github.gdrfgdrf.cutebedwars.utils.extension

import io.github.gdrfgdrf.cutebedwars.utils.logger

fun String.isInt(): Boolean {
    for (c in toCharArray()) {
        if (!Character.isDigit(c)) {
            return false
        }
    }

    return true
}

fun String.toIntOrDefault(defaultValue: Int): Int {
    runCatching {
        return toInt()
    }.onFailure {
        return defaultValue
    }
    return defaultValue
}

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