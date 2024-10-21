package io.github.gdrfgdrf.cutebedwars.commands.sub

import io.github.gdrfgdrf.cutebedwars.abstracts.commands.AbstractSubCommand
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IItems
import io.github.gdrfgdrf.cutebedwars.abstracts.items.IItem
import io.github.gdrfgdrf.cuteframework.locale.LanguageString
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object DevCommands : AbstractSubCommand(
    command = ICommands.valueOf("DEV")
) {
    override fun syntax(): LanguageString? = null
    override fun description(): LanguageString? = null

    override fun run(sender: CommandSender, args: Array<String>, paramSchemeIndex: Int) {
        val item = IItems.valueOf("DEV_TOOL").item()
        item.give(sender as Player)
    }
}