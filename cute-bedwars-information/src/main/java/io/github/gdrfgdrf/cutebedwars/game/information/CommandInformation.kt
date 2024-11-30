package io.github.gdrfgdrf.cutebedwars.game.information

import io.github.gdrfgdrf.cutebedwars.abstracts.commands.ISubCommandManager
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands
import io.github.gdrfgdrf.cutebedwars.abstracts.information.ICommandInformation
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ITranslationAgent
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommonLanguage
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.localizationScope
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import org.bukkit.command.CommandSender

@ServiceImpl("command_information")
object CommandInformation : ICommandInformation {
    override fun convert(sender: CommandSender, command: ICommands): List<ITranslationAgent> =
        localizationScope(sender) {
            val subCommand = ISubCommandManager.instance().get(command)

            val messages = arrayListOf<ITranslationAgent>().apply {
                add(
                    message(CommonLanguage.COMMAND_RAW_IS)
                        .format0(command.getRaw())
                )
                subCommand?.description?.let { description ->
                    add(
                        message(CommonLanguage.COMMAND_DESCRIPTION_IS)
                            .format0(
                                message(description).buildString()
                            )
                    )
                }

                val booleanTrue = text(CommonLanguage.BOOLEAN_TRUE).string
                val booleanFalse = text(CommonLanguage.BOOLEAN_FALSE).string
                val none = text(CommonLanguage.NONE).string

                add(
                    message(CommonLanguage.COMMAND_IS_ALLOW_EMPTY_PARAM)
                        .format0(if (command.allowEmptyParam) booleanTrue else booleanFalse)
                )

                add(
                    message(CommonLanguage.COMMAND_PARAM_SCHEME_IS)
                )

                if (command.paramSchemes.isNullOrEmpty()) {
                    add(
                        message(CommonLanguage.PARAM_SCHEME_FORMAT)
                            .format0(none)
                    )
                } else {
                    command.paramSchemes!!.forEach { paramScheme ->
                        val content = paramScheme.content(true)
                        add(
                            message(CommonLanguage.PARAM_SCHEME_FORMAT)
                                .format0(content)
                                .apply {
                                    get0().apply {
                                        rebuildParts()

                                        val length = paramScheme.length()

                                        for (i in 0 until length) {
                                            val param = paramScheme.params[i]
                                            val description = param.description

                                            val value = description.value()
                                            if (value != null) {
                                                if (enablePart) {
                                                    showTextInPart(i, value.operate().string)
                                                } else {
                                                    showText(value.operate().string)
                                                }
                                            }

                                            if (enablePart) {
                                                runCommandInPart(i, "/cbw query description args ${description.name.lowercase()}")
                                            } else {
                                                runCommand("/cbw query description args ${description.name.lowercase()}")
                                            }
                                        }
                                    }
                                }
                        )
                    }

                    add(
                        message(CommonLanguage.COMMAND_INFO_TIP)
                    )
                }
            }

            return@localizationScope messages
        }
}