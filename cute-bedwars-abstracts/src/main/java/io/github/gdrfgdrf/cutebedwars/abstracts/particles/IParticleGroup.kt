package io.github.gdrfgdrf.cutebedwars.abstracts.particles

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IParticleStatuses
import org.bukkit.World

interface IParticleGroup {
    val name: String
    val parent: IManagedParticle

    fun status(): IParticleStatuses

    fun add(particleInfo: IParticleInfo)
    fun add(x: Double, y: Double, z: Double, count: Int = 1)
    fun remove(particleInfo: IParticleInfo)
    fun removeAt(index: Int)
    fun removeAt(x: Double, y: Double, z: Double)

    fun spawn(world: World, frequency: Long)
    fun spawn(world: World): List<Any>

    fun dismiss()
}