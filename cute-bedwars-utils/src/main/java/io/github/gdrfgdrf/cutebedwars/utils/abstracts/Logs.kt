package io.github.gdrfgdrf.cutebedwars.utils.abstracts

import io.github.gdrfgdrf.cutebedwars.abstracts.utils.ILogs
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logger
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl

@ServiceImpl("logs")
object Logs : ILogs {
    override fun info(string: String) {
        logger().info(string)
    }

    override fun warn(string: String) {
        logger().warning(string)
    }

    override fun error(string: String, throwable: Throwable) {
        logger().severe(string)
        throwable.printStackTrace()
    }
}