package io.github.gdrfgdrf.cutebedwars.abstracts.commands

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet

@Service("param_scheme", singleton = false)
interface IParamScheme {
    val params: List<IParam>
    val length: Int

    fun add(descriptionName: String, typeName: String)
    fun content(partDivider: Boolean = false): String

    companion object {
        fun new(builder: IParamScheme.() -> Unit): IParamScheme = Mediator.get(
            IParamScheme::class.java, ArgumentSet(
                arrayOf(builder)
            )
        )!!
    }
}