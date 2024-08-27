package io.github.gdrfgdrf.cutebedwars.abstracts.game.management

import org.bukkit.command.CommandSender

interface ISetter {
    fun set(sender: CommandSender, jsonKey: String, any: Any)
}