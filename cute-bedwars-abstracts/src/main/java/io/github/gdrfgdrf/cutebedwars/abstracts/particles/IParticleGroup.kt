package io.github.gdrfgdrf.cutebedwars.abstracts.particles

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IParticleStatuses
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.IStopSignal
import org.bukkit.World
import org.bukkit.entity.Player

interface IParticleGroup {
    val name: String
    val parent: IManagedParticle

    fun status(): IParticleStatuses

    fun add(particleInfo: IParticleInfo)
    fun add(x: Double, y: Double, z: Double, count: Int = 1)
    fun remove(particleInfo: IParticleInfo)
    fun removeAt(index: Int)
    fun removeAt(x: Double, y: Double, z: Double)
    fun clear()

    fun spawn(player: Player, far: Boolean = true) {
        spawn(arrayListOf(player), far)
    }
    fun spawn(player: Player, frequency: Long, far: Boolean = true): IStopSignal {
        return spawn(arrayListOf(player), frequency, far)
    }
    fun spawn(playerCollection: Collection<Player>, frequency: Long, far: Boolean = true): IStopSignal
    fun spawn(playerCollection: Collection<Player>, far: Boolean = true)

    fun dismiss()
}