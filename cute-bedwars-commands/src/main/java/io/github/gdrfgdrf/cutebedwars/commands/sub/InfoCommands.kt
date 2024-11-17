package io.github.gdrfgdrf.cutebedwars.commands.sub

import io.github.gdrfgdrf.cutebedwars.abstracts.chatpage.IChatPage
import io.github.gdrfgdrf.cutebedwars.abstracts.chatpage.IChatPages
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPageRequestTypes
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPermissions
import io.github.gdrfgdrf.cutebedwars.abstracts.information.ICommandInformation
import io.github.gdrfgdrf.cutebedwars.abstracts.commands.AbstractSubCommand
import io.github.gdrfgdrf.cutebedwars.abstracts.commands.IParamCombination
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

    override fun run(sender: CommandSender, args: Array<String>, paramCombination: IParamCombination) {
        val pageIndex = paramCombination.pageIndex()

        val array = ICommands.values().toList().stream()
            .filter {
                return@filter IPermissions.valueOf("INFO_COMMANDS").hasPermission(sender)
            }
            .filter {
                return@filter !((it as ICommands).permissions().needOps() &&
                        !IPermissions.valueOf("INFO_ADMINISTRATION_COMMANDS").hasPermission(sender))
            }
            .map {
                it as ICommands
            }
            .toList()

        localizationScope(sender) {
            if (array.isEmpty()) {
                message(CommonLanguage.NOT_FOUND_COMMANDS)
                    .send()
                return@localizationScope
            }

            val chatPage = IChatPages.instance().cache(
                sender,
                IPageRequestTypes.valueOf("INFO_COMMANDS"),
                IPermissions.valueOf("INFO_COMMANDS").hasPermission(sender)
                    .toString() + " | " + IPermissions.valueOf("INFO_ADMINISTRATION_COMMANDS").hasPermission(sender)
            ) {
                return@cache arrayListOf()
            }
            if (chatPage.changeable()) {
                array.forEach { command ->
                    chatPage.addPage {
                        ICommandInformation.instance().convert(sender, command)
                    }
                }

                chatPage.changeable(false)
            }

            chatPage.send(pageIndex - 1)
        }
    }
}