package io.github.gdrfgdrf.cutebedwars.locale.extension

import io.github.gdrfgdrf.cutebedwars.locale.LocalizationContext
import io.github.gdrfgdrf.cutebedwars.locale.collect.CommonLanguage
import io.github.gdrfgdrf.cutebedwars.locale.localizationScope
import org.bukkit.command.CommandSender

fun middleWork(commandSender: CommandSender, work: LocalizationContext.() -> Unit) {
    localizationScope(commandSender) {
        message(CommonLanguage.COMMON_TOP)
            .send()

        work(this)

        message(CommonLanguage.COMMON_BOTTOM)
            .send()
    }
}