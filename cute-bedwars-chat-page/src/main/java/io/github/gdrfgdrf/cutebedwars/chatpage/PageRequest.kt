package io.github.gdrfgdrf.cutebedwars.chatpage

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPageRequestTypes
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ILocalizationMessage
import io.github.gdrfgdrf.cuteframework.locale.LanguageString
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import java.util.UUID

class PageRequest(
    val uuid: String,
    val type: IPageRequestTypes,
    val flagContent: String,
    val loader: () -> List<ILocalizationMessage>
) {
    fun getSender(): CommandSender {
        if (uuid == "not_a_player") {
            return Bukkit.getConsoleSender()
        }
        return Bukkit.getPlayer(uuid)
    }

    override fun equals(other: Any?): Boolean {
        if (other !is PageRequest) {
            return false
        }
        return other.uuid == uuid && other.type == type && other.flagContent == flagContent
    }

    override fun hashCode(): Int {
        var result = uuid.hashCode()
        result = 31 * result + type.hashCode()
        return result
    }
}