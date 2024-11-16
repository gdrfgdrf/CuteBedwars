package io.github.gdrfgdrf.cutebedwars.abstracts.particles

import org.bukkit.Particle

interface IManagedParticle {
    val particle: Particle

    operator fun get(name: String): IParticleGroup?
    fun create(name: String): IParticleGroup
    fun remove(name: String)

    fun dismissAll()
}