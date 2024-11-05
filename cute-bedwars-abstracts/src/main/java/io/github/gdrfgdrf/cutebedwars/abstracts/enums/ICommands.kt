package io.github.gdrfgdrf.cutebedwars.abstracts.enums

import io.github.gdrfgdrf.cutebedwars.abstracts.commons.IParamScheme
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ILocalizationContext
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ITranslationAgent
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ITranslationTextAgent
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumService
import org.bukkit.command.CommandSender

@EnumService("commands_enum")
interface ICommands {
    fun string(): String
    fun onlyPlayer(): Boolean
    fun argsRange(): IntRange
    fun permissions(): IPermissions
    fun allowEmptyParam(): Boolean
    fun node(): ICommandNodes
    fun paramsSchemes(): Array<IParamScheme>?
    fun getRaw(): String
    fun getShort(): String
    fun get(): String

    companion object {
        fun valueOf(name: String): ICommands = Mediator.valueOf(ICommands::class.java, name)!!
        fun values(): Array<*> = Mediator.values(ICommands::class.java)!!

        private val map = HashMap<String, MutableList<ICommands>>()

        @Synchronized
        private fun initMap() {
            values().forEach {
                val list = map.computeIfAbsent((it as ICommands).string()) {
                    ArrayList()
                }
                list.add(it)
            }
        }

        fun find(string: String, node: ICommandNodes): ICommands? {
            if (map.isEmpty()) {
                initMap()
            }
            map[string]?.let { list ->
                val command = list.stream()
                    .filter { commands ->
                        commands.node() == node
                    }
                    .findAny()
                    .orElse(null)

                if (command != null) {
                    return command
                }
            }

            return null
        }

        fun allDisplayOnRootTab(): List<ICommands> {
            if (map.isEmpty()) {
                initMap()
            }
            return map.keys.stream()
                .map {
                    map[it]!!
                }
                .flatMap {
                    it.stream()
                }
                .filter {
                    return@filter it.node() == ICommandNodes.valueOf("ROOT")
                }
                .toList()
        }

        fun getChild(parent: ICommandNodes): List<ICommands> {
            if (map.isEmpty()) {
                initMap()
            }
            return map.keys.stream()
                .map {
                    map[it]!!
                }
                .flatMap {
                    it.stream()
                }
                .filter {
                    return@filter it.node() == parent
                }
                .toList()
        }
    }
}