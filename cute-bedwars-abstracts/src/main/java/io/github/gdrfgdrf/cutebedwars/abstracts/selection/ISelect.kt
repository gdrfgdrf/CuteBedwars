package io.github.gdrfgdrf.cutebedwars.abstracts.selection

import io.github.gdrfgdrf.cutebedwars.abstracts.utils.IStopSignal
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Coordinate
import org.bukkit.Particle
import org.bukkit.entity.Player

interface ISelect {
    val player: Player
    var stopSignal: IStopSignal?

    fun pos1(): Coordinate?
    fun pos2(): Coordinate?

    fun pos1(coordinate: Coordinate)
    fun pos2(coordinate: Coordinate)

    fun trySpawnParticle(particle: Particle, frequency: Long): IStopSignal?
    fun spawnParticle(particle: Particle, frequency: Long): IStopSignal

    fun build(): ISelection
    fun destroy()
}