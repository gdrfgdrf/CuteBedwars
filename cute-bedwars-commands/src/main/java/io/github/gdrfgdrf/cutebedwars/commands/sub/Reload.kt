package io.github.gdrfgdrf.cutebedwars.commands.sub

import io.github.gdrfgdrf.cutebedwars.abstracts.core.IDisabler
import io.github.gdrfgdrf.cutebedwars.abstracts.core.IEnabler
import io.github.gdrfgdrf.cutebedwars.abstracts.core.ILoader
import io.github.gdrfgdrf.cutebedwars.abstracts.core.IPlugin
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPluginState
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IRequestTypes
import io.github.gdrfgdrf.cutebedwars.abstracts.requests.IRequests
import io.github.gdrfgdrf.cutebedwars.commands.base.SubCommand
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandDescriptionLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandSyntaxLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommonLanguage
import io.github.gdrfgdrf.cutebedwars.locale.localizationScope
import io.github.gdrfgdrf.cuteframework.locale.LanguageString
import org.bukkit.command.CommandSender
import java.util.concurrent.TimeUnit

object Reload : SubCommand(
    command = ICommands.valueOf("RELOAD"),
) {
    override fun syntax(): LanguageString? = CommandSyntaxLanguage.RELOAD
    override fun description(): LanguageString? = CommandDescriptionLanguage.RELOAD

    override fun run(sender: CommandSender, args: Array<String>, pageSchemeIndex: Int) {
        localizationScope(sender) {
            val pair = IRequests.get().auto(type = IRequestTypes.valueOf("RELOAD"), sender = sender)
            val new = pair.first
            val request = pair.second

            if (new) {
                message(CommonLanguage.RELOAD_WARRING)
                    .format(TimeUnit.SECONDS.convert(request.timeout(), request.timeUnit()))
                    .send()
                return@localizationScope
            }
            IRequests.get().removeForAuto(type = IRequestTypes.valueOf("RELOAD"), sender = sender)

            if (IPlugin.get().state() == IPluginState.valueOf("LOADING")) {
                message(CommonLanguage.PHASE_ERROR)
                    .send()
                return@localizationScope
            }

            message(CommonLanguage.RELOADING_PLUGIN)
                .send()

            IPlugin.get().state(IPluginState.valueOf("LOADING"))

            IDisabler.get().reloadPhase()
            ILoader.get().reloadPhase()
            IEnabler.get().reloadPhase()

            IPlugin.get().state(IPluginState.valueOf("RUNNING"))

            message(CommonLanguage.RELOAD_FINISHED)
                .send()
        }
    }

}