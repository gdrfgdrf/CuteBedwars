package io.github.gdrfgdrf.cutebedwars.commands.sub

import io.github.gdrfgdrf.cutebedwars.abstracts.chatpage.IChatPage
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPageRequestTypes
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPermissions
import io.github.gdrfgdrf.cutebedwars.abstracts.game.information.ICommandInformation
import io.github.gdrfgdrf.cutebedwars.abstracts.commands.AbstractSubCommand
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandDescriptionLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandSyntaxLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommonLanguage
import io.github.gdrfgdrf.cutebedwars.locale.localizationScope
import io.github.gdrfgdrf.cuteframework.locale.LanguageString
import org.bukkit.command.CommandSender

object InfoCommands : AbstractSubCommand(
    command = ICommands.valueOf("INFO_COMMANDS")
) {
    override fun syntax(): LanguageString? = CommandSyntaxLanguage.INFO_COMMANDS
    override fun description(): LanguageString? = CommandDescriptionLanguage.INFO_COMMANDS

    override fun run(sender: CommandSender, args: Array<String>, paramSchemeIndex: Int) {
        var pageIndex = 1
        if (paramSchemeIndex == 0) {
            pageIndex = args[0].toInt()
        }

        val array = ICommands.values().toList().stream()
            .filter {
                return@filter IPermissions.valueOf("INFO_COMMANDS").hasPermission(sender)
            }
            .filter {
                return@filter !((it as ICommands).permissions().needOps() &&
                        !IPermissions.valueOf("INFO_ADMINISTRATION_COMMANDS").hasPermission(sender))
            }
            .toList()
            .toTypedArray()

        localizationScope(sender) {
            if (array.isEmpty()) {
                message(CommonLanguage.NOT_FOUND_COMMANDS)
                    .send()
                return@localizationScope
            }

            val chatPage = IChatPage.get(
                sender,
                IPageRequestTypes.valueOf("INFO_COMMANDS"),
                IPermissions.valueOf("INFO_COMMANDS").hasPermission(sender)
                    .toString() + " | " + IPermissions.valueOf("INFO_ADMINISTRATION_COMMANDS").hasPermission(sender)
            ) {
                return@get arrayListOf()
            }
            if (chatPage.changeable()) {
                array.forEach {
                    it ?: return@forEach
                    if (it !is ICommands) {
                        return@forEach
                    }

                    chatPage.addPage {
                        ICommandInformation.get().convert(sender, it)
                    }
                }

                chatPage.changeable(false)
            }

            chatPage.send(pageIndex - 1)
        }
    }
}