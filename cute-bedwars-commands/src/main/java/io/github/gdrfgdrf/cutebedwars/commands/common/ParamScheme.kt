package io.github.gdrfgdrf.cutebedwars.commands.common

import io.github.gdrfgdrf.cutebedwars.abstracts.commands.IParam
import io.github.gdrfgdrf.cutebedwars.abstracts.commands.IParamScheme
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet

@ServiceImpl("param_scheme", needArgument = true, instanceGetter = "create")
class ParamScheme : IParamScheme {
    override val params = arrayListOf<IParam>()
    override val length = params.size

    override fun add(descriptionName: String, typeName: String) {
        val param = IParam.new(descriptionName, typeName)
        params.add(param)
    }

    override fun content(partDivider: Boolean): String {
        val stringBuilder = StringBuilder()

        val list = params
        list.forEachIndexed { index, param ->
            if (index >= list.size - 1) {
                if (partDivider) {
                    if (index <= 0) {
                        stringBuilder.append(param.content)
                    } else {
                        stringBuilder.append("&|").append(param.content)
                    }
                } else {
                    stringBuilder.append(param.content)
                }
            } else {
                if (partDivider) {
                    if (index <= 0) {
                        stringBuilder.append(param.content).append(" ")
                    } else {
                        stringBuilder.append("&|").append(param.content).append(" ")
                    }
                } else {
                    stringBuilder.append(param.content).append(" ")
                }
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

        private fun create(builder: IParamScheme.() -> Unit): IParamScheme {
            val scheme = ParamScheme()
            builder(scheme)
            return scheme
        }

        const val NO_MATCH = -1
    }

}