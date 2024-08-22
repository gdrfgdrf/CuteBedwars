package io.github.gdrfgdrf.cutebedwars.commands.sub

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands
import io.github.gdrfgdrf.cutebedwars.commands.base.SubCommand
import io.github.gdrfgdrf.cuteframework.locale.LanguageString
import org.bukkit.command.CommandSender

object CreateArea : SubCommand(
    command = ICommands.get("CREATE_AREA")
){
    override fun syntax(): LanguageString? {
        TODO("Not yet implemented")
    }

    override fun description(): LanguageString? {
        TODO("Not yet implemented")
    }

    override fun run(sender: CommandSender, args: Array<String>) {
        TODO("Not yet implemented")
    }
}