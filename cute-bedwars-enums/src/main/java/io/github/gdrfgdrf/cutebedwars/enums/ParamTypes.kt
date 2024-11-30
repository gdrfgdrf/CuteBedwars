package io.github.gdrfgdrf.cutebedwars.enums

import io.github.gdrfgdrf.cutebedwars.abstracts.editing.AbstractAreaEditor
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.AbstractEditor
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.AbstractGameEditor
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IParamTypes
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.IChangeTypeRegistry
import io.github.gdrfgdrf.cutebedwars.abstracts.finder.IEditorFinder
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.IManagers
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.area.IAreaManager
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.isInt
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.isLong
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.toBooleanOrNull
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumServiceImpl
import org.bukkit.command.CommandSender

@EnumServiceImpl("param_types_enum")
enum class ParamTypes : IParamTypes {
    BOOLEAN {
        override fun validate(sender: CommandSender, args: Array<String>, currentIndex: Int, any: Any): Boolean {
            if (any !is String) {
                return false
            }
            if (any.isInt()) {
                return false
            }

            return any.toBooleanOrNull() != null
        }

        override fun tab(sender: CommandSender, args: Array<String>): MutableList<String> {
            return arrayListOf("true", "false")
        }
    },
    NOT_BLANK_STRING {
        override fun validate(sender: CommandSender, args: Array<String>, currentIndex: Int, any: Any): Boolean {
            if (any !is String) {
                return false
            }
            if (any.isInt()) {
                return false
            }

            return any.isNotBlank()
        }
    },
    VALUE {
        override fun validate(sender: CommandSender, args: Array<String>, currentIndex: Int, any: Any): Boolean {
            if (any !is String) {
                return false
            }
            if (any.isBlank()) {
                return false
            }
            return true
        }
    },
    FIND_BY_ID_OR_NAME {
        override fun validate(sender: CommandSender, args: Array<String>, currentIndex: Int, any: Any): Boolean {
            if (any !is String) {
                return false
            }
            return any == "by-id" || any == "by-name"
        }

        override fun tab(sender: CommandSender, args: Array<String>): MutableList<String> {
            return arrayListOf("by-id", "by-name")
        }
    },
    BY_ID {
        override fun validate(sender: CommandSender, args: Array<String>, currentIndex: Int, any: Any): Boolean {
            if (any !is String) {
                return false
            }
            return any == "by-id"
        }

        override fun tab(sender: CommandSender, args: Array<String>): MutableList<String> {
            return arrayListOf("by-id")
        }
    },
    BY_NAME {
        override fun validate(sender: CommandSender, args: Array<String>, currentIndex: Int, any: Any): Boolean {
            if (any !is String) {
                return false
            }
            return any == "by-name"
        }

        override fun tab(sender: CommandSender, args: Array<String>): MutableList<String> {
            return arrayListOf("by-name")
        }
    },
    POSITIVE_NUMBER {
        override fun validate(sender: CommandSender, args: Array<String>, currentIndex: Int, any: Any): Boolean {
            if (any !is String) {
                return false
            }
            val int = any.toIntOrNull() ?: return false
            return int > 0
        }
    },
    AREAS {
        override fun validate(sender: CommandSender, args: Array<String>, currentIndex: Int, any: Any): Boolean {
            if (any !is String) {
                return false
            }
            val findType = args[currentIndex - 1]

            if (findType == "by-id") {
                return any.isLong()
            }
            if (findType == "by-name") {
                return true
            }

            return false
        }

        override fun tab(sender: CommandSender, args: Array<String>): MutableList<String> {
            val list = IManagers.instance().list()
            val findType = args[args.size - 2]

            if (findType == "by-id") {
                return list.stream()
                    .map {
                        it.area.id.toString()
                    }
                    .toList()
            }
            if (findType == "by-name") {
                return list.stream()
                    .map {
                        it.area.name
                    }
                    .toList()
            }
            return arrayListOf()
        }
    },
    GAMES {
        override fun validate(sender: CommandSender, args: Array<String>, currentIndex: Int, any: Any): Boolean {
            if (any !is String) {
                return false
            }
            val findType = args[currentIndex - 1]

            if (findType == "by-id") {
                return any.isLong()
            }
            if (findType == "by-name") {
                return true
            }

            return false
        }

        override fun tab(sender: CommandSender, args: Array<String>): MutableList<String> {
            val areaFindType = args[args.size - 4]
            val areaIdentifier = args[args.size - 3]
            if (areaFindType.isBlank()) {
                return arrayListOf()
            }
            val gameFindType = args[args.size - 2]
            if (gameFindType.isBlank()) {
                return arrayListOf()
            }

            val managers = IManagers.instance()
            var areaManager: IAreaManager? = null

            if (areaFindType == "by-id") {
                if (!areaIdentifier.isLong()) {
                    return arrayListOf()
                }

                areaManager = managers.get(areaIdentifier.toLong())
            }
            if (areaFindType == "by-name") {
                val areaManagers = managers.get(areaIdentifier)
                if (!areaManagers.isNullOrEmpty() && areaManagers.size == 1) {
                    areaManager = areaManagers[0]
                }
            }
            if (areaManager?.context == null) {
                return arrayListOf()
            }

            if (gameFindType == "by-id") {

                return areaManager.context!!
                    .games
                    .stream()
                    .map {
                        it.game.id.toString()
                    }
                    .toList()
            }
            if (gameFindType == "by-name") {
                return areaManager.context!!
                    .games
                    .stream()
                    .map {
                        it.game.name
                    }
                    .toList()
            }
            return arrayListOf()
        }

    },
    CHANGES {
        override fun validate(sender: CommandSender, args: Array<String>, currentIndex: Int, any: Any): Boolean {
            if (any !is String) {
                return false
            }

            var editor: AbstractEditor<*>? = null
            IEditorFinder.instance().find(sender, false) {
                editor = it
            }

            val names = arrayListOf<String>()
            if (editor != null) {
                IChangeTypeRegistry.instance().forEach { s, changeType ->
                    if (changeType.type == editor!!.type() ||
                        editor!!.t!!::class.java.superclass == changeType.type ||
                        editor!!.t!!::class.java.interfaces.contains(changeType.type)
                    ) {
                        names.add(s)
                    }
                }
            }
            return names.contains(any)
        }

        override fun tab(sender: CommandSender, args: Array<String>): MutableList<String> {
            var editor: AbstractEditor<*>? = null
            IEditorFinder.instance().find(sender, false) {
                editor = it
            }

            val names = arrayListOf<String>()
            if (editor != null) {
                IChangeTypeRegistry.instance().forEach { s, changeType ->
                    if (changeType.type == editor!!.type() ||
                        editor!!.t!!::class.java.superclass == changeType.type ||
                        editor!!.t!!::class.java.interfaces.contains(changeType.type)
                    ) {
                        names.add(s)
                    }
                }
            }

            return names
        }
    },
    COMMIT_IDS {
        override fun validate(sender: CommandSender, args: Array<String>, currentIndex: Int, any: Any): Boolean {
            if (any !is String) {
                return false
            }

            val ids = findIds(sender)
            return ids.contains(any)
        }

        override fun tab(sender: CommandSender, args: Array<String>): MutableList<String> {
            return findIds(sender)
        }

        private fun findIds(sender: CommandSender): MutableList<String> {
            var editor: AbstractEditor<*>? = null
            IEditorFinder.instance().find(sender, false) {
                editor = it
            }

            val ids = arrayListOf<String>()
            if (editor != null) {
                if (editor is AbstractAreaEditor) {
                    val commitStorage = (editor as AbstractAreaEditor).t.manager.commitStorage ?: return arrayListOf()
                    val commits = commitStorage.get()
                    commits?.commitsList?.forEach {
                        ids.add(it.id)
                    }
                }
                if (editor is AbstractGameEditor) {
                    val gameContext = (editor as AbstractGameEditor).t

                    val commitStorage = gameContext.commitStorage
                    val gameCommits = commitStorage.get()
                    val commitsList = gameCommits?.getMapOrDefault(gameContext.game.id.toString(), null)?.commitsList

                    commitsList?.forEach {
                        ids.add(it.id)
                    }
                }
            }

            return ids
        }
    },
    CHANGE_IDS {
        override fun validate(sender: CommandSender, args: Array<String>, currentIndex: Int, any: Any): Boolean {
            if (any !is String) {
                return false
            }

            val ids = findIds(sender)
            return ids.contains(any)
        }

        override fun tab(sender: CommandSender, args: Array<String>): MutableList<String> {
            return findIds(sender)
        }

        private fun findIds(sender: CommandSender): MutableList<String> {
            var editor: AbstractEditor<*>? = null
            IEditorFinder.instance().find(sender, false) {
                editor = it
            }

            val ids = arrayListOf<String>()
            if (editor != null && editor!!.currentChanges() != null) {
                val currentChanges = editor!!.currentChanges()
                currentChanges!!.forEach {
                    ids.add(it.id.toString())
                }
            }

            return ids
        }
    }


    ;
}