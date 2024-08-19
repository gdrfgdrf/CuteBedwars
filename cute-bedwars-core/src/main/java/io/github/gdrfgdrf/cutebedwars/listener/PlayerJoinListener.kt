package io.github.gdrfgdrf.cutebedwars.listener

import io.github.gdrfgdrf.cutebedwars.beans.PlayerData
import io.github.gdrfgdrf.cutebedwars.database.service.IPlayerService
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

object PlayerJoinListener : Listener {
    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        val uuid = event.player.uniqueId
        val service = IPlayerService.get()
        if (!service.exist(uuid)) {
            val playerData = PlayerData()
            playerData.uuid = uuid
            service.insert(playerData)
        }

        val playerData = service.selectByUuid(uuid)
        println(playerData)
    }
}