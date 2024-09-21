package io.github.gdrfgdrf.cutebedwars.abstracts.finder

import io.github.gdrfgdrf.cutebedwars.abstracts.game.editing.IChanges
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import org.bukkit.command.CommandSender

@Service("changes_finder")
@KotlinSingleton
interface IChangesFinder {
    fun find(sender: CommandSender, onFound: (IChanges<*>) -> Unit): IFindResult

    companion object {
        fun get(): IChangesFinder = Mediator.get(IChangesFinder::class.java)!!
    }

}