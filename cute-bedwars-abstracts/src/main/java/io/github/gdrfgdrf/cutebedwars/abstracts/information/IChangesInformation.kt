package io.github.gdrfgdrf.cutebedwars.abstracts.information

import io.github.gdrfgdrf.cutebedwars.abstracts.editing.IChanges
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ILocalizationMessage
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ITranslationAgent
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import org.bukkit.command.CommandSender

@Service("changes_information")
@KotlinSingleton
interface IChangesInformation {
    fun convert(sender: CommandSender, changes: IChanges<*>): List<ITranslationAgent>

    companion object {
        fun instance(): IChangesInformation = Mediator.get(IChangesInformation::class.java)!!
    }
}