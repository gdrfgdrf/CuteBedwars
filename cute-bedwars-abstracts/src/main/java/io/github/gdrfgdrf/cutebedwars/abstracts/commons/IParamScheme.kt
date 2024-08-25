package io.github.gdrfgdrf.cutebedwars.abstracts.commons

import io.github.gdrfgdrf.cutebedwars.abstracts.commands.IParam
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet

@Service("param_scheme", singleton = false)
interface IParamScheme {
    fun add(descriptionName: String, typeName: String)
    fun get(): List<IParam>
    fun length(): Int

    companion object {
        fun get(builder: IParamScheme.() -> Unit): IParamScheme = Mediator.get(
            IParamScheme::class.java, ArgumentSet(
                arrayOf(builder)
            )
        )!!
    }
}