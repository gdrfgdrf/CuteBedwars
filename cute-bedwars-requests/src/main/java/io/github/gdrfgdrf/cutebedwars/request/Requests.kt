package io.github.gdrfgdrf.cutebedwars.request

import io.github.gdrfgdrf.cutebedwars.abstracts.commons.IConfig
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IRequestStatuses
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IRequestTypes
import io.github.gdrfgdrf.cutebedwars.abstracts.requests.IRequest
import io.github.gdrfgdrf.cutebedwars.abstracts.requests.IRequests
import io.github.gdrfgdrf.cutebedwars.locale.localizationScope
import io.github.gdrfgdrf.cutebedwars.languages.collect.RequestLanguage
import io.github.gdrfgdrf.cutebedwars.request.timer.HighCountdownTimer
import io.github.gdrfgdrf.cutebedwars.request.timer.LowCountdownTimer
import io.github.gdrfgdrf.cutebedwars.utils.extension.logInfo
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import org.bukkit.command.CommandSender
import java.util.concurrent.ConcurrentHashMap

@ServiceImpl("requests")
object Requests : IRequests {
    var GLOBAL_TIMEOUT: Long? = null
        get() {
            if (field == null) {
                field = if (IConfig.getRequestTimeout() == null) {
                    30000
                } else {
                    val customDefinition = IConfig.getRequestTimeout()

                    if (customDefinition!! <= 0) {
                        30000
                    } else {
                       customDefinition
                    }
                }
            }
            return field
        }

    private val playerRequestMap = ConcurrentHashMap<CommandSender, ConcurrentHashMap<IRequestTypes, IRequest>>()

    override fun initialize() {
        "Initializing request system".logInfo()

        HighCountdownTimer.start()
        LowCountdownTimer.start()
    }

    override fun reset() {
        "Resetting request system".logInfo()

        HighCountdownTimer.reset()
        LowCountdownTimer.reset()

        GLOBAL_TIMEOUT = null
        playerRequestMap.clear()
    }

    override fun auto(high: Boolean, type: IRequestTypes, sender: CommandSender): Pair<Boolean, IRequest> {
        if (playerRequestMap.containsKey(sender)) {
            return Pair(false, playerRequestMap[sender]?.get(type)!!)
        }

        val request = Request(eachSecond = {}, endRun = {
            localizationScope(sender) {
                message(RequestLanguage.TIMEOUT)
                    .format(type.displayName())
                    .send()
            }
            removeForAuto(high, type, sender)
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

    override fun removeForAuto(high: Boolean, type: IRequestTypes, sender: CommandSender) {
        val requestMap = playerRequestMap.computeIfAbsent(sender) {
            ConcurrentHashMap()
        }
        val request = requestMap[type]
        if (request != null) {
            request.status(IRequestStatuses.valueOf("STOPPED"))
            if (high) {
                HighCountdownTimer.remove(request as Request)
            } else {
                LowCountdownTimer.remove(request as Request)
            }
        }

        requestMap.remove(type)

        if (requestMap.isEmpty()) {
            playerRequestMap.remove(sender)
        }
    }

    override fun make(high: Boolean, endRun: IRequest.() -> Unit): IRequest {
        val request = Request(eachSecond = {}, endRun = endRun)

        if (high) {
            HighCountdownTimer.put(request)
        } else {
            LowCountdownTimer.put(request)
        }

        return request
    }

    override fun make(high: Boolean, eachSecond: IRequest.() -> Unit, endRun: IRequest.() -> Unit): IRequest {
        val request = Request(eachSecond = eachSecond, endRun = endRun)

        if (high) {
            HighCountdownTimer.put(request)
        } else {
            LowCountdownTimer.put(request)
        }

        return request
    }


}