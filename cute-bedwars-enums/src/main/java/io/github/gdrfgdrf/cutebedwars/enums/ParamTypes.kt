package io.github.gdrfgdrf.cutebedwars.enums

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IParamTypes
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.IChangeTypeRegistry
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.IManagers
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.area.IAreaManager
import io.github.gdrfgdrf.cutebedwars.utils.extension.isInt
import io.github.gdrfgdrf.cutebedwars.utils.extension.isLong
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumServiceImpl

@EnumServiceImpl("param_types_enum")
enum class ParamTypes : IParamTypes {
    NOT_BLANK_STRING {
        override fun validate(args: Array<String>, currentIndex: Int, any: Any): Boolean {
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
        override fun validate(args: Array<String>, currentIndex: Int, any: Any): Boolean {
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
        override fun validate(args: Array<String>, currentIndex: Int, any: Any): Boolean {
            if (any !is String) {
                return false
            }
            return any == "by-id" || any == "by-name"
        }

        override fun tab(args: Array<String>): MutableList<String> {
            return arrayListOf("by-id", "by-name")
        }
    },
    POSITIVE_NUMBER {
        override fun validate(args: Array<String>, currentIndex: Int, any: Any): Boolean {
            if (any !is String) {
                return false
            }
            val int = any.toIntOrNull() ?: return false
            return int > 0
        }
    },
    AREAS {
        override fun validate(args: Array<String>, currentIndex: Int, any: Any): Boolean {
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

        override fun tab(args: Array<String>): MutableList<String> {
            val list = IManagers.get().list()
            val findType = args[args.size - 2]

            if (findType == "by-id") {
                return list.stream()
                    .map {
                        it.area().id.toString()
                    }
                    .toList()
            }
            if (findType == "by-name") {
                return list.stream()
                    .map {
                        it.area().name
                    }
                    .toList()
            }
            return arrayListOf()
        }
    },
    GAMES {
        override fun validate(args: Array<String>, currentIndex: Int, any: Any): Boolean {
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

        override fun tab(args: Array<String>): MutableList<String> {
            val areaFindType = args[args.size - 4]
            val areaIdentifier = args[args.size - 3]
            if (areaFindType.isBlank()) {
                return arrayListOf()
            }
            val gameFindType = args[args.size - 2]
            if (gameFindType.isBlank()) {
                return arrayListOf()
            }

            val managers = IManagers.get()
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
            if (areaManager == null) {
                return arrayListOf()
            }

            if (gameFindType == "by-id") {
                return areaManager.context()
                    .games()
                    .stream()
                    .map {
                        it.game().id.toString()
                    }
                    .toList()
            }
            if (gameFindType == "by-name") {
                return areaManager.context()
                    .games()
                    .stream()
                    .map {
                        it.game().name
                    }
                    .toList()
            }
            return arrayListOf()
        }

    },
    CHANGES {
        override fun validate(args: Array<String>, currentIndex: Int, any: Any): Boolean {
            if (any !is String) {
                return false
            }

            val names = arrayListOf<String>()
            IChangeTypeRegistry.get().forEach { s, _ ->
                names.add(s)
            }
            return names.contains(any)
        }

        override fun tab(args: Array<String>): MutableList<String> {
            val names = arrayListOf<String>()
            IChangeTypeRegistry.get().forEach { s, _ ->
                names.add(s)
            }

            return names
        }
    }


    ;

    override fun name_(): String = name
}