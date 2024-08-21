package io.github.gdrfgdrf.cutebedwars.request

import io.github.gdrfgdrf.cutebedwars.commons.Config
import io.github.gdrfgdrf.cutebedwars.commons.extension.logInfo
import io.github.gdrfgdrf.cutebedwars.locale.collect.RequestLanguage
import io.github.gdrfgdrf.cutebedwars.locale.localizationScope
import io.github.gdrfgdrf.cutebedwars.request.enums.RequestTypes
import io.github.gdrfgdrf.cutebedwars.request.timer.HighCountdownTimer
import io.github.gdrfgdrf.cutebedwars.request.timer.LowCountdownTimer
import org.bukkit.command.CommandSender
import java.util.concurrent.ConcurrentHashMap

object Requests {
    var GLOBAL_TIMEOUT: Long? = null
        get() {
            if (field == null) {
                field = if (Config.INSTANCE?.requestTimeout == null) {
                    30000
                } else {
                    val customDefinition = Config.INSTANCE.requestTimeout

                    if (customDefinition <= 0) {
                        3000
                    } else {
                       customDefinition
                    }
                }
            }
            return field
        }

    private val playerRequestMap = ConcurrentHashMap<CommandSender, ConcurrentHashMap<RequestTypes, Request>>()

    fun initialize() {
        "Initializing request system".logInfo()

        HighCountdownTimer.start()
        LowCountdownTimer.start()
    }

    fun reset() {
        "Resetting request system".logInfo()

        HighCountdownTimer.reset()
        LowCountdownTimer.reset()
    }

    fun auto(high: Boolean = false, type: RequestTypes, sender: CommandSender): Pair<Boolean, Request?> {
        if (playerRequestMap.containsKey(sender)) {
            return Pair(false, playerRequestMap[sender]?.get(type))
        }

        val request = Request(eachSecond = {}, endRun = {
            localizationScope(sender) {
                message(RequestLanguage.TIMEOUT)
                    .format(type.displayName)
                    .send()
            }
            removeForAuto(type, sender)
        })

        if (high) {
            HighCountdownTimer.put(request)
        } else {
            LowCountdownTimer.put(request)
        }
        val requestMap = playerRequestMap.computeIfAbsent(sender) {
            ConcurrentHashMap()
        }
        requestMap[type] = request

        return Pair(true, request)
    }

    fun removeForAuto(type: RequestTypes, sender: CommandSender) {
        val requestMap = playerRequestMap.computeIfAbsent(sender) {
            ConcurrentHashMap()
        }
        requestMap.remove(type)

        if (requestMap.isEmpty()) {
            playerRequestMap.remove(sender)
        }
    }

    fun make(high: Boolean = false, endRun: Request.() -> Unit): Request {
        val request = Request(eachSecond = {}, endRun = endRun)

        if (high) {
            HighCountdownTimer.put(request)
        } else {
            LowCountdownTimer.put(request)
        }

        return request
    }

    fun make(high: Boolean = false, eachSecond: Request.() -> Unit, endRun: Request.() -> Unit): Request {
        val request = Request(eachSecond = eachSecond, endRun = endRun)

        if (high) {
            HighCountdownTimer.put(request)
        } else {
            LowCountdownTimer.put(request)
        }

        return request
    }

    fun put(high: Boolean = false, request: Request) {
        if (high) {
            HighCountdownTimer.put(request)
        } else {
            LowCountdownTimer.put(request)
        }
    }


}