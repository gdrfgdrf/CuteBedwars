package io.github.gdrfgdrf.cutebedwars.commands.common

import io.github.gdrfgdrf.cutebedwars.abstracts.commands.IParam
import io.github.gdrfgdrf.cutebedwars.abstracts.commons.IParamScheme
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet

@ServiceImpl("param_scheme", needArgument = true, instanceGetter = "create")
class ParamScheme : IParamScheme {
    private val list = arrayListOf<IParam>()

    override fun add(descriptionName: String, typeName: String) {
        val param = IParam.get(descriptionName, typeName)
        list.add(param)
    }

    override fun params(): List<IParam> {
        return list
    }

    override fun length(): Int {
        return list.size
    }

    override fun get(): String {
        val stringBuilder = StringBuilder()

        val list = params()
        list.forEachIndexed { index2, param ->
            if (index2 != list.size - 1) {
                stringBuilder.append(param.get()).append(" ")
            } else {
                stringBuilder.append(param.get())
            }
        }

        return stringBuilder.toString()
    }

    companion object {
        @JvmStatic
        @Suppress("UNCHECKED_CAST")
        fun create(argumentSet: ArgumentSet): IParamScheme {
            return create(argumentSet.args[0] as IParamScheme.() -> Unit)
        }

        fun create(builder: IParamScheme.() -> Unit): IParamScheme {
            val scheme = ParamScheme()
            builder(scheme)
            return scheme
        }

        val NO_MATCH = -1
    }

}