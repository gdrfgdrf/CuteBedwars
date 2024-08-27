package io.github.gdrfgdrf.cutebedwars.enums

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IParamTypes
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.IManagers
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
    SEARCH_BY_ID_OR_NAME {
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
    AREAS {
        override fun validate(args: Array<String>, currentIndex: Int, any: Any): Boolean {
            if (any !is String) {
                return false
            }
            val searchType = args[currentIndex - 1]

            if (searchType == "by-id") {
                return any.isLong()
            }
            if (searchType == "by-name") {
                return true
            }

            return false
        }

        override fun tab(args: Array<String>): MutableList<String> {
            val list = IManagers.get().list()
            val searchType = args[args.size - 2]

            if (searchType == "by-id") {
                return list.stream().map { it.area().id.toString() }.toList()
            }
            if (searchType == "by-name") {
                return list.stream().map { it.area().name }.toList()
            }
            return arrayListOf()
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



    ;

    override fun name_(): String = name
}