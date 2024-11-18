package io.github.gdrfgdrf.cutebedwars.abstracts.selection

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import org.bukkit.entity.Player

@Service("selections")
@KotlinSingleton
interface ISelections {
    fun get(player: Player): ISelect?
    fun create(player: Player): ISelect
    fun remove(player: Player)

    companion object {
        fun instance(): ISelections = Mediator.get(ISelection::class.java)!!
    }
}