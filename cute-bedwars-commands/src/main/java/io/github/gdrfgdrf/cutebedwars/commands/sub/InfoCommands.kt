package io.github.gdrfgdrf.cutebedwars.commands.sub

import io.github.gdrfgdrf.cutebedwars.abstracts.chatpage.IChatPages
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPageRequestTypes
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPermissions
import io.github.gdrfgdrf.cutebedwars.abstracts.information.ICommandInformation
import io.github.gdrfgdrf.cutebedwars.abstracts.commands.AbstractSubCommand
import io.github.gdrfgdrf.cutebedwars.abstracts.commands.IParamCombination
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ILanguageString
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandDescriptionLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandSyntaxLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommonLanguage
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.localizationScope
import org.bukkit.command.CommandSender

object InfoCommands : AbstractSubCommand(
    command = ICommands.valueOf("INFO_COMMANDS")
) {
    override val syntax: ILanguageString = CommandSyntaxLanguage.INFO_COMMANDS
    override val description: ILanguageString = CommandDescriptionLanguage.INFO_COMMANDS

    override fun run(sender: CommandSender, args: Array<String>, paramCombination: IParamCombination) {
        val pageIndex = if (paramCombination.paramSchemeIndex == 0) {
            paramCombination.pageIndex()
        } else {
            1
        }
        val enumName = if (paramCombination.paramSchemeIndex == 1) {
            paramCombination.notNullString("COMMAND_ENUM")
        } else {
            ""
        }

        val array = ICommands.values().toList().stream()
            .map {
                it as ICommands
            }
            .filter {
                return@filter IPermissions.valueOf("INFO_COMMANDS").hasPermission(sender)
            }
            .filter {
                return@filter !((it as ICommands).permissions.needOps &&
                        !IPermissions.valueOf("INFO_ADMINISTRATION_COMMANDS").hasPermission(sender))
            }
            .filter {
                return@filter it != ICommands.valueOf("ROOT")
            }
            .filter {
                if (enumName.isBlank()) {
                    return@filter true
                }
                return@filter it.name == enumName.uppercase()
            }
            .toList()
            .reversed()

        localizationScope(sender) {
            if (array.isEmpty()) {
                message(CommonLanguage.NOT_FOUND_COMMANDS)
                    .send()
                return@localizationScope
            }

            val chatPage = IChatPages.instance().cache(
                sender,
                IPageRequestTypes.valueOf("INFO_COMMANDS"),
                "${IPermissions.valueOf("INFO_COMMANDS").hasPermission(sender)} | " +
                        "${IPermissions.valueOf("INFO_ADMINISTRATION_COMMANDS").hasPermission(sender)} | " +
                        enumName,
            ) {
                return@cache arrayListOf()
            }
            if (chatPage.changeable) {
                array.forEach { command ->
                    chatPage.addPage {
                        ICommandInformation.instance().convert(sender, command)
                    }
                }

                chatPage.changeable = false
            }

            chatPage.send(pageIndex - 1)
        }
    }
}