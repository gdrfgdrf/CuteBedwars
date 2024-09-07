package io.github.gdrfgdrf.cutebedwars.abstracts.enums

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumService

@EnumService("command_nodes_enum")
interface ICommandNodes {
    fun string(): String
    fun displayOnRootTab(): Boolean
    fun parent(): ICommandNodes?
    fun get(command: String): String

    companion object {
        fun valueOf(name: String): ICommandNodes = Mediator.valueOf(ICommandNodes::class.java, name)!!
        fun values(): Array<*> = Mediator.values(ICommandNodes::class.java)!!

        private val map = HashMap<String, ICommandNodes>()

        @Synchronized
        private fun initMap() {
            values().forEach {
                map[(it as ICommandNodes).string()] = it
            }
        }

        fun find(command: String): ICommandNodes? {
            if (map.isEmpty()) {
                initMap()
            }
            return map[command]
        }

        fun allDisplayOnRootTab(): List<ICommandNodes> {
            if (map.isEmpty()) {
                initMap()
            }
            return map.keys.stream()
                .map {
                    map[it]!!
                }
                .filter {
                    return@filter it.displayOnRootTab()
                }
                .toList()
        }

        fun getChild(parent: ICommandNodes): List<ICommandNodes> {
            if (map.isEmpty()) {
                initMap()
            }
            return map.keys.stream()
                .map {
                    map[it]!!
                }
                .filter {
                    return@filter it.parent() == parent
                }
                .toList()
        }
    }
}