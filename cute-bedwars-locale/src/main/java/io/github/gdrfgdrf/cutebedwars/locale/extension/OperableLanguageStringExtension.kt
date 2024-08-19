package io.github.gdrfgdrf.cutebedwars.locale.extension

import io.github.gdrfgdrf.cuteframework.locale.OperableLanguageString
import org.bukkit.command.CommandSender

fun OperableLanguageString.send(sender: CommandSender) {
    sender.sendMessage(this.string.replace("&", "§"))
}