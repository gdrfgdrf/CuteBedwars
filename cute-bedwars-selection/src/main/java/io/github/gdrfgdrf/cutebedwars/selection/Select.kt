package io.github.gdrfgdrf.cutebedwars.selection

import io.github.gdrfgdrf.cutebedwars.abstracts.selection.ISelect
import io.github.gdrfgdrf.cutebedwars.abstracts.selection.ISelection
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.IStopSignal
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Coordinate
import org.bukkit.Particle
import org.bukkit.World
import org.bukkit.entity.Player

class Select(
    override val player: Player
) : ISelect {
    private val pos1 = Point(player)
    private val pos2 = Point(player)
    private var cachedSelection: ISelection? = null

    override var stopSignal: IStopSignal? = null

    override fun pos1(): Coordinate? = pos1.coordinate
    override fun pos2(): Coordinate? = pos2.coordinate

    override fun pos1(coordinate: Coordinate) {
        val currentWorld = player.world
        if (pos1.world != currentWorld || pos2.world != currentWorld) {
            pos1.set(coordinate)
            pos2.set(null)
            if (stopSignal != null) {
                stopSignal!!.stop()
            }
        } else {
            pos1.set(coordinate)
        }
    }

    override fun pos2(coordinate: Coordinate) {
        val currentWorld = player.world
        if (pos1.world != currentWorld || pos2.world != currentWorld) {
            pos1.set(null)
            pos2.set(coordinate)
            if (stopSignal != null) {
                stopSignal!!.stop()
            }
        } else {
            pos2.set(coordinate)
        }
    }

    override fun trySpawnParticle(particle: Particle, frequency: Long): IStopSignal? {
        runCatching {
            return spawnParticle(particle, frequency)
        }.onFailure {

        }
        return null
    }

    override fun spawnParticle(particle: Particle, frequency: Long): IStopSignal {
        val currentWorld = player.world
        if (pos1.world != currentWorld || pos2.world != currentWorld) {
            throw IllegalArgumentException("the selection's point is in a different world than the player's")
        }

        val selection = build()

        if (this.stopSignal != null) {
            this.stopSignal!!.stop()
        }

        this.stopSignal = selection.spawnParticle(particle, currentWorld, frequency)
        return this.stopSignal!!
    }

    override fun build(): ISelection {
        if (pos1.coordinate == null || pos2.coordinate == null) {
            throw IllegalArgumentException("pos1 and pos2 is required")
        }
        if (cachedSelection != null &&
            cachedSelection!!.pos1 == pos1.coordinate &&
            cachedSelection!!.pos2 == pos2.coordinate) {
            return cachedSelection!!
        }
        if (cachedSelection != null) {
            cachedSelection!!.destroy()
        }
        cachedSelection = Selection(pos1.coordinate!!, pos2.coordinate!!)
        return cachedSelection!!
    }

    override fun destroy() {
        stopSignal?.stop()
        pos1.set(null)
        pos2.set(null)
    }

    class Point(
        val player: Player
    ) {
        var coordinate: Coordinate? = null
        var world: World? = null

        fun set(coordinate: Coordinate?) {
            this.coordinate = coordinate
            this.world = player.world
        }
    }
}