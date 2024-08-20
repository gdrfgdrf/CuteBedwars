package io.github.gdrfgdrf.cutebedwars.commands.sub

import io.github.gdrfgdrf.cutebedwars.commands.base.SubCommand
import io.github.gdrfgdrf.cutebedwars.commands.manager.SubCommandManager
import io.github.gdrfgdrf.cutebedwars.commons.enums.Commands
import io.github.gdrfgdrf.cutebedwars.locale.LocalizationContext
import io.github.gdrfgdrf.cutebedwars.locale.collect.CommandDescriptionLanguage
import io.github.gdrfgdrf.cutebedwars.locale.collect.CommandLanguage
import io.github.gdrfgdrf.cutebedwars.locale.collect.CommandSyntaxLanguage
import io.github.gdrfgdrf.cutebedwars.locale.collect.CommonLanguage
import io.github.gdrfgdrf.cutebedwars.locale.extension.middleWork
import io.github.gdrfgdrf.cuteframework.locale.LanguageString
import org.bukkit.command.CommandSender

object Help : SubCommand(
    command = Commands.HELP,
){
    override fun syntax(): LanguageString? = CommandSyntaxLanguage.HELP
    override fun description(): LanguageString? = CommandDescriptionLanguage.HELP

    override fun run(sender: CommandSender, args: Array<String>) {
        middleWork(sender) {
            val accessibleUserCommand = arrayListOf<Pair<Commands, SubCommand>>()
            SubCommandManager.forEachUser { commands, subCommand ->
                if (!subCommand.hasPermission(sender)) {
                    return@forEachUser
                }

                accessibleUserCommand.add(commands to subCommand)
            }
            if (accessibleUserCommand.isEmpty()) {
                message(CommonLanguage.NONE)
                    .send()
            } else {
                accessibleUserCommand.forEach {
                    send(this@middleWork, it.first, it.second)
                }
            }

            val accessibleAdminCommand = arrayListOf<Pair<Commands, SubCommand>>()
            SubCommandManager.forEachAdmin { commands, subCommand ->
                if (!subCommand.hasPermission(sender)) {
                    return@forEachAdmin
                }

                accessibleAdminCommand.add(commands to subCommand)
            }
            if (accessibleAdminCommand.isEmpty()) {
                if (accessibleUserCommand.isEmpty() || !sender.isOp) {
                    return@middleWork
                }

                message(CommandLanguage.COMMAND_HELP_ADMIN_INFIX)
                    .send()

                message(CommonLanguage.NONE)
                    .send()
            } else {
                message(CommandLanguage.COMMAND_HELP_ADMIN_INFIX)
                    .send()

                accessibleAdminCommand.forEach {
                    send(this@middleWork, it.first, it.second)
                }
            }
        }
    }

    private fun send(
        localizationContext: LocalizationContext,
        commands: Commands,
        subCommand: SubCommand,
    ) {
        if (subCommand.syntax() != null && subCommand.description() != null) {
            localizationContext.message(CommandLanguage.COMMAND_FORMAT)
                .format(subCommand.syntax()!!.get().string, subCommand.description()!!.get().string)
                .send()
        } else {
            localizationContext.message(CommandLanguage.COMMAND_FORMAT)
                .format(commands.get(), "null")
                .send()
        }
    }
}