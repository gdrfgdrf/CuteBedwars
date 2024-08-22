package io.github.gdrfgdrf.cutebedwars.locale.extension

import io.github.gdrfgdrf.cutebedwars.languages.collect.CommonLanguage
import io.github.gdrfgdrf.cutebedwars.locale.LocalizationContext
import io.github.gdrfgdrf.cutebedwars.locale.localizationScope
import org.bukkit.command.CommandSender

fun middleWork(prefix: String? = null, commandSender: CommandSender, work: LocalizationContext.() -> Unit) {
    localizationScope(commandSender) {
        if (prefix != null) {
            message(CommonLanguage.COMMON_TOP)
                .send(prefix)
        } else {
            message(CommonLanguage.COMMON_TOP)
                .send()
        }

        work(this)

        if (prefix != null) {
            message(CommonLanguage.COMMON_BOTTOM)
                .send(prefix)
        } else {
            message(CommonLanguage.COMMON_BOTTOM)
                .send()
        }
    }
}