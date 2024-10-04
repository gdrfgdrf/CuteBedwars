package io.github.gdrfgdrf.cutebedwars.commands.common

import io.github.gdrfgdrf.cutebedwars.abstracts.commands.IParam
import io.github.gdrfgdrf.cutebedwars.abstracts.commons.IParamScheme
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ITranslationAgent
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ITranslationTextAgent
import io.github.gdrfgdrf.cutebedwars.locale.localizationScope
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet
import org.bukkit.command.CommandSender

@ServiceImpl("param_scheme", needArgument = true, instanceGetter = "create")
class ParamScheme : IParamScheme {
    private val list = arrayListOf<IParam>()

    override fun add(descriptionName: String, typeName: String) {
        val param = IParam.new(descriptionName, typeName)
        list.add(param)
    }

    override fun params(): List<IParam> {
        return list
    }

    override fun length(): Int {
        return list.size
    }

    override fun content(partDivider: Boolean): String {
        val stringBuilder = StringBuilder()

        val list = params()
        list.forEachIndexed { index, param ->
            if (index >= list.size - 1) {
                if (partDivider) {
                    if (index <= 0) {
                        stringBuilder.append(param.content())
                    } else {
                        stringBuilder.append("&|").append(param.content())
                    }
                } else {
                    stringBuilder.append(param.content())
                }
            } else {
                if (partDivider) {
                    if (index <= 0) {
                        stringBuilder.append(param.content()).append(" ")
                    } else {
                        stringBuilder.append("&|").append(param.content()).append(" ")
                    }
                } else {
                    stringBuilder.append(param.content()).append(" ")
                }
            }
        }

        return stringBuilder.toString()
    }

    override fun convenient(sender: CommandSender): ITranslationAgent {
        return localizationScope(sender) {
            val result = message("")

            params().forEachIndexed { index, param ->
                val paramConvenient = param.convenient(sender)
                if (index >= list.size - 1) {
                    if (paramConvenient != null) {
                        result.append(paramConvenient)
                    } else {
                        result.append(param.content())
                    }
                } else {
                    if (paramConvenient != null) {
                        result.append(paramConvenient).append(" ")
                    } else {
                        result.append(param.content()).append(" ")
                    }
                }
            }

            return@localizationScope result
        }
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