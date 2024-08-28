package io.github.gdrfgdrf.cutebedwars.enums

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IFindType
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumServiceImpl

@EnumServiceImpl("find_type", searcher = "search")
enum class FindType(private val identifier: String) : IFindType {
    BY_ID("by-id"),
    BY_NAME("by-name");

    companion object {
        private val map = HashMap<String, FindType>()
        init {
            entries.toTypedArray().forEach {
                map[it.identifier] = it
            }
        }

        private fun valueOfOrNull(name: String): FindType? {
            runCatching {
                var result = map[name]
                if (result == null) {
                    result = FindType.valueOf(name)
                }
                return result
            }.onFailure {
                return null
            }
            return null
        }

        @JvmStatic
        fun search(name: String): List<IFindType> {
            val result = arrayListOf<IFindType>()

            val findType = valueOfOrNull(name)
            if (findType != null) {
                result.add(findType)
            }

            return result
        }
    }
}