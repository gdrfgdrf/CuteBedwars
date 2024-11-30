package io.github.gdrfgdrf.cutebedwars.commands.sub

import io.github.gdrfgdrf.cutebedwars.abstracts.commands.AbstractSubCommand
import io.github.gdrfgdrf.cutebedwars.abstracts.commands.IParamCombination
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPermissions
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ILanguageString
import io.github.gdrfgdrf.cutebedwars.commands.manager.SubCommandManager
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandDescriptionLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandSyntaxLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommonLanguage
import io.github.gdrfgdrf.cutebedwars.locale.extension.middleWork
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.localizationScope
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object Help : AbstractSubCommand(
    command = ICommands.valueOf("HELP"),
) {
    override val syntax: ILanguageString = CommandSyntaxLanguage.HELP
    override val description: ILanguageString = CommandDescriptionLanguage.HELP

    override fun run(sender: CommandSender, args: Array<String>, paramCombination: IParamCombination) {
        middleWork("", sender) {
            val accessibleUserCommand = arrayListOf<Pair<ICommands, AbstractSubCommand>>()
            SubCommandManager.forEachUser { commands, subCommand ->
                if (!subCommand.hasPermission(sender)) {
                    return@forEachUser
                }

                accessibleUserCommand.add(commands to subCommand)
            }
            if (accessibleUserCommand.isEmpty()) {
                message(CommonLanguage.NONE)
                    .send("")
            } else {
                accessibleUserCommand.forEach {
                    send(sender, it.first, it.second)
                }
            }

            val accessibleAdminCommand = arrayListOf<Pair<ICommands, AbstractSubCommand>>()
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
                    .send("")

                message(CommonLanguage.NONE)
                    .send("")
            } else {
                message(CommandLanguage.COMMAND_HELP_ADMIN_INFIX)
                    .send("")

                accessibleAdminCommand.forEach {
                    send(sender, it.first, it.second)
                }
            }
        }
    }

    private fun send(
        sender: CommandSender,
        commands: ICommands,
        subCommand: AbstractSubCommand,
    ) {
        val array: Array<Any?>? = if (sender is Player) {
            ICommands.values().toList().stream()
                .filter {
                    return@filter IPermissions.valueOf("INFO_COMMANDS").hasPermission(sender)
                }
                .filter {
                    return@filter !((it as ICommands).permissions.needOps &&
                            !IPermissions.valueOf("INFO_ADMINISTRATION_COMMANDS").hasPermission(sender))
                }
                .toList()
                .toTypedArray()
        } else {
            null
        }
        val indexOf = array?.indexOf(subCommand.command)

        if (subCommand.syntax != null && subCommand.description != null) {
            localizationScope(sender) {
                if (sender is Player) {
                    message(CommandLanguage.COMMAND_FORMAT)
                        .format0(subCommand.syntax!!.operate().string)
                        .apply {
                            get0().showText(subCommand.description!!.operate().string)

                            if (indexOf!! >= 0) {
                                get0().runCommand("/cbw info commands args ${indexOf + 1}")
                            }
                        }
                        .send("")
                } else {
                    message(CommandLanguage.COMMAND_FORMAT_FOR_CONSOLE)
                        .format0(
                            subCommand.syntax!!.operate().string,
                            subCommand.description!!.operate().string
                        )
                        .send("")
                }
            }
        } else {
            localizationScope(sender) {
                if (sender is Player) {
                    message(CommandLanguage.COMMAND_FORMAT)
                        .format0(commands.get(), "null")
                        .apply {
                            if (indexOf!! >= 0) {
                                get0().runCommand("/cbw info commands args ${indexOf + 1}")
                            }
                        }
                        .send("")
                } else {
                    message(CommandLanguage.COMMAND_FORMAT_FOR_CONSOLE)
                        .apply {
                            val syntax = if (subCommand.syntax != null) {
                                subCommand.syntax!!.operate().string
                            } else {
                                "null"
                            }
                            format0(syntax, "null")
                        }
                        .send("")
                }
            }
        }
    }
}