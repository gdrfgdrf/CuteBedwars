package io.github.gdrfgdrf.cutebedwars.abstracts.commands

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IDescriptions
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IParamTypes
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet

@Service("param", singleton = false)
interface IParam {
    fun get(): String?
    fun tab(args: Array<String>): MutableList<String>
    fun validate(any: Any): Boolean

    companion object {
        fun get(descriptionName: String, typeName: String): IParam = Mediator.get(
            IParam::class.java,
            ArgumentSet(arrayOf(IDescriptions.find(descriptionName), IParamTypes.valueOf(typeName)))
        )!!

        fun get(description: IDescriptions, type: IParamTypes): IParam =
            Mediator.get(IParam::class.java, ArgumentSet(arrayOf(description, type)))!!
    }
}