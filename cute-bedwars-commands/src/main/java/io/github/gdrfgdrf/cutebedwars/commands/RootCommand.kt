package io.github.gdrfgdrf.cutebedwars.commands

import io.github.gdrfgdrf.cutebedwars.commands.base.SubCommand
import io.github.gdrfgdrf.cutebedwars.commands.manager.SubCommandManager
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandLanguage
import io.github.gdrfgdrf.cutebedwars.locale.localizationScope
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
        if (!"cbw".equals(label, true) && !"cutebedwars".equals(label, true)) {
            return true
        }

        if (args.isEmpty()) {
            val helpCommand = SubCommandManager.get("help") ?: return true
            execute(sender, args, helpCommand)
            return true
        }

        val subCommand = SubCommandManager.get(args[0])
        if (subCommand != null) {
            execute(sender, args, subCommand)
            return true
        }

        localizationScope(sender) {
            message(CommandLanguage.NOT_FOUND)
                .send()
        }
        return true
    }

    @Suppress("UNCHECKED_CAST")
    private fun execute(sender: CommandSender, args: Array<String>, subCommand: SubCommand) {
        localizationScope(sender) {
            if (subCommand.hasPermission(sender)) {
                if (!subCommand.command.onlyPlayer() || sender is Player) {
                    if (args.isEmpty()) {
                        subCommand.run(sender, args)
                        return@localizationScope
                    }
                    if (subCommand.command.argsRange().contains(args.size - 1)) {
                        val newArray = arrayOfNulls<String>(args.size - 1)
                        System.arraycopy(args, 1, newArray, 0, args.size - 1)

                        subCommand.run(sender, newArray as Array<String>)
                        return@localizationScope
                    }

                    if (subCommand.syntax() != null) {
                        message(CommandLanguage.SYNTAX_ERROR)
                            .format(subCommand.syntax()!!.get().string)
                            .send()
                    } else {
                        message(CommandLanguage.SYNTAX_ERROR)
                            .format("null")
                            .send()
                    }
                } else {
                    message(CommandLanguage.ONLY_PLAYER)
                        .send()
                }
            } else {
                message(CommandLanguage.NO_PERMISSION)
                    .send()
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
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
                    result.add(subCommand.command.string())
                }
            }
        } else {
            if (args.size > 1) {
                val pair = SubCommandManager.search { _, subCommand ->
                    return@search subCommand.command.string() == args[0] && subCommand.hasPermission(sender)
                } ?: return arrayListOf()

                val newArray = arrayOfNulls<String>(args.size - 1)
                System.arraycopy(args, 1, newArray, 0, args.size - 1)

                return pair.second.tab(sender, newArray as Array<String>)
            }
        }

        return result
    }
}