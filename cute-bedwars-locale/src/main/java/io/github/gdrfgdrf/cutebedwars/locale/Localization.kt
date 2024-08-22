package io.github.gdrfgdrf.cutebedwars.locale

import org.bukkit.command.CommandSender

fun <R> localizationScope(sender: CommandSender, work: LocalizationContext.() -> R): R {
    val localizationContext = LocalizationContext(sender)
    return work(localizationContext)
}







