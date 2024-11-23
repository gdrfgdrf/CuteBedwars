package io.github.gdrfgdrf.cutebedwars.abstracts.selection

import io.github.gdrfgdrf.cutebedwars.abstracts.utils.IStopSignal
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Coordinate
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet
import org.bukkit.Particle
import org.bukkit.World

@Service("selection", singleton = false)
interface ISelection {
    val pos1: Coordinate
    val pos2: Coordinate

    fun spawnParticle(particle: Particle, world: World, frequency: Long): IStopSignal
    fun destroy()

    companion object {
        fun new(pos1: Coordinate, pos2: Coordinate): ISelection = Mediator.get(
            ISelection::class.java, ArgumentSet(
                arrayOf(pos1, pos2)
            )
        )!!

        fun new(
            x1: Double,
            y1: Double,
            z1: Double,
            x2: Double,
            y2: Double,
            z2: Double
        ): ISelection {
            val pos1 = Coordinate()
            pos1.x = x1
            pos1.y = y1
            pos1.z = z1
            val pos2 = Coordinate()
            pos2.x = x2
            pos2.y = y2
            pos2.z = z2

            return new(pos1, pos2)
        }
    }
}