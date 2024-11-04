package io.github.gdrfgdrf.cutebedwars.commands.common

import io.github.gdrfgdrf.cutebedwars.abstracts.commands.IParam
import io.github.gdrfgdrf.cutebedwars.abstracts.commands.IParamCombination
import io.github.gdrfgdrf.cutebedwars.abstracts.commons.IParamScheme
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IDescriptions
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IFindType
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.toIntOrDefault

class ParamCombination(
    override val array: Array<String>,
    override val paramSchemeIndex: Int,
    override val paramScheme: IParamScheme? = null
) : IParamCombination {
    private fun check() {
        if (paramScheme == null) {
            throw IllegalArgumentException("there is no param is matched")
        }
    }

    override fun param(index: Int): IParam {
        check()
        return paramScheme!!.params()[index]
    }

    override fun find(descriptionName: String, providedIndex: Int): String? {
        return find(IDescriptions.valueOf(descriptionName), providedIndex)
    }

    override fun find(description: IDescriptions, providedIndex: Int): String? {
        check()
        val params = paramScheme!!.params()

        var count = 0
        array.forEachIndexed { index, s ->
            val param = params[index]
            if (param.description() != description) {
                return@forEachIndexed
            }
            if (count == providedIndex) {
                return s
            }

            count++
        }

        return null
    }

    override fun pageIndex(providedIndex: Int): Int {
        if (paramSchemeIndex == ParamScheme.NO_MATCH) {
            return 1
        }

        val pageIndex = find("PAGE_INDEX", providedIndex) ?: return 1
        return pageIndex.toIntOrDefault(1)
    }

    override fun pageIndex(): Int {
        return pageIndex(0)
    }

    override fun findType(providedIndex: Int): IFindType? {
        if (paramSchemeIndex == ParamScheme.NO_MATCH) {
            return null
        }
        val findType = find("FIND_BY_ID_OR_NAME", providedIndex) ?: return null
        return IFindType.find(findType)
    }

    override fun findType(): IFindType? {
        return findType(0)
    }

    override fun string(description: IDescriptions, providedIndex: Int): String? {
        if (paramSchemeIndex == ParamScheme.NO_MATCH) {
            return null
        }
        return find(description, providedIndex)
    }

    override fun string(descriptionName: String, providedIndex: Int): String? {
        return string(IDescriptions.valueOf(descriptionName), providedIndex)
    }

    override fun string(description: IDescriptions): String? {
        return string(description, 0)
    }

    override fun string(descriptionName: String): String? {
        return string(IDescriptions.valueOf(descriptionName), 0)
    }

    override fun notNullString(description: IDescriptions, providedIndex: Int): String {
        if (paramSchemeIndex == ParamScheme.NO_MATCH) {
            return ""
        }
        return find(description, providedIndex) ?: return ""
    }

    override fun notNullString(descriptionName: String, providedIndex: Int): String {
        return string(IDescriptions.valueOf(descriptionName), providedIndex) ?: return ""
    }

    override fun notNullString(description: IDescriptions): String {
        return string(description, 0) ?: return ""
    }

    override fun notNullString(descriptionName: String): String {
        return string(IDescriptions.valueOf(descriptionName), 0) ?: return ""
    }

    companion object {
        fun empty(array: Array<String>) = ParamCombination(array, ParamScheme.NO_MATCH)
        fun create(array: Array<String>, paramSchemeIndex: Int, paramScheme: IParamScheme) =
            ParamCombination(array, paramSchemeIndex, paramScheme)
    }
}