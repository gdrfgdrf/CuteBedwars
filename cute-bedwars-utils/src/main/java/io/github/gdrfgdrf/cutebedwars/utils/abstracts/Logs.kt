package io.github.gdrfgdrf.cutebedwars.utils.abstracts

import io.github.gdrfgdrf.cutebedwars.abstracts.utils.ILogs
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logger
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl

@ServiceImpl("logs")
object Logs : ILogs {
    override fun info(string: String) {
        runCatching {
            logger().info(string)
        }.onFailure {
            println("[CuteBedwars] $string")
        }
    }

    override fun warn(string: String) {
        runCatching {
            logger().warning(string)
        }.onFailure {
            println("[CuteBedwars] $string")
        }
    }

    override fun error(string: String, throwable: Throwable) {
        runCatching {
            logger().severe(string)
        }.onFailure {
            println("[CuteBedwars] $string")
        }
        throwable.printStackTrace()
    }
}