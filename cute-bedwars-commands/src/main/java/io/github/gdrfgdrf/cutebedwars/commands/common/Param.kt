package io.github.gdrfgdrf.cutebedwars.commands.common

import io.github.gdrfgdrf.cutebedwars.abstracts.commands.IParam
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IDescriptions
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IParamTypes
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet

@ServiceImpl("param", needArgument = true)
class Param(
    argumentSet: ArgumentSet
): IParam {
    private val description: IDescriptions = argumentSet.args[0] as IDescriptions
    private val type: IParamTypes = argumentSet.args[1] as IParamTypes

    override fun get(): String {
        return "<" + description.name_().lowercase() + ">"
    }

    override fun tab(args: Array<String>): MutableList<String> {
        return type.tab(args)
    }

    override fun validate(any: Any): Boolean {
        return type.validate(any)
    }
}