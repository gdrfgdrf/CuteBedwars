package io.github.gdrfgdrf.cutebedwars.commands

import io.github.gdrfgdrf.cutebedwars.abstracts.commons.IParamScheme
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommandNodes
import io.github.gdrfgdrf.cutebedwars.commands.base.SubCommand
import io.github.gdrfgdrf.cutebedwars.commands.common.ParamScheme
import io.github.gdrfgdrf.cutebedwars.commands.manager.SubCommandManager
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandLanguage
import io.github.gdrfgdrf.cutebedwars.locale.localizationScope
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.CopyOnWriteArrayList

object RootCommand : TabExecutor {
    @Suppress("UNCHECKED_CAST")
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
        var subCommand: SubCommand? = null
        val params = arrayListOf<String>()

        if (!args.contains("args")) {
            val nodeString = if (args.size == 1) {
                "cbw"
            } else {
                args[args.size - 2]
            }
            val commandPart = args[args.size - 1]

            val node = ICommandNodes.find(nodeString)
            if (node != null) {
                params.add(commandPart)
                subCommand = SubCommandManager.get(commandPart, node)

                if (subCommand == null) {
                    subCommand = SubCommandManager.get(commandPart, ICommandNodes.valueOf("ALLOW_NO_ARGS_ON_ROOT"))
                }
            }
        } else {
            val argsSplitIndex = args.indexOf("args")
            if (argsSplitIndex < args.size - 1) {
                val nodePart = arrayOfNulls<String>(argsSplitIndex)
                val paramPart = arrayOfNulls<String>(args.size - argsSplitIndex - 1)

                System.arraycopy(args, 0, nodePart, 0, argsSplitIndex)
                System.arraycopy(args, argsSplitIndex + 1, paramPart, 0, args.size - argsSplitIndex - 1)


                val nodeString = if (nodePart.size == 1) {
                    "cbw"
                } else {
                    nodePart[nodePart.size - 2]!!
                }

                val node = ICommandNodes.find(nodeString)
                val commandPart = nodePart[nodePart.size - 1]!!

                if (node != null) {
                    params.add("args")
                    params.addAll(paramPart as Array<String>)
                    subCommand = SubCommandManager.get(commandPart, node)

                    if (subCommand == null) {
                        subCommand = SubCommandManager.get(commandPart, ICommandNodes.valueOf("ARGS"))
                    }
                    if (subCommand == null) {
                        subCommand = SubCommandManager.get(commandPart, ICommandNodes.valueOf("ALLOW_NO_ARGS"))
                    }
                }
            }
        }

        if (subCommand != null) {
            execute(sender, params.toTypedArray(), subCommand)
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
                        subCommand.run(sender, args, ParamScheme.NO_MATCH)
                        return@localizationScope
                    }

                    val argsRange = subCommand.command.argsRange()
                    val newArray = arrayOfNulls<String>(args.size - 1)
                    System.arraycopy(args, 1, newArray, 0, args.size - 1)

                    if (newArray.isEmpty() && subCommand.command.allowEmptyParam()) {
                        subCommand.run(sender, args, ParamScheme.NO_MATCH)
                        return@localizationScope
                    }

                    if (argsRange.contains(newArray.size)) {
                        var validateResult: Map.Entry<IParamScheme, Int>? = null

                        val providedLength = newArray.size
                        val paramSchemes = ConcurrentHashMap<IParamScheme, Int>()

                        for ((index, it) in subCommand.command.paramsSchemes()!!.withIndex()) {
                            val length = it.length()
                            if (length == providedLength) {
                                paramSchemes[it] = index
                            }
                        }

                        newArray.forEachIndexed { index, realParam ->
                            realParam ?: return@localizationScope

                            paramSchemes.forEach { (paramScheme, index2) ->
                                val list = paramScheme.get()
                                val param = list[index]

                                if (param.validate(newArray as Array<String>, index, realParam)) {
                                    if (index >= list.size - 1) {
                                        validateResult = object : Map.Entry<IParamScheme, Int> {
                                            override val key: IParamScheme
                                                get() = paramScheme
                                            override val value: Int
                                                get() = index2
                                        }
                                    }
                                } else {
                                    paramSchemes.remove(paramScheme)
                                }

                            }
                        }

                        if (validateResult != null) {
                            subCommand.run(sender, newArray as Array<String>, validateResult!!.value)
                            return@localizationScope
                        }
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
                    return@search subCommand.command.string().equals(args[0], true) && subCommand.hasPermission(sender)
                } ?: return arrayListOf()

                val newArray = arrayOfNulls<String>(args.size - 1)
                System.arraycopy(args, 1, newArray, 0, args.size - 1)

                val subCommand = pair.second
                val paramSchemes = subCommand.command.paramsSchemes()

                if (!paramSchemes.isNullOrEmpty()) {
                    val providedLength = newArray.size

                    val filterResult = CopyOnWriteArrayList<IParamScheme>()
                    val result2 = arrayListOf<IParamScheme>()

                    paramSchemes.forEach {
                        val length = it.length()
                        if (length >= providedLength) {
                            filterResult.add(it)
                        }
                    }

                    newArray.forEachIndexed { index, realParam ->
                        realParam ?: return@forEachIndexed

                        filterResult.forEach {
                            if (realParam.isEmpty()) {
                                result2.add(it)
                                return@forEach
                            }

                            val list = it.get()
                            val param = list[index]

                            if (param.validate(newArray as Array<String>, index, realParam)) {
                                result2.add(it)
                            } else {
                                filterResult.remove(it)
                            }
                        }
                    }

                    result2.forEach { paramScheme ->
                        val params = paramScheme.get()
                        val param = params[newArray.size - 1]
                        val paramProvideTab = param.tab(newArray as Array<String>)
                        if (paramProvideTab.isNotEmpty()) {
                            return paramProvideTab
                        }
                    }
                }

                return pair.second.tab(sender, newArray as Array<String>)
            }
        }

        return result
    }
}