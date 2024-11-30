package io.github.gdrfgdrf.cutebedwars.commands.common

import io.github.gdrfgdrf.cutebedwars.abstracts.commands.IParam
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IDescriptions
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IParamTypes
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPermissions
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ILocalizationContext
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ITranslationTextAgent
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.localizationScope
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet
import org.bukkit.command.CommandSender

@ServiceImpl("param", needArgument = true)
class Param(
    argumentSet: ArgumentSet
): IParam {
    private val description: IDescriptions = argumentSet.args[0] as IDescriptions
    private val type: IParamTypes = argumentSet.args[1] as IParamTypes

    override fun description(): IDescriptions = description

    override fun content(): String {
        return "<" + description.name.lowercase() + ">"
    }

    override fun tab(sender: CommandSender, args: Array<String>): MutableList<String> {
        return type.tab(sender, args)
    }

    override fun validate(sender: CommandSender, args: Array<String>, currentIndex: Int, any: Any): Boolean {
        return type.validate(sender, args, currentIndex, any)
    }

    override fun convenient(sender: CommandSender): ITranslationTextAgent? {
        val content = content()
        if (!content.contains("args")) {
            return null
        }
        if (!content.startsWith("<") || !content.endsWith(">")) {
            return null
        }

        return localizationScope(sender) {
            val onlyArgument = content.substring(1, content.indexOf(">"))
            val searchResult = IDescriptions.search(onlyArgument)?.stream()
                ?.filter { description ->
                    return@filter !(description.administration
                            && !IPermissions.valueOf("QUERY_ADMINISTRATION_DESCRIPTION").hasPermission(sender))
                }
                ?.findAny()
                ?.orElse(null)

            val argumentText = text(content)
                .runCommand("/cbw query description args $onlyArgument")

            if (searchResult != null) {
                val value = searchResult.value()
                if (value != null) {
                    argumentText.showText(value.operate().string)
                }
            }

            return@localizationScope argumentText
        }
    }
}