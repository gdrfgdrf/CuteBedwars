package io.github.gdrfgdrf.cutebedwars.command

import io.github.gdrfgdrf.cutebedwars.command.base.SubCommand
import io.github.gdrfgdrf.cutebedwars.command.manager.SubCommandManager
import io.github.gdrfgdrf.cutebedwars.commons.enums.Commands
import io.github.gdrfgdrf.cutebedwars.locale.collect.CommandLanguage
import io.github.gdrfgdrf.cutebedwars.locale.extension.send
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player

object RootCommand : TabExecutor {
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<String>,
    ): Boolean {
        if (!"cbw".equals(label, true)) {
            return true
        }

        if (args.isEmpty()) {
            val helpCommand = SubCommandManager.get(Commands.HELP.string) ?: return true
            execute(sender, args, helpCommand)
            return true
        }

        val subCommand = SubCommandManager.get(args[0])
        if (subCommand != null) {
            execute(sender, args, subCommand)
            return true
        }

        CommandLanguage.NOT_FOUND
            ?.get()!!
            .send(sender)
        return true
    }

    private fun execute(sender: CommandSender, args: Array<String>, subCommand: SubCommand) {
        if (subCommand.hasPermission(sender)) {
            if (!subCommand.command.onlyPlayer || sender is Player) {
                if (args.isEmpty()) {
                    subCommand.run(sender, args)
                    return
                }
                if (subCommand.command.argsRange.contains(args.size - 1)) {
                    subCommand.run(sender, args)
                    return
                }
                CommandLanguage.SYNTAX_ERROR
                    ?.get()!!
                    .send(sender)
            } else {
                CommandLanguage.ONLY_PLAYER
                    ?.get()!!
                    .send(sender)
            }
        } else {
            CommandLanguage.NO_PERMISSION
                ?.get()!!
                .send(sender)
        }
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        alias: String,
        args: Array<String>,
    ): MutableList<String> {
        val result = arrayListOf<String>()

        if (args.size == 1) {
            SubCommandManager.forEach { _, subCommand ->
                if (subCommand.hasPermission(sender)) {
                    result.add(subCommand.command.string)
                }
            }
        } else {
            if (args.size > 1) {
                val pair = SubCommandManager.filterAndFindFirst { _, subCommand ->
                    return@filterAndFindFirst subCommand.hasPermission(sender)
                } ?: return result

                return pair.second.tab(sender, args)
            }
        }

        return result
    }
}