package io.github.gdrfgdrf.cutebedwars.commands.sub

import io.github.gdrfgdrf.cutebedwars.abstracts.core.IDisabler
import io.github.gdrfgdrf.cutebedwars.abstracts.core.IEnabler
import io.github.gdrfgdrf.cutebedwars.abstracts.core.ILoader
import io.github.gdrfgdrf.cutebedwars.abstracts.core.IPlugin
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPluginState
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IRequestTypes
import io.github.gdrfgdrf.cutebedwars.abstracts.requests.IRequests
import io.github.gdrfgdrf.cutebedwars.abstracts.commands.AbstractSubCommand
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandDescriptionLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandSyntaxLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommonLanguage
import io.github.gdrfgdrf.cutebedwars.locale.localizationScope
import io.github.gdrfgdrf.cuteframework.locale.LanguageString
import org.bukkit.command.CommandSender
import java.util.concurrent.TimeUnit

object Reload : AbstractSubCommand(
    command = ICommands.valueOf("RELOAD"),
) {
    override fun syntax(): LanguageString? = CommandSyntaxLanguage.RELOAD
    override fun description(): LanguageString? = CommandDescriptionLanguage.RELOAD

    override fun run(sender: CommandSender, args: Array<String>, paramSchemeIndex: Int) {
        localizationScope(sender) {
            val pair = IRequests.instance().auto(type = IRequestTypes.valueOf("RELOAD"), sender = sender)
            val new = pair.first
            val request = pair.second

            if (new) {
                message(CommonLanguage.RELOAD_WARRING)
                    .format0(TimeUnit.SECONDS.convert(request.timeout(), request.timeUnit()))
                    .send()
                return@localizationScope
            }
            IRequests.instance().removeForAuto(type = IRequestTypes.valueOf("RELOAD"), sender = sender)

            if (IPlugin.instance().state() == IPluginState.valueOf("LOADING")) {
                message(CommonLanguage.PHASE_ERROR)
                    .send()
                return@localizationScope
            }

            message(CommonLanguage.RELOADING_PLUGIN)
                .send()

            IPlugin.instance().state(IPluginState.valueOf("LOADING"))

            IDisabler.instance().reloadPhase()
            ILoader.instance().reloadPhase()
            IEnabler.instance().reloadPhase()

            IPlugin.instance().state(IPluginState.valueOf("RUNNING"))

            message(CommonLanguage.RELOAD_FINISHED)
                .send()
        }
    }

}