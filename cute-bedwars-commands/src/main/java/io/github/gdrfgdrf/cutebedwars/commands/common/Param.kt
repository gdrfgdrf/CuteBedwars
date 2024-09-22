package io.github.gdrfgdrf.cutebedwars.commands.common

import io.github.gdrfgdrf.cutebedwars.abstracts.commands.IParam
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IDescriptions
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IParamTypes
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet
import org.bukkit.command.CommandSender

@ServiceImpl("param", needArgument = true)
class Param(
    argumentSet: ArgumentSet
): IParam {
    private val description: IDescriptions = argumentSet.args[0] as IDescriptions
    private val type: IParamTypes = argumentSet.args[1] as IParamTypes

    override fun get(): String {
        return "<" + description.name_().lowercase() + ">"
    }

    override fun tab(sender: CommandSender, args: Array<String>): MutableList<String> {
        return type.tab(sender, args)
    }

    override fun validate(sender: CommandSender, args: Array<String>, currentIndex: Int, any: Any): Boolean {
        return type.validate(sender, args, currentIndex, any)
    }
}