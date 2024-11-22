package io.github.gdrfgdrf.cutebedwars.abstracts.locale

import org.bukkit.command.CommandSender

fun <R> localizationScope(sender: CommandSender, work: ILocalizationContext.() -> R): R {
    val localizationContext = ILocalizationContext.new(sender)
    return work(localizationContext)
}