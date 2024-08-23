package io.github.gdrfgdrf.cutebedwars.commands.common

import io.github.gdrfgdrf.cutebedwars.abstracts.commands.IParam
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IDescriptions
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet

@ServiceImpl("param", needArgument = true)
class Param(
    argumentSet: ArgumentSet
): IParam {
    private val description: IDescriptions = argumentSet.args[0] as IDescriptions

    override fun get(): String {
        return "<" + description.name_().lowercase() + ">"
    }
}