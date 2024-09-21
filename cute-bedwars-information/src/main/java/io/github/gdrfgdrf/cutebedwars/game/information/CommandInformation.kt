package io.github.gdrfgdrf.cutebedwars.game.information

import io.github.gdrfgdrf.cutebedwars.abstracts.commands.ISubCommandManager
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands
import io.github.gdrfgdrf.cutebedwars.abstracts.information.ICommandInformation
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ILocalizationMessage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommonLanguage
import io.github.gdrfgdrf.cutebedwars.locale.localizationScope
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import org.bukkit.command.CommandSender

@ServiceImpl("command_information")
object CommandInformation : ICommandInformation {
    override fun convert(sender: CommandSender, command: ICommands): List<ILocalizationMessage> =
        localizationScope(sender) {
            val subCommand = ISubCommandManager.get().get(command)

            val messages = arrayListOf<ILocalizationMessage>().apply {
                add(
                    message(CommonLanguage.COMMAND_RAW_IS)
                        .format(command.getRaw())
                )
                subCommand?.description()?.let { description ->
                    add(
                        message(CommonLanguage.COMMAND_DESCRIPTION_IS)
                            .format(
                                message(description)
                                    .toString()
                            )
                    )
                }

                val booleanTrue = message(CommonLanguage.BOOLEAN_TRUE).toString()
                val booleanFalse = message(CommonLanguage.BOOLEAN_FALSE).toString()
                val none = message(CommonLanguage.NONE).toString()

                add(
                    message(CommonLanguage.COMMAND_IS_ALLOW_EMPTY_PARAM)
                        .format(if (command.allowEmptyParam()) booleanTrue else booleanFalse)
                )

                add(
                    message(CommonLanguage.COMMAND_PARAM_SCHEME_IS)
                )

                if (command.paramsSchemes().isNullOrEmpty()) {
                    add(
                        message(CommonLanguage.PARAM_SCHEME_FORMAT)
                            .format(none)
                    )
                } else {
                    command.paramsSchemes()!!.forEach { paramScheme ->
                        val string = paramScheme.get()
                        add(
                            message(CommonLanguage.PARAM_SCHEME_FORMAT)
                                .format(string)
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