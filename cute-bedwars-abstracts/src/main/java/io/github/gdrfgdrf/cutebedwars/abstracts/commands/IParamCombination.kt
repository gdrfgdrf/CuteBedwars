package io.github.gdrfgdrf.cutebedwars.abstracts.commands

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IDescriptions
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IFindType

interface IParamCombination {
    val array: Array<String>
    val paramSchemeIndex: Int
    val paramScheme: IParamScheme?

    fun param(index: Int): IParam
    fun find(descriptionName: String, providedIndex: Int): String?
    fun find(description: IDescriptions, providedIndex: Int): String?

    fun pageIndex(providedIndex: Int): Int
    fun pageIndex(): Int

    fun findType(providedIndex: Int): IFindType?
    fun findType(): IFindType?

    fun string(description: IDescriptions, providedIndex: Int): String?
    fun string(descriptionName: String, providedIndex: Int): String?
    fun string(description: IDescriptions): String?
    fun string(descriptionName: String): String?

    fun notNullString(description: IDescriptions, providedIndex: Int): String
    fun notNullString(descriptionName: String, providedIndex: Int): String
    fun notNullString(description: IDescriptions): String
    fun notNullString(descriptionName: String): String

    fun areaIdentifier(providedIndex: Int): String
    fun areaIdentifier(): String
    fun gameIdentifier(providedIndex: Int): String
    fun gameIdentifier(): String
}