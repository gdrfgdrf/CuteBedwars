package io.github.gdrfgdrf.cutebedwars.commands.sub

import io.github.gdrfgdrf.cutebedwars.abstracts.core.Disabler
import io.github.gdrfgdrf.cutebedwars.abstracts.core.Enabler
import io.github.gdrfgdrf.cutebedwars.abstracts.core.Loader
import io.github.gdrfgdrf.cutebedwars.abstracts.core.Plugin
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.PluginState
import io.github.gdrfgdrf.cutebedwars.commands.base.SubCommand
import io.github.gdrfgdrf.cutebedwars.commons.enums.Commands
import io.github.gdrfgdrf.cutebedwars.locale.collect.CommandDescriptionLanguage
import io.github.gdrfgdrf.cutebedwars.locale.collect.CommandLanguage
import io.github.gdrfgdrf.cutebedwars.locale.collect.CommandSyntaxLanguage
import io.github.gdrfgdrf.cutebedwars.locale.collect.CommonLanguage
import io.github.gdrfgdrf.cutebedwars.locale.collect.RequestLanguage
import io.github.gdrfgdrf.cutebedwars.locale.localizationScope
import io.github.gdrfgdrf.cutebedwars.request.Requests
import io.github.gdrfgdrf.cutebedwars.request.enums.RequestStatuses
import io.github.gdrfgdrf.cutebedwars.request.enums.RequestTypes
import io.github.gdrfgdrf.cuteframework.locale.LanguageString
import org.bukkit.command.CommandSender
import java.util.concurrent.TimeUnit

object Reload : SubCommand(
    Commands.RELOAD,
) {
    override fun syntax(): LanguageString? = CommandSyntaxLanguage.RELOAD
    override fun description(): LanguageString? = CommandDescriptionLanguage.RELOAD

    override fun run(sender: CommandSender, args: Array<String>) {
        localizationScope(sender) {
            val pair = Requests.auto(type = RequestTypes.RELOAD, sender = sender)
            val new = pair.first
            val request = pair.second

            if (request == null) {
                message(RequestLanguage.CREATE_FAILED)
                    .send()
                return@localizationScope
            }
            if (new) {
                message(CommonLanguage.RELOAD_WARRING)
                    .format(TimeUnit.SECONDS.convert(request.timeout, request.timeUnit))
                    .send()
                return@localizationScope
            }
            Requests.removeForAuto(type = RequestTypes.RELOAD, sender = sender)

            if (Plugin.get().state() == PluginState.LOADING) {
                message(CommonLanguage.PHASE_ERROR)
                    .send()
                return@localizationScope
            }

            message(CommonLanguage.RELOADING_PLUGIN)
                .send()

            Plugin.get().state(PluginState.LOADING)

            Disabler.get().reloadPhase()
            Loader.get().reloadPhase()
            Enabler.get().reloadPhase()

            Plugin.get().state(PluginState.RUNNING)

            message(CommonLanguage.RELOAD_FINISHED)
                .send()
        }
    }

}