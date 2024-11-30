package io.github.gdrfgdrf.cutebedwars.abstracts.commands

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IDescriptions
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IParamTypes
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPermissions
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ILocalizationContext
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ITranslationTextAgent
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet
import org.bukkit.command.CommandSender

@Service("param", singleton = false)
interface IParam {
    val description: IDescriptions
    val content: String

    fun tab(sender: CommandSender, args: Array<String>): MutableList<String>
    fun validate(sender: CommandSender, args: Array<String>, currentIndex: Int, any: Any): Boolean
    fun convenient(sender: CommandSender): ITranslationTextAgent?

    companion object {
        fun new(descriptionName: String, typeName: String): IParam = Mediator.get(
            IParam::class.java,
            ArgumentSet(arrayOf(IDescriptions.find(descriptionName), IParamTypes.valueOf(typeName)))
        )!!

        fun new(description: IDescriptions, type: IParamTypes): IParam =
            Mediator.get(IParam::class.java, ArgumentSet(arrayOf(description, type)))!!
    }
}