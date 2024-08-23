package io.github.gdrfgdrf.cutebedwars.abstracts.commands

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IDescriptions
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ILocalizationContext
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet
import net.md_5.bungee.api.chat.BaseComponent

@Service("param", singleton = false)
interface IParam {
    fun get(): String?

    companion object {
        fun get(descriptionName: String): IParam = Mediator.get(IParam::class.java, ArgumentSet(arrayOf(IDescriptions.get(descriptionName))))!!
        fun get(description: IDescriptions): IParam = Mediator.get(IParam::class.java, ArgumentSet(arrayOf(description)))!!
    }
}